package ts24.com.vn.service;

import java.util.HashMap;
import java.util.Map;

import ts24.com.vn.model.ReportConst;
import ts24.com.vn.service.impl.BangKeBanRaReportServiceImpl;
import ts24.com.vn.service.impl.BaoCaoBanHangReportServiceImpl;
import ts24.com.vn.service.impl.ThongBaoPhatHanhReportServiceImpl;
import ts24.com.vn.service.impl.TongHopBangKeHoaDonBanRaReportServiceImpl;
import ts24.com.vn.service.impl.UseOfInvoicesReportServiceImpl;

public class ReportServiceFactory {

	@SuppressWarnings("rawtypes")
	private Map<String, Class<? extends IReportService>> maps = new HashMap<String, Class<? extends IReportService>>();
	private static ReportServiceFactory instance = new ReportServiceFactory(); 
	
	public static ReportServiceFactory getInstance(){
		return instance;
	}
	
	public ReportServiceFactory(){
		initialize();
	}
	public void initialize(){
		maps.put(ReportConst.REPORT_BANG_KE_BAN_RA, BangKeBanRaReportServiceImpl.class);
		maps.put(ReportConst.REPORT_BAO_CAO_BAN_HANG, BaoCaoBanHangReportServiceImpl.class);
		maps.put(ReportConst.REPORT_USE_OF_INVOICES, UseOfInvoicesReportServiceImpl.class);
		maps.put(ReportConst.REPORT_TONGHOP_BANGKE_HOADON_BANRA, TongHopBangKeHoaDonBanRaReportServiceImpl.class);
		maps.put(ReportConst.REPORT_THONGBAO_PHATHANH, ThongBaoPhatHanhReportServiceImpl.class);
	}
	
	@SuppressWarnings("rawtypes")
	public IReportService getReportService(String type) throws Exception{
		Class clazz = maps.get(type);
		IReportService service =  (IReportService) clazz.newInstance();
		return service;
	}
	
}
