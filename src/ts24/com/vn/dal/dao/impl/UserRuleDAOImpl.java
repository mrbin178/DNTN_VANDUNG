package ts24.com.vn.dal.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ts24.com.vn.dal.dao.UserRuleDAO;
import ts24.com.vn.dal.model.UserRule;

public class UserRuleDAOImpl extends BaseDAOImpl<UserRule> implements UserRuleDAO{

	public void insertUserRule(UserRule userRule) throws Exception {
//		String projectId = null;
		Session session = null;
		Transaction tx = null;
		try{			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();			
			session.save(userRule);			
			tx.commit();
		}catch(Exception e){
			rollbackTransaction(tx);
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
	}

	
	@Override
	public void deleteUserRule(String user) throws Exception {
		Session session = null;
		Transaction tx = null;
		try{			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();			
			Query query = session.createQuery("delete from w_user_rule p where p.Username=:user");
			query.setParameter("user", user);
			query.executeUpdate();				
			tx.commit();
		}catch(Exception e){
			rollbackTransaction(tx);
			throw e;
		}finally{
			closeSession(session);
		}
	}
	
	public void insertUserRuleNew(String user,String[] Arr, Session session) throws Exception {
		if (Arr != null && !Arr.equals("")) {
			for (int j = 0; j < Arr.length; j++) {
				UserRule objRule = new UserRule();
				objRule.setRule(Arr[j]);
				objRule.setUserName(user);
				session.save(objRule);	
			}
		}
	
	}

	
	@Override
	public int deleteUserRuleNew(String user,Session session) throws Exception {
			int result = 0 ;		
			Query query = session.createQuery("delete from UserRule p where p.userName=:user");
			query.setParameter("user", user);
			result = query.executeUpdate(); 
			return result ;
		
	}
	
	/*public UserRule getUserRule(String userName) throws Exception {
		UserRule user = null;
		Session session = null;		
		try{
			session = sessionFactory.openSession();
			user = (UserRule)session.get(UserRule.class, userName);
		}catch (Exception e) {			
			return null;
		}
		return user;
	}*/
	
	 public List<UserRule> getListUserRuleTheoUser(String user) throws Exception {
			Session session = null;
			
			try{
				session = sessionFactory.openSession();
				Query query = session.createQuery("FROM UserRule where userName=:user  ORDER BY userName ASC");				
				query.setParameter("user", user);
				List<UserRule> listRule = query.list();
				if (listRule != null && listRule.size() > 0){
					return listRule;
				}
			}finally{
				closeSession(session);
			}
			return null;
		}
	 
	 @Override
		public UserRule getUserRule(String userNam) throws Exception {
			List<UserRule> result = null;
			Session session = null;
			
			try{
				session = sessionFactory.openSession();
				String hql = "FROM UserRule WHERE userName =:userNam ORDER BY id DESC";
				Query query = session.createQuery(hql);
				query.setParameter("userNam", userNam);
				result = query.list();
				if(result != null && result.size() > 0){
					return result.get(0);
				}
			}finally{
				closeSession(session);
			}
			return null;
		}
	 
}
