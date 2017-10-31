package ts24.com.vn.dal.dao;

import java.util.List;

import ts24.com.vn.dal.model.Phieuthu;
import ts24.com.vn.dal.model.nonentity.PhieuthuView;
import ts24.com.vn.model.ReceiptForm;

public interface PhieuthuDao {
	
	Phieuthu searchReceipt(ReceiptForm form) throws Exception;
	Phieuthu getObjByID(String id) throws Exception;
	List<PhieuthuView> getPhieuThuByMaTraCuu(String maTraCuu, String tuNgay,
			String denNgay, String sortName, int startIndex, int pageSize)
			throws Exception;
	long getCountAllPhieuThu(String idHDLimit, String tuNgay, String denNgay)
			throws Exception;

}
