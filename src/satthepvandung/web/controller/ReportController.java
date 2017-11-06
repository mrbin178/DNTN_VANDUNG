//package satthepvandung.web.controller;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.servlet.ModelAndView;
//
//import ts24.com.vn.dal.dao.HoaDonDao;
//import ts24.com.vn.dal.dao.impl.HoaDonDaoImpl;
//import ts24.com.vn.dal.model.HoaDon;
//import ts24.com.vn.dal.model.nonentity.BaoCaoBanHangView;
//import ts24.com.vn.dal.model.nonentity.ReportBangKeBanRaView;
//import ts24.com.vn.dal.model.nonentity.ReportTBaoPhatHanhView;
//import ts24.com.vn.dal.model.nonentity.ReportTongHopView;
//import ts24.com.vn.dal.model.nonentity.ReportUseOfInvoicesView;
//import ts24.com.vn.model.AuthenticationModel;
//import ts24.com.vn.model.LocaleType;
//import ts24.com.vn.model.ReportConst;
//import ts24.com.vn.model.UserInfo;
//import ts24.com.vn.model.objSearchInvoice;
//import ts24.com.vn.model.objSearchPhatHanh;
//import ts24.com.vn.service.CoQuanThueService;
//import ts24.com.vn.service.IReportService;
//import ts24.com.vn.service.ReportServiceFactory;
//import ts24.com.vn.service.impl.CoQuanThueServiceImpl;
//import ts24.com.vn.web.common.BreadCrumbConst;
//import ts24.com.vn.web.common.LocaleCustomize;
//import ts24.com.vn.web.common.SessionConst;
//import ts24.com.vn.web.common.ValueConst;
//import ts24.com.vn.web.common.WebSession;
//import ts24.com.vn.web.model.datatable.JsonResultList;
//import ts24.com.vn.web.model.datatable.ResultEnum;
//import ts24.com.vn.web.util.Commons;
//import ts24.com.vn.web.util.Path;
//import ts24.com.vn.web.util.ReadXMLFileUsingDom;
//import ts24.com.vn.web.util.Utilities;
//import dummiesmind.breadcrumb.springmvc.annotations.Link;
//
//@Controller
//@RequestMapping(value = "/report")
//@SessionAttributes({SessionConst.WEB_SESSION})
//public class ReportController {
//	
//	private static final Logger logger = Logger.getLogger(ReportController.class);
//	private AuthenticationModel authenticationModel;
//	private CoQuanThueService coquanthueService;
//	private HoaDonDao hoaDonDao;
//	
//	static String folderRoot = Path.PATH_DOWNLOAD;
//	ReadXMLFileUsingDom readXML = new ReadXMLFileUsingDom();
//	Commons commons = new Commons();
//	Utilities Util = new Utilities();
//	
//	@Autowired
//	private ApplicationContext appContext;
//	@Autowired
//	private MessageSource messageSource;
//	private String getMessage(String code, Object[] args){
//		return appContext.getMessage(code, args, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
//	}
//	private String getMessage(String code){
//		return messageSource.getMessage(code, null, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
//	}
//	
//	public ReportController(){
//		this.authenticationModel = new AuthenticationModel();
//		this.coquanthueService = new CoQuanThueServiceImpl();
//		this.hoaDonDao = new HoaDonDaoImpl();
//	}
//	
//	@ModelAttribute("report")
//	public HoaDon createInvoiceModel(){
//		HoaDon hd = new HoaDon();
//		return hd;
//	}
//	
//	@SuppressWarnings("static-access")
//	@Link(label=BreadCrumbConst.BAOCAO_BANGKEBANRA_VIEW, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/bangKeBanRa.bv", method = RequestMethod.GET)
//	public ModelAndView bangKeBanRaView(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//		try {
//			String role = ValueConst.LABLE_BANGKE_BANRA;
//			String mav = "";
//			String checkRole = authenticationModel.CheckUserRule(role, webSession);
//			if (checkRole.equals("1")) {
//				Map<String, Object> models  = new HashMap<String, Object>();
//				UserInfo objUser = webSession.getCurrentUser();			
//				int roleOld = objUser.getRole();
//				
//				String sMaTinhCQBH = "",quanCQBH = "";
//				sMaTinhCQBH = String.valueOf(objUser.getMatinh());
//				quanCQBH = String.valueOf(objUser.getMacqt());
//				
//				String curDate="", beginDate="", endDate = "";
//				//curDate= hoaDonDao.getMySqlDateCur();
//				curDate = hoaDonDao.getSQLDateTime();
//				beginDate= Util.formatDateTimeTo01MMyyyy_new(curDate);
//				endDate = Util.formatDateTimeToEndMMyyyy_new(beginDate);
//				
//				if(roleOld == ValueConst.ROLE_SUPER_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else if(roleOld == ValueConst.ROLE_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else{
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListLimitQuan(quanCQBH));
//				}
//				models.put("role", webSession.getCurrentUser().getRole());
//				models.put("tuNgay", beginDate);
//				models.put("denNgay", endDate);
//				ModelAndView mv = new ModelAndView("bangKeBanRa", models);
//				return mv;
//			}else{
//				mav = checkRole;
//				return new ModelAndView(mav);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@SuppressWarnings("static-access")
//	@Link(label=BreadCrumbConst.BAOCAO_BANGKEBANRA_VIEW, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/bangKeBanRa.bv", method = RequestMethod.POST)
//	public @ResponseBody JsonResultList<ReportBangKeBanRaView> bangKeBanRaSearch(@ModelAttribute objSearchInvoice criteria, int jtStartIndex, int jtPageSize, String jtSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		String role = ValueConst.LABLE_BANGKE_BANRA;
//		String checkRole = authenticationModel.CheckUserRule(role, webSession);
//		JsonResultList<ReportBangKeBanRaView> listResponse = new JsonResultList<ReportBangKeBanRaView>();
//		if (checkRole.equals("1")) {
//			logger.info("Loading data for invoice Report BangKeBanRa");
//			try {
//				String tuNgay="",denNgay = ""; 
//				tuNgay = criteria.getTuNgay();
//				denNgay = criteria.getDenNgay();
//								
//				if(StringUtils.isEmpty(tuNgay)){
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.login.fieldRequired", new String[] {ValueConst.LABLE_TUNGAY}));
//					return listResponse;
//				}
//				if(StringUtils.isEmpty(denNgay)){
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.login.fieldRequired", new String[] {ValueConst.LABLE_DENNGAY}));
//					return listResponse;
//				}
//			
//				if(tuNgay.contains("/") && tuNgay.contains("-")){
//					//errors.rejectValue("tuNgay", "error.format.tuNgay", new String[] {ValueConst.LABLE_TUNGAY}, "");
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.format.tuNgay"));
//					return listResponse;
//				}
//				if(denNgay.contains("/") && denNgay.contains("-")){
//					//errors.rejectValue("tuNgay", "error.format.tuNgay", new String[] {ValueConst.LABLE_DENNGAY}, "");
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.format.denNgay"));
//					return listResponse;
//				}
//				
//				if(!Util.isValidDateddmmyyyy(tuNgay)){
//				//	errors.rejectValue("tuNgay", "error.format.tuNgay", new String[] {ValueConst.LABLE_TUNGAY}, "");
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.format.tuNgay"));
//					return listResponse;
//				}
//				if(!Util.isValidDateddmmyyyy(denNgay)){
//					//errors.rejectValue("denNgay", "error.format.denNgay", new String[] {ValueConst.LABLE_DENNGAY}, "");
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.format.denNgay"));
//					return listResponse;
//				}
//				if((Util.formatNgaySo_ddmmyyyy(denNgay)) < (Util.formatNgaySo_ddmmyyyy(tuNgay))){
//				//	errors.rejectValue("denNgay", "error.denNgay.greater.tuNgay", new String[] {ValueConst.LABLE_DENNGAY}, "");
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.denNgay.greater.tuNgay"));
//					return listResponse;
//				}
//				
//				if(Util.calNumberDay(tuNgay,denNgay) > 93){
//					//errors.rejectValue("denNgay", "error.month.tracuu", new String[] {ValueConst.LABLE_DENNGAY}, "");
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.month.tracuu"));
//					return listResponse;
//				}
//				//=================
//				UserInfo objUser = webSession.getCurrentUser();	
////				List<ReportBangKeBanRaView> list = hoaDonDao.getReportBangKeBanRaNotJoin(criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole(), jtSorting, jtStartIndex, jtPageSize);
//				List<ReportBangKeBanRaView> list = hoaDonDao.getReportBangKeBanRa(criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole(), jtSorting, jtStartIndex, jtPageSize);
//				listResponse.setRecords(list);
//				listResponse.setResult(ResultEnum.OK);
////				listResponse.setTotalRecordsCount(hoaDonDao.getRecordCountBangKeBanRa(criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole()));
//				listResponse.setTotalRecordsCount(list.size());
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("Caused by");
//				e.printStackTrace();
//				listResponse.setResult(ResultEnum.ERROR);
//				listResponse.setMessage(e.getMessage());
//			}  
//			return listResponse;
//		}else{
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.rule.permission"));
//			return listResponse;
//		}
//	}
//	
//	@SuppressWarnings({"unchecked" })
//	@RequestMapping(value="/exportToExcelBangKeBanRaSearch.bv", method = RequestMethod.GET)
//	public HttpEntity<byte[]> exportToExcelBangKeBanRaSearch(@ModelAttribute objSearchInvoice criteria, String fieldSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
//		try {
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			IReportService<ReportBangKeBanRaView> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_BANG_KE_BAN_RA);
//			String sNameExcel = "Bangkebanra_" + Util.getDateCurFull();
//			UserInfo objUser = webSession.getCurrentUser();	
////			List<ReportBangKeBanRaView> list = hoaDonDao.getReportBangKeBanRaNotJoin(criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole(), fieldSorting, 0, 0);
//			List<ReportBangKeBanRaView> list = hoaDonDao.getReportBangKeBanRa(criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole(), fieldSorting, 0, 0);
//			workbook = iReport.exportExcel(null, list, criteria.getTuNgay(), criteria.getDenNgay(),fieldSorting);
//			return writeByteToFileExcel(workbook, sNameExcel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	private HttpEntity<byte[]> writeByteToFileExcel(HSSFWorkbook workbook,
//			String sNameExcel) throws IOException {
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		try {
//			workbook.write(bos);
//		} finally {
//			bos.close();
//		}
//		byte[] documentBody = bos.toByteArray();
//		HttpHeaders header = new HttpHeaders();
//		header.setContentType(new MediaType("application", "xls"));
//		header.set("Content-Disposition", "attachment; filename=" + sNameExcel + ".xls");
//		header.setContentLength(documentBody.length);
//		return new HttpEntity<byte[]>(documentBody, header);
//	}
//	
//	// bao cao ban hang
//	@SuppressWarnings("static-access")
//	@Link(label=BreadCrumbConst.BAOCAO_BANHANG_VIEW, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/baoCaoBanHang.bv", method = RequestMethod.GET)
//	public ModelAndView baoCaoBanHangView(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//		try {
//			String role = ValueConst.LABLE_BAOCAO_BANHANG;
//			String mav = "";
//			String checkRole = authenticationModel.CheckUserRule(role, webSession);
//			if (checkRole.equals("1")) {
//				Map<String, Object> models  = new HashMap<String, Object>();
//				UserInfo objUser = webSession.getCurrentUser();			
//				int roleOld = objUser.getRole();
//				// lay gia tri ngay mac dinh=========
//				String curDate="", beginDate="", endDate = "";
//				//curDate= hoaDonDao.getMySqlDateCur();
//				curDate = hoaDonDao.getSQLDateTime();
//				beginDate= Util.formatDateTimeTo01MMyyyy_new(curDate);
//				endDate = Util.formatDateTimeToEndMMyyyy_new(beginDate);
//				//=========			
//				String sMaTinhCQBH = "",quanCQBH = "";
//				sMaTinhCQBH = String.valueOf(objUser.getMatinh());
//				quanCQBH = String.valueOf(objUser.getMacqt());
//				
//				if(roleOld == ValueConst.ROLE_SUPER_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else if(roleOld == ValueConst.ROLE_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else{
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListLimitQuan(quanCQBH));
//				}
//				models.put("role", webSession.getCurrentUser().getRole());
//				models.put("tuNgay", beginDate);
//				models.put("denNgay", endDate);
//				ModelAndView mv = new ModelAndView("baoCaoBanHang", models);
//				return mv;
//			}else{
//				mav = checkRole;
//				return new ModelAndView(mav);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@SuppressWarnings("static-access")
//	@Link(label=BreadCrumbConst.BAOCAO_BANHANG_VIEW, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/baoCaoBanHang.bv", method = RequestMethod.POST)
//	public @ResponseBody JsonResultList<BaoCaoBanHangView> baoCaoBanHangSearch(@ModelAttribute objSearchInvoice obj, int jtStartIndex, int jtPageSize, String jtSorting, BindingResult bindingResult,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		String role = ValueConst.LABLE_BAOCAO_BANHANG;
//		String checkRole = authenticationModel.CheckUserRule(role, webSession);
//		JsonResultList<BaoCaoBanHangView> listResponse = new JsonResultList<BaoCaoBanHangView>();
//		if (checkRole.equals("1")) {
//			logger.info("Loading data for invoice report");
//			try {
//				String tuNgay="",denNgay = ""; 
//				tuNgay = obj.getTuNgay();
//				denNgay = obj.getDenNgay();
//								
//				if(StringUtils.isEmpty(tuNgay)){
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.login.fieldRequired", new String[] {ValueConst.LABLE_TUNGAY}));
//					return listResponse;
//				}
//				if(StringUtils.isEmpty(denNgay)){
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.login.fieldRequired", new String[] {ValueConst.LABLE_DENNGAY}));
//					return listResponse;
//				}
//			
//				if(tuNgay.contains("/") && tuNgay.contains("-")){
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.format.tuNgay"));
//					return listResponse;
//				}
//				if(denNgay.contains("/") && denNgay.contains("-")){
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.format.denNgay"));
//					return listResponse;
//				}
//				
//				if(!Util.isValidDateddmmyyyy(tuNgay)){
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.format.tuNgay"));
//					return listResponse;
//				}
//				if(!Util.isValidDateddmmyyyy(denNgay)){
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.format.denNgay"));
//					return listResponse;
//				}
//				if((Util.formatNgaySo_ddmmyyyy(denNgay)) < (Util.formatNgaySo_ddmmyyyy(tuNgay))){
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.denNgay.greater.tuNgay"));
//					return listResponse;
//				}
//				
//				if(Util.calNumberDay(tuNgay,denNgay) > 93){
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage(getMessage("error.month.tracuu"));
//					return listResponse;
//				}
//				UserInfo objUser = webSession.getCurrentUser();	
//				List<BaoCaoBanHangView> views = hoaDonDao.getAllHoaDonBaoCaoBanHang_new(obj, objUser.getMacqt(),objUser.getLimitMST(), objUser.getRole(), jtSorting, jtStartIndex, jtPageSize);
//				listResponse.setRecords(views);
//				listResponse.setResult(ResultEnum.OK);
//				listResponse.setTotalRecordsCount(views.size());
//					
//			} catch (Exception e) {
//				System.out.println("Caused by");
//				e.printStackTrace();
//				listResponse.setResult(ResultEnum.ERROR);
//				listResponse.setMessage(e.getMessage());
//			}  
//			return listResponse;
//		}else{
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.rule.permission"));
//			return listResponse;
//		}
//	}
//	
//	@SuppressWarnings({"unchecked" })
//	@RequestMapping(value="/exportToExcelBaoCaoBanHangSearch.bv", method = RequestMethod.GET)
//	public  HttpEntity<byte[]> exportToExcelBaoCaoBanHangSearch(@ModelAttribute objSearchInvoice criteria, String fieldSorting,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
//		try {
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			IReportService<BaoCaoBanHangView> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_BAO_CAO_BAN_HANG);
//			String sNameExcel = "BaoCaoBanHang_" + Util.getDateCurFull();
//			UserInfo objUser = webSession.getCurrentUser();	
//			String tuNgay="",denNgay = ""; 
//			tuNgay = criteria.getTuNgay();
//			denNgay = criteria.getDenNgay();
//			/*tuNgay = criteria.getTuNgay();
//			denNgay = criteria.getDenNgay();
//			if(tuNgay != null && !tuNgay.equals("")){
//				tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
//			}
//			if(denNgay != null && !denNgay.equals("")){
//				denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
//			}
//			criteria.setTuNgay(tuNgay);
//			criteria.setDenNgay(denNgay);*/
//			List<BaoCaoBanHangView> list = hoaDonDao.getAllHoaDonBaoCaoBanHang_new(criteria, objUser.getMacqt(),objUser.getLimitMST(), objUser.getRole(), fieldSorting, 0, 0);
//			List<BaoCaoBanHangView> list_TH = hoaDonDao.getAllHoaDonBaoCaoBanHang_new_TH(criteria, objUser.getMacqt(),objUser.getLimitMST(), objUser.getRole(), fieldSorting, 0, 0);
//			
//			//List<BaoCaoBanHangView> list = hoaDonDao.getAllHoaDonBaoCaoBanHang(criteria, objUser.getMacqt(),objUser.getLimitMST(), objUser.getRole(), fieldSorting, 0, 0);
//
//			/*if(list != null && list.size() > 0){
//				BaoCaoBanHangView objViewDenNgay = null ;
//				BaoCaoBanHangView objViewTuNgay = null ;
//				
//					int sizeList = list.size() ;
//					if(fieldSorting.contains("ASC")){
//						objViewDenNgay = list.get(sizeList-1);					
//	    				objViewTuNgay = list.get(0);
//					}else{
//						objViewDenNgay = list.get(0);					
//	    				objViewTuNgay = list.get(sizeList-1);
//					}
//												
//				if(!StringUtils.isEmpty(criteria.getTuNgay()) && StringUtils.isEmpty(criteria.getDenNgay())){
//					if(objViewDenNgay != null){
//						denNgay = objViewDenNgay.getNgayXHDon();
//					}
//				}else if(StringUtils.isEmpty(criteria.getTuNgay()) && !StringUtils.isEmpty(criteria.getDenNgay())){
//					if(objViewTuNgay != null){
//						tuNgay = objViewTuNgay.getNgayXHDon();
//					}
//					
//				}else if(StringUtils.isEmpty(criteria.getTuNgay()) && StringUtils.isEmpty(criteria.getDenNgay())){
//					if(objViewTuNgay != null){
//						tuNgay = objViewTuNgay.getNgayXHDon();
//					}
//					if(objViewDenNgay != null){
//						denNgay = objViewDenNgay.getNgayXHDon();
//					}
//				}
//			}*/
//			workbook = iReport.exportExcel(list_TH,list, tuNgay, denNgay, fieldSorting);
//			return writeByteToFileExcel(workbook, sNameExcel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	//===========================
//	
//	@SuppressWarnings("static-access")
//	@Link(label=BreadCrumbConst.BAOCAO_TINHHINH_SUDUNG_HD_VIEW, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/reportUseOfInvoices.bv", method = RequestMethod.GET)
//	public ModelAndView reportUseOfInvoicesView(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//		try {
//			String role = ValueConst.LABLE_TINHHINH_SUDUNG_HDON;
//			String mav = "";
//			String checkRole = authenticationModel.CheckUserRule(role, webSession);
//			if (checkRole.equals("1")) {
//				Map<String, Object> models  = new HashMap<String, Object>();
//				UserInfo objUser = webSession.getCurrentUser();			
//				int roleOld = objUser.getRole();
//				// lay gia tri ngay mac dinh=========
//				String curDate="", beginDate="", endDate = "";
//				//curDate= hoaDonDao.getMySqlDateCur();
//				curDate = hoaDonDao.getSQLDateTime();
//				beginDate= Util.formatDateTimeTo01MMyyyy_new(curDate);
//				endDate = Util.formatDateTimeToEndMMyyyy_new(beginDate);
//				
//				String sMaTinhCQBH = "",quanCQBH = "";
//				sMaTinhCQBH = String.valueOf(objUser.getMatinh());
//				quanCQBH = String.valueOf(objUser.getMacqt());
//				
//				if(roleOld == ValueConst.ROLE_SUPER_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else if(roleOld == ValueConst.ROLE_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else{
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListLimitQuan(quanCQBH));
//				}
//				models.put("role", webSession.getCurrentUser().getRole());
//				models.put("tuNgay", beginDate);
//				models.put("denNgay", endDate);
//				ModelAndView mv = new ModelAndView("reportUseOfInvoices", models);
//				return mv;
//			}else{
//				mav = checkRole;
//				return new ModelAndView(mav);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@Link(label=BreadCrumbConst.BAOCAO_TINHHINH_SUDUNG_HD_VIEW, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/reportUseOfInvoices.bv", method = RequestMethod.POST)
//	public @ResponseBody JsonResultList<ReportUseOfInvoicesView> reportUseOfInvoicesSearch(@ModelAttribute objSearchInvoice criteria, int jtStartIndex, int jtPageSize, String jtSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		String role = ValueConst.LABLE_TINHHINH_SUDUNG_HDON;
//		String checkRole = authenticationModel.CheckUserRule(role, webSession);
//		JsonResultList<ReportUseOfInvoicesView> listResponse = new JsonResultList<ReportUseOfInvoicesView>();
//		if (checkRole.equals("1")) {
//			logger.info("Loading data for invoice Report reportUseOfInvoices");
//			try {
//								
//				listResponse = checkIsValidDate(listResponse, criteria.getTuNgay(), criteria.getDenNgay());
//				if(listResponse != null && !StringUtils.isEmpty(listResponse.getMessage())){
//					return listResponse;
//				}
//				
//				UserInfo objUser = webSession.getCurrentUser();	
//				List<ReportUseOfInvoicesView> list = hoaDonDao.getReportUseOfInvoices(criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole(), jtSorting, jtStartIndex, jtPageSize);
//				listResponse.setRecords(list);
//				listResponse.setResult(ResultEnum.OK);
//				listResponse.setTotalRecordsCount(list.size());
//			} catch (Exception e) {
//				System.out.println("Caused by");
//				e.printStackTrace();
//				listResponse.setResult(ResultEnum.ERROR);
//				listResponse.setMessage(e.getMessage());
//			}  
//			return listResponse;
//		}else{
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.rule.permission"));
//			return listResponse;
//		}
//	}
//
//	@SuppressWarnings("static-access")
//	private JsonResultList<ReportUseOfInvoicesView> checkIsValidDate(JsonResultList<ReportUseOfInvoicesView> listResponse, String tuNgay, String denNgay) {
//		if(StringUtils.isEmpty(tuNgay)){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.login.fieldRequired", new String[] {ValueConst.LABLE_TUNGAY}));
//			return listResponse;
//		}
//		if(StringUtils.isEmpty(denNgay)){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.login.fieldRequired", new String[] {ValueConst.LABLE_DENNGAY}));
//			return listResponse;
//		}
//
//		if(tuNgay.contains("/") && tuNgay.contains("-")){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.format.tuNgay"));
//			return listResponse;
//		}
//		if(denNgay.contains("/") && denNgay.contains("-")){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.format.denNgay"));
//			return listResponse;
//		}
//		
//		if(!Util.isValidDateddmmyyyy(tuNgay)){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.format.tuNgay"));
//			return listResponse;
//		}
//		if(!Util.isValidDateddmmyyyy(denNgay)){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.format.denNgay"));
//			return listResponse;
//		}
//		if((Util.formatNgaySo_ddmmyyyy(denNgay)) < (Util.formatNgaySo_ddmmyyyy(tuNgay))){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.denNgay.greater.tuNgay"));
//			return listResponse;
//		}
//		
//		if(Util.calNumberDay(tuNgay,denNgay) > 93){
//			//errors.rejectValue("denNgay", "error.month.tracuu", new String[] {ValueConst.LABLE_DENNGAY}, "");
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.month.tracuu"));
//			return listResponse;
//		}
//		return listResponse;
//	}
//	
//	@SuppressWarnings({"unchecked" })
//	@RequestMapping(value="/exportToExcelreportUseOfInvoicesSearch.bv", method = RequestMethod.GET)
//	public  HttpEntity<byte[]> exportToExcelreportUseOfInvoicesSearch(@ModelAttribute objSearchInvoice criteria, String fieldSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
//		try {
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			IReportService<ReportUseOfInvoicesView> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_USE_OF_INVOICES);
//			String sNameExcel = "TinhhinhSDHoaDon_" + Util.getDateCurFull();
//			UserInfo objUser = webSession.getCurrentUser();	
//			List<ReportUseOfInvoicesView> list = hoaDonDao.getReportUseOfInvoices(criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole(), fieldSorting, 0, 0);
//			workbook = iReport.exportExcel(null,list, criteria.getTuNgay(), criteria.getDenNgay(),fieldSorting);
//			return writeByteToFileExcel(workbook, sNameExcel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	@SuppressWarnings("static-access")
//	@Link(label=BreadCrumbConst.BAOCAO_TONGHOP_VIEW, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/bangKeBanRaTongHop.bv", method = RequestMethod.GET)
//	public ModelAndView bangKeBanRaTongHopView(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//		try {
//			String role = ValueConst.LABLE_BANGKE_BANRA;
//			String mav = "";
//			String checkRole = authenticationModel.CheckUserRule(role, webSession);
//			if (checkRole.equals("1")) {
//				Map<String, Object> models  = new HashMap<String, Object>();
//				UserInfo objUser = webSession.getCurrentUser();			
//				int roleOld = objUser.getRole();
//				
//				String sMaTinhCQBH = "",quanCQBH = "";
//				sMaTinhCQBH = String.valueOf(objUser.getMatinh());
//				quanCQBH = String.valueOf(objUser.getMacqt());
//				
//				String curDate="", beginDate="", endDate = "";
//				//curDate= hoaDonDao.getMySqlDateCur();
//				curDate = hoaDonDao.getSQLDateTime();
//				beginDate= Util.formatDateTimeTo01MMyyyy_new(curDate);
//				endDate = Util.formatDateTimeToEndMMyyyy_new(beginDate);
//				
//				if(roleOld == ValueConst.ROLE_SUPER_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else if(roleOld == ValueConst.ROLE_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else{
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListLimitQuan(quanCQBH));
//				}
//				models.put("role", webSession.getCurrentUser().getRole());
//				models.put("tuNgay", beginDate);
//				models.put("denNgay", endDate);
//				ModelAndView mv = new ModelAndView("reportTongHop", models);
//				return mv;
//			}else{
//				mav = checkRole;
//				return new ModelAndView(mav);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@SuppressWarnings({"unchecked" })
//	@RequestMapping(value="/exportToExcelTongHopBangKeHoaDonBanRa.bv", method = RequestMethod.GET)
//	public HttpEntity<byte[]> exportToExcelBangKeHoaDonBanRaSearch(@ModelAttribute objSearchInvoice criteria, String fieldSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
//		try {
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			IReportService<ReportTongHopView> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_TONGHOP_BANGKE_HOADON_BANRA);
//			String sNameExcel = "BangKeHoaDonBanRa_" + Util.getDateCurFull();
//			UserInfo objUser = webSession.getCurrentUser();	
//			List<ReportTongHopView> list = hoaDonDao.getReportTongHop(criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole());
//			workbook = iReport.exportExcel(null, list, criteria.getTuNgay(), criteria.getDenNgay(),fieldSorting);
//			return writeByteToFileExcel(workbook, sNameExcel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	@SuppressWarnings({"unchecked" })
//	@RequestMapping(value="/exportToExcelTongHopBC26.bv", method = RequestMethod.GET)
//	public HttpEntity<byte[]> exportToExcelTongHopBC26(@ModelAttribute objSearchInvoice criteria, String fieldSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
//		try {
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			IReportService<ReportUseOfInvoicesView> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_USE_OF_INVOICES);
//			String sNameExcel = "TinhhinhSDHoaDon_BC26_" + Util.getDateCurFull();
//			UserInfo objUser = webSession.getCurrentUser();	
//			List<ReportUseOfInvoicesView> list = hoaDonDao.getReportUseOfInvoices(criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole(), fieldSorting, 0, 0);
//			workbook = iReport.exportExcel(null,list, criteria.getTuNgay(), criteria.getDenNgay(),fieldSorting);
//			return writeByteToFileExcel(workbook, sNameExcel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	@SuppressWarnings({"unchecked" })
//	@RequestMapping(value="/exportToExcelThongBaoPhatHanh.bv", method = RequestMethod.GET)
//	public HttpEntity<byte[]> exportToExcelThongBaoPhatHanh(@ModelAttribute objSearchInvoice criteria, String fieldSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
//		try {
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			IReportService<ReportTBaoPhatHanhView> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_THONGBAO_PHATHANH);
//			String sNameExcel = "ThongBaoPhatHanh" + Util.getDateCurFull();
//			UserInfo objUser = webSession.getCurrentUser();	
//			List<ReportTBaoPhatHanhView> list = hoaDonDao.getReportTBaoPhatHanh(criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole());
//			workbook = iReport.exportExcel(null, list, criteria.getTuNgay(), criteria.getDenNgay(),fieldSorting);
//			return writeByteToFileExcel(workbook, sNameExcel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Link(label=BreadCrumbConst.BAOCAO_PHATHANH_TONGHOP_VIEW, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/baoCaoPhatHanhTongHop.bv", method = RequestMethod.GET)
//	public ModelAndView phatHanhTongHopView(@ModelAttribute objSearchInvoice criteria, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, String selectedNoticeIds) {
//		try {
//			String role = ValueConst.LABLE_TINHHINH_SUDUNG_HDON;
//			String mav = "";
//			String checkRole = authenticationModel.CheckUserRule(role, webSession);
//			if (checkRole.equals("1")) {
//				Map<String, Object> models  = new HashMap<String, Object>();
//				models.put("tuNgay", criteria.getTuNgay());
//				models.put("denNgay", criteria.getDenNgay());
//				models.put("selectedNoticeIds", selectedNoticeIds);
//				ModelAndView mv = new ModelAndView("popupReportPhatHanhTongHop", models);
//				return mv;
//			}else{
//				mav = checkRole;
//				return new ModelAndView(mav);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@SuppressWarnings("static-access")
//	@Link(label=BreadCrumbConst.BAOCAO_THONGBAO_PHATHANH_VIEW, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/reportThongBaoPhatHanh.bv", method = RequestMethod.GET)
//	public ModelAndView reportThongBaoPhatHanhView(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//		try {
//			String role = ValueConst.LABLE_TINHHINH_SUDUNG_HDON;
//			String mav = "";
//			String checkRole = authenticationModel.CheckUserRule(role, webSession);
//			if (checkRole.equals("1")) {
//				Map<String, Object> models  = new HashMap<String, Object>();
//				UserInfo objUser = webSession.getCurrentUser();			
//				int roleOld = objUser.getRole();
//				// lay gia tri ngay mac dinh=========
//				String curDate="", beginDate="", endDate = "";
//				//curDate= hoaDonDao.getMySqlDateCur();
//				curDate = hoaDonDao.getSQLDateTime();
//				beginDate= Util.formatDateTimeTo01MMyyyy_new(curDate);
//				endDate = Util.formatDateTimeToEndMMyyyy_new(beginDate);
//				
//				String sMaTinhCQBH = "",quanCQBH = "";
//				sMaTinhCQBH = String.valueOf(objUser.getMatinh());
//				quanCQBH = String.valueOf(objUser.getMacqt());
//				
//				if(roleOld == ValueConst.ROLE_SUPER_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else if(roleOld == ValueConst.ROLE_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else{
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListLimitQuan(quanCQBH));
//				}
//				models.put("role", webSession.getCurrentUser().getRole());
//				models.put("tuNgay", beginDate);
//				models.put("denNgay", endDate);
//				ModelAndView mv = new ModelAndView("reportThongBaoPhatHanh", models);
//				return mv;
//			}else{
//				mav = checkRole;
//				return new ModelAndView(mav);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@Link(label=BreadCrumbConst.BAOCAO_THONGBAO_PHATHANH_VIEW, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/reportThongBaoPhatHanh.bv", method = RequestMethod.POST)
//	public @ResponseBody JsonResultList<ReportTBaoPhatHanhView> reportThongBaoPhatHanhSearch(@ModelAttribute objSearchInvoice criteria, String fieldSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		String role = ValueConst.LABLE_TINHHINH_SUDUNG_HDON;
//		String checkRole = authenticationModel.CheckUserRule(role, webSession);
//		JsonResultList<ReportTBaoPhatHanhView> listResponse = new JsonResultList<ReportTBaoPhatHanhView>();
//		if (checkRole.equals("1")) {
//			logger.info("Loading data for invoice Report reportThongBaoPhatHanh");
//			try {
//								
//				listResponse = checkIsValidDatePhatHanhHoaDon(listResponse, criteria.getTuNgay(), criteria.getDenNgay());
//				if(listResponse != null && !StringUtils.isEmpty(listResponse.getMessage())){
//					return listResponse;
//				}
//				
//				UserInfo objUser = webSession.getCurrentUser();	
//				List<ReportTBaoPhatHanhView> list = hoaDonDao.reportTBaoPhatHanhSearch(criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole(), fieldSorting, 0, 0);
//				listResponse.setRecords(list);
//				listResponse.setResult(ResultEnum.OK);
//				listResponse.setTotalRecordsCount(list.size());
//			} catch (Exception e) {
//				System.out.println("Caused by");
//				e.printStackTrace();
//				listResponse.setResult(ResultEnum.ERROR);
//				listResponse.setMessage(e.getMessage());
//			}  
//			return listResponse;
//		}else{
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.rule.permission"));
//			return listResponse;
//		}
//	}
//	
//	@SuppressWarnings("static-access")
//	private JsonResultList<ReportTBaoPhatHanhView> checkIsValidDatePhatHanhHoaDon(JsonResultList<ReportTBaoPhatHanhView> listResponse, String tuNgay, String denNgay) {
//		if(StringUtils.isEmpty(tuNgay)){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.login.fieldRequired", new String[] {ValueConst.LABLE_TUNGAY}));
//			return listResponse;
//		}
//		if(StringUtils.isEmpty(denNgay)){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.login.fieldRequired", new String[] {ValueConst.LABLE_DENNGAY}));
//			return listResponse;
//		}
//
//		if(tuNgay.contains("/") && tuNgay.contains("-")){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.format.tuNgay"));
//			return listResponse;
//		}
//		if(denNgay.contains("/") && denNgay.contains("-")){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.format.denNgay"));
//			return listResponse;
//		}
//		
//		if(!Util.isValidDateddmmyyyy(tuNgay)){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.format.tuNgay"));
//			return listResponse;
//		}
//		if(!Util.isValidDateddmmyyyy(denNgay)){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.format.denNgay"));
//			return listResponse;
//		}
//		if((Util.formatNgaySo_ddmmyyyy(denNgay)) < (Util.formatNgaySo_ddmmyyyy(tuNgay))){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.denNgay.greater.tuNgay"));
//			return listResponse;
//		}
//		
//		/*if(Util.calNumberDay(tuNgay,denNgay) > 93){
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.month.tracuu"));
//			return listResponse;
//		}*/
//		return listResponse;
//	}
//
//	
//	// report khi chon phat hanh
//	
//	@SuppressWarnings({"unchecked" })
//	@RequestMapping(value="/exportToExcelPhatHanhHoaDonBanRa.bv", method = RequestMethod.GET)
//	public HttpEntity<byte[]> exportToExcelPhatHanhHoaDonBanRaSearch(@ModelAttribute objSearchInvoice criteria, String selectedNoticeIds,  String fieldSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
//		try {
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			IReportService<ReportTongHopView> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_TONGHOP_BANGKE_HOADON_BANRA);
//			String sNameExcel = "BangKeHoaDonBanRa_" + Util.getDateCurFull();
//			UserInfo objUser = webSession.getCurrentUser();	
//			
//			List<ReportTongHopView> list = null ;
//			String arrID = "" ;			
//			List<objSearchPhatHanh> listPH = null ;
//			arrID = selectedNoticeIds;
//			if(arrID != null && !arrID.equals("")){
//				listPH = hoaDonDao.getDanhSachPhatHanh(arrID);
//			}
//			if(listPH != null && listPH.size() > 0){
//				 list = hoaDonDao.getReportTongHopPhatHanh(listPH,criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole());
//			}
//			workbook = iReport.exportExcel(null, list, criteria.getTuNgay(), criteria.getDenNgay(),fieldSorting);
//			return writeByteToFileExcel(workbook, sNameExcel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	@SuppressWarnings({"unchecked" })
//	@RequestMapping(value="/exportToExcelPhatHanhTongHopBC26.bv", method = RequestMethod.GET)
//	public HttpEntity<byte[]> exportToExcelPhatHanhTongHopBC26(@ModelAttribute objSearchInvoice criteria, String selectedNoticeIds, String fieldSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
//		try {
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			IReportService<ReportUseOfInvoicesView> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_USE_OF_INVOICES);
//			String sNameExcel = "TinhhinhSDHoaDon_BC26_" + Util.getDateCurFull();
//			UserInfo objUser = webSession.getCurrentUser();	
//			
//			List<ReportUseOfInvoicesView> list = null ;
//			String arrID = "" ;			
//			List<objSearchPhatHanh> listPH = null ;
//			arrID = selectedNoticeIds ;
//			if(arrID != null && !arrID.equals("")){
//				listPH = hoaDonDao.getDanhSachPhatHanh(arrID);
//			}
//			if(listPH != null && listPH.size() > 0){
//				list = hoaDonDao.getReportUseOfInvoicesPhatHanhTongHop(listPH,criteria, objUser.getMacqt(), objUser.getLimitMST(), objUser.getRole(), fieldSorting, 0, 0);
//			}
//			workbook = iReport.exportExcel(null,list, criteria.getTuNgay(), criteria.getDenNgay(),fieldSorting);
//			return writeByteToFileExcel(workbook, sNameExcel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	
//	@SuppressWarnings({"unchecked" })
//	@RequestMapping(value="/exportToExcelListSelectedThongBaoPhatHanh.bv", method = RequestMethod.GET)
//	public HttpEntity<byte[]> exportToExcelListSelectedThongBaoPhatHanh(@ModelAttribute objSearchInvoice criteria, String selectedNoticeIds, String fieldSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
//		try {
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			IReportService<ReportTBaoPhatHanhView> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_THONGBAO_PHATHANH);
//			String sNameExcel = "ThongBaoPhatHanh" + Util.getDateCurFull();
//			List<ReportTBaoPhatHanhView> list = hoaDonDao.getDanhSachPhatHanhSelected(selectedNoticeIds, fieldSorting);
//			workbook = iReport.exportExcel(null, list, criteria.getTuNgay(), criteria.getDenNgay(), fieldSorting);
//			return writeByteToFileExcel(workbook, sNameExcel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	
////	@SuppressWarnings({"unchecked" })
////	@RequestMapping(value="/exportToExcelBaoCaoBanHangSearchThread.bv", method = RequestMethod.GET)
////	public  HttpEntity<byte[]> exportToExcelBaoCaoBanHangSearchThread(@ModelAttribute objSearchInvoice criteria, String fieldSorting,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
////		try {
////			HSSFWorkbook workbook = new HSSFWorkbook();
////			IReportService<BaoCaoBanHangView> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_BAO_CAO_BAN_HANG);
////			String sNameExcel = "BaoCaoBanHang_" + Util.getDateCurFull();
////			UserInfo objUser = webSession.getCurrentUser();	
////			String tuNgay="",denNgay = ""; 
////			tuNgay = criteria.getTuNgay();
////			denNgay = criteria.getDenNgay();
////			List<BaoCaoBanHangView> list = hoaDonDao.getAllHoaDonBaoCaoBanHang_new(criteria, objUser.getMacqt(),objUser.getLimitMST(), objUser.getRole(), fieldSorting, 0, 0);
////			List<BaoCaoBanHangView> list_TH = hoaDonDao.getAllHoaDonBaoCaoBanHang_new_TH(criteria, objUser.getMacqt(),objUser.getLimitMST(), objUser.getRole(), fieldSorting, 0, 0);
////			workbook = iReport.exportExcel(list_TH,list, tuNgay, denNgay, fieldSorting);
////			return writeByteToFileExcel(workbook, sNameExcel);
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		return null;
////	}
//	
//} 
