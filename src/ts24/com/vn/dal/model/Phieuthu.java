package ts24.com.vn.dal.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
@Entity
@Table(name = "phieuthu")
public class Phieuthu implements Serializable {

	private String id;
	private String idHoaDon;
	private String maSoThue;
	private String hoTenNguoiBan;
	private String diaChiNguoiBan;
	private String dienThoaiNguoiBan;
	private String emailNguoiBan;
	private String hoTenNguoiMua;
	private String mstnguoiMua;
	private String diaChiNguoiMua;
	private String dienThoaiNguoiMua;
	private String emailNguoiMua;
	private String mauHdon;
	private String soThuTu;
	private String kyHieuHdon;
	private String soHoaDon;
	private Double soTien;
	private String ngayXhdon;
	private String ngayKyNguoiMua;
	private int loaiHdon;
	private int doiTuong;
	private String fileHdon;
	private String fileBban;
	private int tinhTrangKyNguoiBan;
	private int tinhTrangHdon;
	private String ngayTinhTrangHdon;
	private int tinhTrangKyBban;
	private String ngayKyNguoiBan;
	private int tinhTrangKyNguoiMua;
	private String ngayTao;
	private String ngayCapNhat;
	private String ngayTinhTrangBban;
	private int tinhTrangXacThuc;
	private Double tienHang;
	private Double tienThue;
	private String maKqxacThuc;
	private String moTaKqxacThuc;
	private String executeTime;
	private String soHdonXacThuc;
	private String maHdonXacThuc;
	private String maNhanHdon;
	private String maCqt;
	private int loaiWs;
	private int nam;
	private String fkey;
	private String barcode;
	private int tinhTrangHienThi;
	private int tinhTrangDongBo;
	private String status;

	@Basic
	@JsonProperty("ID")
	@Id
	@Column(name = "ID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic
	@JsonProperty("IdHoaDon")
	@Column(name = "IdHoaDon")
	public String getIdHoaDon() {
		return this.idHoaDon;
	}

	public void setIdHoaDon(String idHoaDon) {
		this.idHoaDon = idHoaDon;
	}

	@Basic
	@JsonProperty("MaSoThue")
	@Column(name = "MaSoThue")
	public String getMaSoThue() {
		return this.maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	@Basic
	@JsonProperty("HoTenNguoiBan")
	@Column(name = "HoTenNguoiBan")
	public String getHoTenNguoiBan() {
		return this.hoTenNguoiBan;
	}

	public void setHoTenNguoiBan(String hoTenNguoiBan) {
		this.hoTenNguoiBan = hoTenNguoiBan;
	}

	@Basic
	@JsonProperty("DiaChiNguoiBan")
	@Column(name = "DiaChiNguoiBan")
	public String getDiaChiNguoiBan() {
		return this.diaChiNguoiBan;
	}

	public void setDiaChiNguoiBan(String diaChiNguoiBan) {
		this.diaChiNguoiBan = diaChiNguoiBan;
	}
	
	@Basic
	@JsonProperty("DienThoaiNguoiBan")
	@Column(name = "DienThoaiNguoiBan")
	public String getDienThoaiNguoiBan() {
		return this.dienThoaiNguoiBan;
	}

	public void setDienThoaiNguoiBan(String dienThoaiNguoiBan) {
		this.dienThoaiNguoiBan = dienThoaiNguoiBan;
	}

	@Basic
	@JsonProperty("EmailNguoiBan")
	@Column(name = "EmailNguoiBan")
	public String getEmailNguoiBan() {
		return this.emailNguoiBan;
	}

	public void setEmailNguoiBan(String emailNguoiBan) {
		this.emailNguoiBan = emailNguoiBan;
	}

	@Basic
	@JsonProperty("HoTenNguoiMua")
	@Column(name = "HoTenNguoiMua")
	public String getHoTenNguoiMua() {
		return this.hoTenNguoiMua;
	}

	public void setHoTenNguoiMua(String hoTenNguoiMua) {
		this.hoTenNguoiMua = hoTenNguoiMua;
	}

	@Basic
	@JsonProperty("MSTNguoiMua")
	@Column(name = "MSTNguoiMua")
	public String getMstnguoiMua() {
		return this.mstnguoiMua;
	}

	public void setMstnguoiMua(String mstnguoiMua) {
		this.mstnguoiMua = mstnguoiMua;
	}

	@Basic
	@JsonProperty("DiaChiNguoiMua")
	@Column(name = "DiaChiNguoiMua")
	public String getDiaChiNguoiMua() {
		return this.diaChiNguoiMua;
	}

	public void setDiaChiNguoiMua(String diaChiNguoiMua) {
		this.diaChiNguoiMua = diaChiNguoiMua;
	}

	@Basic
	@JsonProperty("DienThoaiNguoiMua")
	@Column(name = "DienThoaiNguoiMua")
	public String getDienThoaiNguoiMua() {
		return this.dienThoaiNguoiMua;
	}

	public void setDienThoaiNguoiMua(String dienThoaiNguoiMua) {
		this.dienThoaiNguoiMua = dienThoaiNguoiMua;
	}

	@Basic
	@JsonProperty("EmailNguoiMua")
	@Column(name = "EmailNguoiMua")
	public String getEmailNguoiMua() {
		return this.emailNguoiMua;
	}

	public void setEmailNguoiMua(String emailNguoiMua) {
		this.emailNguoiMua = emailNguoiMua;
	}

	@Basic
	@JsonProperty("MauHDon")
	@Column(name = "MauHDon")
	public String getMauHdon() {
		return this.mauHdon;
	}

	public void setMauHdon(String mauHdon) {
		this.mauHdon = mauHdon;
	}

	@Basic
	@JsonProperty("SoThuTu")
	@Column(name = "SoThuTu")
	public String getSoThuTu() {
		return this.soThuTu;
	}

	public void setSoThuTu(String soThuTu) {
		this.soThuTu = soThuTu;
	}

	@Basic
	@JsonProperty("KyHieuHDon")
	@Column(name = "KyHieuHDon")
	public String getKyHieuHdon() {
		return this.kyHieuHdon;
	}

	public void setKyHieuHdon(String kyHieuHdon) {
		this.kyHieuHdon = kyHieuHdon;
	}
	
	@Basic
	@JsonProperty("SoHoaDon")
	@Column(name = "SoHoaDon")
	public String getSoHoaDon() {
		return this.soHoaDon;
	}

	public void setSoHoaDon(String soHoaDon) {
		this.soHoaDon = soHoaDon;
	}

	@Basic
	@JsonProperty("SoTien")
	@Column(name = "SoTien")
	public Double getSoTien() {
		return this.soTien;
	}

	public void setSoTien(Double soTien) {
		this.soTien = soTien;
	}

	@Basic
	@JsonProperty("NgayXHDon")
	@Column(name = "NgayXHDon")
	public String getNgayXhdon() {
		return this.ngayXhdon;
	}

	public void setNgayXhdon(String ngayXhdon) {
		this.ngayXhdon = ngayXhdon;
	}

	@Basic
	@JsonProperty("NgayKyNguoiMua")
	@Column(name = "NgayKyNguoiMua")
	public String getNgayKyNguoiMua() {
		return this.ngayKyNguoiMua;
	}

	public void setNgayKyNguoiMua(String ngayKyNguoiMua) {
		this.ngayKyNguoiMua = ngayKyNguoiMua;
	}

	@Basic
	@JsonProperty("LoaiHDon")
	@Column(name = "LoaiHDon")
	public int getLoaiHdon() {
		return this.loaiHdon;
	}

	public void setLoaiHdon(int loaiHdon) {
		this.loaiHdon = loaiHdon;
	}

	@Basic
	@JsonProperty("DoiTuong")
	@Column(name = "DoiTuong")
	public int getDoiTuong() {
		return this.doiTuong;
	}

	public void setDoiTuong(int doiTuong) {
		this.doiTuong = doiTuong;
	}

	@Basic
	@JsonProperty("FileHDon")
	@Column(name = "FileHDon")
	public String getFileHdon() {
		return this.fileHdon;
	}

	public void setFileHdon(String fileHdon) {
		this.fileHdon = fileHdon;
	}

	@Basic
	@JsonProperty("FileBBan")
	@Column(name = "FileBBan")
	public String getFileBban() {
		return this.fileBban;
	}

	public void setFileBban(String fileBban) {
		this.fileBban = fileBban;
	}

	@Basic
	@JsonProperty("TinhTrangKyNguoiBan")
	@Column(name = "TinhTrangKyNguoiBan")
	public int getTinhTrangKyNguoiBan() {
		return this.tinhTrangKyNguoiBan;
	}

	public void setTinhTrangKyNguoiBan(int tinhTrangKyNguoiBan) {
		this.tinhTrangKyNguoiBan = tinhTrangKyNguoiBan;
	}
	
	@Basic
	@JsonProperty("TinhTrangHDon")
	@Column(name = "TinhTrangHDon")
	public int getTinhTrangHdon() {
		return this.tinhTrangHdon;
	}

	public void setTinhTrangHdon(int tinhTrangHdon) {
		this.tinhTrangHdon = tinhTrangHdon;
	}

	@Basic
	@JsonProperty("NgayTinhTrangHDon")
	@Column(name = "NgayTinhTrangHDon")
	public String getNgayTinhTrangHdon() {
		return this.ngayTinhTrangHdon;
	}

	public void setNgayTinhTrangHdon(String ngayTinhTrangHdon) {
		this.ngayTinhTrangHdon = ngayTinhTrangHdon;
	}

	@Basic
	@JsonProperty("TinhTrangKyBBan")
	@Column(name = "TinhTrangKyBBan")
	public int getTinhTrangKyBban() {
		return this.tinhTrangKyBban;
	}

	public void setTinhTrangKyBban(int tinhTrangKyBban) {
		this.tinhTrangKyBban = tinhTrangKyBban;
	}

	@Basic
	@JsonProperty("NgayKyNguoiBan")
	@Column(name = "NgayKyNguoiBan")
	public String getNgayKyNguoiBan() {
		return this.ngayKyNguoiBan;
	}

	public void setNgayKyNguoiBan(String ngayKyNguoiBan) {
		this.ngayKyNguoiBan = ngayKyNguoiBan;
	}

	@Basic
	@JsonProperty("TinhTrangKyNguoiMua")
	@Column(name = "TinhTrangKyNguoiMua")
	public int getTinhTrangKyNguoiMua() {
		return this.tinhTrangKyNguoiMua;
	}

	public void setTinhTrangKyNguoiMua(int tinhTrangKyNguoiMua) {
		this.tinhTrangKyNguoiMua = tinhTrangKyNguoiMua;
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

	@Basic
	@JsonProperty("NgayCapNhat")
	@Column(name = "NgayCapNhat")
	public String getNgayCapNhat() {
		return this.ngayCapNhat;
	}

	public void setNgayCapNhat(String ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	@Basic
	@JsonProperty("NgayTinhTrangBBan")
	@Column(name = "NgayTinhTrangBBan")
	public String getNgayTinhTrangBban() {
		return this.ngayTinhTrangBban;
	}

	public void setNgayTinhTrangBban(String ngayTinhTrangBban) {
		this.ngayTinhTrangBban = ngayTinhTrangBban;
	}

	@Basic
	@JsonProperty("TinhTrangXacThuc")
	@Column(name = "TinhTrangXacThuc")
	public int getTinhTrangXacThuc() {
		return this.tinhTrangXacThuc;
	}

	public void setTinhTrangXacThuc(int tinhTrangXacThuc) {
		this.tinhTrangXacThuc = tinhTrangXacThuc;
	}

	@Basic
	@JsonProperty("TienHang")
	@Column(name = "TienHang")
	public Double getTienHang() {
		return this.tienHang;
	}

	public void setTienHang(Double tienHang) {
		this.tienHang = tienHang;
	}

	@Basic
	@JsonProperty("TienThue")
	@Column(name = "TienThue")
	public Double getTienThue() {
		return this.tienThue;
	}

	public void setTienThue(Double tienThue) {
		this.tienThue = tienThue;
	}

	@Basic
	@JsonProperty("MaKQXacThuc")
	@Column(name = "MaKQXacThuc")
	public String getMaKqxacThuc() {
		return this.maKqxacThuc;
	}

	public void setMaKqxacThuc(String maKqxacThuc) {
		this.maKqxacThuc = maKqxacThuc;
	}

	@Basic
	@JsonProperty("MoTaKQXacThuc")
	@Column(name = "MoTaKQXacThuc")
	public String getMoTaKqxacThuc() {
		return this.moTaKqxacThuc;
	}

	public void setMoTaKqxacThuc(String moTaKqxacThuc) {
		this.moTaKqxacThuc = moTaKqxacThuc;
	}

	@Basic
	@JsonProperty("ExecuteTime")
	@Column(name = "ExecuteTime")
	public String getExecuteTime() {
		return this.executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	@Basic
	@JsonProperty("SoHDonXacThuc")
	@Column(name = "SoHDonXacThuc")
	public String getSoHdonXacThuc() {
		return this.soHdonXacThuc;
	}

	public void setSoHdonXacThuc(String soHdonXacThuc) {
		this.soHdonXacThuc = soHdonXacThuc;
	}

	@Basic
	@JsonProperty("MaHDonXacThuc")
	@Column(name = "MaHDonXacThuc")
	public String getMaHdonXacThuc() {
		return this.maHdonXacThuc;
	}

	public void setMaHdonXacThuc(String maHdonXacThuc) {
		this.maHdonXacThuc = maHdonXacThuc;
	}

	@Basic
	@JsonProperty("MaNhanHDon")
	@Column(name = "MaNhanHDon")
	public String getMaNhanHdon() {
		return this.maNhanHdon;
	}

	public void setMaNhanHdon(String maNhanHdon) {
		this.maNhanHdon = maNhanHdon;
	}

	@Basic
	@JsonProperty("MaCQT")
	@Column(name = "MaCQT")
	public String getMaCqt() {
		return this.maCqt;
	}

	public void setMaCqt(String maCqt) {
		this.maCqt = maCqt;
	}

	@Basic
	@JsonProperty("LoaiWS")
	@Column(name = "LoaiWS")
	public int getLoaiWs() {
		return this.loaiWs;
	}

	public void setLoaiWs(int loaiWs) {
		this.loaiWs = loaiWs;
	}

	@Basic
	@JsonProperty("Nam")
	@Column(name = "Nam")
	public int getNam() {
		return this.nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}

	@Basic
	@JsonProperty("FKey")
	@Column(name = "FKey")
	public String getFkey() {
		return this.fkey;
	}

	public void setFkey(String fkey) {
		this.fkey = fkey;
	}

	@Basic
	@JsonProperty("Barcode")
	@Column(name = "Barcode")
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Basic
	@JsonProperty("TinhTrangHienThi")
	@Column(name = "TinhTrangHienThi")
	public int getTinhTrangHienThi() {
		return this.tinhTrangHienThi;
	}

	public void setTinhTrangHienThi(int tinhTrangHienThi) {
		this.tinhTrangHienThi = tinhTrangHienThi;
	}

	@Basic
	@JsonProperty("TinhTrangDongBo")
	@Column(name = "TinhTrangDongBo")
	public int getTinhTrangDongBo() {
		return this.tinhTrangDongBo;
	}

	public void setTinhTrangDongBo(int tinhTrangDongBo) {
		this.tinhTrangDongBo = tinhTrangDongBo;
	}

	@Basic
	@JsonProperty("Status")
	@Column(name = "Status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
