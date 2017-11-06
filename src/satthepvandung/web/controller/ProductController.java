package satthepvandung.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ts24.com.vn.dal.dao.HoaDonDao;
import ts24.com.vn.dal.dao.PhieuthuDao;
import ts24.com.vn.dal.dao.impl.HoaDonDaoImpl;
import ts24.com.vn.dal.dao.impl.PhieuthuDaoImpl;
import ts24.com.vn.dal.model.Phieuthu;
import ts24.com.vn.dal.model.ThongTinCongTy;
import ts24.com.vn.model.InvoiceForm;
import ts24.com.vn.model.ReceiptForm;
import ts24.com.vn.web.common.LocaleCustomize;
import ts24.com.vn.web.common.LocaleType;
import ts24.com.vn.web.digisign.DigitalSignVerify;
import ts24.com.vn.web.util.Commons;
import ts24.com.vn.web.util.ConstantValue;
import ts24.com.vn.web.util.FileManager;
import ts24.com.vn.web.util.Path;
import ts24.com.vn.web.util.ReadXMLFileUsingDom;
import ts24.com.vn.web.util.Utilities;

@Controller
@RequestMapping(value = "/sanpham")
public class ProductController {
	
	private HoaDonDao hoaDonDao;
	private PhieuthuDao phieuthuDao;
	
	public ReceiptController(){
		this.hoaDonDao = new HoaDonDaoImpl();
		this.phieuthuDao = new PhieuthuDaoImpl();
	}
	
	@Autowired
	private MessageSource messageSource;
	
	FileManager fmg = new FileManager();
	Commons coms = new Commons();
	Utilities util = new Utilities();
	ReadXMLFileUsingDom readXML = new ReadXMLFileUsingDom();
	static String folderRoot = Path.PATH_DOWNLOAD;
	
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.setIgnoreInvalidFields(true);
		binder.setIgnoreUnknownFields(true);
	}
	
	@RequestMapping(value = "/searchReceipt.bv")
	public ModelAndView init(@ModelAttribute("searchReceiptForm") ReceiptForm form,
			Model model, HttpSession session, HttpServletRequest req) {
		String key = "";
		key = "KEY";
		if((key != null && !key.equals(""))){
			key = key.trim();
			if(checkAccessKey(key) != null){
				form.setAccessKey(key);
				model.addAttribute("searchReceiptForm", form);
				model.addAttribute(ConstantValue.ACCESSKEY, key);
				return new ModelAndView("search.receipt");
			}else return new ModelAndView("access.denied");
		}else return new ModelAndView("access.denied");
	}

	private ThongTinCongTy checkAccessKey(String key){
		try {
			ThongTinCongTy obj = new ThongTinCongTy();
			return obj;
		} catch (Exception e) {
			System.out.println("Caused by");
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/searchReceipt.bv", method = RequestMethod.POST)
	public ModelAndView listreceipt_process(@ModelAttribute("searchReceiptForm") ReceiptForm form, Model model, HttpSession session, HttpServletRequest req) {
		String accessKey = "";
		try {
			String filePath = "", maTraCuu = "", soPhieuThu = "", ngayThuTien = "", status = "", keyViewBrowseFile="", mstNguoiBan = "";
			ThongTinCongTy octy = null;
			// set value null for condion check fileNameBrowse
			form.setFileNameBrowse(null);
			keyViewBrowseFile = req.getParameter("keyViewBrowseFile");
			if(keyViewBrowseFile != null && !keyViewBrowseFile.equals("") && keyViewBrowseFile.equals("showViewBrowseFile")){
				// process
				String fileName_org = "";
				String fileName = "", xmlFilePath = "";
				boolean check_file = false;
				String fileContent = "";
				
				accessKey = form.getAccessKey();
				/** Begin upload file **/
				MultipartHttpServletRequest multipartRequest = ( MultipartHttpServletRequest ) req;
				/**
				 * BEGIN UPLOAD FILE XML
				 * **/
				String FolderHS = Path.PATH_DOWNLOAD;
				String destinationDir = FolderHS + ConstantValue.XHD_TEMP_PATH + ConstantValue.FOLDER_BROWSE_FILE;
				MultipartFile file = multipartRequest.getFile( "fileHS" );
				fileName_org = file.getOriginalFilename( );
				/** check file extension valid **/
				check_file = coms.checkValidFileUpload_File_XML( fileName_org );
				if ( !check_file ){
					model.addAttribute( "statusViewBrowseFileHD", -1 ); // file not validate
					model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
					model.addAttribute("searchReceiptForm", form);
//					return new ModelAndView( "error.certified.receipt" );
					form.setFileNameBrowse(null);
					return new ModelAndView("search.receipt");
				}else{
					fileName_org = fileName_org + ConstantValue.MINUS + util.randomXref_tenFile() + ConstantValue.XML_EXTEND_FILE ;
					fileName = fileName_org;

					// check path exists
					File path = new File( destinationDir );
					if ( !path.exists( ) ){
						path.mkdirs( );
					}
					xmlFilePath = destinationDir + fileName;
					File destination = new File( xmlFilePath );
					file.transferTo( destination );
					if (Utilities.checkExitsFile(xmlFilePath) == 1) {
						
						fileContent = FileManager.readFileToString( xmlFilePath );
						if(fileContent.contains("inv:LoaiHDView")){
							model.addAttribute("LoaiHDView", "1");
						}else{
							model.addAttribute("LoaiHDView", "0");
						}
						String LoaiHDView = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_LOAIHDVIEW, ConstantValue.END_NODE_LOAIHDVIEW);
						model.addAttribute("LoaiHDView", LoaiHDView);
						model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
						model.addAttribute("searchReceiptForm", form);
						form.setFileNameBrowse(fileName);
						model.addAttribute("showBtnPrintFile", "show");
						return new ModelAndView("search.receipt");
					}else{
						model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
						model.addAttribute("searchReceiptForm", form);
						form.setFileNameBrowse(null);
						model.addAttribute( "statusViewBrowseFileHD", -99 ); // not validate data
						return new ModelAndView("search.receipt");
					}
				} // end view file invoice from browse 
			}else{
				maTraCuu = form.getMaTraCuu();
				if(maTraCuu != null && !maTraCuu.equals("")){
					maTraCuu = maTraCuu.trim();
				}else{
					
					soPhieuThu = form.getSoPhieuThu();
					if(soPhieuThu != null && !soPhieuThu.equals(""))
						soPhieuThu = soPhieuThu.trim();
					
					ngayThuTien = form.getNgayThuTien();
					if(ngayThuTien != null && !ngayThuTien.equals(""))
						ngayThuTien = Utilities.formatNgaySo4(ngayThuTien.replaceAll("/", ConstantValue.MINUS));
					
					mstNguoiBan = form.getMstNguoiBan();
					if(mstNguoiBan != null && !mstNguoiBan.equals(""))
						mstNguoiBan = mstNguoiBan.trim();
				}
				accessKey = form.getAccessKey();
				
				/** CHECK ACCESS KEY*/
				octy = checkAccessKey(accessKey); 
				if(octy != null){
					return searchReceipt(form, model, accessKey, filePath, maTraCuu, soPhieuThu, ngayThuTien, status, mstNguoiBan);
				}else return new ModelAndView("access.denied");
			}
		} catch (Exception e) {
			System.out.println("Caused by");
			e.printStackTrace();
			model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
			model.addAttribute("searchReceiptForm", form);
			return new ModelAndView("search.receipt");
		}
	}

	private ModelAndView searchReceipt(ReceiptForm form, Model model, String accessKey, String filePath, String maTraCuu, String soPhieuThu, String ngayThuTien, String status, String mstNguoiBan) {
		try {
			if ( (maTraCuu != null && !maTraCuu.equals("")) || (soPhieuThu != null && !soPhieuThu.equals("") && ngayThuTien != null && !ngayThuTien.equals("") )) {
				ReceiptForm objSearch = new ReceiptForm();
				objSearch.setMaTraCuu(maTraCuu);
				objSearch.setSoPhieuThu(soPhieuThu);
				objSearch.setNgayThuTien(ngayThuTien);
				objSearch.setMstNguoiBan(mstNguoiBan);
				// set key equals key configuration then allow search all invoice
				objSearch.setAccessKey(accessKey);
				
				Phieuthu oPTSearch = phieuthuDao.searchReceipt(objSearch);
				if(oPTSearch == null){
					status = "err.not.exists.receipt";
					model.addAttribute("searchResult", "");
					return ErrorModelView("search.receipt", status);
				}
				filePath += folderRoot + oPTSearch.getFileHdon();
				model.addAttribute( "hdcd", "0");
				model.addAttribute("searchResult", oPTSearch);
			} else {// ket thuc search
				status = "err.not.exists.receipt";
				model.addAttribute("searchResult", "");
			}
			
			model.addAttribute("hideLoading", "yes");
			model.addAttribute("status", status);
			model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
			model.addAttribute("searchReceiptForm", form);
			return ErrorModelView("search.receipt", status);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/download_file_xml.bv", method = RequestMethod.POST)
	public ModelAndView download_file_xml(@ModelAttribute("searchReceiptForm") ReceiptForm form, Model model, HttpSession session, HttpServletRequest req,
			HttpServletResponse response) {
		String accessKey = "";
		try {
			accessKey = form.getAccessKey();
			/** CHECK ACCESS KEY*/
			if(checkAccessKey(accessKey) != null){
				String filePath = "";
				
				// Get id from Form
				Phieuthu oPhieuThu = phieuthuDao.getObjByID(form.getId());
				if(oPhieuThu != null){
					filePath += folderRoot + oPhieuThu.getFileHdon();
					File myfile = new File(filePath);
					if(Utilities.checkExitsFile(filePath) == 1){ // kiem tra file co hay khong? 1:ton tai
						try {
							BufferedInputStream buf = null;
							ServletOutputStream myOut = null;
							// set response headers
							response.setContentType("application/xml");
							response.addHeader("Content-Disposition", "attachment; filename=" + Utilities.getFileNameByFilePath(filePath));
							response.setContentLength((int) myfile.length());
							
							myOut = response.getOutputStream();
							FileInputStream input = new FileInputStream(myfile);
							buf = new BufferedInputStream(input);
							int readBytes = 0;
							
							// read from the file; write to the ServletOutputStream
							while ((readBytes = buf.read()) != -1)
								myOut.write(readBytes);
							myOut.close();
							buf.close();
							return null ;
						} catch (Exception e) {
							System.out.print("------ GET FILE [XML RECEIPT ROOT] ERROR:");
							e.printStackTrace();
							model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
							model.addAttribute("searchReceiptForm", form);
							return new ModelAndView("search.receipt");
						}
					} // end check file
				}
			}
			model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
			model.addAttribute("searchReceiptForm", form);
			return new ModelAndView("search.receipt");
		} catch (Exception e) {
			System.out.println("Caused by");
			e.printStackTrace();
			model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
			model.addAttribute("searchReceiptForm", form);
			return new ModelAndView("search.receipt");
		}
	}
	
	@SuppressWarnings({"rawtypes", "static-access"})
	@RequestMapping(value = "/view_certified_receipt.bv")
	public ModelAndView view_certified_receipt(@ModelAttribute("searchReceiptForm") ReceiptForm form, Model model, HttpSession session, HttpServletRequest req, HttpServletResponse response) {
		String accessKey = "";
		String filePath = "", fileContent = "", linkXMLB = "", result="";
		String tencty = "", serial = "";
		String sFileNameXSL = "", pathXSL = "", pathWebapp = Path.URL_WEBAPP, mauIn = "";
		try {
			DigitalSignVerify digiVerify = new DigitalSignVerify( );
			
			accessKey = req.getParameter(ConstantValue.KEY);
			Phieuthu oPhieuThu = phieuthuDao.getObjByID(form.getId());
			if(oPhieuThu != null){
				filePath += folderRoot + oPhieuThu.getFileHdon();
				if(checkAccessKey(accessKey) != null){
					model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
					model.addAttribute("searchReceiptForm", form);
					if (Utilities.checkExitsFile(filePath) == 1) {
						fileContent = FileManager.readFileToString( filePath );
						String pathStyleSheet = "", pathFileXSL = "";
						if(StringUtils.isNotEmpty(fileContent)){
							mauIn = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_MAUIN, ConstantValue.END_NODE_MAUIN);
							if(StringUtils.isEmpty(mauIn)){
								mauIn = ConstantValue.DEFAULT_TEMPLATE_KEY_NAME_XSL; // default template using old xml not contain node <inv:MauIn> 
							}
							pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL_RECEIPT;
							pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL;
							pathXSL = Path.URL_XSLT_RECEIPT;
							File fileStyleSheet = new File( pathStyleSheet);
							sFileNameXSL = readXML.ReadXMLFileStyleSheet( fileStyleSheet, mauIn );
						}else{
							model.addAttribute( "statusViewHD", -3 ); // not found key, content
							model.addAttribute( "showHD", false );
							return new ModelAndView( "error.certified.receipt" );
						}
						
						if(Utilities.checkExitsFile( pathFileXSL + sFileNameXSL ) == 1 ){
							String infoSign = "";
//							if (oHoaDon.getLoaWS() == WebServiceType.WS_VNPT.getCode()) {
//								String lid = "serSig,vanSig";
//								String[] listId = lid.split(",");
//								infoSign = digiVerify.getInfoXmlSignatureXpathHDDT_Vnpt(filePath, listId);
//							}else{
								infoSign = digiVerify.getInfoXmlSignatureXpathHDDT_BenhVien(  filePath );
//							}
							ArrayList sigArr = Utilities.getThongTinCKS(infoSign);
							String result1 = "", idSign = "", resultCNCOM = "", resultCNTVAN = "";
							if(sigArr != null && sigArr.size() > 0){
								result1 = "";
								for(int k = 0 ;k <sigArr.size() ; k ++){
									ArrayList objSign = ( ArrayList ) sigArr.get( k );
									idSign = objSign.get(6).toString( );
									if(idSign != null && !idSign.equals("") && idSign.equals("seller")){
										serial = objSign.get(1).toString( );
										tencty = objSign.get(3).toString( );
										result1 += "<CKS><CKSNguoiBan><Subject>" + tencty + "</Subject><Serial>" + serial + "</Serial></CKSNguoiBan></CKS>";
									}else if(idSign != null && !idSign.equals("") && idSign.equals("buyer")){
										serial = objSign.get(1).toString( );
										tencty = objSign.get(3).toString( );
										result1 += "<CKS><CKSNguoiMua><Subject>" + tencty + "</Subject><Serial>" + serial + "</Serial></CKSNguoiMua></CKS>";
									}else if(idSign != null && !idSign.equals("") && idSign.equals("serSig")){
										serial = objSign.get(1).toString( );
										tencty = objSign.get(3).toString( );
										resultCNCOM = ConstantValue.BEGIN_NODE_CNCOM + tencty + ConstantValue.END_NODE_CNCOM;
									}else if(idSign != null && !idSign.equals("") && idSign.equals("vanSig")){
										serial = objSign.get(1).toString( );
										tencty = objSign.get(3).toString( );
										resultCNTVAN = ConstantValue.BEGIN_NODE_CNTVAN + tencty + ConstantValue.END_NODE_CNTVAN;
									}
									if(result1 != null && !result1.equals("")){
										result1 = result1.replaceAll("&", "&amp;");
									}
								}
							}
							
							linkXMLB = "<?xml-stylesheet type=\"text/xsl\" href=\""+ pathXSL + sFileNameXSL+ "\"?>";
							
							result = fileContent;
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
								result = StringUtils.substringBetween(result, ConstantValue.BEGIN_NODE_INVOICES, ConstantValue.END_NODE_INVOICES);
								result = result.replace(ConstantValue.BEGIN_CDATA, "").replace(ConstantValue.END_CDATA, "");
								result = result.replace(ConstantValue.BEGIN_NODE_INVOICE, ConstantValue.BEGIN_HTTP_NODE_INVOICE);
								result = result.replace(ConstantValue.END_NODE_INVOICE, result1 + ConstantValue.END_NODE_INVOICE);
//							}
							
							result = linkXMLB + result;
							model.addAttribute("noidung", result);
							model.addAttribute( "showHD", true );
							return new ModelAndView("download.certified.receipt");
						}else{
							model.addAttribute( "statusViewHD", -1 ); // not found xsl file
							model.addAttribute( "showHD", false );
							return new ModelAndView( "error.certified.receipt" );
						}
					}else{
						model.addAttribute( "statusViewHD", -2 ); // not found xml file
						model.addAttribute( "showHD", false );
						return new ModelAndView( "error.certified.receipt" );
					}
				}else { 
					model.addAttribute( "statusViewHD", -3 ); // not found key, content
					model.addAttribute( "showHD", false );
					return new ModelAndView( "error.certified.receipt" );
				}
			}
			
			model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
			model.addAttribute( "statusViewHD", -99 ); // error
			model.addAttribute("searchReceiptForm", form);
			return new ModelAndView( "error.certified.receipt");
		} catch (Exception e) {
			System.out.println("Caused by");
			e.printStackTrace();
			model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
			model.addAttribute("searchReceiptForm", form);
			model.addAttribute( "statusViewHD", -99 ); // error
			return new ModelAndView( "error.certified.receipt");
		}
	}
	
	@SuppressWarnings({"rawtypes", "static-access"})
	@RequestMapping( value = "/show_browse_file_receipt.bv")
	public ModelAndView show_browse_file_receipt( @ModelAttribute( "searchReceiptForm" ) InvoiceForm form, Model model, HttpSession session, HttpServletRequest req ) throws IOException
	{
		String accessKey = "";
		String fileName = "", xmlFilePath = "";
		try{
			// process
			String fileContent = "", linkXMLB = "", result="", tencty = "", serial = "";
			String sFileNameXSL = "", pathXSL = "", pathWebapp = Path.URL_WEBAPP, mauIn = "";
			DigitalSignVerify digiVerify = new DigitalSignVerify( );
			
			accessKey = req.getParameter(ConstantValue.KEY);
			fileName= form.getFileNameBrowse();
			String destinationDir = Path.PATH_DOWNLOAD + ConstantValue.XHD_TEMP_PATH + ConstantValue.FOLDER_BROWSE_FILE;
			xmlFilePath = destinationDir + fileName;
			if (Utilities.checkExitsFile(xmlFilePath) == 1) {
				fileContent = FileManager.readFileToString( xmlFilePath );
				String pathStyleSheet = "", pathFileXSL = "", benNhanTra = "";
				if (!fileContent.contains(ConstantValue.END_NODE_TVANDATA)) {
					if(fileContent == null || fileContent.equals("") || !fileContent.contains(ConstantValue.BEGIN_NODE_INVOICES) || !fileContent.contains(ConstantValue.BEGIN_NODE_INVOICETYPE)){
						model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
						model.addAttribute("searchReceiptForm", form);
						model.addAttribute( "statusViewBrowseFileHD", -99 ); // not validate data
						// delele file invoice temp
						fmg.DeleteFiles(xmlFilePath);
						return new ModelAndView( "error.certified.receipt" );
					}
				}
				if(StringUtils.isNotEmpty(fileContent)){
					mauIn = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_MAUIN, ConstantValue.END_NODE_MAUIN);
					benNhanTra = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_BENHNHANTRA, ConstantValue.END_NODE_BENHNHANTRA);
					if(StringUtils.isEmpty(mauIn)){
						mauIn = ConstantValue.DEFAULT_TEMPLATE_KEY_NAME_XSL; // default template using old xml not contain node <inv:MauIn> 
					}
					
					// Lay Node <StyleSheet>  theo <MaToKhai> tu file [StyleSheet] (ten file xsl)
					if (fileContent.contains(ConstantValue.END_NODE_TVANDATA)) {// vnpt
						mauIn = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_MAUIN_VNPT, ConstantValue.END_NODE_MAUIN_VNPT);							
						
						if(StringUtils.isEmpty(mauIn)){
						mauIn = ConstantValue.TEMPLATE_VNPT;
						}
						pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL_VNPT;
						pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL_VNPT;
						pathXSL = Path.URL_XSLT_VNPT;
					}else if(!StringUtils.isEmpty(benNhanTra) && !benNhanTra.equals("null")){ // benh vien
						pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL_HOSPITAL;
//						pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL + ConstantValue.FOLDER_XSL_HOSPITAL;
//						pathXSL = ConstantValue.FOLDER_PARENT + ConstantValue.FOLDER_XSL_HOSPITAL;
						pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL_HOSPITAL;
						pathXSL = Path.URL_XSL_BV;
					}else{
						pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL;
						pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL;
						pathXSL = Path.URL_XSL;
					}
					File fileStyleSheet = new File( pathStyleSheet);
					sFileNameXSL = readXML.ReadXMLFileStyleSheet( fileStyleSheet, mauIn );
				}else{
					model.addAttribute( "statusViewHD", -3 ); // not found key, content
					model.addAttribute( "showHD", false );
					return new ModelAndView( "error.certified.receipt" );
				}
				
				if(Utilities.checkExitsFile( pathFileXSL + sFileNameXSL ) == 1 ){
					String infoSign = "";
					if (fileContent.contains(ConstantValue.END_NODE_TVANDATA)) {// vnpt
						String lid = "serSig,vanSig";
						String[] listId = lid.split(",");
						infoSign = digiVerify.getInfoXmlSignatureXpathHDDT_Vnpt(xmlFilePath, listId);
					}else{
						infoSign = digiVerify.getInfoXmlSignatureXpathHDDT_BenhVien(  xmlFilePath );
					}
					ArrayList sigArr = Utilities.getThongTinCKS(infoSign);
					String result1 = "", idSign = "", resultCNCOM = "", resultCNTVAN = "";
					if(sigArr != null && sigArr.size() > 0){
						result1 = "";
						for(int k = 0 ;k <sigArr.size() ; k ++){
							ArrayList objSign = ( ArrayList ) sigArr.get( k );
							idSign = objSign.get(6).toString( );
							if(idSign != null && !idSign.equals("") && idSign.equals("seller")){
								serial = objSign.get(1).toString( );
								tencty = objSign.get(3).toString( );
								result1 += "<CKS><CKSNguoiBan><Subject>" + tencty + "</Subject><Serial>" + serial + "</Serial></CKSNguoiBan></CKS>";
							}else if(idSign != null && !idSign.equals("") && idSign.equals("buyer")){
								serial = objSign.get(1).toString( );
								tencty = objSign.get(3).toString( );
								result1 += "<CKS><CKSNguoiMua><Subject>" + tencty + "</Subject><Serial>" + serial + "</Serial></CKSNguoiMua></CKS>";
							}else if(idSign != null && !idSign.equals("") && idSign.equals("serSig")){
								serial = objSign.get(1).toString( );
								tencty = objSign.get(3).toString( );
								resultCNCOM = ConstantValue.BEGIN_NODE_CNCOM + tencty + ConstantValue.END_NODE_CNCOM;
							}else if(idSign != null && !idSign.equals("") && idSign.equals("vanSig")){
								serial = objSign.get(1).toString( );
								tencty = objSign.get(3).toString( );
								resultCNTVAN = ConstantValue.BEGIN_NODE_CNTVAN + tencty + ConstantValue.END_NODE_CNTVAN;
							}
							if(result1 != null && !result1.equals("")){
								result1 = result1.replaceAll("&", "&amp;");
							}
						}
					}
					
					linkXMLB = "<?xml-stylesheet type=\"text/xsl\" href=\""+ pathXSL + sFileNameXSL+ "\"?>";
					
					result = fileContent;
					if (fileContent.contains(ConstantValue.END_NODE_TVANDATA)) {// vnpt
						result = StringUtils.substringBetween(result, ConstantValue.BEGIN_NODE_INVOICES_VNPT, ConstantValue.END_NODE_INVOICES_VNPT);
						if (result.contains(ConstantValue.BEGIN_NODE_CNCOM)) {
							result = result.replace(ConstantValue.BEGIN_NODE_CNCOM+ConstantValue.END_NODE_CNCOM, resultCNCOM);
						}else if(result.contains(ConstantValue.END_NODE_CNCOM_)){
							result = result.replace(ConstantValue.END_NODE_CNCOM_, resultCNCOM);
						}else{
							result = result.replace("</qrCodeData>", resultCNCOM);
						}
						if (result.contains(ConstantValue.BEGIN_NODE_CNTVAN)) {
							result = result.replace(ConstantValue.BEGIN_NODE_CNTVAN+ConstantValue.END_NODE_CNTVAN, resultCNTVAN);
						}else if(result.contains(ConstantValue.END_NODE_CNTVAN_)){
							result = result.replace(ConstantValue.END_NODE_CNTVAN_, resultCNTVAN);
						}else{
							result = result.replace("</qrCodeData>", resultCNTVAN);
						}
						result = ConstantValue.BEGIN_NODE_INVOICES_VNPT + result + ConstantValue.END_NODE_INVOICES_VNPT;
					}else{
						result = StringUtils.substringBetween(result, ConstantValue.BEGIN_NODE_INVOICES, ConstantValue.END_NODE_INVOICES);
						result = result.replace(ConstantValue.BEGIN_CDATA, "").replace(ConstantValue.END_CDATA, "");
						result = result.replace(ConstantValue.BEGIN_NODE_INVOICE, ConstantValue.BEGIN_HTTP_NODE_INVOICE);
						result = result.replace(ConstantValue.END_NODE_INVOICE, result1 + ConstantValue.END_NODE_INVOICE);
					}
					
					result = linkXMLB + result;
					
					model.addAttribute("noidung", result);
					// delele file invoice temp
					fmg.DeleteFiles(xmlFilePath);
					return new ModelAndView("download.certified.receipt");
				}
			}
			model.addAttribute( "statusViewBrowseFileHD", -2 ); // not view file
			model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
			model.addAttribute("searchReceiptForm", form);
			// delele file invoice temp
			fmg.DeleteFiles(xmlFilePath);
			return new ModelAndView( "error.certified.receipt" );
		}catch ( Exception e ){
			System.out.println("Caused by");
			e.printStackTrace();
			model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
			model.addAttribute("searchReceiptForm", form);
			form.setFileNameBrowse(null);
			model.addAttribute( "statusViewBrowseFileHD", -99 ); // not validate data
			// delele file invoice temp
			fmg.DeleteFiles(xmlFilePath);
			return new ModelAndView( "error.certified.receipt" );
		}
	}
	
	@RequestMapping(value = "/hoadon_mail.bv", method = RequestMethod.GET)
	public ModelAndView hoadon_mail_process(@ModelAttribute("searchReceiptForm") ReceiptForm form, Model model, HttpSession session, HttpServletRequest req) {
		try {
			String filePath = "", maTraCuu = "", soPhieuThu = "", ngayThuTien = "", status = "", accessKey = "", mstNguoiBan="";
			ThongTinCongTy octy = null;
			maTraCuu = req.getParameter("maTraCuu");
			if(maTraCuu != null && !maTraCuu.equals("")){
				maTraCuu = maTraCuu.trim();
			}else{
				soPhieuThu = form.getSoPhieuThu();
				if(soPhieuThu != null && !soPhieuThu.equals(""))
					soPhieuThu =soPhieuThu.trim();
				
				ngayThuTien = form.getNgayThuTien();
				if(ngayThuTien != null && !ngayThuTien.equals(""))
					ngayThuTien = Utilities.formatNgaySo4(ngayThuTien.replaceAll("/", ConstantValue.MINUS));
				
				mstNguoiBan = form.getMstNguoiBan();
				if(mstNguoiBan != null && !mstNguoiBan.equals(""))
					mstNguoiBan = mstNguoiBan.trim();
			}
			accessKey = form.getAccessKey();
			
			/** CHECK ACCESS KEY*/
			octy = checkAccessKey(accessKey); 
			if(octy != null){
				return searchReceipt(form, model, accessKey, filePath, maTraCuu, soPhieuThu, ngayThuTien, status, mstNguoiBan);
			}else return new ModelAndView("access.denied");
			
		} catch (Exception e) {
			System.out.println("Caused by");
			e.printStackTrace();
			model.addAttribute(ConstantValue.ACCESSKEY, "");
			model.addAttribute("searchReceiptForm", form);
			return new ModelAndView("search.receipt");
		}
	}
	
	private String getMessage(String code){
		return messageSource.getMessage(code, null, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
	}
	
	private ModelAndView ErrorModelView(String view, String keyMessage) {
		String message;
		ModelAndView mvErr = new ModelAndView(view);
		if(StringUtils.isNotEmpty(keyMessage)){
			message = getMessage(keyMessage);
			mvErr.addObject("errorMessage", message);
		}
		return mvErr;
	}
}