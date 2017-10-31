package ts24.com.vn.dal.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "a_coquanthue")
@SuppressWarnings("serial")
public class CoQuanThue implements Serializable {

	private Integer id;
	private String tenCoQuanThue;
	private String maCoQuanThue;
	private String maTinhThue;
	private String maThamChieu;
	private String maLoai;
	private String diaChiAnChi;
	private String dienThoai;
	private String email;
	private int kKQM;
	
	
	@Basic
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@JsonProperty("ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Basic
	@Column(name = "TenCoQuanThue", length = 500)
	@JsonProperty("TenCoQuanThue")
	public String getTenCoQuanThue() {
		return tenCoQuanThue;
	}

	public void setTenCoQuanThue(String tenCoQuanThue) {
		this.tenCoQuanThue = tenCoQuanThue;
	}

	@Basic
	@Column(name = "MaCoQuanThue", length = 10)
	@JsonProperty("MaCoQuanThue")
	public String getMaCoQuanThue() {
		return maCoQuanThue;
	}

	public void setMaCoQuanThue(String maCoQuanThue) {
		this.maCoQuanThue = maCoQuanThue;
	}

	@Basic
	@Column(name = "MaTinhThue", length = 10)
	@JsonProperty("MaTinhThue")
	public String getMaTinhThue() {
		return maTinhThue;
	}

	public void setMaTinhThue(String maTinhThue) {
		this.maTinhThue = maTinhThue;
	}

	@Basic
	@Column(name = "MaThamChieu", length = 10)
	@JsonProperty("MaThamChieu")
	public String getMaThamChieu() {
		return maThamChieu;
	}

	public void setMaThamChieu(String maThamChieu) {
		this.maThamChieu = maThamChieu;
	}

	@Basic
	@Column(name = "MaLoai", length = 10)
	@JsonProperty("MaLoai")
	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	@Basic
	@Column(name = "DiaChiAnChi")
	@JsonProperty("DiaChiAnChi")
	public String getDiaChiAnChi() {
		return diaChiAnChi;
	}

	public void setDiaChiAnChi(String diaChiAnChi) {
		this.diaChiAnChi = diaChiAnChi;
	}

	@Basic
	@Column(name = "DienThoai")
	@JsonProperty("DienThoai")
	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	@Basic
	@Column(name = "Email")
	@JsonProperty("Email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "KKQM")
	@JsonProperty("KKQM")
	public int getkKQM() {
		return kKQM;
	}

	public void setkKQM(int kKQM) {
		this.kKQM = kKQM;
	}

	

	
}
