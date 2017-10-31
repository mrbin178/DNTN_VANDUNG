package ts24.com.vn.web.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ts24.com.vn.dal.model.LoginAdmin;
import ts24.com.vn.dal.model.UserRule;
import ts24.com.vn.dal.util.UtilitiesDAL;
import ts24.com.vn.model.LocaleType;
import ts24.com.vn.model.UserInfo;
import ts24.com.vn.service.AuthenticationService;
import ts24.com.vn.service.UserRuleService;
import ts24.com.vn.service.impl.AuthenticationServiceImpl;
import ts24.com.vn.service.impl.UserRuleServiceImpl;
import ts24.com.vn.web.common.LocaleCustomize;
import ts24.com.vn.web.common.SessionConst;
import ts24.com.vn.web.common.WebSession;
import ts24.com.vn.web.util.Path;
import ts24.com.vn.web.util.VerifyRecaptcha;
import ts24.com.vn.web.validator.LoginValidator;
import ts24.com.vn.web.validator.model.LoginView;

@Controller
@SessionAttributes({SessionConst.WEB_SESSION})
public class AuthenticationController {
	
    private AuthenticationService authenticationService;
    private UserRuleService userRuleService;
	
    UtilitiesDAL Util = new UtilitiesDAL();
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
	private ApplicationContext appContext;
    
    public AuthenticationController(){
    	this.authenticationService = new AuthenticationServiceImpl();
    	this.userRuleService = new UserRuleServiceImpl();
    }

    private String getMessage(String code){
		return messageSource.getMessage(code, null, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
	}
    
	@RequestMapping(value="/login.bv", method=RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
		return mv;
	}
	@RequestMapping(value="/logoff.bv", method=RequestMethod.GET)
	public String logoff(){
		return "logoff";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/login.bv", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("loginView") @Validated LoginView loginView, BindingResult bindingResult,
			 String username, String password, HttpSession session, HttpServletRequest req, HttpServletResponse res){
		String message = "";
		try {
			WebSession webSession = (WebSession) session.getAttribute(SessionConst.WEB_SESSION);
			if(webSession != null){
				req.getSession().removeAttribute(SessionConst.WEB_SESSION);
				session.removeAttribute(SessionConst.WEB_SESSION);
				session.invalidate();
			}
			/** Begin captcha version 2**/
			String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			if(!verify){
				ModelAndView mv = new ModelAndView("login");
				message = getMessage("error.captcha");
				mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
				mv.addObject("errorMessageCaptcha", message);
				return mv;
			}
			/** End captcha version 2**/
			
			LoginValidator validator = new LoginValidator();
			validator.validate(loginView, bindingResult);
			if (!bindingResult.hasErrors()){
				if(password != null && !password.equals("")){
					password = Util.md5_md5(password);
				}
				LoginAdmin user = authenticationService.login(username, password);
				if (user == null){
					ModelAndView mv = new ModelAndView("login");
					message = getMessage("error.username.password");
					mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
					mv.addObject("errorMessage", message);
					return mv;
				}else{
					if(webSession == null){
						webSession = new WebSession();
					}
					List<UserRule> listRule = null;
					UserInfo objNew = new UserInfo();
					BeanUtils.copyProperties( user, objNew);
					webSession.setCurrentUser(objNew);
					webSession.setUserInfoViewID(null);
					listRule = userRuleService.getListUserRuleTheoUser(user.getUsername());
					webSession.setListRule(listRule);
					session = req.getSession(true);
					session.setAttribute(SessionConst.WEB_SESSION, webSession);
					return new ModelAndView("redirect:home.bv");
				}
			}else{
				return login();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
			mv.addObject("errorMessage", e.getMessage());
			return mv;
		}
	}
}

