package ts24.com.vn.service;

import ts24.com.vn.dal.model.LoginAdmin;

public interface AuthenticationService {
	public LoginAdmin login(String userName, String password) throws Exception;
}
