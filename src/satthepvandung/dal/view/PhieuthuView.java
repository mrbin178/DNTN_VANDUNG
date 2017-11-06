package satthepvandung.dal.view;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
public class PhieuthuView implements Serializable {

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

	@JsonProperty("ID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("IdHoaDon")
	public String getIdHoaDon() {
		return this.idHoaDon;
	}

	public void setIdHoaDon(String idHoaDon) {
		this.idHoaDon = idHoaDon;
	}

	@JsonProperty("MaSoThue")
	public String getMaSoThue() {
		return this.maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	@JsonProperty("HoTenNguoiBan")
	public String getHoTenNguoiBan() {
		return this.hoTenNguoiBan;
	}

	public void setHoTenNguoiBan(String hoTenNguoiBan) {
		this.hoTenNguoiBan = hoTenNguoiBan;
	}

	@JsonProperty("DiaChiNguoiBan")
	public String getDiaChiNguoiBan() {
		return this.diaChiNguoiBan;
	}

	public void setDiaChiNguoiBan(String diaChiNguoiBan) {
		this.diaChiNguoiBan = diaChiNguoiBan;
	}
	
	@JsonProperty("DienThoaiNguoiBan")
	public String getDienThoaiNguoiBan() {
		return this.dienThoaiNguoiBan;
	}

	public void setDienThoaiNguoiBan(String dienThoaiNguoiBan) {
		this.dienThoaiNguoiBan = dienThoaiNguoiBan;
	}

	@JsonProperty("EmailNguoiBan")
	public String getEmailNguoiBan() {
		return this.emailNguoiBan;
	}

	public void setEmailNguoiBan(String emailNguoiBan) {
		this.emailNguoiBan = emailNguoiBan;
	}

	@JsonProperty("HoTenNguoiMua")
	public String getHoTenNguoiMua() {
		return this.hoTenNguoiMua;
	}

	public void setHoTenNguoiMua(String hoTenNguoiMua) {
		this.hoTenNguoiMua = hoTenNguoiMua;
	}

	@JsonProperty("MSTNguoiMua")
	public String getMstnguoiMua() {
		return this.mstnguoiMua;
	}

	public void setMstnguoiMua(String mstnguoiMua) {
		this.mstnguoiMua = mstnguoiMua;
	}

	@JsonProperty("DiaChiNguoiMua")
	public String getDiaChiNguoiMua() {
		return this.diaChiNguoiMua;
	}

	public void setDiaChiNguoiMua(String diaChiNguoiMua) {
		this.diaChiNguoiMua = diaChiNguoiMua;
	}

	@JsonProperty("DienThoaiNguoiMua")
	public String getDienThoaiNguoiMua() {
		return this.dienThoaiNguoiMua;
	}

	public void setDienThoaiNguoiMua(String dienThoaiNguoiMua) {
		this.dienThoaiNguoiMua = dienThoaiNguoiMua;
	}

	@JsonProperty("EmailNguoiMua")
	public String getEmailNguoiMua() {
		return this.emailNguoiMua;
	}

	public void setEmailNguoiMua(String emailNguoiMua) {
		this.emailNguoiMua = emailNguoiMua;
	}

	@JsonProperty("MauHDon")
	public String getMauHdon() {
		return this.mauHdon;
	}

	public void setMauHdon(String mauHdon) {
		this.mauHdon = mauHdon;
	}

	@JsonProperty("SoThuTu")
	public String getSoThuTu() {
		return this.soThuTu;
	}

	public void setSoThuTu(String soThuTu) {
		this.soThuTu = soThuTu;
	}

	@JsonProperty("KyHieuHDon")
	public String getKyHieuHdon() {
		return this.kyHieuHdon;
	}

	public void setKyHieuHdon(String kyHieuHdon) {
		this.kyHieuHdon = kyHieuHdon;
	}
	
	@JsonProperty("SoHoaDon")
	public String getSoHoaDon() {
		return this.soHoaDon;
	}

	public void setSoHoaDon(String soHoaDon) {
		this.soHoaDon = soHoaDon;
	}

	@JsonProperty("SoTien")
	public Double getSoTien() {
		return this.soTien;
	}

	public void setSoTien(Double soTien) {
		this.soTien = soTien;
	}

	@JsonProperty("NgayXHDon")
	public String getNgayXhdon() {
		return this.ngayXhdon;
	}

	public void setNgayXhdon(String ngayXhdon) {
		this.ngayXhdon = ngayXhdon;
	}

	@JsonProperty("NgayKyNguoiMua")
	public String getNgayKyNguoiMua() {
		return this.ngayKyNguoiMua;
	}

	public void setNgayKyNguoiMua(String ngayKyNguoiMua) {
		this.ngayKyNguoiMua = ngayKyNguoiMua;
	}

	@JsonProperty("LoaiHDon")
	public int getLoaiHdon() {
		return this.loaiHdon;
	}

	public void setLoaiHdon(int loaiHdon) {
		this.loaiHdon = loaiHdon;
	}

	@JsonProperty("DoiTuong")
	public int getDoiTuong() {
		return this.doiTuong;
	}

	public void setDoiTuong(int doiTuong) {
		this.doiTuong = doiTuong;
	}

	@JsonProperty("FileHDon")
	public String getFileHdon() {
		return this.fileHdon;
	}

	public void setFileHdon(String fileHdon) {
		this.fileHdon = fileHdon;
	}

	@JsonProperty("FileBBan")
	public String getFileBban() {
		return this.fileBban;
	}

	public void setFileBban(String fileBban) {
		this.fileBban = fileBban;
	}

	@JsonProperty("TinhTrangKyNguoiBan")
	public int getTinhTrangKyNguoiBan() {
		return this.tinhTrangKyNguoiBan;
	}

	public void setTinhTrangKyNguoiBan(int tinhTrangKyNguoiBan) {
		this.tinhTrangKyNguoiBan = tinhTrangKyNguoiBan;
	}
	
	@JsonProperty("TinhTrangHDon")
	public int getTinhTrangHdon() {
		return this.tinhTrangHdon;
	}

	public void setTinhTrangHdon(int tinhTrangHdon) {
		this.tinhTrangHdon = tinhTrangHdon;
	}

	@JsonProperty("NgayTinhTrangHDon")
	public String getNgayTinhTrangHdon() {
		return this.ngayTinhTrangHdon;
	}

	public void setNgayTinhTrangHdon(String ngayTinhTrangHdon) {
		this.ngayTinhTrangHdon = ngayTinhTrangHdon;
	}

	@JsonProperty("TinhTrangKyBBan")
	public int getTinhTrangKyBban() {
		return this.tinhTrangKyBban;
	}

	public void setTinhTrangKyBban(int tinhTrangKyBban) {
		this.tinhTrangKyBban = tinhTrangKyBban;
	}

	@JsonProperty("NgayKyNguoiBan")
	public String getNgayKyNguoiBan() {
		return this.ngayKyNguoiBan;
	}

	public void setNgayKyNguoiBan(String ngayKyNguoiBan) {
		this.ngayKyNguoiBan = ngayKyNguoiBan;
	}

	@JsonProperty("TinhTrangKyNguoiMua")
	public int getTinhTrangKyNguoiMua() {
		return this.tinhTrangKyNguoiMua;
	}

	public void setTinhTrangKyNguoiMua(int tinhTrangKyNguoiMua) {
		this.tinhTrangKyNguoiMua = tinhTrangKyNguoiMua;
	}

	@JsonProperty("NgayTao")
	public String getNgayTao() {
		return this.ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	@JsonProperty("NgayCapNhat")
	public String getNgayCapNhat() {
		return this.ngayCapNhat;
	}

	public void setNgayCapNhat(String ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	@JsonProperty("NgayTinhTrangBBan")
	public String getNgayTinhTrangBban() {
		return this.ngayTinhTrangBban;
	}

	public void setNgayTinhTrangBban(String ngayTinhTrangBban) {
		this.ngayTinhTrangBban = ngayTinhTrangBban;
	}

	@JsonProperty("TinhTrangXacThuc")
	public int getTinhTrangXacThuc() {
		return this.tinhTrangXacThuc;
	}

	public void setTinhTrangXacThuc(int tinhTrangXacThuc) {
		this.tinhTrangXacThuc = tinhTrangXacThuc;
	}

	@JsonProperty("TienHang")
	public Double getTienHang() {
		return this.tienHang;
	}

	public void setTienHang(Double tienHang) {
		this.tienHang = tienHang;
	}

	@JsonProperty("TienThue")
	public Double getTienThue() {
		return this.tienThue;
	}

	public void setTienThue(Double tienThue) {
		this.tienThue = tienThue;
	}

	@JsonProperty("MaKQXacThuc")
	public String getMaKqxacThuc() {
		return this.maKqxacThuc;
	}

	public void setMaKqxacThuc(String maKqxacThuc) {
		this.maKqxacThuc = maKqxacThuc;
	}

	@JsonProperty("MoTaKQXacThuc")
	public String getMoTaKqxacThuc() {
		return this.moTaKqxacThuc;
	}

	public void setMoTaKqxacThuc(String moTaKqxacThuc) {
		this.moTaKqxacThuc = moTaKqxacThuc;
	}

	@JsonProperty("ExecuteTime")
	public String getExecuteTime() {
		return this.executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	@JsonProperty("SoHDonXacThuc")
	public String getSoHdonXacThuc() {
		return this.soHdonXacThuc;
	}

	public void setSoHdonXacThuc(String soHdonXacThuc) {
		this.soHdonXacThuc = soHdonXacThuc;
	}

	@JsonProperty("MaHDonXacThuc")
	public String getMaHdonXacThuc() {
		return this.maHdonXacThuc;
	}

	public void setMaHdonXacThuc(String maHdonXacThuc) {
		this.maHdonXacThuc = maHdonXacThuc;
	}

	@JsonProperty("MaNhanHDon")
	public String getMaNhanHdon() {
		return this.maNhanHdon;
	}

	public void setMaNhanHdon(String maNhanHdon) {
		this.maNhanHdon = maNhanHdon;
	}

	@JsonProperty("MaCQT")
	public String getMaCqt() {
		return this.maCqt;
	}

	public void setMaCqt(String maCqt) {
		this.maCqt = maCqt;
	}

	@JsonProperty("LoaiWS")
	public int getLoaiWs() {
		return this.loaiWs;
	}

	public void setLoaiWs(int loaiWs) {
		this.loaiWs = loaiWs;
	}

	@JsonProperty("Nam")
	public int getNam() {
		return this.nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}

	@JsonProperty("FKey")
	public String getFkey() {
		return this.fkey;
	}

	public void setFkey(String fkey) {
		this.fkey = fkey;
	}

	@JsonProperty("Barcode")
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@JsonProperty("TinhTrangHienThi")
	public int getTinhTrangHienThi() {
		return this.tinhTrangHienThi;
	}

	public void setTinhTrangHienThi(int tinhTrangHienThi) {
		this.tinhTrangHienThi = tinhTrangHienThi;
	}

	@JsonProperty("TinhTrangDongBo")
	public int getTinhTrangDongBo() {
		return this.tinhTrangDongBo;
	}

	public void setTinhTrangDongBo(int tinhTrangDongBo) {
		this.tinhTrangDongBo = tinhTrangDongBo;
	}

	@JsonProperty("Status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
