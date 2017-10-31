package ts24.com.vn.service;

import java.util.List;

import ts24.com.vn.dal.model.UserRule;


public interface UserRuleService {
	public void createNewUserRule(UserRule userRule, String[] Arr) throws Exception;
	public void deleteUserRule( String user) throws Exception;
	public List<UserRule> getListUserRuleTheoUser(String user) throws Exception;
}
