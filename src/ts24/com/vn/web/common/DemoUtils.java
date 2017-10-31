package ts24.com.vn.web.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import ts24.com.vn.model.UserRole;

public class DemoUtils {
	public static Map<String, String> convertArrayToMap(String[] values) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (String value : values) {
			map.put(value, value);
		}
		return map;
	}
	
	public static Date convertString2Date(String dateString){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
		}
		return date;
	}
	
	public static boolean isIntNumber(String num){
		try{
			Integer.parseInt(num);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	public static Set<UserRole> convertToUserRoles(Collection<GrantedAuthority> authorities){
		Set<UserRole> roles = new HashSet<UserRole>();
		for (GrantedAuthority grantedAuthority : authorities) {
			UserRole role = UserRole.fromString(grantedAuthority.getAuthority());
			roles.add(role);
		}
		return roles;
	}
	
//	public static void updateWebSessionIfNeeded(PricePlan pricePlan, WebSession webSession, int toolPlanRecordCount, Project project){
//			PricePlanInfo pricePlanInfo = new PricePlanInfo();
//			pricePlanInfo.setPricePlanCode(pricePlan.getPricePlanCode());
//			pricePlanInfo.setPricePlanName(pricePlan.getPricePlanName());
//			pricePlanInfo.setPricePlanStatus(pricePlan.getPricePlanStatus());
//			pricePlanInfo.setCheckOutUser(pricePlan.getCheckOutUser());
//			pricePlanInfo.setStatus(pricePlan.getStatus());
//			pricePlanInfo.setPricePlanEmptyStatus(toolPlanRecordCount);
//			webSession.setCurrentPricePlan(pricePlanInfo);
//			// handle current project
//			ProjectInfo projectInfo = null;
//			Project currentProject = null;
//			if (project != null){
//				currentProject = project;
//			}else{
//				currentProject = pricePlan.getProject();
//			}
//			projectInfo = new ProjectInfo(currentProject.getProjectId(), currentProject.getProjectName(), currentProject.getPeat(),currentProject.getStatus());
//			webSession.setCurrentProject(projectInfo);
//	}
}
