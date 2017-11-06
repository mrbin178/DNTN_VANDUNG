package satthepvandung.dal.table;

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
@Table(name="sanpham")
@SuppressWarnings("serial")
public class Sanpham implements Serializable {

	private String id;
	private String giaBan;
	private String giaVon;
	private String maSanPham;
	private String tenSanPham;
	private int trangThai;
	private String ngayTao;
	private String ngayUpdate;

	@Basic
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@JsonProperty("ID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Basic
	@Column(name = "GiaBan")
	@JsonProperty("GiaBan")
	public String getGiaBan() {
		return this.giaBan;
	}

	public void setGiaBan(String giaBan) {
		this.giaBan = giaBan;
	}

	@Basic
	@Column(name = "GiaVon")
	@JsonProperty("GiaVon")
	public String getGiaVon() {
		return this.giaVon;
	}

	public void setGiaVon(String giaVon) {
		this.giaVon = giaVon;
	}

	@Basic
	@Column(name = "MaSanPham")
	@JsonProperty("MaSanPham")
	public String getMaSanPham() {
		return this.maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	@Basic
	@Column(name = "TenSanPham")
	@JsonProperty("TenSanPham")
	public String getTenSanPham() {
		return this.tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	@Basic
	@Column(name = "TrangThai")
	@JsonProperty("TrangThai")
	public int getTrangThai() {
		return this.trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@Basic
	@Column(name = "NgayTao")
	@JsonProperty("NgayTao")
	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	@Basic
	@Column(name = "NgayUpdate")
	@JsonProperty("NgayUpdate")
	public String getNgayUpdate() {
		return ngayUpdate;
	}

	public void setNgayUpdate(String ngayUpdate) {
		this.ngayUpdate = ngayUpdate;
	}

}