package satthepvandung.model;

public class FormUserInfo {

	private int id;
	private String username;
	private String matKhau;
	private int role;
	private int status;
	private String fullName;
	private String email;
	private String macqt, tencqt;
	private String limitMST;	
	private String matinh;	
	private int limit = 0;
	private String nhomQuyen ;
	private String matKhauCu;
	private String matKhauMoi;
	private String matKhauMoiRe;
			
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMacqt() {
		return macqt;
	}
	public void setMacqt(String macqt) {
		this.macqt = macqt;
	}
	public String getTencqt() {
		return tencqt;
	}
	public void setTencqt(String tencqt) {
		this.tencqt = tencqt;
	}
	public String getLimitMST() {
		return limitMST;
	}
	public void setLimitMST(String limitMST) {
		this.limitMST = limitMST;
	}
	public String getMatinh() {
		return matinh;
	}
	public void setMatinh(String matinh) {
		this.matinh = matinh;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public void setNhomQuyen(String nhomQuyen) {
		this.nhomQuyen = nhomQuyen;
	}
	
	public String getNhomQuyen() {
		return nhomQuyen;
	}
	
	public String getMatKhauMoi() {
		return matKhauMoi;
	}
	
	public void setMatKhauMoi(String matKhauMoi) {
		this.matKhauMoi = matKhauMoi;
	}
	
	public String getMatKhauMoiRe() {
		return matKhauMoiRe;
	}
	
	public void setMatKhauMoiRe(String matKhauMoiRe) {
		this.matKhauMoiRe = matKhauMoiRe;
	}
	
	public String getMatKhauCu() {
		return matKhauCu;
	}
	
	public void setMatKhauCu(String matKhauCu) {
		this.matKhauCu = matKhauCu;
	}

	
}
