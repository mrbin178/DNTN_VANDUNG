package satthepvandung.web.controller;


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

import satthepvandung.dal.dao.LoginAdminDAO;
import satthepvandung.dal.dao.UserRuleDAO;
import satthepvandung.dal.dao.impl.LoginAdminDAOImpl;
import satthepvandung.dal.dao.impl.UserRuleDAOImpl;
import satthepvandung.dal.table.LoginAdmin;
import satthepvandung.dal.table.UserRule;
import satthepvandung.dal.util.UtilitiesDAL;
import satthepvandung.model.LocaleType;
import satthepvandung.model.UserInfo;
//import satthepvandung.service.AuthenticationService;
//import satthepvandung.service.UserRuleService;
//import satthepvandung.service.impl.AuthenticationServiceImpl;
//import satthepvandung.service.impl.UserRuleServiceImpl;
import satthepvandung.web.common.LocaleCustomize;
import satthepvandung.web.common.SessionConst;
import satthepvandung.web.common.WebSession;
import satthepvandung.web.validator.LoginValidator;
import satthepvandung.web.validator.model.LoginView;

@Controller
@SessionAttributes({SessionConst.WEB_SESSION})
public class AuthenticationController {
	
    private LoginAdminDAO loginAdminDAO;
    private UserRuleDAO userRuleDao;
	
    UtilitiesDAL Util = new UtilitiesDAL();
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
	private ApplicationContext appContext;
    
    public AuthenticationController(){
    	this.loginAdminDAO = new LoginAdminDAOImpl();
    	this.userRuleDao = new UserRuleDAOImpl();
    }

    private String getMessage(String code){
		return messageSource.getMessage(code, null, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
	}
    
	@RequestMapping(value="/login.bv", method=RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("login");
//		mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
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
			/*String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			if(!verify){
				ModelAndView mv = new ModelAndView("login");
				message = getMessage("error.captcha");
				mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
				mv.addObject("errorMessageCaptcha", message);
				return mv;
			}*/
			/** End captcha version 2**/
			
			LoginValidator validator = new LoginValidator();
			validator.validate(loginView, bindingResult);
			if (!bindingResult.hasErrors()){
				if(password != null && !password.equals("")){
					password = Util.md5_md5(password);
				}
				LoginAdmin user = loginAdminDAO.getUser(username, password);
				if (user == null){
					ModelAndView mv = new ModelAndView("login");
					message = getMessage("error.username.password");
//					mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
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
					listRule = userRuleDao.getListUserRuleTheoUser(user.getUsername());
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
//			mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
			mv.addObject("errorMessage", e.getMessage());
			return mv;
		}
	}
}

