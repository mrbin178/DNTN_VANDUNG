package satthepvandung.dal.dao;

import java.util.List;

import satthepvandung.dal.table.LoginAdmin;
import satthepvandung.dal.view.UserView;
import satthepvandung.model.objSearchUser;

public interface LoginAdminDAO extends BaseDAO<LoginAdmin>{
	public LoginAdmin getUser(String username, String password) throws Exception;
	public List<LoginAdmin> getUserList(int startIndex, int pageSize,String sortingColumn) throws Exception;
    public List<UserView> getAllUsers(String sortName, int startIndex, int pageSize) throws Exception;
    public long countAllUser() throws Exception; 
    public LoginAdmin getUser(String userName) throws Exception;
    
    public List<UserView> getAllDanhSachNguoiDung(objSearchUser objUser,String userLogin,String maCQ,int role,String sortName, int startIndex, int pageSize) throws Exception;
    public long countDanhSachNguoiDung(objSearchUser objUser,String userLogin,String maCQ, int role) throws Exception;
    public int insertUser(LoginAdmin user, String[] Arr) throws Exception;
    public int updateUser(LoginAdmin user,String[] Arr) throws Exception;
    public int deleteUSer(LoginAdmin user) throws Exception;
    public int updateUserStatus(LoginAdmin user) throws Exception;
    
}
