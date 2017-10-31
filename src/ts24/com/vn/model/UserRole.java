package ts24.com.vn.model;

public enum UserRole {

	GROUP_ADMIN("Group_Admin") , GROUP_USER("Group_User");
	private String value;
	UserRole(String value){
		this.value = value;
	}
	public String getValue(){
		return this.value;
	}
	public static UserRole fromString(String text){
		for (UserRole element : UserRole.values()) {
			if(element.getValue().equalsIgnoreCase(text))
				return element;
		}
		return UserRole.GROUP_USER;
	}
}
