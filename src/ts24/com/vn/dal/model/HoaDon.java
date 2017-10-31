package ts24.com.vn.dal.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table( name = "hoadon" )
@SuppressWarnings( "serial" )
public class HoaDon implements Serializable
{
	private String id;
	private String idHoaDon;
	private String maSoThue;
	private String hoTenNguoiBan;
	private String diaChiNguoiBan;
	private String dienThoaiNguoiBan;
	private String emailNguoiBan;
	private String hoTenNguoiMua;
	private String mstNguoiMua;
	private String diaChiNguoiMua;
	private String dienThoaiNguoiMua;
	private String emailNguoiMua;
	private String mauHDon;
	private String soThuTu;
	private String kyHieuHDon;
	private String soHoaDon;
	private String soTien;
	private String ngayXHDon;
	private String ngayKyNguoiMua;
	private String ngayCapNhat;
	private int loaiHDon;
	private int doiTuong;
	private String fileHDon;
	private String fileBBan;
	private int tinhTrangKyNguoiBan;
	private int tinhTrangHDon;
	private String NgayTinhTrangHDon;
	private int tinhTrangKyBBan;
	private String ngayKyNguoiBan;
	private int tinhTrangKyNguoiMua;
	private String ngayTao;
	private String NgayTinhTrangBBan;
	private int tinhTrangXacThuc;
	private String tienHang;
	private String tienThue;
	private String maKQXacThuc;
	private String moTaKQXacThuc;
	private String executeTime;
	private String soHDonXacThuc;
	private String maHDonXacThuc;
	private String maNhanHDon;
	private String maCQT;
//	private Set<HoaDonChiTiet> hoaDonChiTiets;
	private int loaWS;
	private int nam;
	
	private String FKey;
	private String barCode;
	private int tinhTrangHienThi;
	private int tinhTrangDongBo;
	private String status;
	
	@Basic
	@Id
	@Column( name = "ID" )
	@JsonProperty("ID")
	public String getId( )
	{
		return id;
	}

	public void setId( String id )
	{
		this.id = id;
	}

	@Basic
	@Column( name = "IdHoaDon" )
	@JsonProperty("IdHoaDon")
	public String getIdHoaDon( )
	{
		return idHoaDon;
	}

	public void setIdHoaDon( String idHoaDon )
	{
		this.idHoaDon = idHoaDon;
	}

	@Basic
	@Column( name = "MaSoThue" )
	@JsonProperty("MaSoThue")
	public String getMaSoThue( )
	{
		return maSoThue;
	}

	public void setMaSoThue( String maSoThue )
	{
		this.maSoThue = maSoThue;
	}

	@Basic
	@Column( name = "HoTenNguoiBan" )
	@JsonProperty("HoTenNguoiBan")
	public String getHoTenNguoiBan( )
	{
		return hoTenNguoiBan;
	}

	public void setHoTenNguoiBan( String hoTenNguoiBan )
	{
		this.hoTenNguoiBan = hoTenNguoiBan;
	}

	@Basic
	@Column( name = "DiaChiNguoiBan" )
	@JsonProperty("DiaChiNguoiBan")
	public String getDiaChiNguoiBan( )
	{
		return diaChiNguoiBan;
	}

	public void setDiaChiNguoiBan( String diaChiNguoiBan )
	{
		this.diaChiNguoiBan = diaChiNguoiBan;
	}

	@Basic
	@Column( name = "DienThoaiNguoiBan" )
	@JsonProperty("DienThoaiNguoiBan")
	public String getDienThoaiNguoiBan( )
	{
		return dienThoaiNguoiBan;
	}

	public void setDienThoaiNguoiBan( String dienThoaiNguoiBan )
	{
		this.dienThoaiNguoiBan = dienThoaiNguoiBan;
	}

	@Basic
	@Column( name = "EmailNguoiBan" )
	@JsonProperty("EmailNguoiBan")
	public String getEmailNguoiBan( )
	{
		return emailNguoiBan;
	}

	public void setEmailNguoiBan( String emailNguoiBan )
	{
		this.emailNguoiBan = emailNguoiBan;
	}

	@Basic
	@Column( name = "HoTenNguoiMua" )
	@JsonProperty("HoTenNguoiMua")
	public String getHoTenNguoiMua( )
	{
		return hoTenNguoiMua;
	}

	public void setHoTenNguoiMua( String hoTenNguoiMua )
	{
		this.hoTenNguoiMua = hoTenNguoiMua;
	}

	@Basic
	@Column( name = "DiaChiNguoiMua" )
	@JsonProperty("DiaChiNguoiMua")
	public String getDiaChiNguoiMua( )
	{
		return diaChiNguoiMua;
	}

	public void setDiaChiNguoiMua( String diaChiNguoiMua )
	{
		this.diaChiNguoiMua = diaChiNguoiMua;
	}

	@Basic
	@Column( name = "DienThoaiNguoiMua" )
	@JsonProperty("DienThoaiNguoiMua")
	public String getDienThoaiNguoiMua( )
	{
		return dienThoaiNguoiMua;
	}

	public void setDienThoaiNguoiMua( String dienThoaiNguoiMua )
	{
		this.dienThoaiNguoiMua = dienThoaiNguoiMua;
	}

	@Basic
	@Column( name = "EmailNguoiMua" )
	@JsonProperty("EmailNguoiMua")
	public String getEmailNguoiMua( )
	{
		return emailNguoiMua;
	}

	public void setEmailNguoiMua( String emailNguoiMua )
	{
		this.emailNguoiMua = emailNguoiMua;
	}

	@Basic
	@Column( name = "KyHieuHDon" )
	@JsonProperty("KyHieuHDon")
	public String getKyHieuHDon( )
	{
		return kyHieuHDon;
	}

	public void setKyHieuHDon( String kyHieuHDon )
	{
		this.kyHieuHDon = kyHieuHDon;
	}

	@Basic
	@Column( name = "SoHoaDon" )
	@JsonProperty("SoHoaDon")
	public String getSoHoaDon( )
	{
		return soHoaDon;
	}

	public void setSoHoaDon( String soHoaDon )
	{
		this.soHoaDon = soHoaDon;
	}

	@Basic
	@Column( name = "MauHDon" )
	@JsonProperty("MauHDon")
	public String getMauHDon( )
	{
		return mauHDon;
	}

	public void setMauHDon( String mauHDon )
	{
		this.mauHDon = mauHDon;
	}

	@Basic
	@Column( name = "SoTien" )
	@JsonProperty("SoTien")
	public String getSoTien( )
	{
		return soTien;
	}

	public void setSoTien( String soTien )
	{
		this.soTien = soTien;
	}

	@Basic
	@Column( name = "NgayXHDon" )
	@JsonProperty("NgayXHDon")
	public String getNgayXHDon( )
	{
		return ngayXHDon;
	}

	public void setNgayXHDon( String ngayXHDon )
	{
		this.ngayXHDon = ngayXHDon;
	}

	@Basic
	@Column( name = "NgayCapNhat" )
	@JsonProperty("NgayCapNhat")
	public String getNgayCapNhat( )
	{
		return ngayCapNhat;
	}

	public void setNgayCapNhat( String ngayCapNhat )
	{
		this.ngayCapNhat = ngayCapNhat;
	}

	@Basic
	@Column( name = "LoaiHDon" )
	@JsonProperty("LoaiHDon")
	public int getLoaiHDon( )
	{
		return loaiHDon;
	}

	public void setLoaiHDon( int loaiHDon )
	{
		this.loaiHDon = loaiHDon;
	}

	@Basic
	@Column( name = "DoiTuong" )
	@JsonProperty("DoiTuong")
	public int getDoiTuong( )
	{
		return doiTuong;
	}

	public void setDoiTuong( int doiTuong )
	{
		this.doiTuong = doiTuong;
	}


	@Basic
	@Column( name = "TinhTrangHDon" )
	@JsonProperty("TinhTrangHDon")
	public int getTinhTrangHDon( )
	{
		return tinhTrangHDon;
	}

	public void setTinhTrangHDon( int tinhTrangHDon )
	{
		this.tinhTrangHDon = tinhTrangHDon;
	}

	@Basic
	@Column( name = "SoThuTu" )
	@JsonProperty("SoThuTu")
	public String getSoThuTu( )
	{
		return soThuTu;
	}

	public void setSoThuTu( String soThuTu )
	{
		this.soThuTu = soThuTu;
	}
	
	@Basic
	@Column( name = "MSTNguoiMua" )
	@JsonProperty("MSTNguoiMua")
	public String getMstNguoiMua( )
	{
		return mstNguoiMua;
	}

	public void setMstNguoiMua( String mstNguoiMua )
	{
		this.mstNguoiMua = mstNguoiMua;
	}

	@Basic
	@Column( name = "NgayKyNguoiMua" )
	@JsonProperty("NgayKyNguoiMua")
	public String getNgayKyNguoiMua( )
	{
		return ngayKyNguoiMua;
	}

	public void setNgayKyNguoiMua( String ngayKyNguoiMua )
	{
		this.ngayKyNguoiMua = ngayKyNguoiMua;
	}

	@Basic
	@Column( name = "FileHDon" )
	@JsonProperty("FileHDon")
	public String getFileHDon( )
	{
		return fileHDon;
	}

	public void setFileHDon( String fileHDon )
	{
		this.fileHDon = fileHDon;
	}

	@Basic
	@Column( name = "FileBBan" )
	@JsonProperty("FileBBan")
	public String getFileBBan( )
	{
		return fileBBan;
	}

	public void setFileBBan( String fileBBan )
	{
		this.fileBBan = fileBBan;
	}

	@Basic
	@Column( name = "TinhTrangKyNguoiBan" )
	@JsonProperty("TinhTrangKyNguoiBan")
	public int getTinhTrangKyNguoiBan( )
	{
		return tinhTrangKyNguoiBan;
	}

	public void setTinhTrangKyNguoiBan( int tinhTrangKyNguoiBan )
	{
		this.tinhTrangKyNguoiBan = tinhTrangKyNguoiBan;
	}

	@Basic
	@Column( name = "NgayTinhTrangHDon" )
	@JsonProperty("NgayTinhTrangHDon")
	public String getNgayTinhTrangHDon( )
	{
		return NgayTinhTrangHDon;
	}

	public void setNgayTinhTrangHDon( String ngayTinhTrangHDon )
	{
		NgayTinhTrangHDon = ngayTinhTrangHDon;
	}

	@Basic
	@Column( name = "TinhTrangKyBBan" )
	@JsonProperty("TinhTrangKyBBan")
	public int getTinhTrangKyBBan( )
	{
		return tinhTrangKyBBan;
	}

	public void setTinhTrangKyBBan( int tinhTrangKyBBan )
	{
		this.tinhTrangKyBBan = tinhTrangKyBBan;
	}

	@Basic
	@Column( name = "NgayKyNguoiBan" )
	@JsonProperty("NgayKyNguoiBan")
	public String getNgayKyNguoiBan( )
	{
		return ngayKyNguoiBan;
	}

	public void setNgayKyNguoiBan( String ngayKyNguoiBan )
	{
		this.ngayKyNguoiBan = ngayKyNguoiBan;
	}

	@Basic
	@Column( name = "TinhTrangKyNguoiMua" )
	@JsonProperty("TinhTrangKyNguoiMua")
	public int getTinhTrangKyNguoiMua( )
	{
		return tinhTrangKyNguoiMua;
	}

	public void setTinhTrangKyNguoiMua( int tinhTrangKyNguoiMua )
	{
		this.tinhTrangKyNguoiMua = tinhTrangKyNguoiMua;
	}
	
	@Basic
	@Column( name = "NgayTao" )
	@JsonProperty("NgayTao")
	public String getNgayTao( )
	{
		return ngayTao;
	}

	public void setNgayTao( String ngayTao )
	{
		this.ngayTao = ngayTao;
	}

	
	public void setNgayTinhTrangBBan(String ngayTinhTrangBBan) {
		NgayTinhTrangBBan = ngayTinhTrangBBan;
	}

	@Basic
	@Column( name = "NgayTinhTrangBBan" )
	@JsonProperty("NgayTinhTrangBBan")
	public String getNgayTinhTrangBBan() {
		return NgayTinhTrangBBan;
	}

	@Basic
	@Column( name = "TinhTrangXacThuc" )
	@JsonProperty("TinhTrangXacThuc")
	public int getTinhTrangXacThuc( )
	{
		return tinhTrangXacThuc;
	}

	public void setTinhTrangXacThuc( int tinhTrangXacThuc )
	{
		this.tinhTrangXacThuc = tinhTrangXacThuc;
	}
	@Basic
	@Column( name = "TienHang" )
	@JsonProperty("TienHang")
	public String getTienHang( )
	{
		return tienHang;
	}

	public void setTienHang( String tienHang )
	{
		this.tienHang = tienHang;
	}
	@Basic
	@Column( name = "TienThue" )
	@JsonProperty("TienThue")
	public String getTienThue( )
	{
		return tienThue;
	}

	public void setTienThue( String tienThue )
	{
		this.tienThue = tienThue;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	@Basic
	@Column( name = "executeTime" )
	@JsonProperty("executeTime")
	public String getExecuteTime() {
		return executeTime;
	}

	
	public void setMaKQXacThuc(String maKQXacThuc) {
		this.maKQXacThuc = maKQXacThuc;
	}

	@Basic
	@Column( name = "MaKQXacThuc" )
	@JsonProperty("MaKQXacThuc")
	public String getMaKQXacThuc() {
		return maKQXacThuc;
	}

	
	public void setMoTaKQXacThuc(String moTaKQXacThuc) {
		this.moTaKQXacThuc = moTaKQXacThuc;
	}

	@Basic
	@Column( name = "MoTaKQXacThuc" )
	@JsonProperty("MoTaKQXacThuc")
	public String getMoTaKQXacThuc() {
		return moTaKQXacThuc;
	}

	
	public void setSoHDonXacThuc(String soHDonXacThuc) {
		this.soHDonXacThuc = soHDonXacThuc;
	}

	@Basic
	@Column( name = "SoHDonXacThuc" )
	@JsonProperty("SoHDonXacThuc")
	public String getSoHDonXacThuc() {
		return soHDonXacThuc;
	}

	
	public void setMaHDonXacThuc(String maHDonXacThuc) {
		this.maHDonXacThuc = maHDonXacThuc;
	}

	@Basic
	@Column( name = "MaHDonXacThuc" )
	@JsonProperty("MaHDonXacThuc")
	public String getMaHDonXacThuc() {
		return maHDonXacThuc;
	}
	
	public void setMaNhanHDon(String maNhanHDon) {
		this.maNhanHDon = maNhanHDon;
	}

	@Basic
	@Column( name = "MaNhanHDon" )
	@JsonProperty("MaNhanHDon")
	public String getMaNhanHDon() {
		return maNhanHDon;
	}

	
	@Basic
	@Column( name = "MaCQT" )
	@JsonProperty("MaCQT")
	public String getMaCQT() {
		return maCQT;
	}

	public void setMaCQT(String maCQT) {
		this.maCQT = maCQT;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "maHoaDon")
//	@LazyCollection(LazyCollectionOption.EXTRA)
//	@JsonIgnore
//	public Set<HoaDonChiTiet> getHoaDonChiTiets() {
//		return hoaDonChiTiets;
//	}
//
//	public void setHoaDonChiTiets(Set<HoaDonChiTiet> hoaDonChiTiets) {
//		this.hoaDonChiTiets = hoaDonChiTiets;
//	}

	@Basic
	@Column( name = "LoaiWS" )
	public int getLoaWS() {
		return loaWS;
	}

	public void setLoaWS(int loaWS) {
		this.loaWS = loaWS;
	}
	@Basic
	@Column( name = "Nam" )
	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}
	@Basic
	@Column( name = "FKey" )
	public String getFKey() {
		return FKey;
	}

	public void setFKey(String fKey) {
		FKey = fKey;
	}

	@Basic
	@Column( name = "Barcode" )
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	@Basic
	@Column( name = "TinhTrangHienThi" )
	public int getTinhTrangHienThi() {
		return tinhTrangHienThi;
	}

	public void setTinhTrangHienThi(int tinhTrangHienThi) {
		this.tinhTrangHienThi = tinhTrangHienThi;
	}

	@Basic
	@Column( name = "TinhTrangDongBo" )
	public int getTinhTrangDongBo() {
		return tinhTrangDongBo;
	}

	public void setTinhTrangDongBo(int tinhTrangDongBo) {
		this.tinhTrangDongBo = tinhTrangDongBo;
	}

	@Basic
	@Column( name = "Status" )
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}