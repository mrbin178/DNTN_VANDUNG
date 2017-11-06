/*
 * @project: working
 *@author:   ngangv
 *May 7, 2010 10:03:44 AM
 */
package satthepvandung.dal.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "w_user")
@SuppressWarnings("serial")

public class LoginAdmin {

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
	

	/**
	 * <p>
	 * </p>
	 * 
	 * @return id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@JsonProperty("ID")
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            new value for id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return Username
	 */
	@Column(name="Username", nullable=false)
	@JsonProperty("Username")
	@NotEmpty
		public String getUsername() {
		return username;
	}

	/**
	 * @param Username
	 *            new value for Username
	 */
	public void setUsername(String Username) {
		this.username = Username;
	}



	
	@Column(name="FullName", nullable=false)
	@JsonProperty("FullName")
	
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param Status
	 *            new value for FullName
	 */
	public void setFullName(String FullName) {
		this.fullName = FullName;
	}
	@Column(name="Email", nullable=false)
	@JsonProperty("Email")
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="MaCQT", nullable=false)
	@JsonProperty("MaCQT")
	
	public String getMacqt() {
		return macqt;
	}

	public void setMacqt(String macqt) {
		this.macqt = macqt;
	}
	@Column(name="TenCQT", nullable=false)
	@JsonProperty("TenCQT")
	
	public String getTencqt() {
		return tencqt;
	}

	public void setTencqt(String tencqt) {
		this.tencqt = tencqt;
	}
	@Column(name="LimitMST", nullable=false)
	@JsonProperty("LimitMST")
	
	public String getLimitMST() {
		return limitMST;
	}

	public void setLimitMST(String limitMST) {
		this.limitMST = limitMST;
	}
	
	@Column(name="MaTinh", nullable=false)
	@JsonProperty("MaTinh")
	
	public String getMatinh() {
		return matinh;
	}

	public void setMatinh(String matinh) {
		this.matinh = matinh;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Column(name="iLimit", nullable=false)
	@JsonProperty("Limit")
	public int getLimit() {
		return limit;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="iStatus", nullable=false)
	@JsonProperty("Status")
	public int getStatus() {
		return status;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}

	@Column(name="Role", nullable=false)
	@JsonProperty("Role")
	public int getRole() {
		return role;
	}

	/**
	 * @param matKhau the matKhau to set
	 */
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Column(name="MatKhau", nullable=false)
	@JsonProperty("MatKhau")
	public String getMatKhau() {
		return matKhau;
	}
	
	
}
