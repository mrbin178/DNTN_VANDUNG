package ts24.com.vn.service.impl;

import ts24.com.vn.dal.dao.LoginAdminDAO;
import ts24.com.vn.dal.dao.impl.LoginAdminDAOImpl;
import ts24.com.vn.dal.model.LoginAdmin;
import ts24.com.vn.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService{
	private LoginAdminDAO userDao;
	public AuthenticationServiceImpl(){
		userDao = new LoginAdminDAOImpl();
	}
	
	public LoginAdmin login(String username, String password) throws Exception {
		return userDao.getUser(username, password);
	}

}

