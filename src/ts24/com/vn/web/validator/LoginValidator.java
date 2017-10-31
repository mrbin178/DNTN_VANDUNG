package ts24.com.vn.web.validator;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ts24.com.vn.model.AuthenticationModel;
import ts24.com.vn.model.LocaleType;
import ts24.com.vn.service.UserRuleService;
import ts24.com.vn.service.impl.UserRuleServiceImpl;
import ts24.com.vn.web.common.LocaleCustomize;
import ts24.com.vn.web.common.ValueConst;
import ts24.com.vn.web.validator.model.LoginView;

import com.mysql.jdbc.StringUtils;

public class LoginValidator implements Validator {
	private Logger logger = Logger.getLogger(LoginValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(LoginView.class);
	}

   public static ApplicationContext appContext;
   public static MessageSource messageSource;
   
	public static String getMessage(String code, Object[] args){
		return appContext.getMessage(code, args, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
	}

	public String getMessage(String code) {
		return messageSource.getMessage(code, null, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
	}
		
	@Override
	public void validate(Object target, Errors errors) {
		LoginView loginView = (LoginView) target;
		try {
			if(StringUtils.isEmptyOrWhitespaceOnly(loginView.getUsername())){
				errors.rejectValue("username", "error.login.fieldRequired", new String[] {ValueConst.LABLE_USERNAME}, "");
			}
			if(StringUtils.isEmptyOrWhitespaceOnly(loginView.getPassword())){
				errors.rejectValue("password", "error.login.fieldRequired", new String[] {ValueConst.LABLE_PASSWORD}, "");
//				message = getMessage("undo.message.unsupport",new Object[] {getMessage(ValueConst.LABLE_USERNAME)});
			}
		} catch (Exception ex) {
			logger.error("Cannot validate login", ex);
		}

	}
}
