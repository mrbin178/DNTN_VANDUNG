package ts24.com.vn.dal.dao;

import java.util.List;

import ts24.com.vn.dal.model.HoaDon;
import ts24.com.vn.dal.model.nonentity.BaoCaoBanHangView;
import ts24.com.vn.dal.model.nonentity.HoaDonView;
import ts24.com.vn.dal.model.nonentity.ReportBangKeBanRaView;
import ts24.com.vn.dal.model.nonentity.ReportTBaoPhatHanhView;
import ts24.com.vn.dal.model.nonentity.ReportTongHopView;
import ts24.com.vn.dal.model.nonentity.ReportUseOfInvoicesView;
import ts24.com.vn.model.InvoiceForm;
import ts24.com.vn.model.objSearchInvoice;
import ts24.com.vn.model.objSearchPhatHanh;

public interface HoaDonDao {
	HoaDon searchHoaDon(InvoiceForm form) throws Exception;
	HoaDon getObjByID(String id) throws Exception;
	List<HoaDonView> getAllHoaDonNew(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize) throws Exception;
	long getRecordCountInvoice(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role) throws Exception;
	public List<BaoCaoBanHangView> getAllHoaDonBaoCaoBanHang(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize) throws Exception;
	public long getRecordCountInvoiceBCBH(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role) throws Exception;
	List<ReportBangKeBanRaView> getReportBangKeBanRa(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize) throws Exception;
	List<ReportBangKeBanRaView> getReportBangKeBanRaNotJoin(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize) throws Exception;
	long getRecordCountBangKeBanRa(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role) throws Exception;
	String getSQLDateTime() throws Exception;
	List<ReportUseOfInvoicesView> getReportUseOfInvoices(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize) throws Exception;
	public List<BaoCaoBanHangView> getAllHoaDonBaoCaoBanHang_new( objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize) throws Exception;
	public List<BaoCaoBanHangView> getAllHoaDonBaoCaoBanHang_new_TH(objSearchInvoice objSearch, String maCQLimit, String mstLimit , int role, String sortName, int startIndex, int pageSize) throws Exception;
	List<ReportTongHopView> getReportTongHop(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role) throws Exception;
	List<ReportTBaoPhatHanhView> getReportTBaoPhatHanh( objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role) throws Exception;
	public List<ReportTongHopView> getReportTongHopPhatHanh(List<objSearchPhatHanh> listPH, objSearchInvoice objSearch,	String maCQLimit, String mstLimit, int role) throws Exception;
	public List<ReportUseOfInvoicesView> getReportUseOfInvoicesPhatHanhTongHop(List<objSearchPhatHanh> listPH, objSearchInvoice objSearch,String maCQLimit, String mstLimit, int role, String sortName,int startIndex, int pageSize) throws Exception;
	public List<objSearchPhatHanh> getDanhSachPhatHanh(String arrID) throws Exception;
	List<ReportTBaoPhatHanhView> reportTBaoPhatHanhSearch( objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize)throws Exception;
	List<ReportTBaoPhatHanhView> getDanhSachPhatHanhSelected(String arrID, String sortName) throws Exception;
	List<HoaDonView> getHoaDonByMaNhanHD(String maNhanHD, String tuNgay, String denNgay, String sortName, int startIndex, int pageSize)	throws Exception;
	long getCountAllHoaDon(String idHDLimit, String tuNgay, String denNgay)
			throws Exception;


}
