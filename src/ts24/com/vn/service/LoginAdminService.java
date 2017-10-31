package ts24.com.vn.service;

import java.util.List;

import ts24.com.vn.dal.model.LoginAdmin;
import ts24.com.vn.dal.model.nonentity.UserView;
import ts24.com.vn.model.objSearchUser;

public interface LoginAdminService {
	public List<LoginAdmin> getList() throws Exception;
	public LoginAdmin getUserById(String id) throws Exception;
	
	public List<UserView> getAllUsers(String sortName, int startIndex, int pageSize) throws Exception;
	public long countAllUser() throws Exception;
	public LoginAdmin getUser(String userName) throws Exception;
	
	public List<UserView> getAllDanhSachNguoiDung(objSearchUser objUser,String userLogin,String maCQ, int role, String sortName, int startIndex, int pageSize) throws Exception;
	public long countDanhSachNguoiDung(objSearchUser objUser,String userLogin,String maCQ, int role) throws Exception;
	
	public int createNewUser(LoginAdmin user, String[] Arr) throws Exception;
	
	public int updateUser(LoginAdmin user,String[] Arr) throws Exception;
	public int updateUserStatus(LoginAdmin user) throws Exception;
	
	public LoginAdmin getUserName(String userName) throws Exception;
	public int deleteUSer(LoginAdmin user) throws Exception;
	public LoginAdmin login(String username, String password) throws Exception;
}
