package satthepvandung.web.validator;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mysql.jdbc.StringUtils;

import satthepvandung.model.LocaleType;
import satthepvandung.web.common.LocaleCustomize;
import satthepvandung.web.common.ValueConst;
import satthepvandung.web.validator.model.LoginView;

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
