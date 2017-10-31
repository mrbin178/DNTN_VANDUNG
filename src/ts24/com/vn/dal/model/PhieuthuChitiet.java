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

@SuppressWarnings("serial")
@Entity
@Table(name = "phieuthu_chitiet")
public class PhieuthuChitiet implements Serializable {

	private int id;
	private String maHoaDon;
	private String maHang;
	private String tenHang;
	private String donViTinh;
	private String soLuong;
	private String donGia;
	private String donGiaGoc;
	private String thanhTien;
	private String thueSuat;
	private String tienThue;
	private String tongTien;
	private Boolean khuyenMai;
	private String ghiChu;
	private String loaiDieuChinh;

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
	@JsonProperty("MaHoaDon")
	@Column(name = "MaHoaDon")
	public String getMaHoaDon() {
		return this.maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	@Basic
	@JsonProperty("MaHang")
	@Column(name = "MaHang")
	public String getMaHang() {
		return this.maHang;
	}

	public void setMaHang(String maHang) {
		this.maHang = maHang;
	}

	@Basic
	@JsonProperty("TenHang")
	@Column(name = "TenHang")
	public String getTenHang() {
		return this.tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}
	
	@Basic
	@JsonProperty("DonViTinh")
	@Column(name = "DonViTinh")
	public String getDonViTinh() {
		return this.donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	@Basic
	@JsonProperty("SoLuong")
	@Column(name = "SoLuong")
	public String getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}

	@Basic
	@JsonProperty("DonGia")
	@Column(name = "DonGia")
	public String getDonGia() {
		return this.donGia;
	}

	public void setDonGia(String donGia) {
		this.donGia = donGia;
	}

	@Basic
	@JsonProperty("DonGiaGoc")
	@Column(name = "DonGiaGoc")
	public String getDonGiaGoc() {
		return this.donGiaGoc;
	}

	public void setDonGiaGoc(String donGiaGoc) {
		this.donGiaGoc = donGiaGoc;
	}

	@Basic
	@JsonProperty("ThanhTien")
	@Column(name = "ThanhTien")
	public String getThanhTien() {
		return this.thanhTien;
	}

	public void setThanhTien(String thanhTien) {
		this.thanhTien = thanhTien;
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
	@JsonProperty("TienThue")
	@Column(name = "TienThue")
	public String getTienThue() {
		return this.tienThue;
	}

	public void setTienThue(String tienThue) {
		this.tienThue = tienThue;
	}

	@Basic
	@JsonProperty("TongTien")
	@Column(name = "TongTien")
	public String getTongTien() {
		return this.tongTien;
	}

	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}

	@Basic
	@JsonProperty("KhuyenMai")
	@Column(name = "KhuyenMai")
	public Boolean getKhuyenMai() {
		return this.khuyenMai;
	}

	public void setKhuyenMai(Boolean khuyenMai) {
		this.khuyenMai = khuyenMai;
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
	@JsonProperty("LoaiDieuChinh")
	@Column(name = "LoaiDieuChinh")
	public String getLoaiDieuChinh() {
		return this.loaiDieuChinh;
	}

	public void setLoaiDieuChinh(String loaiDieuChinh) {
		this.loaiDieuChinh = loaiDieuChinh;
	}

}
