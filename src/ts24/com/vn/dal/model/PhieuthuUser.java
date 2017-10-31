package ts24.com.vn.dal.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "phieuthu_user")
@SuppressWarnings("serial")
public class PhieuthuUser implements Serializable {

	private int id;
	private String username;
	private String matKhau;
	private int role;
	private int istatus;
	private String fullName;
	private String mst;
	private String tenCongTy;
	private String email;
	private String userCreate;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	@Basic
	@JsonProperty("ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@JsonProperty("Username")
	@Column(name = "Username", length = 40)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Basic
	@JsonProperty("MatKhau")
	@Column(name = "MatKhau")
	public String getMatKhau() {
		return this.matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Basic
	@JsonProperty("Role")
	@Column(name = "Role")
	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Basic
	@JsonProperty("iStatus")
	@Column(name = "iStatus")
	public int getIstatus() {
		return this.istatus;
	}

	public void setIstatus(int istatus) {
		this.istatus = istatus;
	}

	@Basic
	@JsonProperty("FullName")
	@Column(name = "FullName")
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Basic
	@JsonProperty("Email")
	@Column(name = "Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "MST")
	@JsonProperty("MST")
	public String getMst() {
		return mst;
	}

	public void setMst(String mst) {
		this.mst = mst;
	}

	@Basic
	@Column(name = "TenCongTy")
	@JsonProperty("TenCongTy")
	public String getTenCongTy() {
		return tenCongTy;
	}

	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
	}

	@Basic
	@Column(name = "UserCreate")
	@JsonProperty("UserCreate")
	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

}
