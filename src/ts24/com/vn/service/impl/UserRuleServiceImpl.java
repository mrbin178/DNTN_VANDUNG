package ts24.com.vn.service.impl;

import java.util.List;

import ts24.com.vn.dal.dao.UserRuleDAO;
import ts24.com.vn.dal.dao.impl.UserRuleDAOImpl;
import ts24.com.vn.dal.model.CoQuanThue;
import ts24.com.vn.dal.model.UserRule;
import ts24.com.vn.service.UserRuleService;

public class UserRuleServiceImpl extends BaseServiceImpl<UserRule> implements UserRuleService{

	 private UserRuleDAO userRuleDao;
	    
	    public UserRuleServiceImpl(UserRuleDAO dao){
	    	this.userRuleDao = dao;
	    }
	    
	    public UserRuleServiceImpl(){
	    	this.userRuleDao = new UserRuleDAOImpl();
	    }
	    
	public void createNewUserRule(UserRule userRule, String[] Arr) throws Exception {
		userRuleDao.insertUserRule(userRule);
	}

	public void deleteUserRule(String user) throws Exception {
		userRuleDao.deleteUserRule(user);
	}
	
	@Override
	public List<UserRule> getListUserRuleTheoUser(String user) throws Exception {
		// TODO Auto-generated method stub
		return userRuleDao.getListUserRuleTheoUser(user);
	}
}
