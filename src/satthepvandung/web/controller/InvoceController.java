//package satthepvandung.web.controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.MessageSource;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.servlet.ModelAndView;
//
//import ts24.com.vn.dal.dao.HoaDonDao;
//import ts24.com.vn.dal.dao.impl.HoaDonDaoImpl;
//import ts24.com.vn.dal.model.HoaDon;
//import ts24.com.vn.dal.model.UserRule;
//import ts24.com.vn.dal.model.nonentity.HoaDonView;
//import ts24.com.vn.model.AuthenticationModel;
//import ts24.com.vn.model.CertifyStatus;
//import ts24.com.vn.model.CertifyStatusResult;
//import ts24.com.vn.model.InvoiceElectronicType;
//import ts24.com.vn.model.LocaleType;
//import ts24.com.vn.model.StatusSystem;
//import ts24.com.vn.model.UserInfo;
//import ts24.com.vn.model.WebServiceType;
//import ts24.com.vn.model.objSearchInvoice;
//import ts24.com.vn.service.CoQuanThueService;
//import ts24.com.vn.service.impl.CoQuanThueServiceImpl;
//import ts24.com.vn.web.common.BreadCrumbConst;
//import ts24.com.vn.web.common.LocaleCustomize;
//import ts24.com.vn.web.common.SessionConst;
//import ts24.com.vn.web.common.ValueConst;
//import ts24.com.vn.web.common.WebSession;
//import ts24.com.vn.web.digisign.DigitalSignVerify;
//import ts24.com.vn.web.model.datatable.JsonResultList;
//import ts24.com.vn.web.model.datatable.ResultEnum;
//import ts24.com.vn.web.util.Commons;
//import ts24.com.vn.web.util.ConstantValue;
//import ts24.com.vn.web.util.ExcelManager;
//import ts24.com.vn.web.util.FileManager;
//import ts24.com.vn.web.util.Path;
//import ts24.com.vn.web.util.ReadXMLFileUsingDom;
//import ts24.com.vn.web.util.Utilities;
//import ts24.com.vn.web.util.md5;
//import dummiesmind.breadcrumb.springmvc.annotations.Link;
//
//@Controller
//@RequestMapping(value = "/invoice")
//@SessionAttributes({SessionConst.WEB_SESSION})
//public class InvoceController {
//	
//	private static final Logger logger = Logger.getLogger(InvoceController.class);
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
//	public InvoceController(){
//		this.authenticationModel = new AuthenticationModel();
//		this.coquanthueService = new CoQuanThueServiceImpl();
//		this.hoaDonDao = new HoaDonDaoImpl();
//	}
//	
//	@ModelAttribute("invoice")
//	public HoaDon createInvoiceModel(){
//		HoaDon hd = new HoaDon();
//		return hd;
//	}
//	
//	@Link(label=BreadCrumbConst.VIEW_INVOICE, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/hoadon_view.bv", method = RequestMethod.GET)
//	public ModelAndView hoadon_view(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//		try {
//			String role = ValueConst.LABLE_QLYTRACUU;
//			String mav = "";
//			String checkRole = authenticationModel.CheckUserRule(role, webSession);
//			if (checkRole.equals("1")) {
//				Map<String, Object> models  = new HashMap<String, Object>();
//				UserInfo objUser = webSession.getCurrentUser();			
//				int roleOld = objUser.getRole();
//				String sMaTinhCQBH = "",quanCQBH = "";
//				sMaTinhCQBH = String.valueOf(objUser.getMatinh());
//				quanCQBH = String.valueOf(objUser.getMacqt());
//				
//				if(roleOld == 1){
//					webSession.setShowFieldMaNhanHD(ValueConst.KEY_SHOW);
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else{
//					List<UserRule> listRule = webSession.getListRule();
//					if(listRule != null && listRule.size() > 0){
//						for (UserRule rule : listRule) {
//							if(rule.getRule().equals(ValueConst.LABLE_TRACUU_MANHAN_HDON)){
//								webSession.setShowFieldMaNhanHD(ValueConst.KEY_SHOW);
//								break;
//							}			
//						}
//					}
//					if(roleOld == 2){
//						models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//						models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//					}else{
//						models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//						models.put("arrQuan", coquanthueService.getListLimitQuan(quanCQBH));
//					}
//				}
//				
//				models.put("role", webSession.getCurrentUser().getRole());
//				//session.setAttribute(SessionConst.WEB_SESSION, webSession);
//				
//				ModelAndView mv = new ModelAndView("hoadondientu_view", models);
//				return mv;
//			
//			//	mav = "hoadondientu_view";
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
//	@Link(label=BreadCrumbConst.VIEW_INVOICE, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/hoadon_search.bv", method = RequestMethod.POST)
//	public @ResponseBody JsonResultList<HoaDonView> hoadondientu_search(@ModelAttribute objSearchInvoice obj, int jtStartIndex, int jtPageSize, String jtSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		String role = ValueConst.LABLE_QLYTRACUU;
//		String checkRole = authenticationModel.CheckUserRule(role, webSession);
//		JsonResultList<HoaDonView> listResponse = new JsonResultList<HoaDonView>();
//		if (checkRole.equals("1")) {
//			logger.info("Loading data for invoice table");
//			try {
//				
//				String tuNgay="",denNgay = ""; 
//				tuNgay = obj.getTuNgay();
//				denNgay = obj.getDenNgay();
//				if(tuNgay != null && !tuNgay.equals("")){
//					tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
//				}
//				if(denNgay != null && !denNgay.equals("")){
//					denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
//				}
//				obj.setTuNgay(tuNgay);
//				obj.setDenNgay(denNgay);
//				UserInfo objUser = webSession.getCurrentUser();	
//				List<HoaDonView> views = hoaDonDao.getAllHoaDonNew(obj, objUser.getMacqt(),objUser.getLimitMST(), objUser.getRole(), jtSorting, jtStartIndex, jtPageSize);
//				listResponse.setRecords(views);
//				listResponse.setResult(ResultEnum.OK);
//				listResponse.setTotalRecordsCount(hoaDonDao.getRecordCountInvoice(obj, objUser.getMacqt(),objUser.getLimitMST(), objUser.getRole()));
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
////	@SuppressWarnings({ "static-access", "rawtypes" })
////	@RequestMapping(value="/hoadon_chitiet.bv", method = RequestMethod.GET)
////	public ModelAndView viewDetailInvoice(Model model, String id, String loaiHD, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
////		String status = "";
////		String role = ValueConst.LABLE_QLYTRACUU;
////		String checkRole = authenticationModel.CheckUserRule(role, webSession);
////		if (checkRole.equals("1")) {
////				
////			try {
////				 String filePath = "", fileContent = "", mauIn = "", sFileNameXSL = "", linkXMLB = "", result="";
////				 String pathWebapp = Path.URL_WEBAPP, pathXSL = "";
////				 DigitalSignVerify digiVerify = new DigitalSignVerify( );
////				 String tencty = "", serial = "";
//////				if(loaiHD.equals("0")){//	hoa don dien tu
//////					HoaDonDienTu objDT = hoaDonDienTuDAO.getHDDTById(id);
//////					if (objDT != null) {
//////						filePath = folderRoot + objDT.getFileHDon();
//////					}
//////				}else{	// hoa don xac thuc
//////					HoaDonDienTuXacThuc objXT = hoaDonDienTuXacThucDAO.getObjByID(id);
//////					if (objXT != null) {
//////						filePath = folderRoot + objXT.getFileHDon();
//////					}
//////				}
////				 
////				HoaDon oHoaDon = hoaDonDao.getObjByID(id);
////				String checkHD = checkHoaDon(oHoaDon);
////				if (checkHD != null && checkHD.equals("true")) {
////					filePath = folderRoot + oHoaDon.getFileHDon();
////				}else{
////					status = checkHD;
////					return ModelView("error.certified.invoice", status);
////				}
////				 
////				if (Utilities.checkExitsFile(filePath) == 1) {
////					String pathStyleSheet = "", pathFileXSL = "";
////					fileContent = FileManager.readFileToString( filePath );
////					if(StringUtils.isNotEmpty(fileContent)){
//////						mauIn = ConstantValue.DEFAULT_TEMPLATE_KEY_NAME_XSL; // default template using old xml not contain node <inv:MauIn>
//////						File fileStyleSheet = new File( pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL);
//////						sFileNameXSL = readXML.ReadXMLFileStyleSheet( fileStyleSheet, mauIn );
////						mauIn = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_MAUIN, ConstantValue.END_NODE_MAUIN);
////						if(StringUtils.isEmpty(mauIn)){
////							mauIn = ConstantValue.DEFAULT_TEMPLATE_KEY_NAME_XSL; // default template using old xml not contain node <inv:MauIn> 
////						}
////						// Lay Node <StyleSheet>  theo <MaToKhai> tu file [StyleSheet] (ten file xsl)
////						if(oHoaDon.getLoaWS() == WebServiceType.WS_HOSPITAL.getCode()){ // benh vien
////							pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL_HOSPITAL;
////							pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL_HOSPITAL;
////							pathXSL = Path.URL_XSL_BV;
////						}else{
////							pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL;
////							pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL;
////							pathXSL = Path.URL_XSL;
////						}
////						File fileStyleSheet = new File( pathStyleSheet);
////						sFileNameXSL = readXML.ReadXMLFileStyleSheet( fileStyleSheet, mauIn );
////						if(Utilities.checkExitsFile( pathFileXSL + sFileNameXSL ) == 1 ){
////							String infoSign = digiVerify.getInfoXmlSignatureXpathHDDT_BenhVien(  filePath );
////							ArrayList sigArr = Utilities.getThongTinCKS(infoSign);
////							String result1 = "", idSign = "";
////							if(sigArr != null && sigArr.size() > 0){
////								result1 = "";
////								for(int k = 0 ;k <sigArr.size() ; k ++){
////									ArrayList objSign = ( ArrayList ) sigArr.get( k );
////									idSign = objSign.get(6).toString( );
////									if(idSign != null && !idSign.equals("") && idSign.equals("seller")){
////										serial = objSign.get(1).toString( );
////										tencty = objSign.get(3).toString( );
////										result1 += "<CKS><CKSNguoiBan><Subject>" + tencty + "</Subject><Serial>" + serial + "</Serial></CKSNguoiBan></CKS>";
////									}else if(idSign != null && !idSign.equals("") && idSign.equals("buyer")){
////										serial = objSign.get(1).toString( );
////										tencty = objSign.get(3).toString( );
////										result1 += "<CKS><CKSNguoiMua><Subject>" + tencty + "</Subject><Serial>" + serial + "</Serial></CKSNguoiMua></CKS>";
////									}
////									if(result1 != null && !result1.equals("")){
////										result1 = result1.replaceAll("&", "&amp;");
////									}
////								}
////							}
////							
////							linkXMLB = "<?xml-stylesheet type=\"text/xsl\" href=\""+ pathXSL + sFileNameXSL+ "\"?>";
////							
////							result = fileContent;
////							result = StringUtils.substringBetween(result, ConstantValue.BEGIN_NODE_INVOICES, ConstantValue.END_NODE_INVOICES);
////							result = result.replace(ConstantValue.BEGIN_CDATA, "").replace(ConstantValue.END_CDATA, "");
////							result = result.replace(ConstantValue.BEGIN_NODE_INVOICE, ConstantValue.BEGIN_HTTP_NODE_INVOICE);
////							result = result.replace(ConstantValue.END_NODE_INVOICE, result1 + ConstantValue.END_NODE_INVOICE);
////							
////							result = linkXMLB + result;
////							model.addAttribute("noidung", result);
////							model.addAttribute( "showHD", true );
////							return new ModelAndView("download.certified.invoice");
//////							return ModelView("download.certified.invoice", null);
////							
////						}
////					}else{
//////						model.addAttribute( "statusViewHD", -2 ); // not view file
//////						// delele file invoice temp
//////						return new ModelAndView( "error.certified.invoice" );
////						status = "err.not.file";
////						return ModelView("error.certified.invoice", status);
////					}
////				}else{
//////					model.addAttribute( "statusViewHD", -2 ); // not view file
//////					// delele file invoice temp
//////					return new ModelAndView( "error.certified.invoice" );
////					status = "err.not.file";
////					return ModelView("error.certified.invoice", status);
////				}
//////				model.addAttribute( "statusViewHD", -2 ); // not view file
//////				// delele file invoice temp
//////				return new ModelAndView( "error.certified.invoice" );
////				status = "err.not.file";
////				return ModelView("error.certified.invoice", status);
////			} catch (Exception e) {
////				e.printStackTrace();
//////				model.addAttribute( "statusViewHD", -99 ); // not validate data
//////				// delele file invoice temp
//////				return new ModelAndView( "error.certified.invoice" );
////				status = "err.not.file";
////				return ModelView("error.certified.invoice", status);
////			}
////		}else{
////			
////			return new ModelAndView(checkRole);
////		}
////	}
//	
//	 @RequestMapping(value = "/changeCity.bv", method = RequestMethod.POST)
//		public ModelAndView changeCityProcess(String maTinh, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//			String checkQuyen = "error";
//			String role = ValueConst.LABLE_QLYTRACUU;
//			checkQuyen = authenticationModel.CheckUserRule(role, webSession);
//					
//			if(checkQuyen.equals("1")){
//				Map<String, Object> models  = new HashMap<String, Object>();
//				try{
//					UserInfo objUser = webSession.getCurrentUser();
//					if(objUser.getRole() == 1){
//						
//						models.put("arrQuan", coquanthueService.getListQuanTheoTinh(maTinh));
//					}else if(objUser.getRole() == 2){
//						
//						models.put("arrQuan", coquanthueService.getListQuanTheoTinh(maTinh));
//					}else{
//					
//						models.put("arrQuan", coquanthueService.getListLimitQuan(objUser.getMacqt()));
//					}
//					
//				//	models.put("arrQuan", coquanthueService.getListQuanTheoTinh(maTinh));
//					/*models.put("role", webSession.getCurrentUser().getRole());
//					if(webSession.getCurrentUser().getRole() < 3){
//						quan = "quan";
//					}
//					models.put("quyenMaQuan", quan);*/
//				}catch(Exception e){
//					System.out.println("Can't get create project, caused by");
//					e.printStackTrace();
//				}
//				ModelAndView mv = new ModelAndView("listCategoryDistrict", models);
//				return mv;
//			}else{
//				ModelAndView view = new ModelAndView(checkQuyen);
//				return view;
//			}
//		}
//	 
//	@SuppressWarnings("deprecation")
//	@Link(label=BreadCrumbConst.VIEW_INVOICE, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/searchInvoiceFromExcel.bv", method = RequestMethod.POST)
//	public ModelAndView importFileExcelSearchNew(@ModelAttribute("searchHDDTFormBrowse") objSearchInvoice form, WebSession webSession, Model model, int jtStartIndex, int jtPageSize, String jtSorting, HttpSession session, MultipartHttpServletRequest req) {
////	public @ResponseBody JsonResultList<HoaDonView> importFileExcelSearchNew(@ModelAttribute objSearchInvoice obj, int jtStartIndex, int jtPageSize, String jtSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpSession session, MultipartHttpServletRequest req){
//		String role = ValueConst.LABLE_QLYTRACUU;
//		String mav = "";
//		String checkRole = authenticationModel.CheckUserRule(role, webSession);
//		if (checkRole.equals("1")) {
//		
//			logger.info("Loading data for invoice table");
//			JsonResultList<HoaDonView> listResponse = new JsonResultList<HoaDonView>();
//			Map<String, Object> models = new HashMap<String, Object>();
//			ModelAndView mv = null;
//			ArrayList<HoaDonView> listResult = new ArrayList<HoaDonView>();
//			int totalRecords = 0;
//			try {
//				ExcelManager oImport = new ExcelManager();
//				FileManager oFile = new FileManager();
//				ArrayList<objSearchInvoice> listInvoice = new ArrayList<objSearchInvoice>();
//				String FolderTemp = Path.UPLOAD_WEBFILE_PATH;
//				String destinationDir = FolderTemp;
//				String fileName = "", fileName_org = "", sName = "", sTypeFile = "";
//				String message = "", status_add = "";
//				boolean check_file = false;
//				int rows = 0;
//				try {
//	
//					Iterator<String> itr =  req.getFileNames();
//				    MultipartFile multipartRequest = req.getFile(itr.next());
//				    fileName_org = multipartRequest.getOriginalFilename();
//				    byte[] bytes = multipartRequest.getBytes();
//	//		        content =  multipartRequest.getContentType();
//				    
//					/**
//					 * check file extension valid
//					 */
//					check_file = commons.checkValidFileExcelUpload(fileName_org);
//					if (!check_file) {
//						message = getMessage("error.file.excel.browefile",new Object[] {getMessage(fileName_org)});
//						models.put("errorMessage", message);
//						mv = new ModelAndView("hoadondientu_view", models);
//						return mv;
//					} else {
//						sName = md5.encode(fileName_org);
//						String[] arrFile = fileName_org.split("\\.");
//	
//						sTypeFile = arrFile[1];
//						fileName = sName + "." + sTypeFile;
//	
//						File destination = new File(destinationDir + fileName);
//						File f = new File(destinationDir);
//						if(!f.exists()){
//							f.mkdirs();
//						}
//						
//						FileManager.writeBytesToFile(destination, bytes);
//						/**
//						 * process upload excel data
//						 */
//	
//						POIFSFileSystem fs = new POIFSFileSystem(
//								new FileInputStream(destinationDir + fileName));
//						HSSFWorkbook wb = new HSSFWorkbook(fs);
//						int Sheet_Number = wb.getNumberOfSheets();
//						int Sheet_init = 0;
//						for (int k = 0; k < Sheet_Number; k++) {
//							if (wb.getSheetName(k).toUpperCase().equals("SHEET1")) {
//								Sheet_init = k;
//								break;
//							}
//						}
//						HSSFSheet sheet = wb.getSheetAt(Sheet_init);
//						rows = sheet.getPhysicalNumberOfRows();
//						for (int r = 1; r < rows; r++) {
//							String mstNguoiBan = "", mauHoaDon = "", stt = "", kyHieu = "", soHoaDon = "";
//							for (short c = 0; c < 5; c++) // cot
//							{
//								if (c == 0) { // Mã số thuế
//									HSSFRow row = sheet.getRow(r);
//									if (row != null) {
//										HSSFCell cell = row.getCell(c);
//										mstNguoiBan = oImport.getValueExcel_NoNumber(cell, sheet, wb);
//									}
//								}
//	
//								if (c == 1) { 
//									HSSFRow row = sheet.getRow(r);
//									if (row != null) {
//										HSSFCell cell = row.getCell(c);
//										mauHoaDon = oImport.getValueExcel_NoNumber(cell,sheet, wb);
//									}
//	
//								}
//	
//								if (c == 2) { 
//									HSSFRow row = sheet.getRow(r);
//									if (row != null) {
//										HSSFCell cell = row.getCell(c);
//										stt = oImport.getValueExcel_NoNumber(cell, sheet, wb);
//									}
//								}
//								
//								if (c == 3) { 
//									HSSFRow row = sheet.getRow(r);
//									if (row != null) {
//										HSSFCell cell = row.getCell(c);
//										kyHieu = oImport.getValueExcel_NoNumber(cell, sheet, wb);
//									}
//								}
//								
//								if (c == 4) { 
//									HSSFRow row = sheet.getRow(r);
//									if (row != null) {
//										HSSFCell cell = row.getCell(c);
//										soHoaDon = oImport.getValueExcel_NoNumber(cell, sheet, wb);
//									}
//								}
//								// end columns	
//							}
//							objSearchInvoice objSearch = new objSearchInvoice();
//							// kiem tra dieu kien theo yeu cau form
//							if (StringUtils.isNotEmpty(mstNguoiBan) 
//									&& StringUtils.isNotEmpty(mauHoaDon)
//									&& StringUtils.isNotEmpty(stt)
//									&& StringUtils.isNotEmpty(kyHieu)
//									&& StringUtils.isNotEmpty(soHoaDon)){
//								
//								objSearch.setMstNguoiBan(mstNguoiBan);
//								objSearch.setMauHoaDon(mauHoaDon);
//								objSearch.setStt(stt);
//								objSearch.setKyHieu(kyHieu);
//								objSearch.setSoHoaDon(soHoaDon);
//								// Add record true to list
//								listInvoice.add(objSearch);
//								status_add = status_add + mstNguoiBan;
//							}
//						}
//						oFile.DeleteFiles(destinationDir + fileName);
//	
//					} // end if
//	
//				} catch (Exception e) {
//					e.printStackTrace();
//					oFile.DeleteFiles(destinationDir + fileName);
//					message = getMessage("error.data.excel.import");										
//					models.put("errorMessage", message);
//					return new ModelAndView("hoadondientu_view", models);
//				}
//				UserInfo objUser = webSession.getCurrentUser();	
//				if(listInvoice != null && listInvoice.size()>0){
//					for (objSearchInvoice objSearchInvoice : listInvoice) {
//						List<HoaDonView> views = hoaDonDao.getAllHoaDonNew(objSearchInvoice, objUser.getMacqt(),objUser.getLimitMST(), objUser.getRole(), jtSorting, jtStartIndex, jtPageSize);
//						if(views != null && views.size()>0 ){
//							for (HoaDonView hoaDonView : views) {
//								totalRecords +=1;
//								listResult.add(hoaDonView);
//							}
//						}
//					}
//				}
//				models.put("listRecords", listResult);
//				models.put("totalRecords", totalRecords);
//				if(totalRecords == 0){
//					message = getMessage("notice.search.null");
//					models.put("errorMessage", message);
//				}
//				
//				// lay lai form
//				
//				
//					UserInfo objUser1 = webSession.getCurrentUser();			
//					int roleOld = objUser1.getRole();
//					
//					String sMaTinhCQBH = "",quanCQBH = "";
//					sMaTinhCQBH = String.valueOf(objUser.getMatinh());
//					quanCQBH = String.valueOf(objUser.getMacqt());
//					
//					if(roleOld == 1){
//						models.put("arrTinhTP", coquanthueService.getListTinh());
//						models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//					}else if(roleOld == 2){
//						models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//						models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//					}else{
//						models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//						models.put("arrQuan", coquanthueService.getListLimitQuan(quanCQBH));
//					}
//					
//					models.put("role", webSession.getCurrentUser().getRole());
//					
//					
//				//==============================
//				
//							
//			} catch (Exception e) {
//				System.out.println("Caused by");
//				e.printStackTrace();
//				listResponse.setResult(ResultEnum.ERROR);
//				listResponse.setMessage(e.getMessage());
//				return null;
//			}
//			mv = new ModelAndView("hoadondientu_view", models);
//			return mv;
//		
//		}else{
//			mav = checkRole;
//			return new ModelAndView(mav);
//		}
//		
//	 }
//	
//	private ModelAndView ModelView(String view, String keyMessage) {
//		String message;
//		ModelAndView mvErr = new ModelAndView(view);
//		if(StringUtils.isNotEmpty(keyMessage)){
//			message = getMessage(keyMessage);
//			mvErr.addObject("errorMessage", message);
//		}
//		return mvErr;
//	}
//	
//	private String checkHoaDon(HoaDon oHoaDon) {
//			String status = "",flag = "";
//			if(oHoaDon == null){
//				status = StatusSystem.ERR_NOT_EXISTS_IDHOADON.getDes();
//			//LoaWS -> 1: WS Bênh Viện; 2: WS HDDT TS24; 3: WS HDDT VNPT
//			}else if(oHoaDon.getLoaWS() == WebServiceType.WS_VNPT.getCode()){
//				if(oHoaDon.getLoaiHDon() == InvoiceElectronicType.XAC_THUC_VNPT.getCode()){
//					if(StringUtils.isEmpty(oHoaDon.getStatus()) &&  CertifyStatus.CERTIFY_WAIT.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
//						status = "err.certificate.vnpt.pending.send";
//					}else if(StringUtils.isEmpty(oHoaDon.getStatus()) && CertifyStatus.CERTIFY_NOT_YET.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
//						status = "err.certificate.vnpt.not.send";
//					}else if(CertifyStatusResult.STATUS_03.getCode().equals(oHoaDon.getStatus())){
//						status = "err.certificate.vnpt.send";
//					}else if(StringUtils.isNotEmpty(oHoaDon.getFKey()) && StringUtils.isNotEmpty(oHoaDon.getBarCode())){
//						flag = "true";
//					}else flag = "false";
//				}else if(oHoaDon.getLoaiHDon() == InvoiceElectronicType.HOADON_VNPT.getCode()){
//					flag = "true";
//				}else flag = "false"; // hien tai VNPT chi co hoa don chong gia, ko co dien tu nen set false
//			}else if(oHoaDon.getLoaWS() == WebServiceType.WS_TS24.getCode() || oHoaDon.getLoaWS() == WebServiceType.WS_HOSPITAL.getCode()){
//				//LoaiHDon -> 1: Điện tử; 2: Xác thực; 3: HDDT-Xác thực chống giả (Điện tử xác thực)
//				if(oHoaDon.getLoaiHDon() == InvoiceElectronicType.XAC_THUC.getCode()){
//					// TinhTrangXacThuc -> 0: Chưa xác thực; 1: Đã xác thực; -1 Lỗi xác thực; 2: Đang gửi xác thực
//					if(CertifyStatus.CERTIFY_WAIT.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
//						status = "err.wait.cetificated";
//					}else if(CertifyStatus.CERTIFY_NOT_YET.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
//						status = "err.not.yet.cetificated";
//					}else if(CertifyStatus.CERTIFY_ERROR.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
//						status = "err.cetificated";
//					}else if(CertifyStatus.CERTIFY_SUCCESS.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
//						flag = "true";
//					}else flag = "false";
//				}else flag = "true"; 
//			}else flag = "true";
//			if (status != null && !status.equals("")) {
//				return status;
//			}else if (flag != null && !flag.equals("")) {
//				return flag;
//			}else{
//				return "err.system";
//			}
//	}
//	
//	
//	@SuppressWarnings({ "static-access", "rawtypes" })
//	@RequestMapping(value="/hoadon_chitiet.bv", method = RequestMethod.GET)
//	public ModelAndView viewDetailInvoice(Model model, String id, String loaiHD, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		String status = "";
//		String role = ValueConst.LABLE_QLYTRACUU;
//		String checkRole = authenticationModel.CheckUserRule(role, webSession);
//		if (checkRole.equals("1")) {
//			try {
//				 String filePath = "", fileContent = "", mauIn = "", sFileNameXSL = "", linkXMLB = "", result="";
//				 String pathWebapp = Path.URL_WEBAPP, pathXSL = "";
//				 DigitalSignVerify digiVerify = new DigitalSignVerify( );
//				 String tencty = "", serial = "";
//				 
//				HoaDon oHoaDon = hoaDonDao.getObjByID(id);
//				if(oHoaDon == null){
//					status = "err.not.file";
//					return ModelView("error.certified.invoice", status);
//				}
////					String checkHD = checkHoaDon(oHoaDon);
////					if (checkHD != null && checkHD.equals("true")) {
////						filePath = folderRoot + oHoaDon.getFileHDon();
////					}else{
////						status = checkHD;
////						return ModelView("error.certified.invoice", status);
////					}
//				
//				if (oHoaDon.getLoaWS() == WebServiceType.WS_VNPT.getCode()) {
//					filePath += folderRoot + ConstantValue.PATH_HDDT_DAKY + oHoaDon.getFileHDon();
//				}else{
//					filePath += folderRoot + oHoaDon.getFileHDon();
//				}
//				if (Utilities.checkExitsFile(filePath) == 1) {
//					String pathStyleSheet = "", pathFileXSL = "";
//					fileContent = FileManager.readFileToString( filePath );
//					if(StringUtils.isNotEmpty(fileContent)){
//						mauIn = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_MAUIN, ConstantValue.END_NODE_MAUIN);
//						if(StringUtils.isEmpty(mauIn)){
//							mauIn = ConstantValue.DEFAULT_TEMPLATE_KEY_NAME_XSL; // default template using old xml not contain node <inv:MauIn> 
//						}
//						// Lay Node <StyleSheet>  theo <MaToKhai> tu file [StyleSheet] (ten file xsl)
//						if (oHoaDon.getLoaWS() == WebServiceType.WS_VNPT.getCode()) {// vnpt
//							mauIn = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_MAUIN_VNPT, ConstantValue.END_NODE_MAUIN_VNPT);							
//							if(StringUtils.isEmpty(mauIn)){
//							mauIn = ConstantValue.TEMPLATE_VNPT;
//							}
//							pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL_VNPT;
//							pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL_VNPT;
//							pathXSL = Path.URL_XSLT_VNPT;
//						}else if(oHoaDon.getLoaWS() == WebServiceType.WS_HOSPITAL.getCode()){ // benh vien
//							pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL_HOSPITAL;
//							pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL_HOSPITAL;
//							pathXSL = Path.URL_XSL_BV;
//						}else{
//							pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL;
//							pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL;
//							pathXSL = Path.URL_XSL;
//						}
//						File fileStyleSheet = new File( pathStyleSheet);
//						sFileNameXSL = readXML.ReadXMLFileStyleSheet( fileStyleSheet, mauIn );
//						if(Utilities.checkExitsFile( pathFileXSL + sFileNameXSL ) == 1 ){
//							String infoSign = "";
//							if (oHoaDon.getLoaWS() == WebServiceType.WS_VNPT.getCode()) {
//								String lid = "serSig,vanSig";
//								String[] listId = lid.split(",");
//								infoSign = digiVerify.getInfoXmlSignatureXpathHDDT_Vnpt(filePath, listId);
//							}else{
//								infoSign = digiVerify.getInfoXmlSignatureXpathHDDT_BenhVien(  filePath );
//							}
//								
//							ArrayList sigArr = Utilities.getThongTinCKS(infoSign);
//							String result1 = "", idSign = "", resultCNCOM = "", resultCNTVAN = "";
//							if(sigArr != null && sigArr.size() > 0){
//								result1 = "";
//								for(int k = 0 ;k <sigArr.size() ; k ++){
//									ArrayList objSign = ( ArrayList ) sigArr.get( k );
//									idSign = objSign.get(6).toString( );
//									if(idSign != null && !idSign.equals("") && idSign.equals("seller")){
//										serial = objSign.get(1).toString( );
//										tencty = objSign.get(3).toString( );
//										result1 += "<CKS><CKSNguoiBan><Subject>" + tencty + "</Subject><Serial>" + serial + "</Serial></CKSNguoiBan></CKS>";
//									}else if(idSign != null && !idSign.equals("") && idSign.equals("buyer")){
//										serial = objSign.get(1).toString( );
//										tencty = objSign.get(3).toString( );
//										result1 += "<CKS><CKSNguoiMua><Subject>" + tencty + "</Subject><Serial>" + serial + "</Serial></CKSNguoiMua></CKS>";
//									}else if(idSign != null && !idSign.equals("") && idSign.equals("serSig")){
//										serial = objSign.get(1).toString( );
//										tencty = objSign.get(3).toString( );
//										resultCNCOM = ConstantValue.BEGIN_NODE_CNCOM + tencty + ConstantValue.END_NODE_CNCOM;
//									}else if(idSign != null && !idSign.equals("") && idSign.equals("vanSig")){
//										serial = objSign.get(1).toString( );
//										tencty = objSign.get(3).toString( );
//										resultCNTVAN = ConstantValue.BEGIN_NODE_CNTVAN + tencty + ConstantValue.END_NODE_CNTVAN;
//									}
//									if(result1 != null && !result1.equals("")){
//										result1 = result1.replaceAll("&", "&amp;");
//									}
//								}
//							}
//							
//							linkXMLB = "<?xml-stylesheet type=\"text/xsl\" href=\""+ pathXSL + sFileNameXSL+ "\"?>";
//							
//							result = fileContent;
//							if (oHoaDon.getLoaWS() == WebServiceType.WS_VNPT.getCode()) {// vnpt
//								result = StringUtils.substringBetween(result, ConstantValue.BEGIN_NODE_INVOICES_VNPT, ConstantValue.END_NODE_INVOICES_VNPT);
//								if (result.contains(ConstantValue.BEGIN_NODE_CNCOM)) {
//									result = result.replace(ConstantValue.BEGIN_NODE_CNCOM+ConstantValue.END_NODE_CNCOM, resultCNCOM);
//								}else if(result.contains(ConstantValue.END_NODE_CNCOM_)){
//									result = result.replace(ConstantValue.END_NODE_CNCOM_, resultCNCOM);
//								}else{
//									result = result.replace("</qrCodeData>", resultCNCOM);
//								}
//								if (result.contains(ConstantValue.BEGIN_NODE_CNTVAN)) {
//									result = result.replace(ConstantValue.BEGIN_NODE_CNTVAN+ConstantValue.END_NODE_CNTVAN, resultCNTVAN);
//								}else if(result.contains(ConstantValue.END_NODE_CNTVAN_)){
//									result = result.replace(ConstantValue.END_NODE_CNTVAN_, resultCNTVAN);
//								}else{
//									result = result.replace("</qrCodeData>", resultCNTVAN);
//								}
//								result = ConstantValue.BEGIN_NODE_INVOICES_VNPT + result + ConstantValue.END_NODE_INVOICES_VNPT;
//							}else{
//								result = StringUtils.substringBetween(result, ConstantValue.BEGIN_NODE_INVOICES, ConstantValue.END_NODE_INVOICES);
//								result = result.replace(ConstantValue.BEGIN_CDATA, "").replace(ConstantValue.END_CDATA, "");
//								result = result.replace(ConstantValue.BEGIN_NODE_INVOICE, ConstantValue.BEGIN_HTTP_NODE_INVOICE);
//								result = result.replace(ConstantValue.END_NODE_INVOICE, result1 + ConstantValue.END_NODE_INVOICE);
//							}
//							
//							result = linkXMLB + result;
//							model.addAttribute("noidung", result);
//							model.addAttribute( "showHD", true );
//							return new ModelAndView("download.certified.invoice");
//						}
//					}else{
//						status = "err.not.file";
//						return ModelView("error.certified.invoice", status);
//					}
//				}else{
//					status = "err.not.file";
//					return ModelView("error.certified.invoice", status);
//				}
//				status = "err.not.file";
//				return ModelView("error.certified.invoice", status);
//			} catch (Exception e) {
//				System.out.println("Caused by [viewDetailInvoice_TS24ID]: ");
//				e.printStackTrace();
//				status = "err.not.file";
//				return ModelView("error.certified.invoice", status);
//			}
//		}else{
//			return new ModelAndView(checkRole);
//		}
//	}
//	
//} 
