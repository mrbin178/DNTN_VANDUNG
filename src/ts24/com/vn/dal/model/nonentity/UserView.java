/*
 * @project: working
 *@author:   ngangv
 *May 7, 2010 10:03:44 AM
 */
package ts24.com.vn.dal.model.nonentity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "w_user")
public class UserView implements Serializable{

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
	private int limit;

	@JsonProperty("ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("Username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String Username) {
		this.username = Username;
	}

	@JsonProperty("FullName")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String FullName) {
		this.fullName = FullName;
	}

	@JsonProperty("Email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("MaCQT")
	public String getMacqt() {
		return macqt;
	}

	public void setMacqt(String macqt) {
		this.macqt = macqt;
	}

	@JsonProperty("TenCQT")
	public String getTencqt() {
		return tencqt;
	}

	public void setTencqt(String tencqt) {
		this.tencqt = tencqt;
	}

	@JsonProperty("LimitMST")
	public String getLimitMST() {
		return limitMST;
	}

	public void setLimitMST(String limitMST) {
		this.limitMST = limitMST;
	}

	@JsonProperty("MaTinh")
	public String getMatinh() {
		return matinh;
	}

	public void setMatinh(String matinh) {
		this.matinh = matinh;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@JsonProperty("Limit")
	public int getLimit() {
		return limit;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@JsonProperty("Status")
	public int getStatus() {
		return status;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@JsonProperty("Role")
	public int getRole() {
		return role;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@JsonProperty("MatKhau")
	public String getMatKhau() {
		return matKhau;
	}

}
