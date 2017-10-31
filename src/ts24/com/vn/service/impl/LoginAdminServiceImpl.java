package ts24.com.vn.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ts24.com.vn.dal.dao.LoginAdminDAO;
import ts24.com.vn.dal.dao.impl.LoginAdminDAOImpl;
import ts24.com.vn.dal.model.LoginAdmin;
import ts24.com.vn.dal.model.nonentity.UserView;
import ts24.com.vn.model.objSearchUser;
import ts24.com.vn.service.LoginAdminService;

public class LoginAdminServiceImpl extends BaseServiceImpl<LoginAdmin> implements LoginAdminService{
	  private LoginAdminDAO loginAdminDao;
	    
	    public LoginAdminServiceImpl(LoginAdminDAO dao){
	    	this.loginAdminDao = dao;
	    }
	    
	    public LoginAdminServiceImpl(){
	    	this.loginAdminDao = new LoginAdminDAOImpl();
	    }

	@Override
	public List<LoginAdmin> getList() throws Exception {
		// TODO Auto-generated method stub
		return getList(LoginAdmin.class);
	}

	@Override
	public LoginAdmin getUserById(String id) throws Exception {
		String hSql = "From LoginAdmin u where u.id =:userId";
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hSql);
			query.setParameter("userId", Integer.parseInt(id));
		    return (LoginAdmin) query.uniqueResult();
		}finally{
			closeSession(session);
		}
	}

	@Override
	public List<UserView> getAllUsers(String sortName, int startIndex, int pageSize) throws Exception{
		return loginAdminDao.getAllUsers(sortName, startIndex, pageSize);
	}
	
	@Override
	public long countAllUser() throws Exception{
		return loginAdminDao.countAllUser();
	}
	
	public LoginAdmin getUser(String userName) throws Exception {
		return loginAdminDao.getUser(userName);
	}

	@Override
	public List<UserView> getAllDanhSachNguoiDung(objSearchUser objUser,String userLogin,String maCQ, int role, String sortName, int startIndex, int pageSize) throws Exception{
		return loginAdminDao.getAllDanhSachNguoiDung(objUser,userLogin,maCQ, role,sortName, startIndex, pageSize);
	}
	
	@Override
	public long countDanhSachNguoiDung(objSearchUser objUser,String  userLogin,String maCQ, int role) throws Exception{
		return loginAdminDao.countDanhSachNguoiDung(objUser,userLogin,maCQ,role);
	}
	
	public int createNewUser(LoginAdmin user, String[] Arr) throws Exception {
		return loginAdminDao.insertUser(user,Arr);
	}
	
	public int updateUser(LoginAdmin user, String[] Arr) throws Exception {
		/*LoginAdmin oldUser = loginAdminDao.getUser(user.getUsername());
		BeanUtils.copyProperties( user, oldUser);
		oldUser.setId(oldUser.getId());*/		
		return loginAdminDao.updateUser(user,Arr);
	}
	
	@Override
	public LoginAdmin getUserName(String user) throws Exception {
		String hSql = "From LoginAdmin u where u.username =:user";
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hSql);
			query.setParameter("user", user);
		    return (LoginAdmin) query.uniqueResult();
		}finally{
			closeSession(session);
		}
	}
	
	public int deleteUSer(LoginAdmin user) throws Exception {
		return loginAdminDao.deleteUSer(user);
	}
	
	public int updateUserStatus(LoginAdmin user) throws Exception {
				
		return loginAdminDao.updateUserStatus(user);
	}
	
	public LoginAdmin login(String username, String password) throws Exception {
		return loginAdminDao.getUser(username, password);
	}
}
