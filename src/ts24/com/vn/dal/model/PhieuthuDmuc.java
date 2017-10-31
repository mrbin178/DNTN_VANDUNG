package ts24.com.vn.dal.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
@Entity
@Table(name = "phieuthu_dmuc")
public class PhieuthuDmuc implements Serializable {

	private int id;
	private String noiDung;
	private String ma;
	private BigDecimal soTien;
	private String thueSuat;
	private String ghiChu;
	private String ngayTao;

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
	@JsonProperty("NoiDung")
	@Column(name = "NoiDung")
	public String getNoiDung() {
		return this.noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	@Basic
	@JsonProperty("Ma")
	@Column(name = "Ma")
	public String getMa() {
		return this.ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	@Basic
	@JsonProperty("SoTien")
	@Column(name = "SoTien")
	public BigDecimal getSoTien() {
		return this.soTien;
	}

	public void setSoTien(BigDecimal soTien) {
		this.soTien = soTien;
	}

	@Basic
	@JsonProperty("ThueSuat")
	@Column(name = "ThueSuat")
	public String getThueSuat() {
		return this.thueSuat;
	}

	public void setThueSuat(String thueSuat) {
		this.thueSuat = thueSuat;
	}

	@Basic
	@JsonProperty("GhiChu")
	@Column(name = "GhiChu")
	public String getGhiChu() {
		return this.ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Basic
	@JsonProperty("NgayTao")
	@Column(name = "NgayTao")
	public String getNgayTao() {
		return this.ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

}
