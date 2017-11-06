package satthepvandung.web.springmvc.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import dummiesmind.breadcrumb.springmvc.annotations.Link;
import dummiesmind.breadcrumb.springmvc.breadcrumb.BreadCrumbLink;
import satthepvandung.web.common.BreadCrumbConst;


public class BreadCrumbInterceptor extends HandlerInterceptorAdapter {

	private static final String BREAD_CRUMB_LINKS = "breadCrumb";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	
		Annotation[] declaredAnnotations = getDeclaredAnnotationsForHandler(handler);
		HttpSession session = request.getSession();
		emptyCurrentBreadCrumb(session);
		for (Annotation annotation : declaredAnnotations) {
			if(annotation.annotationType().equals(Link.class)){
				processAnnotation(request, session, annotation);
			}
		}
		
		return true;
	}


	private void emptyCurrentBreadCrumb(HttpSession session) {
		session.setAttribute("currentBreadCrumb", new LinkedList<BreadCrumbLink>());
	}

	private void processAnnotation(HttpServletRequest request, HttpSession session, Annotation annotation) {
		Link link = (Link) annotation;
		String family = link.family();
		String label = link.label();
		
		
		Map<String, LinkedHashMap<String, BreadCrumbLink>> breadCrumb = getBreadCrumbLinksFromSession(session);
		
		if(breadCrumb == null){
			breadCrumb = new HashMap<String, LinkedHashMap<String,BreadCrumbLink>>();
			session.setAttribute(BREAD_CRUMB_LINKS, breadCrumb);
		}

		LinkedHashMap<String, BreadCrumbLink> familyMap = breadCrumb.get(family);
		
		
		if(familyMap == null){
			familyMap = new LinkedHashMap<String, BreadCrumbLink>();
			breadCrumb.put(family, familyMap);
		}
		
		if (family.equals(BreadCrumbConst.MAIN_FLOW)){
//			specficProcessFor7J(familyMap, link, request);
		}
		
		BreadCrumbLink breadCrumbLink = null;
		breadCrumbLink = getBreadCrumbLink(request, link, familyMap);
		LinkedList<BreadCrumbLink> currentBreadCrumb = new LinkedList<BreadCrumbLink>();
		generateBreadCrumbsRecursively(breadCrumbLink,currentBreadCrumb);
		session.setAttribute("currentBreadCrumb", currentBreadCrumb);
	}


	private BreadCrumbLink getBreadCrumbLink(HttpServletRequest request, Link link,
			LinkedHashMap<String, BreadCrumbLink> familyMap) {
		BreadCrumbLink breadCrumbLink;
		BreadCrumbLink breadCrumbObject = familyMap.get(link.label());
		resetBreadCrumbs(familyMap);
		if(breadCrumbObject != null){
			breadCrumbObject.setCurrentPage(true);
			breadCrumbLink = breadCrumbObject;
		}else{
			breadCrumbLink = new BreadCrumbLink(link.family(), link.label(), true, link.parent());
			StringBuffer fullURL = request.getRequestURL();
			if (!StringUtils.isEmpty(request.getQueryString())){
				fullURL = fullURL.append("?").append(request.getQueryString());
			}
			breadCrumbLink.setUrl(fullURL.toString());
			createRelationships(familyMap, breadCrumbLink);
			familyMap.put(link.label(), breadCrumbLink);
		}
		return breadCrumbLink;
	}


	@SuppressWarnings("unchecked")
	private Map<String, LinkedHashMap<String, BreadCrumbLink>> getBreadCrumbLinksFromSession(HttpSession session) {
		Map<String, LinkedHashMap<String, BreadCrumbLink>> breadCrumb = (Map<String, LinkedHashMap<String, BreadCrumbLink>>)session.getAttribute(BREAD_CRUMB_LINKS);
		return breadCrumb;
	}

	private Annotation[] getDeclaredAnnotationsForHandler(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Method method = handlerMethod.getMethod();
		Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
		return declaredAnnotations;
	}
	
	private void resetBreadCrumbs(LinkedHashMap<String, BreadCrumbLink> familyMap) {
		for(BreadCrumbLink breadCrumbLink : familyMap.values()){
			breadCrumbLink.setCurrentPage(false);
		}
	}

	private void generateBreadCrumbsRecursively(BreadCrumbLink link , LinkedList<BreadCrumbLink> breadCrumbLinks){
		if(link.getPrevious() != null){
			generateBreadCrumbsRecursively(link.getPrevious(), breadCrumbLinks);
		}
		 breadCrumbLinks.add(link);
	}
	
	
	private void createRelationships(LinkedHashMap<String, BreadCrumbLink> familyMap , BreadCrumbLink newLink){
		Collection<BreadCrumbLink> values = familyMap.values();
		for (BreadCrumbLink breadCrumbLink : values) {
			if(breadCrumbLink.getLabel().equalsIgnoreCase(newLink.getParentKey())){
					breadCrumbLink.addNext(newLink);
					newLink.setPrevious(breadCrumbLink);
					newLink.setParent(breadCrumbLink);
			}
		}
		
	}
	
	private void addProjectManagementLink(HttpServletRequest request, Link link,LinkedHashMap<String, BreadCrumbLink> familyMap){
		BreadCrumbLink projectManagementLink = new BreadCrumbLink(BreadCrumbConst.MAIN_FLOW, BreadCrumbConst.PROJECT_MANAGEMENT, false, BreadCrumbConst.HOME);
		projectManagementLink.setUrl(request.getContextPath() + "/project/projectList.htm");
		BreadCrumbLink homeLink = familyMap.get(BreadCrumbConst.HOME);
		projectManagementLink.setParent(homeLink);
		projectManagementLink.setCurrentPage(false);
		homeLink.addNext(projectManagementLink);
		projectManagementLink.setPrevious(homeLink);
		familyMap.put(projectManagementLink.getLabel(), projectManagementLink);
	}
	
	private void addViewProjecLink(HttpServletRequest request, Link link,LinkedHashMap<String, BreadCrumbLink> familyMap){
		BreadCrumbLink viewProjectLink = new BreadCrumbLink(BreadCrumbConst.MAIN_FLOW, BreadCrumbConst.VIEW_PROJECT, false, BreadCrumbConst.PROJECT_MANAGEMENT);
		viewProjectLink.setUrl(request.getContextPath() + "/project/viewProject.htm");
		BreadCrumbLink projectManagementLink = familyMap.get(BreadCrumbConst.PROJECT_MANAGEMENT);
		viewProjectLink.setParent(projectManagementLink);
		projectManagementLink.addNext(viewProjectLink);
		viewProjectLink.setPrevious(projectManagementLink);
		familyMap.put(viewProjectLink.getLabel(), viewProjectLink);
	}
	
//	private void addViewPricePlanLink(HttpServletRequest request, Link link,LinkedHashMap<String, BreadCrumbLink> familyMap){
//		BreadCrumbLink viewPricePlanLink = new BreadCrumbLink(BreadCrumbConst.MAIN_FLOW, BreadCrumbConst.VIEW_PRICE_PLAN, false, BreadCrumbConst.VIEW_PROJECT);
//		viewPricePlanLink.setUrl(request.getContextPath() + "/priceplan/forwardToViewPricePlan.htm");
//		BreadCrumbLink viewProjectLink = familyMap.get(BreadCrumbConst.VIEW_PROJECT);
//		viewPricePlanLink.setParent(viewProjectLink);
//		viewProjectLink.addNext(viewPricePlanLink);
//		viewPricePlanLink.setPrevious(viewProjectLink);
//		familyMap.put(viewPricePlanLink.getLabel(), viewPricePlanLink);
//	}
//
//	private void specficProcessFor7J(LinkedHashMap<String, BreadCrumbLink> familyMap, Link link, HttpServletRequest request){
//		if (link.label().equals(BreadCrumbConst.VIEW_PROJECT)){
//			if (!familyMap.containsKey(BreadCrumbConst.PROJECT_MANAGEMENT)){
//				addProjectManagementLink(request, link, familyMap);
//			}
//		}else if (link.label().equals(BreadCrumbConst.VIEW_PRICE_PLAN)){
//			if (!familyMap.containsKey(BreadCrumbConst.PROJECT_MANAGEMENT)){
//				addProjectManagementLink(request, link, familyMap);
//			}
//			if (!familyMap.containsKey(BreadCrumbConst.VIEW_PROJECT)){
//				addViewProjecLink(request, link, familyMap);
//			}
//		}else if (link.label().equals(BreadCrumbConst.VIEW_TOOL_PLAN) || link.label().equals(BreadCrumbConst.EDIT_TOOL_PLAN)){
//			if (!familyMap.containsKey(BreadCrumbConst.PROJECT_MANAGEMENT)){
//				addProjectManagementLink(request, link, familyMap);
//			}
//			if (!familyMap.containsKey(BreadCrumbConst.VIEW_PROJECT)){
//				addViewProjecLink(request, link, familyMap);
//			}
//			if (!familyMap.containsKey(BreadCrumbConst.VIEW_PRICE_PLAN)){
//				addViewPricePlanLink(request, link, familyMap);
//			}
//		}
//	}

}
