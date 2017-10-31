package ts24.com.vn.dal.dao;

import java.util.List;

import org.hibernate.Session;

import ts24.com.vn.dal.model.UserRule;

public interface UserRuleDAO extends BaseDAO<UserRule>{
	 public void insertUserRule(UserRule userRule) throws Exception;
	 public void deleteUserRule(String user) throws Exception;
	 
	 public void insertUserRuleNew(String user,String[] Arr,Session session) throws Exception;
	 public int deleteUserRuleNew(String user,Session session) throws Exception;
	 public UserRule getUserRule(String userName) throws Exception;
	 public List<UserRule> getListUserRuleTheoUser(String maTinh) throws Exception;
	}
