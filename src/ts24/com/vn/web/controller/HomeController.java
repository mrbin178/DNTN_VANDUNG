package ts24.com.vn.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ts24.com.vn.web.common.BreadCrumbConst;
import ts24.com.vn.web.common.SessionConst;
import ts24.com.vn.web.common.WebSession;
import ts24.com.vn.web.util.Path;
import dummiesmind.breadcrumb.springmvc.annotations.Link;

@Controller
@SessionAttributes({SessionConst.WEB_SESSION})
public class HomeController {
	@Link(label=BreadCrumbConst.HOME, family=BreadCrumbConst.MAIN_FLOW, parent="")
	@RequestMapping(value = "/home.bv", method = RequestMethod.GET)
	public ModelAndView homeView(HttpSession session){
		try {
			ModelAndView view = new ModelAndView("home");
			WebSession webSession = new WebSession();
			webSession = (WebSession) session.getAttribute(SessionConst.WEB_SESSION);
			if(webSession != null && webSession.getCurrentUser() != null 
					&& StringUtils.isNotEmpty(webSession.getCurrentUser().getUsername())){
				return view;
			}else{
				ModelAndView mv = new ModelAndView("login");
				mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
				return mv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("login");
		}
	}
	
	@Link(label=BreadCrumbConst.USE_GUIDE, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
	@RequestMapping(value = "/useGuide.bv", method = RequestMethod.GET)
	public ModelAndView useGuide(HttpSession session){
		try {
			ModelAndView view = new ModelAndView("useGuide");
			return view;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("login");
		}
	}
}
