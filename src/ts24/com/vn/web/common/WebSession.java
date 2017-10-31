package ts24.com.vn.web.common;

import java.util.List;

import ts24.com.vn.dal.model.UserRule;
import ts24.com.vn.model.UserInfo;
import ts24.com.vn.web.validator.model.UserInfoViewID;

public class WebSession {
	
	private String showFieldMaNhanHD;
	private UserInfo currentUser;
	private UserInfoViewID userInfoViewID; 
	private List<UserRule> listRule;
	public String getShowFieldMaNhanHD() {
		return showFieldMaNhanHD;
	}
	public void setShowFieldMaNhanHD(String showFieldMaNhanHD) {
		this.showFieldMaNhanHD = showFieldMaNhanHD;
	}
	public UserInfo getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(UserInfo currentUser) {
		this.currentUser = currentUser;
	}
	public UserInfoViewID getUserInfoViewID() {
		return userInfoViewID;
	}
	public void setUserInfoViewID(UserInfoViewID userInfoViewID) {
		this.userInfoViewID = userInfoViewID;
	}
	public List<UserRule> getListRule() {
		return listRule;
	}
	public void setListRule(List<UserRule> listRule) {
		this.listRule = listRule;
	}
}
