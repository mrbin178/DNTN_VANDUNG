package ts24.com.vn.dal.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "hoadon_chitiet" )
@SuppressWarnings( "serial" )
public class HoaDonChiTiet implements Serializable
{
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
	private int khuyenMai;
	private String ghiChu;
	private String loaiDieuChinh;
	
	
	@Basic
	@Id
	@Column( name = "ID" )
	public int getId( )
	{
		return id;
	}

	public void setId( int id )
	{
		this.id = id;
	}

	
	@Basic
	@Column( name = "MaHoaDon" )
	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	@Basic
	@Column( name = "MaHang" )
	public String getMaHang() {
		return maHang;
	}

	public void setMaHang(String maHang) {
		this.maHang = maHang;
	}
	@Basic
	@Column( name = "TenHang" )
	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}
	@Basic
	@Column( name = "DonViTinh" )
	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	@Basic
	@Column( name = "SoLuong" )
	public String getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}
	@Basic
	@Column( name = "DonGia" )
	public String getDonGia() {
		return donGia;
	}

	public void setDonGia(String donGia) {
		this.donGia = donGia;
	}
	@Basic
	@Column( name = "DonGiaGoc" )
	public String getDonGiaGoc() {
		return donGiaGoc;
	}

	public void setDonGiaGoc(String donGiaGoc) {
		this.donGiaGoc = donGiaGoc;
	}
	@Basic
	@Column( name = "ThanhTien" )
	public String getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(String thanhTien) {
		this.thanhTien = thanhTien;
	}
	@Basic
	@Column( name = "ThueSuat" )
	public String getThueSuat() {
		return thueSuat;
	}

	public void setThueSuat(String thueSuat) {
		this.thueSuat = thueSuat;
	}
	@Basic
	@Column( name = "TienThue" )
	public String getTienThue() {
		return tienThue;
	}

	public void setTienThue(String tienThue) {
		this.tienThue = tienThue;
	}
	@Basic
	@Column( name = "TongTien" )
	public String getTongTien() {
		return tongTien;
	}

	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}
	@Basic
	@Column( name = "KhuyenMai" )
	public int getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(int khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	@Basic
	@Column( name = "GhiChu" )
	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	@Basic
	@Column( name = "LoaiDieuChinh" )
	public String getLoaiDieuChinh() {
		return loaiDieuChinh;
	}

	public void setLoaiDieuChinh(String loaiDieuChinh) {
		this.loaiDieuChinh = loaiDieuChinh;
	}

	
	
}