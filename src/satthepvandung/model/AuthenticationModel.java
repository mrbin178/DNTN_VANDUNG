package satthepvandung.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mysql.jdbc.StringUtils;

import satthepvandung.dal.table.UserRule;
import satthepvandung.web.common.LocaleCustomize;
import satthepvandung.web.common.SessionConst;
import satthepvandung.web.common.ValueConst;
import satthepvandung.web.common.WebSession;

public class AuthenticationModel {
   private static AuthenticationModel instance = new AuthenticationModel();
   public static ApplicationContext appContext;
   public static MessageSource messageSource;
   
   public static AuthenticationModel getInstance(){
	   return instance;
   }
   
	public static String getMessage(String code, Object[] args){
		return appContext.getMessage(code, args, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
	}
	
	public static String getMessage(String code){
		return messageSource.getMessage(code, null, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
	}
	
   public String CheckUserRule(String role, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
	   try{
		   	if(webSession == null || webSession.equals("")){
		   		return "login";
		   	}
		   	UserInfo objUser = webSession.getCurrentUser();
		   	if(objUser == null || StringUtils.isNullOrEmpty(objUser.getUsername())){
		   		return "login";
		   	}
			boolean checkRule = false;
			if(objUser != null && objUser.getRole() == ValueConst.ROLE_SUPER_ADMIN){
				return "1";
			}
			//List<UserRule> listRule = userRuleService.getListUserRuleTheoUser(objUser.getUsername());
			List<UserRule> listRule = webSession.getListRule();
			if(listRule != null && listRule.size() > 0){
				for (UserRule rule : listRule) {
					if(rule.getRule().equals(role)){
						checkRule = true ;
						break;
					}			
				}
			}
			if(checkRule){
				return "1";
			}else{
				return "error";
			}
		}catch (Exception e) {
			return "error";
		}
	}
}
