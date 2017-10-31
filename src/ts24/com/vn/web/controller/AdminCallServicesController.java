package ts24.com.vn.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ts24.com.vn.dal.dao.HddtTracuuDao;
import ts24.com.vn.dal.dao.HoaDonDao;
import ts24.com.vn.dal.dao.PhieuthuDao;
import ts24.com.vn.dal.dao.impl.HddtTracuuDaoImpl;
import ts24.com.vn.dal.dao.impl.HoaDonDaoImpl;
import ts24.com.vn.dal.dao.impl.PhieuthuDaoImpl;
import ts24.com.vn.dal.model.HddtTracuu;
import ts24.com.vn.dal.model.HoaDon;
import ts24.com.vn.dal.model.Phieuthu;
import ts24.com.vn.dal.model.nonentity.HoaDonView;
import ts24.com.vn.dal.model.nonentity.PhieuthuView;
import ts24.com.vn.model.AccountType;
import ts24.com.vn.model.CertifyStatus;
import ts24.com.vn.model.CertifyStatusResult;
import ts24.com.vn.model.GooglePojo;
import ts24.com.vn.model.InvoiceElectronicType;
import ts24.com.vn.model.LocaleType;
import ts24.com.vn.model.ObjResponse;
import ts24.com.vn.model.ObjUserProfile;
import ts24.com.vn.model.ObjUserProfileUpdate;
import ts24.com.vn.model.StatusSystem;
import ts24.com.vn.model.WebServiceType;
import ts24.com.vn.model.objSearchInvoice;
import ts24.com.vn.model.objSearchReceipt;
import ts24.com.vn.web.common.BreadCrumbConst;
import ts24.com.vn.web.common.LocaleCustomize;
import ts24.com.vn.web.common.SessionConst;
import ts24.com.vn.web.common.WebSession;
import ts24.com.vn.web.digisign.DigitalSignVerify;
import ts24.com.vn.web.model.datatable.JsonResultList;
import ts24.com.vn.web.model.datatable.ResultEnum;
import ts24.com.vn.web.util.ConstantValue;
import ts24.com.vn.web.util.FileManager;
import ts24.com.vn.web.util.Path;
import ts24.com.vn.web.util.ReadXMLFileUsingDom;
import ts24.com.vn.web.util.Utilities;
import ts24.com.vn.web.util.VerifyRecaptcha;
import ts24.com.vn.web.validator.LoginValidator;
import ts24.com.vn.web.validator.ValidatorUserInfoID;
import ts24.com.vn.web.validator.ValidatorUserUpdateInfoID;
import ts24.com.vn.web.validator.model.LoginView;
import ts24.com.vn.web.validator.model.UserInfoViewID;
import ts24.com.vn.ws.ts24id.TS24IDServiceRest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dummiesmind.breadcrumb.springmvc.annotations.Link;


@Controller
@RequestMapping(value = "/admincallservices")
@SessionAttributes({SessionConst.WEB_SESSION})
public class AdminCallServicesController {
	
	static String folderRoot = Path.PATH_DOWNLOAD;
	ReadXMLFileUsingDom readXML = new ReadXMLFileUsingDom();
	
	String linkTS24ID =Path.IDTS24REST_URL;
	String userAgent = Path.ID_AGENT;
	String passAgent = Path.PASS_AGENT;

	static String GOOGLE_CLIENT_ID = Path.GOOGLE_CLIENT_ID;
	static String GOOGLE_CLIENT_SECRET = Path.GOOGLE_CLIENT_SECRET;
	static String GOOGLE_REDIRECT_URI = Path.GOOGLE_REDIRECT_URI;
	static String FORCE = "force";
	static String CODE = "code";
	 
	Utilities util = new Utilities();
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	private MessageSource messageSource;
	
	private HoaDonDao hoaDonDao;
	private PhieuthuDao phieuthuDao;
	private HddtTracuuDao hddtTracuuDao;
	public AdminCallServicesController(){
		this.hoaDonDao = new HoaDonDaoImpl();
		this.phieuthuDao = new PhieuthuDaoImpl();
		this.hddtTracuuDao = new HddtTracuuDaoImpl();
		
	}
	
	private String getMessage(String code){
		return messageSource.getMessage(code, null, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
	}
	
	private static ObjResponse dataReponse( String code, String des, String desDetail, Object obj )
	{
		ObjResponse response = new ObjResponse();
		response.setCode(code);
		response.setDes(des);
		response.setDesDetail(desDetail);
		response.setObjResponse(obj);
		return response;
	}
	
	@RequestMapping(value="/logoutTS24ID.bv", method=RequestMethod.GET)
	public String logoutTS24ID(HttpServletRequest request, HttpSession session){
		try {
			WebSession webSession = new WebSession();
			webSession = (WebSession) session.getAttribute(SessionConst.WEB_SESSION);
			if(webSession != null){
				request.getSession().removeAttribute(SessionConst.WEB_SESSION);
				session.removeAttribute(SessionConst.WEB_SESSION);
				session.invalidate();
				webSession.setCurrentUser(null);
				webSession.setListRule(null);
				webSession.setShowFieldMaNhanHD(null);
				webSession.setUserInfoViewID(null);
				System.out.println("session.getMaxInactiveInterval(): "+ session.getMaxInactiveInterval());
				if(session.getMaxInactiveInterval() > 0){
					session = request.getSession(true);
					session.setAttribute(SessionConst.WEB_SESSION, webSession);
					session.invalidate();
					session.setMaxInactiveInterval(0);
				}
			}
			return "redirect:loginTS24ID.bv";
		} catch (Exception e) {
			System.out.println("Caused by [logoutTS24ID]: ");
			e.printStackTrace();
			return "redirect:loginTS24ID.bv";
		}
	}
	
	@Link(label=BreadCrumbConst.LOGIN_TS24ID, family=BreadCrumbConst.MAIN_FLOW, parent="")
	@RequestMapping(value="/loginTS24ID.bv", method=RequestMethod.GET)
	public ModelAndView loginTS24ID(){
		ModelAndView mv = new ModelAndView("loginTs24Id");
		mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
		return mv;
	}
	
	@Link(label=BreadCrumbConst.LOGIN_TS24ID, family=BreadCrumbConst.MAIN_FLOW, parent="")
	@RequestMapping(value="/loginTS24ID.bv", method=RequestMethod.POST)
	public ModelAndView loginTS24ID_Process(@ModelAttribute("loginTS24IDView") @Validated LoginView loginView, BindingResult bindingResult,
			 String username, String password, HttpSession session, HttpServletRequest req, HttpServletResponse res){
		String message = "";
		try {
			
			WebSession webSession = (WebSession) session.getAttribute(SessionConst.WEB_SESSION);
			if(webSession != null){
				req.getSession().removeAttribute(SessionConst.WEB_SESSION);
				session.removeAttribute(SessionConst.WEB_SESSION);
				session.invalidate();
			}
			/** Begin captcha version 2**/
			String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			if(!verify){
				ModelAndView mv = new ModelAndView("loginTs24Id");
				message = getMessage("error.captcha");
				mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
				mv.addObject("errorMessageCaptcha", message);
				return mv;
			}
			/** End captcha version 2**/
			
			ObjResponse response = null ;
			String tokenID = "" ,diaChi ="",duong = "",phuong ="",huyen = "", tinh="";
			ObjUserProfile  resultUser = new  ObjUserProfile();
			LoginValidator validator = new LoginValidator();
			validator.validate(loginView, bindingResult);
			if (!bindingResult.hasErrors()){
				if(password != null && !password.equals("")){
					password = Utilities.md5_md5(password);
				}
				response = checkLoginID(username,password,"ID");
				if(response != null && response.getCode().equals(StatusSystem.SUCCESSFULLY.getCode())){
					tokenID =response.getDesDetail(); 
					Object obj = response.getObjResponse();
					Gson gson= new Gson();
					String dataUser = gson.toJson(obj);
					resultUser = (ObjUserProfile)new Gson().fromJson(dataUser, ObjUserProfile.class);
				
					if(webSession == null){
						webSession = new WebSession();
					}
					// view thong tin user ra profile
					UserInfoViewID objUserView = new UserInfoViewID();
					if(resultUser != null){
					    Map<String, Object> models  = new HashMap<String, Object>();
					    try {
					    	if(resultUser.getStreet() != null && !resultUser.getStreet().equals("")){
					    		duong = resultUser.getStreet() + "," ;
					    	}
					    	if(resultUser.getPhuong() != null && !resultUser.getPhuong().equals("")){
					    		phuong = resultUser.getPhuong() +",";
					    	}
					    	if(resultUser.getQuanhuyen() != null && !resultUser.getQuanhuyen().equals("")){
					    		huyen = resultUser.getQuanhuyen()+",";
					    	}
					    	if(resultUser.getTinhtp() != null && !resultUser.getTinhtp().equals("")){
					    		tinh = resultUser.getTinhtp() ;
					    	}
					    	
					    	diaChi = duong + phuong +huyen + tinh;
					    	objUserView.setUsername(resultUser.getUid());
					    	objUserView.setDiachi(diaChi);
					    	objUserView.setEmail(resultUser.getMail());		
					    	objUserView.setFullname(resultUser.getFullname());
					    	objUserView.setTelnumber(resultUser.getTelephoneNumber());
					    	objUserView.setTokenId(tokenID);
							models.put("profileView", objUserView);
						    webSession.setUserInfoViewID(objUserView);
						    webSession.setCurrentUser(null);
							session = req.getSession(true);
							session.setAttribute(SessionConst.WEB_SESSION, webSession);
							
							ModelAndView view = new ModelAndView("redirect:profileTS24ID.bv");
							view =  new ModelAndView("profileTs24Id", models);
						    return view;
						} catch (Exception e) {
							System.out.println("Caused by [object resultUser function loginTS24ID_Process]: ");
							e.printStackTrace();
							ModelAndView mv = new ModelAndView("loginTs24Id");
							mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
							mv.addObject("errorMessage", e.getMessage());
							return mv;
						}	
					}else{
						ModelAndView mv = new ModelAndView("loginTs24Id");
						message = getMessage("error.login.unsuccess");
						mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
						mv.addObject("errorMessage", message);
						return mv;
					}	
				}else{
					if(response != null){
						message = response.getDes();
					}
					ModelAndView mv = new ModelAndView("loginTs24Id");
					mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
					mv.addObject("errorMessage", message);
					return mv;
				}
			}else{
				return loginTS24ID();
			}
		} catch (Exception e) {
			System.out.println("Caused by [loginTS24ID_Process]: ");
			e.printStackTrace();
			ModelAndView mv = new ModelAndView("loginTs24Id");
			mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
			mv.addObject("errorMessage", e.getMessage());
			return mv;
		}
	}
	
	@Link(label=BreadCrumbConst.HOME_ID, family=BreadCrumbConst.MAIN_FLOW, parent="")
	@RequestMapping(value="/profileTS24ID.bv", method=RequestMethod.GET)
	public ModelAndView profileTS24ID(HttpSession session, HttpServletRequest request){
		try {
			WebSession webSession = new WebSession();
			webSession = (WebSession) session.getAttribute(SessionConst.WEB_SESSION);
			if(webSession != null && webSession.getUserInfoViewID() != null 
					&& StringUtils.isNotEmpty(webSession.getUserInfoViewID().getUsername())){
				return new ModelAndView("profileTs24Id");
			}else{
				return loginTS24ID();	
			}
		} catch (Exception e) {
			e.printStackTrace();
			return loginTS24ID();	
		}
	}
	
	@Link(label=BreadCrumbConst.CREATE_ACCOUNT_TS24ID, family=BreadCrumbConst.MAIN_FLOW, parent="")
	@RequestMapping(value="/createAccountTS24ID.bv", method=RequestMethod.GET)
//	public String createAccountTS24ID(){
	public ModelAndView createAccountTS24ID(@ModelAttribute("createUserTS24IDView") @Validated UserInfoViewID userViewID, BindingResult bindingResult,
			 String email, String password, String confirmation, String fullname, String telnumber, HttpSession session, HttpServletRequest req){
		return new ModelAndView("createAccountTs24Id");
	}
	
	@Link(label=BreadCrumbConst.CREATE_ACCOUNT_TS24ID, family=BreadCrumbConst.MAIN_FLOW, parent="")
	@RequestMapping(value="/createAccountTS24ID.bv", method=RequestMethod.POST)
	public ModelAndView createAccountTS24ID_Process(@ModelAttribute("createUserTS24IDView") @Validated UserInfoViewID userViewID, BindingResult bindingResult,
			String username, String email, String password, String confirmation, String fullname, String telnumber, HttpSession session, HttpServletRequest req){
		String message = "", successMessage = "";
		try {
			ObjResponse response = null ;
			ValidatorUserInfoID validator = new ValidatorUserInfoID();
			validator.validate(userViewID, bindingResult);
			if (!bindingResult.hasErrors()){
				if(password != null && !password.equals("")){
					password = Utilities.md5_md5(password);
				}
				ObjUserProfile objProfile = new ObjUserProfile();
				objProfile.setUid(username);
				objProfile.setMail(email);
				objProfile.setFullname(fullname);
				objProfile.setTelephoneNumber(telnumber);
				objProfile.setUserPassword(password);
				
				response = createAccountID(objProfile);
				if(response != null && response.getCode().equals(StatusSystem.SUCCESSFULLY.getCode())){
				    ModelAndView mv =  new ModelAndView("loginTs24Id");
					mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
					successMessage = getMessage("success.create.userID");
					mv.addObject("successMessage", successMessage);
				    return mv;
				}else{
					if(response != null){
						message = response.getDes();
					}
					ModelAndView view = new ModelAndView("createAccountTs24Id");
					view.addObject("errorMessage", message);
					return view;
				}
			}else{
				ModelAndView view = new ModelAndView("createAccountTs24Id");
				view.addObject("errorMessage", message);
				return view;
			}
		} catch (Exception e) {
			System.out.println("Caused by [createAccountTS24ID_Process]: ");
			ModelAndView view = new ModelAndView("createAccountTs24Id");
			view.addObject("errorMessage", e.getMessage());
			return view;
		}
	}
	
	private ObjResponse checkLoginID( String userAccount, String passAccount, String option)
	{
		ObjResponse response = null;
		try {
			   String param = "?userAccount="+userAccount+"&passAccount="+passAccount+"&option="+option+"&userAgent="+userAgent+"&passAgent="+passAgent;
			   String issue = TS24IDServiceRest.postMethodData(linkTS24ID + "/loginAuthentication" + param, null);
			   response = (ObjResponse)new Gson().fromJson(issue, ObjResponse.class);
		} catch (Exception e) {
			System.out.println("Caused by [checkLoginID]: ");
			e.printStackTrace();
			return dataReponse(StatusSystem.ERR_CONNECTION_WEB_SERVICES.getCode(), StatusSystem.ERR_CONNECTION_WEB_SERVICES.getDes(), e.toString(), null);
		}
		return response;
	}
	
	private ObjResponse getAccountByTokenID( String tokenID)
	{
		ObjResponse response = null;
		try {
			   String param = "?tokenID="+tokenID+"&userAgent="+userAgent+"&passAgent="+passAgent;
			   String issue = TS24IDServiceRest.postMethodData(linkTS24ID+"/getAccountByTokenID" + param, null);						
			   response = (ObjResponse)new Gson().fromJson(issue, ObjResponse.class);			   			 
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
		
		return response;
	}
	
	private ObjResponse createAccountID( ObjUserProfile objUser)
	{
		ObjResponse response = null;
		try {
			   String param = "?userAgent="+userAgent+"&passAgent="+passAgent;		
				Gson gson = new Gson();
			    String data = gson.toJson(objUser);
			    String issue = TS24IDServiceRest.postMethodData(linkTS24ID+"/createProfile" + param, data);						
			    response = (ObjResponse)new Gson().fromJson(issue, ObjResponse.class);
			  } catch (Exception e) {
				  System.out.println("Caused by [createAccountID]: ");
				  e.printStackTrace();
				  return dataReponse(StatusSystem.ERR_CONNECTION_WEB_SERVICES.getCode(), StatusSystem.ERR_CONNECTION_WEB_SERVICES.getDes(), e.toString(), null);
			  }
		
		return response;
	}
	
	private ObjResponse checkLoginIDGoogle( ObjUserProfile objUser)
	{
		ObjResponse response = null;
		try {
		   String param = "?userAgent="+userAgent+"&passAgent="+passAgent;		
			Gson gson = new Gson();				
		    String data = gson.toJson(objUser);
		    String issue = TS24IDServiceRest.postMethodData(linkTS24ID+"/loginAuthenticationSocial" + param, data);						
		    response = (ObjResponse)new Gson().fromJson(issue, ObjResponse.class);
		  } catch (Exception e) {
			  System.out.println("Caused by [checkLoginIDGoogle]: ");
			  e.printStackTrace();
			  return dataReponse(StatusSystem.ERR_CONNECTION_WEB_SERVICES.getCode(), StatusSystem.ERR_CONNECTION_WEB_SERVICES.getDes(), e.toString(), null);
		  }
		return response;
	}
	
	@Link(label=BreadCrumbConst.VIEW_INVOICE, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME_ID)
	@RequestMapping(value = "/hoaDonTS24ID.bv", method = RequestMethod.GET)
	public ModelAndView hoaDonTS24ID(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
		try {
			Map<String, Object> models  = new HashMap<String, Object>();
			UserInfoViewID obj = webSession.getUserInfoViewID();
			if(obj != null && StringUtils.isNotEmpty(obj.getUsername())){
				ModelAndView mv = new ModelAndView("hoadondientu_ts24id_view", models);
				return mv;
			}else{
				return loginTS24ID();
			}
		} catch (Exception e) {
			System.out.println("Caused by: [hoaDonTS24ID]");
			e.printStackTrace();
			return loginTS24ID();
		}
	}
	
	@Link(label=BreadCrumbConst.VIEW_INVOICE, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME_ID)
	@RequestMapping(value = "/hoaDonTS24ID.bv", method = RequestMethod.POST)
	public @ResponseBody JsonResultList<HoaDonView> hoaDonDienTuTS24IDSearch(@ModelAttribute objSearchInvoice obj, int jtStartIndex, int jtPageSize, String jtSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
		JsonResultList<HoaDonView> listResponse = new JsonResultList<HoaDonView>();
		try {
			
			String tuNgay="",denNgay = ""; 
			tuNgay = obj.getTuNgay();
			denNgay = obj.getDenNgay();
			if(tuNgay != null && !tuNgay.equals("")){
				tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
			}
			if(denNgay != null && !denNgay.equals("")){
				denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
			}
			UserInfoViewID objUser = webSession.getUserInfoViewID();
			if(objUser == null){
				listResponse.setRecords(null);
				listResponse.setResult(ResultEnum.OK);
				listResponse.setTotalRecordsCount(0);
			}
			List<HddtTracuu> listId = null;
			if (obj.getMaNhanHDon() != null && !obj.getMaNhanHDon().equals("")) {
				listId = hddtTracuuDao.searchHddtTracuu(objUser.getUsername(), obj.getMaNhanHDon());
			}else{
				listId = hddtTracuuDao.searchHddtTracuu(objUser.getUsername(),"");
			}
			String idHDLimit = formatSelectINfromArray(listId);
//			ArrayList<HoaDonView> listRT = new ArrayList<HoaDonView>();
//			if(listId != null && listId.size() > 0){
//				for (HddtTracuu hddtTracuu : listId) {
//					obj.setMaNhanHDon(hddtTracuu.getIdhoaDon());
//					List<HoaDonView> listViews = hoaDonDao.getHoaDonByMaNhanHD(obj.getMaNhanHDon(),tuNgay, denNgay, jtSorting, jtStartIndex, jtPageSize);
//					if(listViews != null && listViews.size() > 0){
//						for (HoaDonView hoaDonView : listViews) {
//							listRT.add(hoaDonView);
//						}
//					}
//				}
//			}
			if(idHDLimit == null || idHDLimit.equals("")){
				List<HoaDonView> listViews = null;
				listResponse.setRecords(listViews);
				listResponse.setResult(ResultEnum.OK);
				listResponse.setTotalRecordsCount(0);
			}else{
				List<HoaDonView> listViews = hoaDonDao.getHoaDonByMaNhanHD(idHDLimit ,tuNgay, denNgay, jtSorting, jtStartIndex, jtPageSize);
				listResponse.setRecords(listViews);
				listResponse.setResult(ResultEnum.OK);
				listResponse.setTotalRecordsCount(hoaDonDao.getCountAllHoaDon(idHDLimit, tuNgay, denNgay));
			}
			
		} catch (Exception e) {
			System.out.println("Caused by [hoaDonDienTuTS24IDSearch]: ");
			e.printStackTrace();
			listResponse.setResult(ResultEnum.ERROR);
			listResponse.setMessage(e.getMessage());
		}  
		return listResponse;
	}
	
	@RequestMapping(value = "/viewDialog.bv", method = RequestMethod.GET)
	public ModelAndView viewDialog(@ModelAttribute objSearchInvoice obj, String id, Model model, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
		try {
			String filePath = "", fileContent = "";
			 
			HoaDon oHoaDon = hoaDonDao.getObjByID(id);
			String checkHD = checkHoaDon(oHoaDon);
			if (checkHD != null && checkHD.equals("true")) {
				filePath = folderRoot + oHoaDon.getFileHDon();
			}
			if (Utilities.checkExitsFile(filePath) == 1) {
				fileContent = FileManager.readFileToString( filePath );
				if(StringUtils.isNotEmpty(fileContent)){
					String loaiHDView = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_LOAIHDVIEW, ConstantValue.END_NODE_LOAIHDVIEW);
					if(StringUtils.isNotEmpty(loaiHDView)){
						model.addAttribute("LoaiHDView", loaiHDView);
					}else if(fileContent.contains("inv:LoaiHDView")){
						model.addAttribute("LoaiHDView", "1");
					}else{
						model.addAttribute("LoaiHDView", "0");
					}
					model.addAttribute("idInvoiceDetail", id);
				}else{
					return ModelView("error.certified.invoice", "err.not.file");
				}
			}else{
				return ModelView("error.certified.invoice", "err.not.file");
			}
			return new ModelAndView("view.dialog.admin");
		} catch (Exception e) {
			System.out.println("Caused by [viewDialog]: ");
			e.printStackTrace();
			return ModelView("error.certified.invoice", "err.not.file");
		}  
	}
	  
	@SuppressWarnings({"rawtypes", "static-access"})
	@RequestMapping(value="/detailHoaDonTS24ID.bv", method = RequestMethod.GET)
	public ModelAndView detailHoaDonTS24ID(Model model, String id, String loaiHD, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
		String status = "";
		try {
			 String filePath = "", fileContent = "", mauIn = "", sFileNameXSL = "", linkXMLB = "", result="";
			 String pathWebapp = Path.URL_WEBAPP, pathXSL = "";
			 DigitalSignVerify digiVerify = new DigitalSignVerify( );
			 String tencty = "", serial = "";
			 
			HoaDon oHoaDon = hoaDonDao.getObjByID(id);
			if(oHoaDon == null){
				status = "err.not.file";
				return ModelView("error.certified.invoice", status);
			}
//				String checkHD = checkHoaDon(oHoaDon);
//				if (checkHD != null && checkHD.equals("true")) {
//					filePath = folderRoot + oHoaDon.getFileHDon();
//				}else{
//					status = checkHD;
//					return ModelView("error.certified.invoice", status);
//				}
			
			if (oHoaDon.getLoaWS() == WebServiceType.WS_VNPT.getCode()) {
				filePath += folderRoot + ConstantValue.PATH_HDDT_DAKY + oHoaDon.getFileHDon();
			}else{
				filePath += folderRoot + oHoaDon.getFileHDon();
			}
			if (Utilities.checkExitsFile(filePath) == 1) {
				String pathStyleSheet = "", pathFileXSL = "";
				fileContent = FileManager.readFileToString( filePath );
				if(StringUtils.isNotEmpty(fileContent)){
					mauIn = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_MAUIN, ConstantValue.END_NODE_MAUIN);
					if(StringUtils.isEmpty(mauIn)){
						mauIn = ConstantValue.DEFAULT_TEMPLATE_KEY_NAME_XSL; // default template using old xml not contain node <inv:MauIn> 
					}
					// Lay Node <StyleSheet>  theo <MaToKhai> tu file [StyleSheet] (ten file xsl)
					if (oHoaDon.getLoaWS() == WebServiceType.WS_VNPT.getCode()) {// vnpt
						mauIn = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_MAUIN_VNPT, ConstantValue.END_NODE_MAUIN_VNPT);							
						if(StringUtils.isEmpty(mauIn)){
						mauIn = ConstantValue.TEMPLATE_VNPT;
						}
						pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL_VNPT;
						pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL_VNPT;
						pathXSL = Path.URL_XSLT_VNPT;
					}else if(oHoaDon.getLoaWS() == WebServiceType.WS_HOSPITAL.getCode()){ // benh vien
						pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL_HOSPITAL;
						pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL_HOSPITAL;
						pathXSL = Path.URL_XSL_BV;
					}else{
						pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL;
						pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL;
						pathXSL = Path.URL_XSL;
					}
					File fileStyleSheet = new File( pathStyleSheet);
					sFileNameXSL = readXML.ReadXMLFileStyleSheet( fileStyleSheet, mauIn );
					if(Utilities.checkExitsFile( pathFileXSL + sFileNameXSL ) == 1 ){
						String infoSign = "";
						if (oHoaDon.getLoaWS() == WebServiceType.WS_VNPT.getCode()) {
							String lid = "serSig,vanSig";
							String[] listId = lid.split(",");
							infoSign = digiVerify.getInfoXmlSignatureXpathHDDT_Vnpt(filePath, listId);
						}else{
							infoSign = digiVerify.getInfoXmlSignatureXpathHDDT_BenhVien(  filePath );
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
						if (oHoaDon.getLoaWS() == WebServiceType.WS_VNPT.getCode()) {// vnpt
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
						model.addAttribute( "showHD", true );
						return new ModelAndView("download.certified.invoice");
					}
				}else{
					status = "err.not.file";
					return ModelView("error.certified.invoice", status);
				}
			}else{
				status = "err.not.file";
				return ModelView("error.certified.invoice", status);
			}
			status = "err.not.file";
			return ModelView("error.certified.invoice", status);
		} catch (Exception e) {
			System.out.println("Caused by [viewDetailInvoice_TS24ID]: ");
			e.printStackTrace();
			status = "err.not.file";
			return ModelView("error.certified.invoice", status);
		}
	}
  
	private ModelAndView ModelView(String view, String keyMessage) {
		String message;
		ModelAndView mvErr = new ModelAndView(view);
		if(StringUtils.isNotEmpty(keyMessage)){
			message = getMessage(keyMessage);
			mvErr.addObject("errorMessage", message);
		}
		return mvErr;
	}
	  private String checkHoaDon(HoaDon oHoaDon) {
			String status = "",flag = "";
			if(oHoaDon == null){
				status = StatusSystem.ERR_NOT_EXISTS_IDHOADON.getDes();
			//LoaWS -> 1: WS Bênh Viện; 2: WS HDDT TS24; 3: WS HDDT VNPT
			}else if(oHoaDon.getLoaWS() == WebServiceType.WS_VNPT.getCode()){
				if(oHoaDon.getLoaiHDon() == InvoiceElectronicType.XAC_THUC_VNPT.getCode()){
					if(StringUtils.isEmpty(oHoaDon.getStatus()) &&  CertifyStatus.CERTIFY_WAIT.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
						status = "err.certificate.vnpt.pending.send";
					}else if(StringUtils.isEmpty(oHoaDon.getStatus()) && CertifyStatus.CERTIFY_NOT_YET.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
						status = "err.certificate.vnpt.not.send";
					}else if(CertifyStatusResult.STATUS_03.getCode().equals(oHoaDon.getStatus())){
						status = "err.certificate.vnpt.send";
					}else if(StringUtils.isNotEmpty(oHoaDon.getFKey()) && StringUtils.isNotEmpty(oHoaDon.getBarCode())){
						flag = "true";
					}else flag = "false";
				}else if(oHoaDon.getLoaiHDon() == InvoiceElectronicType.HOADON_VNPT.getCode()){
					flag = "true";
				}else flag = "false"; // hien tai VNPT chi co hoa don chong gia, ko co dien tu nen set false
			}else if(oHoaDon.getLoaWS() == WebServiceType.WS_TS24.getCode() || oHoaDon.getLoaWS() == WebServiceType.WS_HOSPITAL.getCode()){
				//LoaiHDon -> 1: Điện tử; 2: Xác thực; 3: HDDT-Xác thực chống giả (Điện tử xác thực)
				if(oHoaDon.getLoaiHDon() == InvoiceElectronicType.XAC_THUC.getCode()){
					// TinhTrangXacThuc -> 0: Chưa xác thực; 1: Đã xác thực; -1 Lỗi xác thực; 2: Đang gửi xác thực
					if(CertifyStatus.CERTIFY_WAIT.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
						status = "err.wait.cetificated";
					}else if(CertifyStatus.CERTIFY_NOT_YET.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
						status = "err.not.yet.cetificated";
					}else if(CertifyStatus.CERTIFY_ERROR.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
						status = "err.cetificated";
					}else if(CertifyStatus.CERTIFY_SUCCESS.getStatusTypeId() == oHoaDon.getTinhTrangXacThuc()){
						flag = "true";
					}else flag = "false";
				}else flag = "true"; 
			}else flag = "true";
			if (status != null && !status.equals("")) {
				return status;
			}else if (flag != null && !flag.equals("")) {
				return flag;
			}else{
				return "err.system";
			}
	}

	@Link(label=BreadCrumbConst.UPDATE_ACCOUNT_ID, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME_ID)
	@RequestMapping(value="/changeInfoAccountTS24ID.bv", method=RequestMethod.GET)
	public ModelAndView changeInfoAccountTS24ID(@ModelAttribute("changeInfoUserTS24IDView") @Validated UserInfoViewID userViewID, BindingResult bindingResult, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
		try {
			Map<String, Object> models  = new HashMap<String, Object>();
			UserInfoViewID obj = webSession.getUserInfoViewID();
			if(obj != null && StringUtils.isNotEmpty(obj.getUsername())){
				models.put("changeInfoUserTS24IDView", obj);
				return new ModelAndView("changeInfoAccountTs24Id", models);
			}else{
				return loginTS24ID();
			}
		} catch (Exception e) {
			System.out.println("Caused by [changeInfoAccountTS24ID]: ");
			e.printStackTrace();
			return loginTS24ID();
		}
	}
	
	@Link(label=BreadCrumbConst.UPDATE_ACCOUNT_ID, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME_ID)
	@RequestMapping(value="/changeInfoAccountTS24ID.bv", method=RequestMethod.POST)
	public ModelAndView changeInfoAccountTS24IDProcess(@ModelAttribute("changeInfoUserTS24IDView") @Validated UserInfoViewID userViewID, BindingResult bindingResult, 
			String checkchangepassword, HttpSession session, HttpServletRequest req, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
		ModelAndView view = new ModelAndView("changeInfoAccountTs24Id");
		String message = "", errorMessagePassword="", successMessagePassword="", successMessage = "", newPassword="", oldPassword="";
		try {
			ValidatorUserUpdateInfoID validator = new ValidatorUserUpdateInfoID();
			validator.validate(userViewID, bindingResult);
			if (!bindingResult.hasErrors()){
				UserInfoViewID userIDSession  = webSession.getUserInfoViewID();
				if(userIDSession == null ){
					message = getMessage("error.session.null");
					view.addObject("errorMessage", message);
					return view;
				}
				if(StringUtils.isNotEmpty(userViewID.getCheckchangepassword()) && userViewID.getCheckchangepassword().equals("on")){
					newPassword = userViewID.getPassword();
					oldPassword = userViewID.getOldpassword();
					if(StringUtils.isNotEmpty(newPassword)){
						newPassword = Utilities.md5_md5(newPassword);
					}
					if(StringUtils.isNotEmpty(oldPassword)){
						oldPassword = Utilities.md5_md5(oldPassword);
					}
					ObjResponse response = changePassword(userIDSession.getUsername(), oldPassword, newPassword);
					if(response != null && response.getCode().equals(StatusSystem.SUCCESSFULLY.getCode())){
						successMessagePassword = getMessage("success.changepass");
						view.addObject("successMessagePassword", successMessagePassword);
					}else{
						if(StringUtils.isNotEmpty(response.getDes())){
							errorMessagePassword = response.getDes();
						}
						view.addObject("errorMessagePassword", errorMessagePassword);
					}
				}
				
				ObjUserProfileUpdate profileUpdate = new ObjUserProfileUpdate();
				profileUpdate.setUid(userIDSession.getUsername());
				profileUpdate.setMail(userViewID.getEmail());
				profileUpdate.setFullname(userViewID.getFullname());
				profileUpdate.setTelephoneNumber(userViewID.getTelnumber());
				
				ObjResponse responseUpdate = updateProfile(profileUpdate, userIDSession.getTokenId());
				if(responseUpdate != null && responseUpdate.getCode().equals(StatusSystem.SUCCESSFULLY.getCode())){
					successMessage = getMessage("success.update.account");
					view.addObject("successMessage", successMessage);
					userIDSession.setEmail(profileUpdate.getEmailAddress());
					userIDSession.setFullname(profileUpdate.getFullname());
					userIDSession.setTelnumber(profileUpdate.getTelephoneNumber());
					webSession.setUserInfoViewID(userIDSession);
				}else{
					if(StringUtils.isNotEmpty(responseUpdate.getDes())){
						message = responseUpdate.getDes();
					}
					view.addObject("errorMessage", message);
				}
			}
		} catch (Exception e) {
			System.out.println("Caused by [changeInfoAccountTS24IDProcess]: ");
			e.printStackTrace();
			view.addObject("errorMessage", e.getMessage());
		}
		return view;
	}
	
	private ObjResponse changePassword( String userAccount, String oldPassword, String newPassword)
	{
		ObjResponse response = null;
		try {
			   String param = "?userAccount="+userAccount+"&oldPassword="+oldPassword+"&newPassword="+newPassword+"&userAgent="+userAgent+"&passAgent="+passAgent;
			   String issue = TS24IDServiceRest.postMethodData(linkTS24ID + "/changePass" + param, null);
			   response = (ObjResponse)new Gson().fromJson(issue, ObjResponse.class);
		} catch (Exception e) {
			System.out.println("Caused by [changePassword]: ");
			e.printStackTrace();
			return dataReponse(StatusSystem.ERR_CONNECTION_WEB_SERVICES.getCode(), StatusSystem.ERR_CONNECTION_WEB_SERVICES.getDes(), e.toString(), null);
		}
		return response;
	}
	
	private ObjResponse updateProfile(ObjUserProfileUpdate objUserProfileUpdate, String tokenID)
	{
		ObjResponse response = null;
		String data = "";
		try {
			Gson gson = new Gson();
			if(objUserProfileUpdate != null){
				data = gson.toJson(objUserProfileUpdate);
			}
		   String param = "?tokenID="+tokenID+ "&userAgent="+userAgent+"&passAgent="+passAgent;
		   String issue = TS24IDServiceRest.postMethodData(linkTS24ID + "/updateProfile" + param, data);
		   response = (ObjResponse)new Gson().fromJson(issue, ObjResponse.class);
		} catch (Exception e) {
			System.out.println("Caused by [updateProfile]: ");
			e.printStackTrace();
			return dataReponse(StatusSystem.ERR_CONNECTION_WEB_SERVICES.getCode(), StatusSystem.ERR_CONNECTION_WEB_SERVICES.getDes(), e.toString(), null);
		}
		return response;
	}
	
	/*@RequestMapping(value="/loginTS24IDGoogle.bv", method=RequestMethod.GET)
	public String checkLoginIDGoogle(){
		String googleLoginUrl = "";
		try {
			googleLoginUrl = "https://accounts.google.com/o/oauth2/auth?" 
				+ "scope=email"
				+ "&redirect_uri=" + URLEncoder.encode(GOOGLE_REDIRECT_URI, "UTF-8")
				+ "&response_type=" + CODE
				+ "&client_id=" + GOOGLE_CLIENT_ID
				+ "&approval_prompt=" + FORCE;
		} catch (UnsupportedEncodingException e) {
			System.out.println("Caused by [checkLoginIDGoogle]: ");
			e.printStackTrace();
		}
		return googleLoginUrl;
	}*/
	
	@RequestMapping(value="/loginTS24IDGoogle.bv", method=RequestMethod.GET)
	public ModelAndView checkLoginIDGoogle_Process(HttpSession session, HttpServletRequest req){
		String message = "";
		try {
			WebSession webSession = (WebSession) session.getAttribute(SessionConst.WEB_SESSION);
			if(webSession != null){
				req.getSession().removeAttribute(SessionConst.WEB_SESSION);
				session.removeAttribute(SessionConst.WEB_SESSION);
				session.invalidate();
			}
			ObjResponse response = null ;
			String tokenID = "" ,diaChi ="",duong = "",phuong ="",huyen = "", tinh="";
			ObjUserProfile  resultUser = new  ObjUserProfile();
			//https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Ftracuu.xhd%2Fadmincallservices%2FloginTS24IDGoogle.bve&response_type=code&client_id=226127222416-mctn01qh05sgnbbn7fgfa5erqbic1svf.apps.googleusercontent.com&approval_prompt=force
			  // get code
            String code = req.getParameter("code");
            // format parameters to post
            String urlParameters = "code=" + code
                    + "&client_id=" + GOOGLE_CLIENT_ID
                    + "&client_secret=" + GOOGLE_CLIENT_SECRET
                    + "&redirect_uri=" + GOOGLE_REDIRECT_URI
                    + "&grant_type=authorization_code";
            //post parameters
            URL url = new URL("https://accounts.google.com/o/oauth2/token");
            URLConnection urlConn = url.openConnection();
            urlConn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
            writer.write(urlParameters);
            writer.flush();
            
            //get output in outputString 
            String line, outputString = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
                        
            System.out.println(outputString);            
            //get Access Token 
            JsonObject json = (JsonObject)new JsonParser().parse(outputString);
                                  
            String access_token = json.get("access_token").getAsString();
            System.out.println(access_token);
            
            url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + access_token);
            urlConn = url.openConnection();
            outputString = "";
            reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
            System.out.println(outputString);
            
            // Convert JSON response into Pojo class
            GooglePojo data = new Gson().fromJson(outputString, GooglePojo.class);
            System.out.println(data);
            writer.close();
            reader.close();
			//==============
			ObjUserProfile objProfile = new ObjUserProfile();
			objProfile.setUid(data.getEmail());
			objProfile.setMail(data.getEmail());
			objProfile.setFullname(data.getName());
			objProfile.setTokenSocial(access_token);
			objProfile.setAccounttype(AccountType.ACCOUNT_GOOGLE.getCode());
			
			response = checkLoginIDGoogle(objProfile);
			if(response != null && response.getCode().equals(StatusSystem.SUCCESSFULLY.getCode())){
				tokenID =response.getDesDetail(); 
				Object obj = response.getObjResponse();
				Gson gson= new Gson();
				String dataUser = gson.toJson(obj);
				resultUser = (ObjUserProfile)new Gson().fromJson(dataUser, ObjUserProfile.class);
			
				if(webSession == null){
					webSession = new WebSession();
				}
				UserInfoViewID objUserView = new UserInfoViewID();
				if(resultUser != null){
				// ModelAndView view = new ModelAndView("danhsachnguoidung");
				    Map<String, Object> models  = new HashMap<String, Object>();
				    try {
				    	if(resultUser.getStreet() != null && !resultUser.getStreet().equals("")){
				    		duong = resultUser.getStreet() + "," ;
				    	}
				    	if(resultUser.getPhuong() != null && !resultUser.getPhuong().equals("")){
				    		phuong = resultUser.getPhuong() +",";
				    	}
				    	if(resultUser.getQuanhuyen() != null && !resultUser.getQuanhuyen().equals("")){
				    		huyen = resultUser.getQuanhuyen()+",";
				    	}
				    	if(resultUser.getTinhtp() != null && !resultUser.getTinhtp().equals("")){
				    		tinh = resultUser.getTinhtp() ;
				    	}
				    	
				    	diaChi = duong + phuong +huyen + tinh;
				    	objUserView.setUsername(resultUser.getUid());
				    	objUserView.setDiachi(diaChi);
				    	objUserView.setEmail(resultUser.getMail());		
				    	objUserView.setFullname(resultUser.getFullname());
				    	objUserView.setTelnumber(resultUser.getTelephoneNumber());
				    	objUserView.setTokenId(tokenID);
						models.put("profileView", objUserView);
					    webSession.setUserInfoViewID(objUserView);
					    webSession.setCurrentUser(null);
					    session = req.getSession(true);
						session.setAttribute(SessionConst.WEB_SESSION, webSession);
						ModelAndView view = new ModelAndView("redirect:loginTS24ID.bv");
						view =  new ModelAndView("profileTs24Id", models);
					    return view;
					} catch (Exception e) {
						System.out.println("Caused by: ");
						e.printStackTrace();
						ModelAndView mv = new ModelAndView("loginTs24Id");
						mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
						mv.addObject("errorMessage", e.getMessage());
						return mv;
					}	
				}else{
					ModelAndView mv = new ModelAndView("loginTs24Id");
					message = getMessage("error.login.unsuccess");
					mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
					mv.addObject("errorMessage", message);
					return mv;
				}		
			}else{
				if(response != null){
					message = response.getDes();
					if(StringUtils.isNotEmpty(response.getDesDetail())){
						message = message + " {"+ response.getDesDetail() +"}";
					}
				}
				ModelAndView mv = new ModelAndView("loginTs24Id");
				mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
				mv.addObject("errorMessage", message);
				return mv;
			}
		} catch (Exception e) {
			System.out.println("Caused by [checkLoginIDGoogle_Process]: ");
			e.printStackTrace();
			ModelAndView mv = new ModelAndView("loginTs24Id");
			mv.addObject("GOOGLE_CAPTCHA_PUBLICKEY", Path.GOOGLE_CAPTCHA_PUBLICKEY);
			mv.addObject("errorMessage", e.getMessage());
			return mv;
		}
	}
	
	@Link(label=BreadCrumbConst.VIEW_RECEIPT, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME_ID)
	@RequestMapping(value = "/receiptTS24ID.bv", method = RequestMethod.GET)
	public ModelAndView receiptTS24ID(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
		try {
			Map<String, Object> models  = new HashMap<String, Object>();
			UserInfoViewID obj = webSession.getUserInfoViewID();
			if(obj != null && StringUtils.isNotEmpty(obj.getUsername())){
				ModelAndView mv = new ModelAndView("phieuthudientu_ts24id_view", models);
				return mv;
			}else{
				return loginTS24ID();
			}
		} catch (Exception e) {
			System.out.println("Caused by: [receiptTS24ID]");
			e.printStackTrace();
			return loginTS24ID();
		}
	}
	
	@Link(label=BreadCrumbConst.VIEW_INVOICE, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME_ID)
	@RequestMapping(value = "/receiptTS24ID.bv", method = RequestMethod.POST)
	public @ResponseBody JsonResultList<PhieuthuView> phieuthuDienTuTS24IDSearch(@ModelAttribute objSearchReceipt obj, int jtStartIndex, int jtPageSize, String jtSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
		JsonResultList<PhieuthuView> listResponse = new JsonResultList<PhieuthuView>();
		try {
			
			String tuNgay="",denNgay = ""; 
			tuNgay = obj.getTuNgay();
			denNgay = obj.getDenNgay();
			if(tuNgay != null && !tuNgay.equals("")){
				tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
			}
			if(denNgay != null && !denNgay.equals("")){
				denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
			}
			UserInfoViewID objUser = webSession.getUserInfoViewID();
			if(objUser == null){
				listResponse.setRecords(null);
				listResponse.setResult(ResultEnum.OK);
				listResponse.setTotalRecordsCount(0);
			}
			List<HddtTracuu> listId = null;
			if (obj.getMaTraCuu() != null && !obj.getMaTraCuu().equals("")) {
				listId = hddtTracuuDao.searchHddtTracuu(objUser.getUsername(), obj.getMaTraCuu());
			}else{
				listId = hddtTracuuDao.searchHddtTracuu(objUser.getUsername(),"");
			}
			String idHDLimit = formatSelectINfromArray(listId);
			
			
//			ArrayList<PhieuthuView> listRT = new ArrayList<PhieuthuView>();
//			if(listId != null && listId.size() > 0){
//				for (HddtTracuu hddtTracuu : listId) {
//					obj.setMaTraCuu(hddtTracuu.getIdhoaDon());
//					List<PhieuthuView> listViews = phieuthuDao.getPhieuThuByMaTraCuu(obj.getMaTraCuu(),tuNgay, denNgay, jtSorting, jtStartIndex, jtPageSize);
//					if(listViews != null && listViews.size() > 0){
//						for (PhieuthuView phieuthuView : listViews) {
//							listRT.add(phieuthuView);
//						}
//					}
//				}
//			}
			List<PhieuthuView> listViews = phieuthuDao.getPhieuThuByMaTraCuu(idHDLimit ,tuNgay, denNgay, jtSorting, jtStartIndex, jtPageSize);
			listResponse.setRecords(listViews);
			listResponse.setResult(ResultEnum.OK);
			listResponse.setTotalRecordsCount(phieuthuDao.getCountAllPhieuThu(idHDLimit, tuNgay, denNgay));
			
		} catch (Exception e) {
			System.out.println("Caused by [phieuthuDienTuTS24IDSearch]: ");
			e.printStackTrace();
			listResponse.setResult(ResultEnum.ERROR);
			listResponse.setMessage(e.getMessage());
		}  
		return listResponse;
	}
	
	@RequestMapping(value = "/viewReceiptDialog.bv", method = RequestMethod.GET)
	public ModelAndView viewReceiptDialog(@ModelAttribute objSearchReceipt obj, String id, Model model, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
		try {
			String filePath = "", fileContent = "";
			 
			Phieuthu oPhieuthu = phieuthuDao.getObjByID(id);
			filePath = folderRoot + oPhieuthu.getFileHdon();
			if (Utilities.checkExitsFile(filePath) == 1) {
				fileContent = FileManager.readFileToString( filePath );
				if(StringUtils.isNotEmpty(fileContent)){
					String loaiHDView = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_LOAIHDVIEW, ConstantValue.END_NODE_LOAIHDVIEW);
					if(StringUtils.isNotEmpty(loaiHDView)){
						model.addAttribute("LoaiHDView", loaiHDView);
					}else if(fileContent.contains("inv:LoaiHDView")){
						model.addAttribute("LoaiHDView", "1");
					}else{
						model.addAttribute("LoaiHDView", "0");
					}
					model.addAttribute("idInvoiceDetail", id);
				}else{
					return ModelView("error.certified.invoice", "err.not.file");
				}
			}else{
				return ModelView("error.certified.invoice", "err.not.file");
			}
			return new ModelAndView("view.receipt.dialog.admin");
		} catch (Exception e) {
			System.out.println("Caused by [viewReceiptDialog]: ");
			e.printStackTrace();
			return ModelView("error.certified.invoice", "err.not.file");
		}  
	}
	
	@SuppressWarnings({"rawtypes", "static-access"})
	@RequestMapping(value="/detailPhieuThuTS24ID.bv", method = RequestMethod.GET)
	public ModelAndView detailPhieuThuTS24ID(Model model, String id, String loaiHD, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
		String status = "";
		try {
			 String filePath = "", fileContent = "", mauIn = "", sFileNameXSL = "", linkXMLB = "", result="";
			 String pathWebapp = Path.URL_WEBAPP, pathXSL = "";
			 DigitalSignVerify digiVerify = new DigitalSignVerify( );
			 String tencty = "", serial = "";
			 
			Phieuthu oPhieuthu = phieuthuDao.getObjByID(id);
			if(oPhieuthu == null){
				status = "err.not.file";
				return ModelView("error.certified.invoice", status);
			}
			
			filePath += folderRoot + oPhieuthu.getFileHdon();
			if (Utilities.checkExitsFile(filePath) == 1) {
				String pathStyleSheet = "", pathFileXSL = "";
				fileContent = FileManager.readFileToString( filePath );
				if(StringUtils.isNotEmpty(fileContent)){
					mauIn = StringUtils.substringBetween(fileContent, ConstantValue.BEGIN_NODE_MAUIN, ConstantValue.END_NODE_MAUIN);
					if(StringUtils.isEmpty(mauIn)){
						mauIn = ConstantValue.DEFAULT_TEMPLATE_KEY_NAME_XSL; // default template using old xml not contain node <inv:MauIn> 
					}
					pathStyleSheet = pathWebapp + ConstantValue.FOLDER_XML + ConstantValue.TEMPLATE_NAME_XSL_RECEIPT;
					pathFileXSL = pathWebapp + ConstantValue.FOLDER_XSL;
					pathXSL = Path.URL_XSL;
					File fileStyleSheet = new File( pathStyleSheet);
					sFileNameXSL = readXML.ReadXMLFileStyleSheet( fileStyleSheet, mauIn );
					if(Utilities.checkExitsFile( pathFileXSL + sFileNameXSL ) == 1 ){
						String infoSign = "";
						infoSign = digiVerify.getInfoXmlSignatureXpathHDDT_BenhVien(  filePath );
							
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
						result = StringUtils.substringBetween(result, ConstantValue.BEGIN_NODE_INVOICES, ConstantValue.END_NODE_INVOICES);
						result = result.replace(ConstantValue.BEGIN_CDATA, "").replace(ConstantValue.END_CDATA, "");
						result = result.replace(ConstantValue.BEGIN_NODE_INVOICE, ConstantValue.BEGIN_HTTP_NODE_INVOICE);
						result = result.replace(ConstantValue.END_NODE_INVOICE, result1 + ConstantValue.END_NODE_INVOICE);
						
						result = linkXMLB + result;
						model.addAttribute("noidung", result);
						model.addAttribute( "showHD", true );
						return new ModelAndView("download.certified.receipt");
					}
				}else{
					status = "err.not.file";
					return ModelView("error.certified.invoice", status);
				}
			}else{
				status = "err.not.file";
				return ModelView("error.certified.invoice", status);
			}
			status = "err.not.file";
			return ModelView("error.certified.invoice", status);
		} catch (Exception e) {
			System.out.println("Caused by [viewDetailInvoice_TS24ID]: ");
			e.printStackTrace();
			status = "err.not.file";
			return ModelView("error.certified.invoice", status);
		}
	}
	
	private String formatSelectINfromArray(List<HddtTracuu> listId){
		try {
			if(listId == null || listId.size() == 0){
				return null;
			}else if(listId.size() == 1){
				return listId.get(0).getIdhoaDon();
			}
			String result = "";
			for (HddtTracuu hddtTracuu : listId) {
				result += "'" + hddtTracuu.getIdhoaDon() + "',";
			}
			if(StringUtils.isNotEmpty(result)){
				result = result.substring(1, result.length()-2);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
} 
