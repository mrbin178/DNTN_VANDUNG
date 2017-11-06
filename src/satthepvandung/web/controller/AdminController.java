//package satthepvandung.web.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.MessageSource;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.servlet.ModelAndView;
//
//import ts24.com.vn.dal.dao.HddtGioihanHienthiDAO;
//import ts24.com.vn.dal.dao.HoaDonDao;
//import ts24.com.vn.dal.dao.impl.HddtGioihanHienthiDAOImpl;
//import ts24.com.vn.dal.dao.impl.HoaDonDaoImpl;
//import ts24.com.vn.dal.model.CoQuanThue;
//import ts24.com.vn.dal.model.HddtGioihanHienthi;
//import ts24.com.vn.dal.model.LoginAdmin;
//import ts24.com.vn.dal.model.nonentity.HddtGioiHanHienThiView;
//import ts24.com.vn.dal.model.nonentity.UserView;
//import ts24.com.vn.dal.util.UtilitiesDAL;
//import ts24.com.vn.model.AuthenticationModel;
//import ts24.com.vn.model.FormLockMst;
//import ts24.com.vn.model.FormUserInfo;
//import ts24.com.vn.model.LocaleType;
//import ts24.com.vn.model.UserInfo;
//import ts24.com.vn.model.objKhoaMst;
//import ts24.com.vn.model.objSearchUser;
//import ts24.com.vn.service.CoQuanThueService;
//import ts24.com.vn.service.LoginAdminService;
//import ts24.com.vn.service.NhomQuyenService;
//import ts24.com.vn.service.UserRuleService;
//import ts24.com.vn.service.impl.CoQuanThueServiceImpl;
//import ts24.com.vn.service.impl.LoginAdminServiceImpl;
//import ts24.com.vn.service.impl.NhomQuyenServiceImpl;
//import ts24.com.vn.service.impl.UserRuleServiceImpl;
//import ts24.com.vn.web.common.BreadCrumbConst;
//import ts24.com.vn.web.common.LocaleCustomize;
//import ts24.com.vn.web.common.SessionConst;
//import ts24.com.vn.web.common.ValueConst;
//import ts24.com.vn.web.common.WebSession;
//import ts24.com.vn.web.model.datatable.JsonResult;
//import ts24.com.vn.web.model.datatable.JsonResultBase;
//import ts24.com.vn.web.model.datatable.JsonResultList;
//import ts24.com.vn.web.model.datatable.ResultEnum;
//import ts24.com.vn.web.util.ConstantValue;
//import ts24.com.vn.web.util.Utilities;
//import ts24.com.vn.web.validator.ValidatorMatKhau;
//import ts24.com.vn.web.validator.ValidatorUserInfo;
//import ts24.com.vn.web.validator.model.MatKhauInfoView;
//import ts24.com.vn.web.validator.model.UserInfoView;
//import dummiesmind.breadcrumb.springmvc.annotations.Link;
//
//
//@Controller
//@RequestMapping(value = "/admin")
//@SessionAttributes({SessionConst.WEB_SESSION})
//public class AdminController {
//	
//	private static final Logger logger = Logger.getLogger(AdminController.class);
//	private LoginAdminService loginAdminService;
//	private CoQuanThueService coquanthueService;
//	private NhomQuyenService nhomQuyenService ;
//	private UserRuleService userRuleService ;
//	private AuthenticationModel authenticationModel;
//	
//	private HddtGioihanHienthiDAO hddtGioihanHienthiDAO;
//	private HoaDonDao hoaDonDao;
//	
//	
//	Utilities util = new Utilities();
//	UtilitiesDAL utilsDal = new UtilitiesDAL();
//	@Autowired
//	private ApplicationContext appContext;
//	@Autowired
//	 private MessageSource messageSource;
//	public AdminController(){
//		this.loginAdminService = new LoginAdminServiceImpl();
//		this.coquanthueService = new CoQuanThueServiceImpl();
//		this.nhomQuyenService = new NhomQuyenServiceImpl();
//		this.userRuleService = new UserRuleServiceImpl();
//		this.authenticationModel = new AuthenticationModel();
//		
//		this.hddtGioihanHienthiDAO = new HddtGioihanHienthiDAOImpl();
//		this.hoaDonDao = new HoaDonDaoImpl();
//		
//	}
//	
//	private String getMessage(String code){
//		return messageSource.getMessage(code, null, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
//	}
//	
//	//-------------------
//	@Link(label=BreadCrumbConst.SEARCH_TAIKHOAN, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/danhsachnguoidung.bv", method = RequestMethod.GET)
//	public ModelAndView getDanhSachNguoiDung(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//	//	ModelAndView view = new ModelAndView("danhsachnguoidung");
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		//checkQuyen = CheckUserRule(quyen,webSession);
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		if(checkQuyen.equals("1")){
//			Map<String, Object> models  = new HashMap<String, Object>();
//			UserInfo objUser = webSession.getCurrentUser();			
//			int role = objUser.getRole();
//			models.put("role", role);
//			ModelAndView mv = new ModelAndView("danhsachnguoidung", models);
//			return mv;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}		
//		//return view;
//	}
//	//=================
//	
//	@Link(label=BreadCrumbConst.SEARCH_TAIKHOAN, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/danhsachnguoidung.bv", method = RequestMethod.POST)
//	public @ResponseBody JsonResultList<UserView> getDanhSachNguoiDungView(@ModelAttribute objSearchUser objUser,int jtStartIndex, int jtPageSize, String jtSorting, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		logger.info("Loading data for project table");
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		
//	//		Set<UserRole> userGroup = DemoUtils.convertToUserRoles(AuthenticationUtilities.getCurrentUser().getAuthorities());
//			JsonResultList<UserView> listResponse = new JsonResultList<UserView>();
//			if(checkQuyen.equals("1")){
//			try {
//				String userLogin = "" ,maCQ = "";
//				UserInfo objUserLogin = webSession.getCurrentUser();			
//				int role = objUserLogin.getRole();
//				userLogin = objUserLogin.getUsername();
//				maCQ = objUserLogin.getMacqt();
//				List<UserView> views = loginAdminService.getAllDanhSachNguoiDung(objUser, userLogin, maCQ, role, jtSorting, jtStartIndex, jtPageSize);
//	//			List<ProjectView> views = projectDAO.getAllProjects(jtSorting, jtStartIndex, jtPageSize);
//				listResponse.setRecords(views);
//				
//				listResponse.setResult(ResultEnum.OK);
//				listResponse.setTotalRecordsCount(loginAdminService.countDanhSachNguoiDung(objUser,userLogin,maCQ,role));
//			} catch (Exception e) {
//				System.out.println("Caused by");
//				e.printStackTrace();
//				listResponse.setResult(ResultEnum.ERROR);
//				listResponse.setMessage(e.getMessage());
//			}  
//			return listResponse;
//		}else{
//			
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.rule.permission"));
//			return listResponse;
//		}
//	}
//	
//	
//	 @Link(label=BreadCrumbConst.VIEW_USER, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	 @RequestMapping(value="/viewUser.bv", method = RequestMethod.GET)
//	 public ModelAndView getNguoiDung(String id, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		    String checkQuyen = "error";
//		    String quyen = ValueConst.LABLE_QLYUSER ;
//		    checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//			
//			if(checkQuyen.equals("1")){	
//			    ModelAndView view = new ModelAndView("danhsachnguoidung");
//			    Map<String, Object> models  = new HashMap<String, Object>();
//			    try {
//				   LoginAdmin	user =null;
//				   if (!StringUtils.isEmpty(id)){
//					user = loginAdminService.getUserById(id);
//					if(user != null){
//						UserInfo objNew = new UserInfo();
//						BeanUtils.copyProperties( user, objNew);
//					//	webSession.setCurrentUser(objNew);
//						models.put("arrNhomQuyen", nhomQuyenService.getListMhomQuyen());
//						models.put("arrUserRule", userRuleService.getListUserRuleTheoUser(user.getUsername()));
//					    models.put("userForm", user);
//					    view =  new ModelAndView("viewUserPopUp", models);
//					}
//				}
//						
//				} catch (Exception e) {
//					System.out.println("Caused by");
//					e.printStackTrace();
//				}	
//				return view;
//			}else{
//				ModelAndView view = new ModelAndView(checkQuyen);
//				return view;
//			}
//	 }
//	
//	@Link(label=BreadCrumbConst.CREATE_USER, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value="/createNewUser.bv", method = RequestMethod.GET)
//	public ModelAndView createNewUserPage(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		if(checkQuyen.equals("1")){	
//			Map<String, Object> models  = new HashMap<String, Object>();
//			String quan = "";
//			try{
//				
//				UserInfo objUser = webSession.getCurrentUser();			
//				int role = objUser.getRole();
//				
//				String sMaTinhCQBH = "",quanCQBH = "";
//				sMaTinhCQBH = String.valueOf(objUser.getMatinh());
//				quanCQBH = String.valueOf(objUser.getMacqt());
//				
//				if(role == ValueConst.ROLE_SUPER_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else if(role == ValueConst.ROLE_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else{
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListLimitQuan(quanCQBH));
//				}
//				
//				models.put("arrNhomQuyen", nhomQuyenService.getListMhomQuyen());
//				models.put("role", webSession.getCurrentUser().getRole());
//				if(webSession.getCurrentUser().getRole() < ValueConst.ROLE_MANAGER){
//					quan = "quan";
//				}
//				models.put("quyenMaQuan", quan);
//			}catch(Exception e){
//				System.out.println("Can't get createNewUserPage, caused by");
//				e.printStackTrace();
//			}
//			ModelAndView mv = new ModelAndView("createNewUser", models);
//			return mv;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//	}
//	
//	@ModelAttribute("userForm")
//	public FormUserInfo createUserModel(){
//		FormUserInfo project = new FormUserInfo();
//		return project;
//	}
//	
//	@SuppressWarnings({"static-access"})
//	@Link(label=BreadCrumbConst.CREATE_USER, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value="/createNewUser.bv", method=RequestMethod.POST)
//	public ModelAndView createNewUserPage(@ModelAttribute("userForm") @Validated FormUserInfo userInfo, 
//			BindingResult bindingResult,
//			HttpServletRequest request,
//			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		if(checkQuyen.equals("1")){	
//			JsonResultBase resultBase = new JsonResultBase();
//			ModelAndView mv = null;
//			try{
//				LoginAdmin newUser = new LoginAdmin();
//				CoQuanThue objThue = null ;
//				int result = 0 ;
//				String message = "" ;
//				String maCQ = "" ,maCQThue = "";
//				
//				ValidatorUserInfo validatorUser = new ValidatorUserInfo();
//				UserInfoView userView = new UserInfoView();
//			    userView.setUsername(userInfo.getUsername());
//			 	userView.setMatKhau(userInfo.getMatKhau());
//				userView.setEmail(userInfo.getEmail());
//				userView.setMatinh(userInfo.getMatinh());
//				userView.setFullName(userInfo.getFullName());
//				validatorUser.validate(userView, bindingResult);
//				if(userInfo.getEmail() != null && !userInfo.getEmail().equals("")){
//					if(utilsDal.checkEmail(userInfo.getEmail()) != 0){
//						message = getMessage("error.format.email");										
//						mv =  createNewUserPage(webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//					}
//				}
//				if(userInfo.getUsername() != null && !userInfo.getUsername().equals("")){
//					LoginAdmin oldUser = loginAdminService.getUserName(userInfo.getUsername());
//					if(oldUser != null){
//						/*status = "-1" ;
//						models.put("status", status);
//						ModelAndView mv1 = new ModelAndView("createNewUser", models);
//						return mv1;*/
//						message = getMessage("error.create.user.exist");										
//						mv =  createNewUserPage(webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//					}
//				}
//				if(userInfo.getRole() > 2){
//					if(userInfo.getMacqt() == null || userInfo.getMacqt().equals("")){
//						message = getMessage("error.create.user.macoquanthue");										
//						mv =  createNewUserPage(webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//		            	
//					}
//				}
//				
//				BeanUtils.copyProperties( userInfo, newUser);
//				if(userInfo.getLimit() == 1){
//					if(userInfo.getLimitMST() == null || userInfo.getLimitMST().equals("")){
//						message = getMessage("error.create.user.masothue.limit");										
//						mv =  createNewUserPage(webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//					}else{
//						newUser.setLimitMST(userInfo.getLimitMST());
//					}
//					
//				}else{
//					newUser.setLimitMST("");
//				}
//				if(userInfo.getMacqt() != null && !userInfo.getMacqt().equals("")){
//					if(userInfo.getRole() == 2){
//						maCQ = userInfo.getMatinh() ;
//						maCQThue = userInfo.getMatinh() + "00" ;
//					}else{
//						maCQ = userInfo.getMacqt();
//						maCQThue = userInfo.getMacqt();
//					}
//					
//	            	
//				}else{
//					maCQ = userInfo.getMatinh() ;
//					maCQThue = userInfo.getMatinh() + "00" ;
//				}
//				newUser.setMacqt(maCQ);
//				objThue = coquanthueService.getCoQuanThue(maCQThue);
//				newUser.setStatus(1);
//				newUser.setMatKhau(utilsDal.md5_md5(userInfo.getMatKhau()));
//				if(objThue != null){
//					newUser.setTencqt(objThue.getTenCoQuanThue());
//				}
//				
//				//newUser.setLimit(userInfo.getLimit());
//				if (!bindingResult.hasErrors()){
//					if (webSession != null){
//						/*if(webSession.getCurrentUser().getRole() == 1){
//							newUser.setCreatedUser(UserRole.GROUP_ADMIN.getValue());
//						}else{
//							newProject.setCreatedUser(UserRole.GROUP_USER.getValue());
//						}
//						userInfo.setUsername(Username);*/
//					}
//					String[] sNhomQuyen = request.getParameterValues("chkRule");
//					if(sNhomQuyen == null || sNhomQuyen.equals("")){
//						message = getMessage("error.create.user.nhomquyen");										
//						mv =  createNewUserPage(webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//					}
//					result = loginAdminService.createNewUser(newUser,sNhomQuyen);
//					if(result == 1){	
//						JsonResultList<UserView> listResponse = new JsonResultList<UserView>();
//						objSearchUser objUser = new objSearchUser();
//						objUser.setUsername(newUser.getUsername());
//						List<UserView> views = loginAdminService.getAllDanhSachNguoiDung(objUser,"","",1,newUser.getUsername(), 1, 10);
//	//					List<ProjectView> views = projectDAO.getAllProjects(jtSorting, jtStartIndex, jtPageSize);
//						listResponse.setRecords(views);
//						
//						listResponse.setResult(ResultEnum.OK);
//						listResponse.setTotalRecordsCount(loginAdminService.countDanhSachNguoiDung(objUser,"","",1));
//						mv = new ModelAndView("redirect:danhsachnguoidung.bv");
//					}else{
//					//	mv = createNewUserPage(webSession);
//						message = getMessage("error.create.user.unsuccess");										
//						mv =  createNewUserPage(webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//					}
//				}else{
//				//	mv = createNewUserPage(webSession);
//					message = getMessage("error.create.user.form.input");										
//					mv =  createNewUserPage(webSession);
//					mv.addObject("errorMessage", message);
//					return mv ;
//				}
//			}catch(Exception e){
//				System.out.println("Can't create user, caused by");
//				e.printStackTrace();
//				resultBase.setResult(ResultEnum.ERROR);
//				resultBase.setMessage(e.getMessage());
//			}
//			return mv;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//	}
//	
//	@RequestMapping(value = "/changequanly.bv", method = RequestMethod.POST)
//	public ModelAndView getDanhSachquanly(@ModelAttribute("userForm") @Validated FormUserInfo userInfo, 
//			BindingResult bindingResult,
//			HttpServletRequest request,
//			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		if(checkQuyen.equals("1")){
//		
//			Map<String, Object> models  = new HashMap<String, Object>();
//			String quan = "" ;
//			try{
//				UserInfo objUser = webSession.getCurrentUser();			
//				int role = objUser.getRole();
//				
//				String sMaTinhCQBH = "",quanCQBH = "";
//				sMaTinhCQBH = String.valueOf(userInfo.getMatinh());
//				quanCQBH = String.valueOf(objUser.getMacqt());
//			/*	if (role > 1){			
//				
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				} else { // admin
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}*/
//				
//				if(role == ValueConst.ROLE_SUPER_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else if(role == ValueConst.ROLE_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else{
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListLimitQuan(quanCQBH));
//				}
//				
//				userInfo.setRole(userInfo.getRole());
//				models.put("arrNhomQuyen", nhomQuyenService.getListMhomQuyen());
//				models.put("role", objUser.getRole());
//				if(userInfo.getRole() < ValueConst.ROLE_MANAGER){
//					quan = "quan";
//					
//				}
//				models.put("quyenMaQuan", quan);
//			}catch(Exception e){
//				System.out.println("Can't get create project, caused by");
//				e.printStackTrace();
//			}
//	//		ModelAndView mv = new ModelAndView("createNewProject",models);
//			ModelAndView mv = new ModelAndView("createNewUser", models);
//		//	createUserModel();
//			return mv;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//	}
//	
//	// change quan ly quyen
//	@RequestMapping(value = "/changequanly1.bv", method = RequestMethod.GET)
//	public @ResponseBody JsonResult<UserInfo> getDanhSachquanly1( String role, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		logger.info("Loading data for list data table");
////		Set<UserRole> userGroup = DemoUtils.convertToUserRoles(AuthenticationUtilities.getCurrentUser().getAuthorities());
//		JsonResult<UserInfo> listResponse = new JsonResult<UserInfo>();
//		try {
//			UserInfo objUser = webSession.getCurrentUser();
//			objUser.setRole(Integer.parseInt(role));
//			listResponse.setRecord(objUser);			
//			listResponse.setResult(ResultEnum.OK);
//		//	listResponse.setTotalRecordsCount(loginAdminService.countDanhSachNguoiDung(objUser));
//		} catch (Exception e) {
//			System.out.println("Caused by");
//			e.printStackTrace();
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(e.getMessage());
//		}  
//		return listResponse;
//	}
//	
//	
//	@RequestMapping(value = "/changecoquanbaohiem.bv", method = RequestMethod.POST)
//	public ModelAndView getDanhSachCoQuanBaoHiem(@ModelAttribute("userForm") @Validated FormUserInfo userInfo, 
//			BindingResult bindingResult,
//			HttpServletRequest request,
//			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//
//		Map<String, Object> models  = new HashMap<String, Object>();
//		String quan = "" ;
//		try{
//			
//			UserInfo objUser = webSession.getCurrentUser();
//			
//			int role = objUser.getRole();
//			String sMaTinhCQBH = "";
//			sMaTinhCQBH = String.valueOf(objUser.getMatinh());
//			if (role > 1){			
//				userInfo.setMatinh(sMaTinhCQBH);
//				models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//				models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//			} else { // admin
//				models.put("arrTinhTP", coquanthueService.getListTinh());
//				models.put("arrQuan", coquanthueService.getListQuanTheoTinh(userInfo.getMatinh()));
//			}
//			
//			//models.put("arrTinhTP", coquanthueService.getListTinh());
//			//models.put("arrQuan", coquanthueService.getListQuanTheoTinh("701"));
//			models.put("arrNhomQuyen", nhomQuyenService.getListMhomQuyen());
//			models.put("role", webSession.getCurrentUser().getRole());
//			if(userInfo.getRole() < 3){
//				quan = "quan";
//				
//			}
//			models.put("quyenMaQuan", quan);
//		}catch(Exception e){
//			System.out.println("Can't get create user, caused by");
//			e.printStackTrace();
//		}
////		ModelAndView mv = new ModelAndView("createNewProject",models);
//		ModelAndView mv = new ModelAndView("createNewUser", models);
//	//	createUserModel();
//		return mv;
//	}
//	
//	//=====================
//	
//	@Link(label = BreadCrumbConst.EDIT_USER, family = BreadCrumbConst.MAIN_FLOW, parent = BreadCrumbConst.HOME)
//	@RequestMapping(value = "/editUser.bv", method = RequestMethod.GET)
//	public ModelAndView editUSer(String id,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		if(checkQuyen.equals("1")){
//			LoginAdmin user = null;
//			Map<String, Object> models  = new HashMap<String, Object>();
//			try {
//				UserInfo objUser = webSession.getCurrentUser();			
//				int role = objUser.getRole();
//				String quan = "";
////				sMaTinhCQBH = String.valueOf(objUser.getMatinh());
////				quanCQBH = String.valueOf(objUser.getMacqt());
//				
//				user = loginAdminService.getUserById(id);
//				user.setMatKhau("");
//				models.put("userForm", user);
//				if(role == ValueConst.ROLE_SUPER_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(user.getMatinh()));
//				}else if(role == ValueConst.ROLE_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(user.getMatinh()));
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(user.getMatinh()));
//				}else{
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(user.getMatinh()));
//					models.put("arrQuan", coquanthueService.getListLimitQuan(user.getMacqt()));
//				}
//				
//				models.put("arrNhomQuyen", nhomQuyenService.getListMhomQuyen());
//				models.put("role", webSession.getCurrentUser().getRole());
//				if(user.getRole() < ValueConst.ROLE_MANAGER){
//					quan = "quan";
//					
//				}
//				models.put("quyenMaQuan", quan);
//											
//				//models.put("arrTinhTP", coquanthueService.getListTinh());
//				//models.put("arrQuan", coquanthueService.getListQuanTheoTinh("701"));
//				models.put("arrNhomQuyen", nhomQuyenService.getListMhomQuyen());
//				models.put("arrUserRule", userRuleService.getListUserRuleTheoUser(user.getUsername()));
//				models.put("limit", user.getLimit());
//				
//			} catch (Exception e) {
//				System.out.println("Cannot get lookup data and get user for edit, caused by");
//				e.printStackTrace();
//			}
//			ModelAndView modelAndView = new ModelAndView("editUser", models);
//			return modelAndView;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//	}
//	
//	@SuppressWarnings({"static-access"})
//	@Link(label = BreadCrumbConst.EDIT_USER, family = BreadCrumbConst.MAIN_FLOW, parent = BreadCrumbConst.HOME)
//	@RequestMapping(value = "/editUser.bv", method = RequestMethod.POST)
//	public ModelAndView editUSer(@ModelAttribute("userForm") @Validated FormUserInfo userInfo, BindingResult bindingResult,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) {
//	
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		if(checkQuyen.equals("1")){
//			ModelAndView mv = null;
//			int result = 0 ;
//			String message = "",maCQ = "",maCQThue = "";
//			String userName = request.getParameter("username");
//			try {
//				LoginAdmin oldUser = loginAdminService.getUserName(userName);
//				ValidatorUserInfo validatorUser = new ValidatorUserInfo();
//				UserInfoView userView = new UserInfoView();
//			    userView.setUsername(userName);
//			    if(oldUser != null){
//			    	userView.setMatKhau(oldUser.getMatKhau());
//			    	if(userInfo.getEmail() != null && !userInfo.getEmail().equals("")){
//						if(utilsDal.checkEmail(userInfo.getEmail()) != 0){
//							message = getMessage("error.format.email");										
//							mv = initializeEditUser(userInfo, request, webSession);
//							mv.addObject("errorMessage", message);
//							return mv;
//						}
//					}
//			    }
//				userView.setEmail(userInfo.getEmail());
//				userView.setMatinh(userInfo.getMatinh());
//				userView.setFullName(userInfo.getFullName());
//				validatorUser.validate(userView, bindingResult);
//				
//				if (!bindingResult.hasErrors() && webSession != null) {
////					UserInfo userPrincipal = webSession.getCurrentUser();
//					LoginAdmin newUser = new LoginAdmin();	
//					//LoginAdmin oldUser = loginAdminService.getUserName(userName);
//					CoQuanThue objThue = null ;
//					if(oldUser != null){
//						userInfo.setId(oldUser.getId());
//						userInfo.setStatus(oldUser.getStatus());
//						if(userInfo.getMatKhau() != null && !userInfo.getMatKhau().equals("")){
//							userInfo.setMatKhau(utilsDal.md5_md5(userInfo.getMatKhau()));
//						}else{
//							userInfo.setMatKhau(oldUser.getMatKhau());
//						}
//						if(userInfo.getLimit() == 1){
//							if(userInfo.getLimitMST() == null || userInfo.getLimitMST().equals("")){
//								message = getMessage("error.update.user.masothue.limit");										
//								//mv =  createNewUserPage(webSession);					
//								mv = initializeEditUser(userInfo,request,webSession);
//								mv.addObject("errorMessage", message);
//								return mv ;
//							}
//						}
//						if(userInfo.getRole() > 2){
//							if(userInfo.getMacqt() == null || userInfo.getMacqt().equals("")){
//								message = getMessage("error.update.user.macoquan");										
//								//mv =  createNewUserPage(webSession);					
//								mv = initializeEditUser(userInfo,request,webSession);
//								mv.addObject("errorMessage", message);
//								return mv ;
//							}
//						}
//						if(userInfo.getMacqt() != null && !userInfo.getMacqt().equals("")){
//							if(userInfo.getRole() == 2){
//								maCQ = userInfo.getMatinh() ;
//								maCQThue = userInfo.getMatinh() + "00" ;
//							}else{
//								maCQ = userInfo.getMacqt();
//								maCQThue = userInfo.getMacqt();
//							}
//										            	
//						}else{
//							maCQ = userInfo.getMatinh() ;
//							maCQThue = userInfo.getMatinh() + "00" ;
//						}
//						userInfo.setMacqt(maCQ);
//						objThue = coquanthueService.getCoQuanThue(maCQThue);
//						
//						if(objThue != null){
//							userInfo.setTencqt(objThue.getTenCoQuanThue());
//						}
//						BeanUtils.copyProperties( userInfo, newUser);				
//						String[] sNhomQuyen = request.getParameterValues("chkRule");
//						result = loginAdminService.updateUser(newUser,sNhomQuyen);
//						if(result == 1){
//							objSearchUser objSearchUser = new objSearchUser();
//							objSearchUser.setUsername(userInfo.getUsername());
//						//	getDanhSachNguoiDungView(objSearchUser, 0, 0, null, webSession);
//						//	message = getMessage("error.edit.user.successfull");
//							mv = new ModelAndView("redirect:danhsachnguoidung.bv");
//						//	mv.addObject("errorMessage", message);
//						}else{
//							message = getMessage("error.edit.user.unsuccess");										
//							//mv =  createNewUserPage(webSession);					
//							mv = initializeEditUser(userInfo,request,webSession);
//							mv.addObject("errorMessage", message);
//							return mv ;
//						}
//					}else{
//						message = getMessage("error.create.user.not.exist");										
//						//mv =  createNewUserPage(webSession);					
//						mv = initializeEditUser(userInfo,request,webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//					}
//									
//				} else {
//					message = getMessage("error.create.user.form.input");										
//					//mv =  createNewUserPage(webSession);					
//					mv = initializeEditUser(userInfo,request,webSession);
//					mv.addObject("errorMessage", message);
//					return mv ;
//				}
//			} catch (Exception e) {
//				System.out.println("Cannot update user, caused by ");
//				e.printStackTrace();
//			}
//			return mv;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//	}
//	
//	
//	
//	@RequestMapping(value = "/changequanlyedit.bv", method = RequestMethod.POST)
//	public ModelAndView getDanhSachquanlyEdit(@ModelAttribute("userForm") @Validated FormUserInfo userInfo, 
//			BindingResult bindingResult,
//			HttpServletRequest request,
//			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		if(checkQuyen.equals("1")){
//		
//			Map<String, Object> models  = new HashMap<String, Object>();
//			String quan = "" ;
//			try{
//				UserInfo objUser = webSession.getCurrentUser();			
//				int role = objUser.getRole();
//				String userName = request.getParameter("username");
//				String sMaTinhCQBH = "",quanCQBH = "";
//				sMaTinhCQBH = String.valueOf(userInfo.getMatinh());
//				quanCQBH = String.valueOf(objUser.getMacqt());
//				userInfo.setUsername(userName);
//				models.put("userForm", userInfo);
//				if(role == ValueConst.ROLE_SUPER_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else if(role == ValueConst.ROLE_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//				}else{
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//					models.put("arrQuan", coquanthueService.getListLimitQuan(quanCQBH));
//				}
//				
//				userInfo.setRole(userInfo.getRole());
//			
//				models.put("arrNhomQuyen", nhomQuyenService.getListMhomQuyen());
//				models.put("role", objUser.getRole());
//				if(userInfo.getRole() < ValueConst.ROLE_MANAGER){
//					quan = "quan";
//					
//				}
//				models.put("quyenMaQuan", quan);
//				
//				models.put("arrUserRule", userRuleService.getListUserRuleTheoUser(userName));
//				models.put("limit", userInfo.getLimit());
//			}catch(Exception e){
//				System.out.println("Can't get create project, caused by");
//				e.printStackTrace();
//			}
//	//		ModelAndView mv = new ModelAndView("createNewProject",models);
//			ModelAndView mv = new ModelAndView("editUser", models);
//		//	createUserModel();
//			return mv;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//	}
//	@Link(label = BreadCrumbConst.EDIT_USER, family = BreadCrumbConst.MAIN_FLOW, parent = BreadCrumbConst.HOME)
//	private ModelAndView initializeEditUser(FormUserInfo user,HttpServletRequest request,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//		Map<String, Object> models = new HashMap<String, Object>();
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		if(checkQuyen.equals("1")){
//		//	LoginAdmin objUserLogin = null;
//		//	Map<String, Object> models  = new HashMap<String, Object>();
//			try {
//				
//				UserInfo objUser = webSession.getCurrentUser();			
//				int role = objUser.getRole();
//				String quan = "";
////				sMaTinhCQBH = String.valueOf(objUser.getMatinh());
////				quanCQBH = String.valueOf(objUser.getMacqt());
//				
//				String userName = request.getParameter("username");
//				user.setUsername(userName);
//				models.put("userForm", user);
//				if(role == ValueConst.ROLE_SUPER_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListTinh());
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(user.getMatinh()));
//				}else if(role == ValueConst.ROLE_ADMIN){
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(user.getMatinh()));
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(user.getMatinh()));
//				}else{
//					models.put("arrTinhTP", coquanthueService.getListLimitTinh(user.getMatinh()));
//					models.put("arrQuan", coquanthueService.getListLimitQuan(user.getMacqt()));
//				}
//								
//				models.put("arrNhomQuyen", nhomQuyenService.getListMhomQuyen());
//				models.put("role", webSession.getCurrentUser().getRole());
//				
//				models.put("quyenMaQuan", quan);														
//				models.put("arrNhomQuyen", nhomQuyenService.getListMhomQuyen());
//				models.put("arrUserRule", userRuleService.getListUserRuleTheoUser(user.getUsername()));
//				models.put("limit", user.getLimit());
//				
//			} catch (Exception e) {
//				System.out.println("Cannot get lookup data and get user for edit, caused by");
//				e.printStackTrace();
//			}
//			ModelAndView modelAndView = new ModelAndView("editUser", models);
//			return modelAndView;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//										
//		/*try {
//			String userName = request.getParameter("username");
//			user.setUsername(userName);
//			models.put("userForm", user);
//			models.put("arrTinhTP", coquanthueService.getListTinh());
//			models.put("arrQuan", coquanthueService.getListQuanTheoTinh("701"));
//			models.put("arrNhomQuyen", nhomQuyenService.getListMhomQuyen());
//			models.put("arrUserRule", userRuleService.getListUserRuleTheoUser(user.getUsername()));
//		} catch (Exception ex) {
//			System.out.println("Cannot get lookup data and get user for edit, caused by", ex);
//		}
//		ModelAndView modelAndView = new ModelAndView("editUser", models);
//		return modelAndView;*/
//	}
//	@Link(label = BreadCrumbConst.DELETE_TAIKHOAN, family = BreadCrumbConst.MAIN_FLOW, parent = BreadCrumbConst.HOME)
//	@RequestMapping(value="/deleteUser.bv", method = RequestMethod.POST)
//	public @ResponseBody JsonResultBase deleteUser(@RequestParam String id,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		logger.info("Delete user " + id);
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		
//		JsonResultBase jsonResultBase = new JsonResultBase();
//		LoginAdmin user = null;
//		if(checkQuyen.equals("1")){
//			try {
//				user = loginAdminService.getUserById(id);
//				loginAdminService.deleteUSer(user);
//				jsonResultBase.setResult(ResultEnum.OK);
//			} catch (Exception e) {
//				System.out.println("Caused by ");
//				e.printStackTrace();
//				jsonResultBase.setResult(ResultEnum.ERROR);
//				jsonResultBase.setMessage(e.getMessage());
//			}
//			return jsonResultBase;
//		
//		}else{
//			jsonResultBase.setResult(ResultEnum.ERROR);
//			jsonResultBase.setMessage(getMessage("error.rule.permission"));
//			return jsonResultBase;
//		}
//	}
//	
//	/*
//	 
//	 @RequestMapping(value="/cancelProject.bv", method = RequestMethod.POST)
//	 public @ResponseBody JsonResultBase cancelProject(@RequestParam String projectID,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		 JsonResultBase jsonResultBase = new JsonResultBase();
//		 try {
//			 Project oldProject = projectService.getProject(projectID);
//			 LoginAdmin userPrincipal = webSession.getCurrentUser();
//			 projectService.cancelProject(oldProject, userPrincipal.getUsername(), TransactionType.CANCEL_PROJECT.getTransactionTypeID());
//			 jsonResultBase.setResult(ResultEnum.OK);
//		 } catch (Exception e) {
//			 System.out.println("Caused by", e);
//			 jsonResultBase.setResult(ResultEnum.ERROR);
//			 jsonResultBase.setMessage(e.getMessage());
//		 }
//		 return jsonResultBase;
//	 }
//	 
//	 private String getMessage(String code){
//			return appContext.getMessage(code, null, Locale.US);
//	 }*/
//	
//	
//	@Link(label=BreadCrumbConst.CREATE_USER, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.ADMIN_MANAGEMENT)
//	@RequestMapping(value="/changeCity.bv", method = RequestMethod.GET)
//	public ModelAndView changeCityView(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//						
//		Map<String, Object> models  = new HashMap<String, Object>();
//		String quan = "";
//		try{
//			UserInfo objUser = webSession.getCurrentUser();			
//			int role = objUser.getRole();
//			String sMaTinhCQBH = "";
//			sMaTinhCQBH = String.valueOf(objUser.getMatinh());
//			if (role > 1){			
//				models.put("arrTinhTP", coquanthueService.getListLimitTinh(sMaTinhCQBH));
//				models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//			} else { // admin
//				models.put("arrTinhTP", coquanthueService.getListTinh());
//				models.put("arrQuan", coquanthueService.getListQuanTheoTinh(sMaTinhCQBH));
//			}
//			models.put("arrNhomQuyen", nhomQuyenService.getListMhomQuyen());
//			models.put("role", webSession.getCurrentUser().getRole());
//			if(webSession.getCurrentUser().getRole() < 3){
//				quan = "quan";
//			}
//			models.put("quyenMaQuan", quan);
//		}catch(Exception e){
//			System.out.println("Can't get create project, caused by ");
//			e.printStackTrace();
//		}
//		ModelAndView mv = new ModelAndView("changeCity", models);
//		return mv;
//	}
//	
//	@RequestMapping(value = "/changeCity.bv", method = RequestMethod.POST)
//	public ModelAndView changeCityProcess(String maTinh, @ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//	
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//				
//		if(checkQuyen.equals("1")){
//			Map<String, Object> models  = new HashMap<String, Object>();
//			try{
//				UserInfo objUser = webSession.getCurrentUser();
//				if(objUser.getRole() == 1){
//					
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(maTinh));
//				}else if(objUser.getRole() == 2){
//					
//					models.put("arrQuan", coquanthueService.getListQuanTheoTinh(maTinh));
//				}else{
//				
//					models.put("arrQuan", coquanthueService.getListLimitQuan(objUser.getMacqt()));
//				}
//				
//			//	models.put("arrQuan", coquanthueService.getListQuanTheoTinh(maTinh));
//				/*models.put("role", webSession.getCurrentUser().getRole());
//				if(webSession.getCurrentUser().getRole() < 3){
//					quan = "quan";
//				}
//				models.put("quyenMaQuan", quan);*/
//			}catch(Exception e){
//				System.out.println("Can't get create project, caused by");
//				e.printStackTrace();
//			}
//			ModelAndView mv = new ModelAndView("listCategoryDistrict", models);
//			return mv;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//	}
//	
//	@RequestMapping(value="/updateStatus_bk.bv", method = RequestMethod.POST)
//	public @ResponseBody JsonResultList<UserView> updateStatusBK(@ModelAttribute objSearchUser objUser,@RequestParam String id,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		logger.info("update status user " + id);
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		
//		//JsonResultBase jsonResultBase = new JsonResultBase();
//		JsonResultList<UserView> listResponse = new JsonResultList<UserView>();
//		LoginAdmin user = null;
//		int result = 0;
//		if(checkQuyen.equals("1")){
//			try {
//				
//				user = loginAdminService.getUserById(id);
//				//UserInfo userPrincipal = webSession.getCurrentUser();
//				user.setId(user.getId());
//				if(user.getStatus() == 1){
//					user.setStatus(0);
//				}else{
//					user.setStatus(1);
//				}
//				result = loginAdminService.updateUserStatus(user);
//				if(result == 1){
//					String userLogin = "" ,maCQ = "";
//					UserInfo objUserLogin = webSession.getCurrentUser();			
//					int role = objUserLogin.getRole();
//					userLogin = objUserLogin.getUsername();
//					maCQ = objUserLogin.getMacqt();
//					List<UserView> views = loginAdminService.getAllDanhSachNguoiDung(objUser, userLogin, maCQ, role, "Username ASC", 1, 500);
//		//			List<ProjectView> views = projectDAO.getAllProjects(jtSorting, jtStartIndex, jtPageSize);
//					listResponse.setRecords(views);
//					
//					listResponse.setResult(ResultEnum.OK);
//					listResponse.setTotalRecordsCount(loginAdminService.countDanhSachNguoiDung(objUser,userLogin,maCQ,role));
//			
//				}else{
//					listResponse.setResult(ResultEnum.ERROR);
//					listResponse.setMessage("Cập nhật trạng thái tài khoản không thành công.");
//				}
//																				
//			} catch (Exception e) {
//				System.out.println("Caused by");
//				e.printStackTrace();
//				listResponse.setResult(ResultEnum.ERROR);
//				listResponse.setMessage(e.getMessage());
//			}
//			return listResponse;
//		
//		}else{
//			listResponse.setResult(ResultEnum.ERROR);
//			listResponse.setMessage(getMessage("error.rule.permission"));
//			return listResponse;
//		}
//	}
//	@Link(label=BreadCrumbConst.CHANGE_STATUS, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value="/updateStatus.bv", method = RequestMethod.GET)
//	public @ResponseBody ModelAndView updateStatus(@ModelAttribute objSearchUser objUser,@RequestParam String id,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		logger.info("update status user " + id);
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_QLYUSER ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		
//		//JsonResultBase jsonResultBase = new JsonResultBase();
//	//	JsonResultList<UserView> listResponse = new JsonResultList<UserView>();
//		LoginAdmin user = null;
//		ModelAndView mv = null ;
//		int result = 0;
//		String message = "" ;
//		if(checkQuyen.equals("1")){
//			try {
//				
//				user = loginAdminService.getUserById(id);
//				//UserInfo userPrincipal = webSession.getCurrentUser();
//				user.setId(user.getId());
//				if(user.getStatus() == 1){
//					user.setStatus(0);
//				}else{
//					user.setStatus(1);
//				}
//				result = loginAdminService.updateUserStatus(user);
//				if(result == 1){
//					mv = new ModelAndView("redirect:danhsachnguoidung.bv");
//				//	message = getMessage("error.update.status.user.successfull");					
//				//	mv.addObject("errorMessage", message);
//				}else{
//					message = getMessage("error.update.status.user.unsuccess");										
//					mv = new ModelAndView("redirect:danhsachnguoidung.bv");
//					mv.addObject("errorMessage", message);					
//				}
//																				
//			} catch (Exception e) {
//				System.out.println("cập nhật trạng thái tài khoản không thành công. Bởi vì ");
//				e.printStackTrace();
//			}
//			return mv;
//		
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//	}
//	
//	@Link(label=BreadCrumbConst.CHANGE_PASS, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/changePass.bv", method = RequestMethod.GET)
//	public ModelAndView getChangePass(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//	//	ModelAndView view = new ModelAndView("danhsachnguoidung");
//		try{
//			if(webSession != null && !webSession.equals("")){
//				Map<String, Object> models  = new HashMap<String, Object>();
//				UserInfo objUser = webSession.getCurrentUser();			
//				int role = objUser.getRole();
//				models.put("role", role);
//				ModelAndView mv = new ModelAndView("changePass", models);
//				return mv;
//			}else{
//				return new ModelAndView("login");
//			}
//			
//		}catch (Exception e) {
//			System.out.println("cập nhật mật khẩu không thành công. Bởi vì ");
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@SuppressWarnings("static-access")
//	@Link(label=BreadCrumbConst.CHANGE_PASS, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value="/changePass.bv", method=RequestMethod.POST)
//	public ModelAndView changePassPage(@ModelAttribute("userForm") @Validated FormUserInfo userInfo, 
//			BindingResult bindingResult,
//			HttpServletRequest request,
//			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		
//		
//		if(webSession != null && !webSession.equals("")){
//			ModelAndView mv = null;
//			String message = "" ;
//			try{
//				int result = 0 ;
//				String matKhauMoi = "" ,matKhauMoiRe = "";
//				
//				ValidatorMatKhau validatorUser = new ValidatorMatKhau();
//				MatKhauInfoView userView = new MatKhauInfoView();
//			    userView.setMatKhauCu(userInfo.getMatKhauCu());
//			 	userView.setMatKhauMoi(userInfo.getMatKhauMoi());
//				userView.setMatKhauMoiRe(userInfo.getMatKhauMoiRe());
//				validatorUser.validate(userView, bindingResult);				
//				UserInfo objUser = webSession.getCurrentUser();									
//				//newUser.setLimit(userInfo.getLimit());
//				if (!bindingResult.hasErrors()){
//					LoginAdmin user = loginAdminService.login(objUser.getUsername(), utilsDal.md5_md5(userInfo.getMatKhauCu()));
//					if(user != null && !user.equals("")){
//						matKhauMoi = userInfo.getMatKhauMoi();
//						matKhauMoiRe = userInfo.getMatKhauMoiRe();
//						if(!matKhauMoi.equals( matKhauMoiRe)){
//							message = getMessage("error.matkhau.user.not");										
//							mv =  getChangePass(webSession);
//							mv.addObject("errorMessage", message);
//							return mv ;
//						}else{
//							user.setId(user.getId());
//							user.setMatKhau( utilsDal.md5_md5(matKhauMoi));
//							result = loginAdminService.updateUserStatus(user);
//						}
//						
//						if(result == 1){								
//							message = getMessage("success.changepass");										
//							mv =  getChangePass(webSession);
//							mv.addObject("successMessage", message);
//							return mv ;
//						}else{
//						//	mv = createNewUserPage(webSession);
//							message = getMessage("error.matkhau.user.unsuccess");										
//							mv =  getChangePass(webSession);
//							mv.addObject("errorMessage", message);
//							return mv ;
//						}						
//						
//					}else{
//						message = getMessage("error.matkhau.user.not.exist");										
//						mv =  getChangePass(webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//					}
//																																
//				}else{
//				//	mv = createNewUserPage(webSession);
//					message = getMessage("error.matkhau.user.form.input");										
//					mv =  getChangePass(webSession);
//					mv.addObject("errorMessage", message);
//					return mv ;
//				}
//			}catch(Exception e){
//				System.out.println("Không thể cập nhật mật khẩu, bởi vì");
//				e.printStackTrace();
//				message = getMessage("error.matkhau.user.unsuccess");										
//				mv =  getChangePass(webSession);
//				mv.addObject("errorMessage", message);
//				return mv ;
//			}
//			
//		}else{
//			return new ModelAndView("login");
//		}
//								
//	}
//	
//	@Link(label=BreadCrumbConst.VIEW_LOCK_MST, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/lock_mst.bv", method = RequestMethod.GET)
//	public ModelAndView lock_mst_view(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//		try {
//			String role = ValueConst.LABLE_KHOA_MST;
//			String mav = "";
//			String checkRole = authenticationModel.CheckUserRule(role, webSession);
//			if (checkRole.equals("1")) {
//				Map<String, Object> models  = new HashMap<String, Object>();
//				ModelAndView mv = new ModelAndView("lockMst", models);
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
//	@Link(label=BreadCrumbConst.VIEW_LOCK_MST, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/lock_mst.bv", method = RequestMethod.POST)
//	public @ResponseBody JsonResultList<HddtGioiHanHienThiView> lock_mst_procc(@ModelAttribute objKhoaMst obj, int jtStartIndex, int jtPageSize, String jtSorting, BindingResult bindingResult,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		String role = ValueConst.LABLE_KHOA_MST;
//		String checkRole = authenticationModel.CheckUserRule(role, webSession);
//		JsonResultList<HddtGioiHanHienThiView> listResponse = new JsonResultList<HddtGioiHanHienThiView>();
//		if (checkRole.equals("1")) {
//			logger.info("Loading data for invoice report");
//			try {
//				String mst="",tinhTrang = ""; 
//				mst = obj.getMst();
//				tinhTrang = obj.getTinhTrang();
//								
//				List<HddtGioiHanHienThiView> views = hddtGioihanHienthiDAO.getGioihanHienthiSelected(mst, tinhTrang, jtSorting, jtStartIndex, jtPageSize);
//				listResponse.setRecords(views);
//				listResponse.setResult(ResultEnum.OK);
//				listResponse.setTotalRecordsCount(views.size());
//					
//			} catch (Exception e) {
//				System.out.println("Caused by" );
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
//	@ModelAttribute("createLockMstForm")
//	public FormLockMst createLockMstModel(){
//		FormLockMst project = new FormLockMst();
//		return project;
//	}
//
//	@Link(label=BreadCrumbConst.VIEW_LOCK_MST, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value = "/create_lock_mst.bv", method = RequestMethod.GET)
//	public ModelAndView create_lock_mst_view(@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//		try {
//			String role = ValueConst.LABLE_KHOA_MST;
//			String mav = "";
//			String checkRole = authenticationModel.CheckUserRule(role, webSession);
//			if (checkRole.equals("1")) {
//				Map<String, Object> models  = new HashMap<String, Object>();
//				ModelAndView mv = new ModelAndView("createLockMst", models);
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
//	@Link(label=BreadCrumbConst.CREATE_LOCK_MST, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value="/create_lock_mst.bv", method=RequestMethod.POST)
//	public ModelAndView create_lock_mst_procc(@ModelAttribute("createLockMstForm") @Validated FormLockMst form, 
//			BindingResult bindingResult,
//			HttpServletRequest request,
//			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_KHOA_MST ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		if(checkQuyen.equals("1")){	
//			JsonResultBase resultBase = new JsonResultBase();
//			ModelAndView mv = null;
//			try{
//				String message = "" ;
//				UserInfo objUser = webSession.getCurrentUser();
//				if(form.getMst() != null && !form.getMst().equals("")){
//					if (!utilsDal.checkMaSoThue(form.getMst())) {
//						message = getMessage("error.format.mst");										
//						mv =  create_lock_mst_view(webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//					}
//				}else{
//					message = getMessage("error.create.lockmst.mst");										
//					mv =  create_lock_mst_view(webSession);
//					mv.addObject("errorMessage", message);
//					return mv ;
//				}
//				if(form.getNgayBd() == null || form.getNgayBd().equals("")){
//					message = getMessage("error.create.lockmst.ngaybd");										
//					mv =  create_lock_mst_view(webSession);
//					mv.addObject("errorMessage", message);
//					return mv ;
//				}
//				if (form.getNgayKt() != null && !form.getNgayKt().equals("")) {
//					String ngaybd = util.formatNgaySo4(form.getNgayBd().replaceAll("/", ConstantValue.MINUS));
//					String ngaykt = util.formatNgaySo4(form.getNgayKt().replaceAll("/", ConstantValue.MINUS));
//					if (Integer.parseInt(ngaybd.replace("-", "")) > Integer.parseInt(ngaykt.replace("-", ""))) {
//						message = getMessage("error.ngayKt.greater.ngayBd");										
//						mv =  create_lock_mst_view(webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//					}
//				}
//				if (form.getNgayKt() == null || form.getNgayKt().equals("")) {
//					List<HddtGioihanHienthi> listCheck = hddtGioihanHienthiDAO.findByMst(form.getMst());
//					if (listCheck != null && listCheck.size() > 0){
//						for (HddtGioihanHienthi hddtGioihanHienthi : listCheck) {
//							if (hddtGioihanHienthi.getNgayKt() == null || hddtGioihanHienthi.getNgayKt().equals("")) {
//								message = getMessage("exists.save.unsuccess");										
//								mv =  create_lock_mst_view(webSession);
//								mv.addObject("errorMessage", message);
//								return mv ;
//							}
//						}
//					}
//				}
//				mv = create_lock_mst_view(webSession);
//				String date = hoaDonDao.getSQLDateTime();
//				HddtGioihanHienthi objSave = new HddtGioihanHienthi();
//				objSave.setMst(form.getMst());
//				objSave.setNgayBd(util.formatNgaySo4(form.getNgayBd().replaceAll("/", ConstantValue.MINUS)));
//				if (form.getNgayKt() != null && !form.getNgayKt().equals("")) {
//					objSave.setNgayKt(util.formatNgaySo4(form.getNgayKt().replaceAll("/", ConstantValue.MINUS)));
//				}
//				objSave.setNguoiTao(objUser.getUsername());
//				objSave.setNgayTao(date);
//				objSave.setTinhTrang(1);
//				int checkSave = hddtGioihanHienthiDAO.save(objSave);
//				if (checkSave > 0) {
//					message = getMessage("save.success");										
//					mv.addObject("successMessage", message);
//				}else{
//					message = getMessage("error.save.unsuccess");										
//					mv.addObject("errorMessage", message);
//				}
//			}catch(Exception ex){
//				System.out.println("Can't create lock mst, caused by");
//				ex.printStackTrace();
//				resultBase.setResult(ResultEnum.ERROR);
//				resultBase.setMessage(ex.getMessage());
//			}
//			return mv;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//	}
//	
//	@Link(label = BreadCrumbConst.DELETE_TAIKHOAN, family = BreadCrumbConst.MAIN_FLOW, parent = BreadCrumbConst.HOME)
//	@RequestMapping(value="/deleteLockMst.bv", method = RequestMethod.POST)
//	public @ResponseBody JsonResultBase deleteLockMst(@RequestParam String id,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		logger.info("Delete Lock MST " + id);
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_KHOA_MST ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		JsonResultBase jsonResultBase = new JsonResultBase();
//		if(checkQuyen.equals("1")){
//			try {
//				hddtGioihanHienthiDAO.delete(id);
//				jsonResultBase.setResult(ResultEnum.OK);
//				jsonResultBase.setMessage("abc");
//			} catch (Exception e) {
//				System.out.println("Caused by");
//				e.printStackTrace();
//				jsonResultBase.setResult(ResultEnum.ERROR);
//				jsonResultBase.setMessage(e.getMessage());
//			}
//			return jsonResultBase;
//		
//		}else{
//			jsonResultBase.setResult(ResultEnum.ERROR);
//			jsonResultBase.setMessage(getMessage("error.rule.permission"));
//			return jsonResultBase;
//		}
//	}
//	
//	@SuppressWarnings("static-access")
//	@Link(label = BreadCrumbConst.EDIT_LOCK_MST, family = BreadCrumbConst.MAIN_FLOW, parent = BreadCrumbConst.HOME)
//	@RequestMapping(value = "/editLockMst.bv", method = RequestMethod.GET)
//	public ModelAndView editLockMst_view(String id,@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_KHOA_MST ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		if(checkQuyen.equals("1")){
//			HddtGioihanHienthi obj = null;
//			Map<String, Object> models  = new HashMap<String, Object>();
//			try {
//				obj = hddtGioihanHienthiDAO.findById(id);
//				obj.setNgayBd(util.formatNgayThangNam__ddmmyyyy(obj.getNgayBd()));
//				obj.setNgayKt(util.formatNgayThangNam__ddmmyyyy(obj.getNgayKt()));
//				models.put("ngayBd", obj.getNgayBd());
//				models.put("ngayKt", obj.getNgayKt());
//				models.put("createLockMstForm", obj);
//			}catch (Exception e){
//				System.out.println("Cannot get lookup data and get user for edit, caused by");
//				e.printStackTrace();
//			}
//			ModelAndView modelAndView = new ModelAndView("editLockMst", models);
//			return modelAndView;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//	}
//	
//	@SuppressWarnings("static-access")
//	@Link(label=BreadCrumbConst.CREATE_LOCK_MST, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.HOME)
//	@RequestMapping(value="/editLockMst.bv", method=RequestMethod.POST)
//	public ModelAndView editLockMst_procc(@ModelAttribute("createLockMstForm") @Validated FormLockMst form, 
//			BindingResult bindingResult,
//			HttpServletRequest request,
//			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession){
//		
//		String checkQuyen = "error";
//		String quyen = ValueConst.LABLE_KHOA_MST ;
//		checkQuyen = authenticationModel.CheckUserRule(quyen, webSession);
//		if(checkQuyen.equals("1")){	
//			JsonResultBase resultBase = new JsonResultBase();
//			ModelAndView mv = null;
//			try{
//				String id = String.valueOf(form.getId());
//				HddtGioihanHienthi obj = hddtGioihanHienthiDAO.findById(id);
//				if (obj != null) {
//					String message = "" ;
//					UserInfo objUser = webSession.getCurrentUser();
//					if(form.getMst() != null && !form.getMst().equals("")){
//						if (!utilsDal.checkMaSoThue(form.getMst())) {
//							message = getMessage("error.format.mst");										
//							mv =  editLockMst_view(id, webSession);
//							mv.addObject("errorMessage", message);
//							return mv ;
//						}
//					}else{
//						message = getMessage("error.create.lockmst.mst");										
//						mv =  editLockMst_view(id, webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//					}
//					if(form.getNgayBd() == null || form.getNgayBd().equals("")){
//						message = getMessage("error.create.lockmst.ngaybd");										
//						mv =  editLockMst_view(id, webSession);
//						mv.addObject("errorMessage", message);
//						return mv ;
//					}
//					if (form.getNgayKt() != null && !form.getNgayKt().equals("")) {
//						String ngaybd = util.formatNgaySo4(form.getNgayBd().replaceAll("/", ConstantValue.MINUS));
//						String ngaykt = util.formatNgaySo4(form.getNgayKt().replaceAll("/", ConstantValue.MINUS));
//						if (Integer.parseInt(ngaybd.replace("-", "")) > Integer.parseInt(ngaykt.replace("-", ""))) {
//							message = getMessage("error.ngayKt.greater.ngayBd");										
//							mv =  editLockMst_view(id, webSession);
//							mv.addObject("errorMessage", message);
//							return mv ;
//						}
//					}
//					if (form.getNgayKt() == null || form.getNgayKt().equals("")) {
//						List<HddtGioihanHienthi> listCheck = hddtGioihanHienthiDAO.findByMst(form.getMst());
//						if (listCheck != null && listCheck.size() > 0){
//							for (HddtGioihanHienthi hddtGioihanHienthi : listCheck) {
//								if (hddtGioihanHienthi.getNgayKt() == null || hddtGioihanHienthi.getNgayKt().equals("")) {
//									message = getMessage("exists.save.unsuccess");										
//									mv =  editLockMst_view(id, webSession);
//									mv.addObject("errorMessage", message);
//									return mv ;
//								}
//							}
//						}
//					}
//					mv =  editLockMst_view(id, webSession);
//					String date = hoaDonDao.getSQLDateTime();
//					HddtGioihanHienthi objSave = new HddtGioihanHienthi();
//					objSave.setId(Integer.parseInt(id));
//					objSave.setMst(form.getMst());
//					objSave.setNgayBd(util.formatNgaySo4(form.getNgayBd().replaceAll("/", ConstantValue.MINUS)));
//					if (form.getNgayKt() != null && !form.getNgayKt().equals("")) {
//						objSave.setNgayKt(util.formatNgaySo4(form.getNgayKt().replaceAll("/", ConstantValue.MINUS)));
//					}
//					objSave.setNguoiTao(objUser.getUsername());
//					objSave.setNgayTao(form.getNgayTao());
//					objSave.setNgayCapNhat(date);
//					objSave.setTinhTrang(1);
//					hddtGioihanHienthiDAO.update(objSave);
////					if (checkSave > 0) {
//					mv =  editLockMst_view(id, webSession);
//						message = getMessage("save.success");										
//						mv.addObject("successMessage", message);
////						models.put("ngayBd", form.getNgayBd());
////						models.put("ngayKt", form.getNgayKt());
////						models.put("createLockMstForm", objSave);
////						mv = new ModelAndView(checkQuyen, models);
////					}else{
////						message = getMessage("error.save.unsuccess");										
////						mv.addObject("errorMessage", message);
////					}
//				}
//			}catch(Exception ex){
//				System.out.println("Can't create lock mst, caused by");
//				ex.printStackTrace();
//				resultBase.setResult(ResultEnum.ERROR);
//				resultBase.setMessage(ex.getMessage());
//			}
//			return mv;
//		}else{
//			ModelAndView view = new ModelAndView(checkQuyen);
//			return view;
//		}
//	}
//	
//} 
