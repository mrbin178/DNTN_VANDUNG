package ts24.com.vn.dal.model.nonentity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
public class HoaDonView implements Serializable {

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

	@JsonProperty("ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonProperty("MaSoThue")
	public String getMaSoThue() {
		return maSoThue;
	}
	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}
	@JsonProperty("HoTenNguoiMua")
	public String getHoTenNguoiMua() {
		return hoTenNguoiMua;
	}
	public void setHoTenNguoiMua(String hoTenNguoiMua) {
		this.hoTenNguoiMua = hoTenNguoiMua;
	}
	@JsonProperty("MSTNguoiMua")
	public String getMstNguoiMua() {
		return mstNguoiMua;
	}
	public void setMstNguoiMua(String mstNguoiMua) {
		this.mstNguoiMua = mstNguoiMua;
	}
	@JsonProperty("DienThoaiNguoiMua")
	public String getDienThoaiNguoiMua() {
		return dienThoaiNguoiMua;
	}
	public void setDienThoaiNguoiMua(String dienThoaiNguoiMua) {
		this.dienThoaiNguoiMua = dienThoaiNguoiMua;
	}
	@JsonProperty("EmailNguoiMua")
	public String getEmailNguoiMua() {
		return emailNguoiMua;
	}
	public void setEmailNguoiMua(String emailNguoiMua) {
		this.emailNguoiMua = emailNguoiMua;
	}
	@JsonProperty("SoHoaDon")
	public String getSoHoaDon() {
		return soHoaDon;
	}
	public void setSoHoaDon(String soHoaDon) {
		this.soHoaDon = soHoaDon;
	}
	@JsonProperty("NgayTao")
	public String getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}
	@JsonProperty("NgayXHDon")
	public String getNgayXHDon() {
		return ngayXHDon;
	}
	public void setNgayXHDon(String ngayXHDon) {
		this.ngayXHDon = ngayXHDon;
	}
	@JsonProperty("tinhTrangXacThuc")
	public int getTinhTrangXacThuc() {
		return tinhTrangXacThuc;
	}
	public void setTinhTrangXacThuc(int tinhTrangXacThuc) {
		this.tinhTrangXacThuc = tinhTrangXacThuc;
	}
	@JsonProperty("MauHDon")
	public String getMauHDon() {
		return mauHDon;
	}

	public void setMauHDon(String mauHDon) {
		this.mauHDon = mauHDon;
	}
	@JsonProperty("SoThuTu")
	public String getSoThuTu() {
		return soThuTu;
	}

	public void setSoThuTu(String soThuTu) {
		this.soThuTu = soThuTu;
	}
	@JsonProperty("KyHieuHDon")
	public String getKyHieuHDon() {
		return kyHieuHDon;
	}

	public void setKyHieuHDon(String kyHieuHDon) {
		this.kyHieuHDon = kyHieuHDon;
	}

	@JsonProperty("IdHoaDon")
	public String getIdHoaDon() {
		return idHoaDon;
	}

	public void setIdHoaDon(String idHoaDon) {
		this.idHoaDon = idHoaDon;
	}

	@JsonProperty("HoTenNguoiBan")
	public String getHoTenNguoiBan() {
		return hoTenNguoiBan;
	}

	public void setHoTenNguoiBan(String hoTenNguoiBan) {
		this.hoTenNguoiBan = hoTenNguoiBan;
	}

	@JsonProperty("DiaChiNguoiBan")
	public String getDiaChiNguoiBan() {
		return diaChiNguoiBan;
	}

	public void setDiaChiNguoiBan(String diaChiNguoiBan) {
		this.diaChiNguoiBan = diaChiNguoiBan;
	}

	@JsonProperty("DienThoaiNguoiBan")
	public String getDienThoaiNguoiBan() {
		return dienThoaiNguoiBan;
	}

	public void setDienThoaiNguoiBan(String dienThoaiNguoiBan) {
		this.dienThoaiNguoiBan = dienThoaiNguoiBan;
	}

	@JsonProperty("EmailNguoiBan")
	public String getEmailNguoiBan() {
		return emailNguoiBan;
	}

	public void setEmailNguoiBan(String emailNguoiBan) {
		this.emailNguoiBan = emailNguoiBan;
	}

	@JsonProperty("DiaChiNguoiMua")
	public String getDiaChiNguoiMua() {
		return diaChiNguoiMua;
	}

	public void setDiaChiNguoiMua(String diaChiNguoiMua) {
		this.diaChiNguoiMua = diaChiNguoiMua;
	}

	@JsonProperty("SoTien")
	public String getSoTien() {
		return soTien;
	}

	public void setSoTien(String soTien) {
		this.soTien = soTien;
	}

	@JsonProperty("NgayCapNhat")
	public String getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(String ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	@JsonProperty("LoaiHDon")
	public int getLoaiHDon() {
		return loaiHDon;
	}

	public void setLoaiHDon(int loaiHDon) {
		this.loaiHDon = loaiHDon;
	}

	@JsonProperty("DoiTuong")
	public int getDoiTuong() {
		return doiTuong;
	}

	public void setDoiTuong(int doiTuong) {
		this.doiTuong = doiTuong;
	}

	@JsonProperty("TinhTrangHDon")
	public int getTinhTrangHDon() {
		return tinhTrangHDon;
	}

	public void setTinhTrangHDon(int tinhTrangHDon) {
		this.tinhTrangHDon = tinhTrangHDon;
	}

	@JsonProperty("NgayKyNguoiMua")
	public String getNgayKyNguoiMua() {
		return ngayKyNguoiMua;
	}

	public void setNgayKyNguoiMua(String ngayKyNguoiMua) {
		this.ngayKyNguoiMua = ngayKyNguoiMua;
	}

	@JsonProperty("FileHDon")
	public String getFileHDon() {
		return fileHDon;
	}

	public void setFileHDon(String fileHDon) {
		this.fileHDon = fileHDon;
	}

	@JsonProperty("FileBBan")
	public String getFileBBan() {
		return fileBBan;
	}

	public void setFileBBan(String fileBBan) {
		this.fileBBan = fileBBan;
	}

	@JsonProperty("TinhTrangKyNguoiBan")
	public int getTinhTrangKyNguoiBan() {
		return tinhTrangKyNguoiBan;
	}

	public void setTinhTrangKyNguoiBan(int tinhTrangKyNguoiBan) {
		this.tinhTrangKyNguoiBan = tinhTrangKyNguoiBan;
	}

	@JsonProperty("NgayTinhTrangHDon")
	public String getNgayTinhTrangHDon() {
		return NgayTinhTrangHDon;
	}

	public void setNgayTinhTrangHDon(String ngayTinhTrangHDon) {
		NgayTinhTrangHDon = ngayTinhTrangHDon;
	}

	@JsonProperty("TinhTrangKyBBan")
	public int getTinhTrangKyBBan() {
		return tinhTrangKyBBan;
	}

	public void setTinhTrangKyBBan(int tinhTrangKyBBan) {
		this.tinhTrangKyBBan = tinhTrangKyBBan;
	}

	@JsonProperty("NgayKyNguoiBan")
	public String getNgayKyNguoiBan() {
		return ngayKyNguoiBan;
	}

	public void setNgayKyNguoiBan(String ngayKyNguoiBan) {
		this.ngayKyNguoiBan = ngayKyNguoiBan;
	}

	@JsonProperty("TinhTrangKyNguoiMua")
	public int getTinhTrangKyNguoiMua() {
		return tinhTrangKyNguoiMua;
	}

	public void setTinhTrangKyNguoiMua(int tinhTrangKyNguoiMua) {
		this.tinhTrangKyNguoiMua = tinhTrangKyNguoiMua;
	}

	public void setNgayTinhTrangBBan(String ngayTinhTrangBBan) {
		NgayTinhTrangBBan = ngayTinhTrangBBan;
	}

	@JsonProperty("NgayTinhTrangBBan")
	public String getNgayTinhTrangBBan() {
		return NgayTinhTrangBBan;
	}

	@JsonProperty("TienHang")
	public String getTienHang() {
		return tienHang;
	}

	public void setTienHang(String tienHang) {
		this.tienHang = tienHang;
	}

	@JsonProperty("TienThue")
	public String getTienThue() {
		return tienThue;
	}

	public void setTienThue(String tienThue) {
		this.tienThue = tienThue;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	@JsonProperty("executeTime")
	public String getExecuteTime() {
		return executeTime;
	}

	public void setMaKQXacThuc(String maKQXacThuc) {
		this.maKQXacThuc = maKQXacThuc;
	}

	@JsonProperty("MaKQXacThuc")
	public String getMaKQXacThuc() {
		return maKQXacThuc;
	}

	public void setMoTaKQXacThuc(String moTaKQXacThuc) {
		this.moTaKQXacThuc = moTaKQXacThuc;
	}

	@JsonProperty("MoTaKQXacThuc")
	public String getMoTaKQXacThuc() {
		return moTaKQXacThuc;
	}

	public void setSoHDonXacThuc(String soHDonXacThuc) {
		this.soHDonXacThuc = soHDonXacThuc;
	}

	@JsonProperty("SoHDonXacThuc")
	public String getSoHDonXacThuc() {
		return soHDonXacThuc;
	}

	public void setMaHDonXacThuc(String maHDonXacThuc) {
		this.maHDonXacThuc = maHDonXacThuc;
	}

	@JsonProperty("MaHDonXacThuc")
	public String getMaHDonXacThuc() {
		return maHDonXacThuc;
	}

	public void setMaNhanHDon(String maNhanHDon) {
		this.maNhanHDon = maNhanHDon;
	}

	@JsonProperty("MaNhanHDon")
	public String getMaNhanHDon() {
		return maNhanHDon;
	}

	@JsonProperty("MaCQT")
	public String getMaCQT() {
		return maCQT;
	}

	public void setMaCQT(String maCQT) {
		this.maCQT = maCQT;
	}

}
