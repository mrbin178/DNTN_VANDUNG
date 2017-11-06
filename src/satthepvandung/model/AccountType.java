package satthepvandung.model;

public enum AccountType {
	ACCOUNT_DEFAULT("ID", "Loại tài khoản mặc định"),
	ACCOUNT_GOOGLE("G", "Loại tài khoản google "),
	ACCOUNT_FACE("F", "Loại tài khoản facebook"),
	ACCOUNT_TWITTER("T", "Loại tài khoản Twitter");
		
	private String code;
	private String des;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	private AccountType(String code, String des) {
		this.code = code;
		this.des = des;
	}

	public static AccountType getStatusTypeById(String code){
		AccountType type = null;
		for (AccountType status : AccountType.values()){
			if (status.getCode().equals(code)){
				type = status;
				break;
			}
		}
		return type;
	}
}
