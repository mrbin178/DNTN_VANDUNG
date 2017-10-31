package ts24.com.vn.dal.model.nonentity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
public class BaoCaoBanHangView implements Serializable {

			
	 private String maSoThueBan;
	private String maHang;
	private String tenHang;
	private String donViTinh;
	private String soLuong;
	private String donGia;	
	private String thanhTien;		
	private String ghiChu;
	 private String maSoThueMua;
	private String thueSuat;
	 private String doanhThu;	
	 private String soHD;
	 private String dienGiai;
	private String tienThue;
	 private String ngayXHDon;
	 
	private String maHoaDon;
	private String donGiaGoc;		
	private String tongTien;
	private int khuyenMai;		
	private String loaiDieuChinh;
	
	private String tuNgay;
	private String denNgay;
	private String stt ;
	
	private String mauHDon;
	private String soThuTu;
	private String kyHieuHDon;
	
	@JsonProperty("MaHoaDon")
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	@JsonProperty("dongiaGoc")
	public String getDonGiaGoc() {
		return donGiaGoc;
	}
	public void setDonGiaGoc(String donGiaGoc) {
		this.donGiaGoc = donGiaGoc;
	}
	@JsonProperty("TongTien")
	public String getTongTien() {
		return tongTien;
	}
	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}
	@JsonProperty("KhuyenMai")
	public int getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(int khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	@JsonProperty("LoaiDieuChinh")
	public String getLoaiDieuChinh() {
		return loaiDieuChinh;
	}
	public void setLoaiDieuChinh(String loaiDieuChinh) {
		this.loaiDieuChinh = loaiDieuChinh;
	}
	
		
	@JsonProperty("MaSoThue")
	public String getMaSoThueBan() {
		return maSoThueBan;
	}
	public void setMaSoThueBan(String maSoThueBan) {
		this.maSoThueBan = maSoThueBan;
	}
	@JsonProperty("MaHang")
	public String getMaHang() {
		return maHang;
	}
	public void setMaHang(String maHang) {
		this.maHang = maHang;
	}
	@JsonProperty("TenHang")
	public String getTenHang() {
		return tenHang;
	}
	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}
	@JsonProperty("DVT")
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	@JsonProperty("SoLuong")
	public String getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}
	@JsonProperty("DonGia")
	public String getDonGia() {
		return donGia;
	}
	public void setDonGia(String donGia) {
		this.donGia = donGia;
	}
	@JsonProperty("ThanhTien")
	public String getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(String thanhTien) {
		this.thanhTien = thanhTien;
	}
	@JsonProperty("GhiChu")
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	@JsonProperty("MaSoThueMua")
	public String getMaSoThueMua() {
		return maSoThueMua;
	}
	public void setMaSoThueMua(String maSoThueMua) {
		this.maSoThueMua = maSoThueMua;
	}
	@JsonProperty("ThueSuat")
	public String getThueSuat() {
		return thueSuat;
	}
	public void setThueSuat(String thueSuat) {
		this.thueSuat = thueSuat;
	}
	@JsonProperty("DoanhThu")
	public String getDoanhThu() {
		return doanhThu;
	}
	public void setDoanhThu(String doanhThu) {
		this.doanhThu = doanhThu;
	}
	
	@JsonProperty("SoHD")
	public String getSoHD() {
		return soHD;
	}
	public void setSoHD(String soHD) {
		this.soHD = soHD;
	}
	@JsonProperty("DienGiai")
	public String getDienGiai() {
		return dienGiai;
	}
	public void setDienGiai(String dienGiai) {
		this.dienGiai = dienGiai;
	}
	@JsonProperty("TienThue")
	public String getTienThue() {
		return tienThue;
	}
	public void setTienThue(String tienThue) {
		this.tienThue = tienThue;
	}
	@JsonProperty("NgayXHDon")
	public String getNgayXHDon() {
		return ngayXHDon;
	}
	public void setNgayXHDon(String ngayXHDon) {
		this.ngayXHDon = ngayXHDon;
	}
	@JsonProperty("TuNgay")
	public String getTuNgay() {
		return tuNgay;
	}
	public void setTuNgay(String tuNgay) {
		this.tuNgay = tuNgay;
	}
	@JsonProperty("DenNgay")
	public String getDenNgay() {
		return denNgay;
	}
	public void setDenNgay(String denNgay) {
		this.denNgay = denNgay;
	}
	@JsonProperty("STT")
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
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
	
	
	
	
	
}
