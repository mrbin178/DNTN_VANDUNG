package satthepvandung.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;

import com.mysql.jdbc.StringUtils;

import satthepvandung.dal.dao.LoginAdminDAO;
import satthepvandung.dal.dao.UserRuleDAO;
import satthepvandung.dal.table.LoginAdmin;
import satthepvandung.dal.table.UserRule;
import satthepvandung.dal.view.UserView;
import satthepvandung.model.objSearchUser;

public class LoginAdminDAOImpl extends BaseDAOImpl<LoginAdmin> implements LoginAdminDAO{

	private UserRuleDAO userRuleDAO;
	
	public LoginAdminDAOImpl(){
		userRuleDAO = new UserRuleDAOImpl();
		
	}
	public LoginAdmin getUser(String username, String password) throws Exception {
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM LoginAdmin u where u.username = :username and u.matKhau = :password AND u.status=:status ");
			query.setParameter("username", username);
			query.setParameter("password", password);
			query.setParameter("status", 1);
			List<LoginAdmin> users = query.list();
			if (users != null && users.size() > 0){
				return users.get(0);
			}
		}finally{
			closeSession(session);
		}
		return null;
	}
	public List<LoginAdmin> getUserList(int startIndex, int pageSize,String sortingColumn) throws Exception {
		List<LoginAdmin> result=null;
		Session session = null;
			
		try{
				session = sessionFactory.openSession();
				String hql = "FROM LoginAdmin";
				if (!StringUtils.isNullOrEmpty(sortingColumn)){
					hql += " Order by " + sortingColumn;
				}
				Query query = session.createQuery(hql);
				query.setFirstResult(startIndex);
				query.setMaxResults(pageSize);
				result = query.list();
		}finally{
			closeSession(session);
		}
		return result;
	}


	@Override
	public List<UserView> getAllUsers(String sortName, int startIndex, int pageSize) throws Exception{
		List<UserView> projectList = new ArrayList<UserView>();
		List<LoginAdmin> result = null;
		Session session = null;
//		String hql = "FROM Project";
		String hql = "SELECT * FROM w_user"; // WHERE CreationUser = '"+ UserRole.GROUP_ADMIN +"'";
		if (!StringUtils.isNullOrEmpty(sortName)){
			/*if (sortName.startsWith("createdDate")) {
				sortName = sortName.replace("createdDate", "CreationDate");
			}else if (sortName.startsWith("createdUser")) {
				sortName = sortName.replace("createdUser", "CreationUser");
			}*/
			hql += " ORDER BY " + sortName;
		}
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql).addEntity(LoginAdmin.class);
//			Query query = session.createQuery(hql);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			result = query.list();
			if (result.size() > 0){
//				 for (Iterator iterator = result.iterator(); iterator.hasNext();){
//					Project project = (Project) iterator.next(); 
//				for (Object[] row: rows){
//					Project project = (Project)row[1];
				for (LoginAdmin user : result) {
					UserView userView = new UserView();
					BeanUtils.copyProperties(user, userView);
					//projectView.setPricePlanStatus(0);
				//	projectView.setStatusName(ProjectStatus.getProjectStatus(projectView.getStatus()).getStatusName());
					projectList.add(userView);
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return projectList;
	}

	
	public long countAllUser() throws Exception{
		long totalRecords = 0;
		String sql = "SELECT COUNT(p.id) FROM w_user p";
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(sql);
			List<Object> resultSet = query.list();
			totalRecords = Long.parseLong(resultSet.get(0).toString());
		}finally{ 
			closeSession(session);
		}
		return totalRecords;
	}
	
	public LoginAdmin getUser(String userName) throws Exception {
		LoginAdmin user = null;
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			user = (LoginAdmin)session.get(LoginAdmin.class, userName);
		}finally{
			closeSession(session);
		}
		return user;
	}
	
	
	@Override
	public List<UserView> getAllDanhSachNguoiDung(objSearchUser objUser,String userLogin,String maCQ, int role,String sortName, int startIndex, int pageSize) throws Exception{
		List<UserView> projectList = new ArrayList<UserView>();
		List<LoginAdmin> result = null;
		Session session = null;
//		String hql = "FROM Project";
		String hql = "SELECT * FROM w_user WHERE 1 = 1 "; // WHERE CreationUser = '"+ UserRole.GROUP_ADMIN +"'";
		hql += " AND Username != '" + userLogin +"'";
		if (!StringUtils.isNullOrEmpty(objUser.getUsername())){
			hql += " AND Username = '" + objUser.getUsername() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objUser.getFullName())){
			hql += " AND FullName like '%" + objUser.getFullName() +"%'";
		}
		if (!StringUtils.isNullOrEmpty(objUser.getRole())){			
			hql += " AND Role = " + Integer.parseInt(objUser.getRole());
			if(role != 1){
				hql += " AND MaCQT like '"+maCQ+"%'" ;
			}
		}else{
			hql += " AND Role != 1 " ;
			if(role == 1){
				hql += " AND Role > 1" ;
				
			}else{
				if(role == 2){
					hql += " AND Role >=2 " ;
					
				}else if(role == 3){
					hql += " AND Role >=3 " ;
				}else if(role == 4){
					hql += " AND Role =4" ;
				}
				hql += " AND MaCQT like '"+maCQ+"%'" ;
			}
				
		}
		
		//hql += " AND MaCQT like '"+maCQ+"%'" ;
		
		if (!StringUtils.isNullOrEmpty(sortName)){
			/*if (sortName.startsWith("createdDate")) {
				sortName = sortName.replace("createdDate", "CreationDate");
			}else if (sortName.startsWith("createdUser")) {
				sortName = sortName.replace("createdUser", "CreationUser");
			}*/
			hql += " ORDER BY " + sortName;
		}
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql).addEntity(LoginAdmin.class);
//			Query query = session.createQuery(hql);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			result = query.list();
			if (result.size() > 0){
//				 for (Iterator iterator = result.iterator(); iterator.hasNext();){
//					Project project = (Project) iterator.next(); 
//				for (Object[] row: rows){
//					Project project = (Project)row[1];
				for (LoginAdmin user : result) {
					UserView userView = new UserView();
					BeanUtils.copyProperties(user, userView);
					//projectView.setPricePlanStatus(0);
				//	projectView.setStatusName(ProjectStatus.getProjectStatus(projectView.getStatus()).getStatusName());
					projectList.add(userView);
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return projectList;
	}

	public long countDanhSachNguoiDung(objSearchUser objUser,String userLogin,String maCQ,int role) throws Exception{
		long totalRecords = 0;
		String sql = "SELECT COUNT(p.id) FROM w_user p WHERE 1 = 1 ";
		sql += " AND p.Username != '" + userLogin +"'";
		if (!StringUtils.isNullOrEmpty(objUser.getUsername())){
			sql += " AND p.Username = '" + objUser.getUsername() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objUser.getFullName())){
			sql += " AND p.FullName like '%" + objUser.getFullName() +"%'";
		}
		if (!StringUtils.isNullOrEmpty(objUser.getRole())){
			//sql += " AND p.Role = " + Integer.parseInt(objUser.getRole());
			
				sql += " AND p.Role = " + Integer.parseInt(objUser.getRole());
				if(role != 1){
					sql += " AND p.MaCQT like '"+maCQ+"%'" ;
				}
		}else{
			sql += " AND p.Role != 1 " ;
			
			if(role == 1){
				sql += "AND p.Role > 1" ;
			}else{
				if(role == 2){
					sql += "AND p.Role >=2 " ;
				}else if(role == 3){
					sql += "AND p.Role >=3 " ;
				}else if(role == 4){
					sql += "AND p.Role =4" ;
				}
				sql += " AND p.MaCQT like '"+maCQ+"%'" ;
			}
				
		}
		
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(sql);
			List<Object> resultSet = query.list();
			totalRecords = Long.parseLong(resultSet.get(0).toString());
		}finally{ 
			closeSession(session);
		}
		return totalRecords;
	}
	
	public int insertUser(LoginAdmin user, String[] Arr) throws Exception {
//		String projectId = null;
		Session session = null;
		Transaction tx = null;
		try{			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();			
			//session.save(user);	
			createUserRule(user,session,Arr);
			
			tx.commit();
			
		}catch(Exception e){
			rollbackTransaction(tx);
			e.printStackTrace();
			return 2;
		}finally{
			closeSession(session);
		}
		return 1;
	}
	
	private void createUserRule(LoginAdmin user, Session session, String[] Arr) throws Exception {
	    int id =  (Integer)session.save(user);	
	    LoginAdmin objUser = null ;
	    objUser  = (LoginAdmin) session.load(LoginAdmin.class, new Integer(id));
	    if(objUser != null){
	    	UserRule objRule = userRuleDAO.getUserRule(objUser.getUsername());
	    	if(objRule != null){
	    		int deleteRule = userRuleDAO.deleteUserRuleNew(objUser.getUsername(), session);
	    	}
		     userRuleDAO.insertUserRuleNew(objUser.getUsername(),Arr,session);
	    }
		
	}
	
	
	public int updateUser(LoginAdmin user,String[] Arr) throws Exception {
		Session session = null;
		org.hibernate.Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			updateUserAndRule(user,session,Arr);
//			session.update(project);
			tx.commit();
		}catch(Exception e){
			rollbackTransaction(tx);
			e.printStackTrace();
			return 2 ;
		}finally{
			closeSession(session);
		}
		return 1 ;
	}
	
	private void updateUserAndRule( LoginAdmin user, Session session,String[] Arr) throws Exception {
		session.update(user) ;									 
		UserRule objRule = userRuleDAO.getUserRule(user.getUsername());
    	if(objRule != null){
    		int deleteRule = userRuleDAO.deleteUserRuleNew(user.getUsername(), session);
    	}
	     userRuleDAO.insertUserRuleNew(user.getUsername(),Arr,session);
	}
	
	
	@Override
	public int deleteUSer(LoginAdmin user) throws Exception {
		Session session = null;
		Transaction tx = null;
		try{
			String  userName = user.getUsername();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();			
			Query query = session.createQuery("delete from LoginAdmin p where p.username = :userName");
			query.setParameter("userName", userName);
			query.executeUpdate();
			
			query =session.createQuery("DELETE FROM UserRule WHERE userName = :userName");
			query.setParameter("userName", userName);
			query.executeUpdate();
			tx.commit();
		}catch(Exception e){
			rollbackTransaction(tx);
			e.printStackTrace();
			return 2 ;
		}finally{
			closeSession(session);
		}
		return 1 ;
	}
	
	public int updateUserStatus(LoginAdmin user) throws Exception {
		Session session = null;
		org.hibernate.Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(user) ;
//			session.update(project);
			tx.commit();
		}catch(Exception e){
			rollbackTransaction(tx);
			e.printStackTrace();
			return 2 ;
		}finally{
			closeSession(session);
		}
		return 1 ;
	}
	
}
