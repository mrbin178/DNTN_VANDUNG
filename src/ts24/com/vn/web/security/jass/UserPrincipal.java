package ts24.com.vn.web.security.jass;

import java.security.Principal;
import java.util.Date;
import java.util.Set;

import ts24.com.vn.model.UserRole;

public class UserPrincipal implements Principal, java.io.Serializable  {

	private static final long serialVersionUID = 6783625715549585851L;
	/**
	 * 
	 */
	private int userId;
	private String userName;
	private String password;
	private Date loginTime;
	private Set<UserRole> userRoles;
	
	public UserPrincipal(String name, Set<UserRole> role) {
		if (role == null || name == null)
		    throw new NullPointerException("illegal null input");
		this.userRoles = role;
		this.userName = name;
		this.loginTime = new Date();
	}

	@Override
	public String getName() {
		return userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public Set<UserRole> getRoleId() {
		return this.userRoles;
	}

	public void setRoleId(Set<UserRole> roleId) {
		this.userRoles = roleId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getEditRole(){
		return this.userRoles.iterator().next();
	}
}
