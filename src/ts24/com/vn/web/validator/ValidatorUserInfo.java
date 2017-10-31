/*
 * @project: working
 *@author:   ngangv
 *May 7, 2010 10:03:44 AM
 */
package ts24.com.vn.web.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ts24.com.vn.web.common.ValueConst;
import ts24.com.vn.web.validator.model.UserInfoView;

import com.mysql.jdbc.StringUtils;


public class ValidatorUserInfo implements Validator{

	private Logger logger = Logger.getLogger(ValidatorUserInfo.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(UserInfoView.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserInfoView userView = (UserInfoView) target;
		try {
			if(StringUtils.isEmptyOrWhitespaceOnly(userView.getUsername())){
				errors.rejectValue("username", "error.login.fieldRequired", new String[] {ValueConst.LABLE_USERNAME}, "");
			}
			if(StringUtils.isEmptyOrWhitespaceOnly(userView.getMatKhau())){
				errors.rejectValue("matKhau", "error.login.fieldRequired", new String[] {ValueConst.LABLE_PASSWORD}, "");
//				message = getMessage("undo.message.unsupport",new Object[] {getMessage(ValueConst.LABLE_USERNAME)});
			}
			if(StringUtils.isEmptyOrWhitespaceOnly(userView.getFullName())){
				errors.rejectValue("fullName", "error.login.fieldRequired", new String[] {ValueConst.LABLE_FULLNAME}, "");
			}
			if(StringUtils.isEmptyOrWhitespaceOnly(userView.getEmail())){
				errors.rejectValue("email", "error.login.fieldRequired", new String[] {ValueConst.LABLE_EMAIL}, "");
//				message = getMessage("undo.message.unsupport",new Object[] {getMessage(ValueConst.LABLE_USERNAME)});
			}
			if(StringUtils.isEmptyOrWhitespaceOnly(userView.getMatinh())){
				errors.rejectValue("matinh", "error.login.fieldRequired", new String[] {ValueConst.LABLE_MATINH}, "");
			}
			
		} catch (Exception ex) {
			logger.error("Cannot validate login", ex);
		}

	}

	
}
