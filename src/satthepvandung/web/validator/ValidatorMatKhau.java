package satthepvandung.web.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import satthepvandung.web.common.ValueConst;
import satthepvandung.web.validator.model.MatKhauInfoView;
import satthepvandung.web.validator.model.UserInfoView;

import com.mysql.jdbc.StringUtils;


public class ValidatorMatKhau implements Validator{

	private Logger logger = Logger.getLogger(ValidatorMatKhau.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(UserInfoView.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MatKhauInfoView userView = (MatKhauInfoView) target;
		try {
			if(StringUtils.isEmptyOrWhitespaceOnly(userView.getMatKhauCu())){
				errors.rejectValue("matKhauCu", "error.login.fieldRequired", new String[] {ValueConst.LABLE_PASSWORD_CU}, "");
			}
			if(StringUtils.isEmptyOrWhitespaceOnly(userView.getMatKhauMoi())){
				errors.rejectValue("matKhauMoi", "error.login.fieldRequired", new String[] {ValueConst.LABLE_PASSWORD_MOI}, "");
//				message = getMessage("undo.message.unsupport",new Object[] {getMessage(ValueConst.LABLE_USERNAME)});
			}
			if(StringUtils.isEmptyOrWhitespaceOnly(userView.getMatKhauMoiRe())){
				errors.rejectValue("matKhauMoiRe", "error.login.fieldRequired", new String[] {ValueConst.LABLE_PASSWORD_MOI_RE}, "");
			}
			
			
		} catch (Exception ex) {
			logger.error("Cannot validate login", ex);
		}

	}

	
}
