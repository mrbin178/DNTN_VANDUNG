package ts24.com.vn.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mysql.jdbc.StringUtils;

import ts24.com.vn.model.UserInfo;
import ts24.com.vn.web.common.BreadCrumbConst;
import ts24.com.vn.web.common.SessionConst;
import ts24.com.vn.web.common.WebSession;
import dummiesmind.breadcrumb.springmvc.breadcrumb.BreadCrumbLink;

@Controller
@RequestMapping(value = "/breadcrumb")
@SessionAttributes({ SessionConst.WEB_SESSION })
public class BreadCrumbController {
	private Logger logger = Logger.getLogger(BreadCrumbController.class.getName());
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/refreshBreadCrumb.bv", method = RequestMethod.POST)
	public String getBreadCrumb(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) {		
		try{
			logger.debug("------------------------------ breadcrumb QuangDo -------------------------------------");
			HttpSession session = request.getSession();
			if(webSession == null || webSession.equals("")){
				request.getSession().setAttribute("mainFlowBreadCrumb", null);
		   	}
		   	UserInfo objUser = webSession.getCurrentUser();
		   	if(objUser == null || StringUtils.isNullOrEmpty(objUser.getUsername())){
		   		request.getSession().setAttribute("mainFlowBreadCrumb", null);
		   	}
		   	
			Map<String, LinkedHashMap<String,BreadCrumbLink>> breadCrumbs = (HashMap<String, LinkedHashMap<String,BreadCrumbLink>>)session.getAttribute("breadCrumb");
			if (breadCrumbs != null){
//				LinkedList<BreadCrumbLink> breadCrumbLinks = new LinkedList<BreadCrumbLink>();
				LinkedHashMap<String,BreadCrumbLink> breadCrumbLink = breadCrumbs.get(BreadCrumbConst.MAIN_FLOW);
				if (breadCrumbLink.size() > 0){
					request.getSession().setAttribute("mainFlowBreadCrumb", filterBreadCrumb(breadCrumbLink.values()));
				}
			}
		}catch(Exception e){
			System.out.println("Cannot refresh bread crumb");
			e.printStackTrace();
		}
		return "breadcrumb";
	}
	
	private Collection<BreadCrumbLink> filterBreadCrumb(Collection<BreadCrumbLink> breadcrumbLinks){
		List<BreadCrumbLink> breadcrumbs = new ArrayList<BreadCrumbLink>();
		for (BreadCrumbLink breadCrumbLink : breadcrumbLinks) {
			String url = breadCrumbLink.getUrl();
			if (url.contains("/searchInvoice.bv")){
				url = url.replace("/searchInvoice.bv", "/home.bv");
				breadCrumbLink.setUrl(url);
			}
			breadcrumbs.add(breadCrumbLink);
			if (breadCrumbLink.isCurrentPage()){
				break;
			}
		}
		return breadcrumbs;
	}
}
