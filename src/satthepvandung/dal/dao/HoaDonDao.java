package satthepvandung.dal.dao;

import java.util.List;

import satthepvandung.dal.table.HoaDon;
import satthepvandung.dal.view.BaoCaoBanHangView;
import satthepvandung.dal.view.HoaDonView;
import satthepvandung.dal.view.ReportBangKeBanRaView;
import satthepvandung.dal.view.ReportTBaoPhatHanhView;
import satthepvandung.dal.view.ReportTongHopView;
import satthepvandung.dal.view.ReportUseOfInvoicesView;
import satthepvandung.model.InvoiceForm;
import satthepvandung.model.objSearchInvoice;
import satthepvandung.model.objSearchPhatHanh;

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
