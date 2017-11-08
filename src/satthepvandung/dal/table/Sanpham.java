package satthepvandung.dal.table;

import java.io.Serializable;

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

	private int id;
	private String giaBan;
	private String giaVon;
	private String maSanPham;
	private String tenSanPham;
	private int trangThai;
	private String ngayTao;
	private String ngayUpdate;
	private String donViTinh;
	private String tonKho;
	private String nguoiTao;
	private String nguoiCapNhat;

	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@JsonProperty("ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "GiaBan")
	@JsonProperty("GiaBan")
	public String getGiaBan() {
		return this.giaBan;
	}

	public void setGiaBan(String giaBan) {
		this.giaBan = giaBan;
	}

	@Column(name = "GiaVon")
	@JsonProperty("GiaVon")
	public String getGiaVon() {
		return this.giaVon;
	}

	public void setGiaVon(String giaVon) {
		this.giaVon = giaVon;
	}

	@Column(name = "MaSanPham")
	@JsonProperty("MaSanPham")
	public String getMaSanPham() {
		return this.maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	@Column(name = "TenSanPham")
	@JsonProperty("TenSanPham")
	public String getTenSanPham() {
		return this.tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	@Column(name = "TrangThai")
	@JsonProperty("TrangThai")
	public int getTrangThai() {
		return this.trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@Column(name = "NgayTao")
	@JsonProperty("NgayTao")
	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	@Column(name = "NgayUpdate")
	@JsonProperty("NgayUpdate")
	public String getNgayUpdate() {
		return ngayUpdate;
	}

	public void setNgayUpdate(String ngayUpdate) {
		this.ngayUpdate = ngayUpdate;
	}

	@Column(name = "DonViTinh")
	@JsonProperty("DonViTinh")
	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	@Column(name = "TonKho")
	@JsonProperty("TonKho")
	public String getTonKho() {
		return tonKho;
	}

	public void setTonKho(String tonKho) {
		this.tonKho = tonKho;
	}

	@Column(name = "NguoiTao")
	@JsonProperty("NguoiTao")
	public String getNguoiTao() {
		return nguoiTao;
	}

	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}

	@Column(name = "NguoiCapNhat")
	@JsonProperty("NguoiCapNhat")
	public String getNguoiCapNhat() {
		return nguoiCapNhat;
	}

	public void setNguoiCapNhat(String nguoiCapNhat) {
		this.nguoiCapNhat = nguoiCapNhat;
	}

}