package satthepvandung.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import satthepvandung.web.common.ValueConst;
import satthepvandung.web.util.Utilities;
import satthepvandung.web.validator.model.UserInfoView;
import satthepvandung.web.validator.model.UserInfoViewID;

public class ValidatorUserUpdateInfoID implements Validator{

	private Logger logger = Logger.getLogger(ValidatorUserUpdateInfoID.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(UserInfoView.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserInfoViewID userView = (UserInfoViewID) target;
		try {
			if(StringUtils.isEmpty(userView.getUsername())){
				errors.rejectValue("username", "error.login.fieldRequired", new String[] {ValueConst.LABLE_USERNAME}, "");
			}else if(!Utilities.checkEmail(userView.getUsername())){
				errors.rejectValue("username", "error.format.username", new String[] {ValueConst.LABLE_USERNAME}, "");
			}
			if(!StringUtils.isEmpty(userView.getCheckchangepassword()) && userView.getCheckchangepassword().equals("on")){
				if(StringUtils.isEmpty(userView.getOldpassword())){
					errors.rejectValue("oldpassword", "error.login.fieldRequired", new String[] {ValueConst.LABLE_OLDPASSWORD}, "");
				}
				if(StringUtils.isEmpty(userView.getPassword())){
					errors.rejectValue("password", "error.login.fieldRequired", new String[] {ValueConst.LABLE_PASSWORD}, "");
				}else if(userView.getOldpassword().equals(userView.getPassword())){
					errors.rejectValue("password", "error.oldpassword.match.newpassword", null, "");
				}
				if(StringUtils.isEmpty(userView.getConfirmation())){
					errors.rejectValue("confirmation", "error.login.fieldRequired", new String[] {ValueConst.LABLE_CONFIRM_PASSWORD}, "");
				}else if(!userView.getPassword().equals(userView.getConfirmation())){
					errors.rejectValue("confirmation", "error.confirm.not.match", new String[] {ValueConst.LABLE_CONFIRM_PASSWORD}, "");
				}
			}
			
			if(StringUtils.isEmpty(userView.getFullname())){
				errors.rejectValue("fullname", "error.login.fieldRequired", new String[] {ValueConst.LABLE_FULLNAME_CUSTOMER}, "");
			}
			if(StringUtils.isEmpty(userView.getTelnumber())){
				errors.rejectValue("telnumber", "error.login.fieldRequired", new String[] {ValueConst.LABLE_DIENTHOAI}, "");
			}
			
			if(StringUtils.isNotEmpty(userView.getEmail())){
				if(!Utilities.checkEmail(userView.getEmail())){
					errors.rejectValue("email", "error.format.email", new String[] {ValueConst.LABLE_EMAIL}, "");
				}
			}
			
		} catch (Exception ex) {
			logger.error("Cannot validate login", ex);
		}

	}

	
}
