/*
 * @project: working
 *@author:   ngangv
 *May 7, 2010 10:03:44 AM
 */
package ts24.com.vn.web.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ts24.com.vn.model.objSearchInvoice;
import ts24.com.vn.web.common.ValueConst;
import ts24.com.vn.web.util.Utilities;
import ts24.com.vn.web.validator.model.UserInfoView;

import com.mysql.jdbc.StringUtils;


public class ValidatorReport implements Validator{

	private Logger logger = Logger.getLogger(ValidatorReport.class);
	Utilities Util = new Utilities();
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(UserInfoView.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		objSearchInvoice userView = (objSearchInvoice) target;
		try {
			if(StringUtils.isEmptyOrWhitespaceOnly(userView.getTuNgay())){
				errors.rejectValue("tuNgay", "error.login.fieldRequired", new String[] {ValueConst.LABLE_TUNGAY}, "");
			}
			if(StringUtils.isEmptyOrWhitespaceOnly(userView.getDenNgay())){
				errors.rejectValue("denNgay", "error.login.fieldRequired", new String[] {ValueConst.LABLE_DENNGAY}, "");
			}
		
			if(userView.getTuNgay().contains("/") && userView.getTuNgay().contains("-")){
				errors.rejectValue("tuNgay", "error.format.tuNgay", new String[] {ValueConst.LABLE_TUNGAY}, "");
			}
			if(userView.getDenNgay().contains("/") && userView.getDenNgay().contains("-")){
				errors.rejectValue("tuNgay", "error.format.tuNgay", new String[] {ValueConst.LABLE_DENNGAY}, "");
			}
			
			if(!Util.isValidDateddmmyyyy(userView.getTuNgay())){
				errors.rejectValue("tuNgay", "error.format.tuNgay", new String[] {ValueConst.LABLE_TUNGAY}, "");
			}
			if(!Util.isValidDateddmmyyyy(userView.getDenNgay())){
				errors.rejectValue("denNgay", "error.format.denNgay", new String[] {ValueConst.LABLE_DENNGAY}, "");
			}
			if((Util.formatNgaySo_ddmmyyyy(userView.getDenNgay())) < (Util.formatNgaySo_ddmmyyyy(userView.getTuNgay()))){
				errors.rejectValue("denNgay", "error.denNgay.greater.tuNgay", new String[] {ValueConst.LABLE_DENNGAY}, "");
			}
			
			if(Util.calNumberDay(userView.getTuNgay(),userView.getDenNgay()) > 93){
				errors.rejectValue("denNgay", "error.month.tracuu", new String[] {ValueConst.LABLE_DENNGAY}, "");
			}
			
		} catch (Exception ex) {
			logger.error("Cannot validate Report", ex);
		}

	}

	
}
