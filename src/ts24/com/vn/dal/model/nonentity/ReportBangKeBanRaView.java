package ts24.com.vn.dal.model.nonentity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
public class ReportBangKeBanRaView implements Serializable {
	private String stt, mstNguoiBan = "", mauHoaDon = "", kyHieuHoaDon = "",
			soHoaDon = "", ngayThangNamPhatHanh = "", tenNguoiMua = "",
			mstNguoiMua = "", matHang = "", doanhSoBanChuaCoThue = "",
			thueSuat = "", thueGTGT = "", chietKhauGiamTru = "", ghiChu = "";
	
	@JsonProperty("STT")
	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	@JsonProperty("MaSoThue")
	public String getMstNguoiBan() {
		return mstNguoiBan;
	}

	public void setMstNguoiBan(String mstNguoiBan) {
		this.mstNguoiBan = mstNguoiBan;
	}
	@JsonProperty("MauHDon")
	public String getMauHoaDon() {
		return mauHoaDon;
	}

	public void setMauHoaDon(String mauHoaDon) {
		this.mauHoaDon = mauHoaDon;
	}
	@JsonProperty("KyHieuHDon")
	public String getKyHieuHoaDon() {
		return kyHieuHoaDon;
	}

	public void setKyHieuHoaDon(String kyHieuHoaDon) {
		this.kyHieuHoaDon = kyHieuHoaDon;
	}
	@JsonProperty("SoHoaDon")
	public String getSoHoaDon() {
		return soHoaDon;
	}

	public void setSoHoaDon(String soHoaDon) {
		this.soHoaDon = soHoaDon;
	}
	@JsonProperty("NgayXHDon")
	public String getNgayThangNamPhatHanh() {
		return ngayThangNamPhatHanh;
	}

	public void setNgayThangNamPhatHanh(String ngayThangNamPhatHanh) {
		this.ngayThangNamPhatHanh = ngayThangNamPhatHanh;
	}
	@JsonProperty("HoTenNguoiMua")
	public String getTenNguoiMua() {
		return tenNguoiMua;
	}

	public void setTenNguoiMua(String tenNguoiMua) {
		this.tenNguoiMua = tenNguoiMua;
	}
	@JsonProperty("MSTNguoiMua")
	public String getMstNguoiMua() {
		return mstNguoiMua;
	}

	public void setMstNguoiMua(String mstNguoiMua) {
		this.mstNguoiMua = mstNguoiMua;
	}
	@JsonProperty("MatHang")
	public String getMatHang() {
		return matHang;
	}

	public void setMatHang(String matHang) {
		this.matHang = matHang;
	}
	@JsonProperty("DoanhSoBanChuaCoThue")
	public String getDoanhSoBanChuaCoThue() {
		return doanhSoBanChuaCoThue;
	}

	public void setDoanhSoBanChuaCoThue(String doanhSoBanChuaCoThue) {
		this.doanhSoBanChuaCoThue = doanhSoBanChuaCoThue;
	}
	@JsonProperty("ThueSuat")
	public String getThueSuat() {
		return thueSuat;
	}

	public void setThueSuat(String thueSuat) {
		this.thueSuat = thueSuat;
	}
	@JsonProperty("ThueGTGT")
	public String getThueGTGT() {
		return thueGTGT;
	}

	public void setThueGTGT(String thueGTGT) {
		this.thueGTGT = thueGTGT;
	}
	@JsonProperty("ChietKhauGiamTru")
	public String getChietKhauGiamTru() {
		return chietKhauGiamTru;
	}

	public void setChietKhauGiamTru(String chietKhauGiamTru) {
		this.chietKhauGiamTru = chietKhauGiamTru;
	}
	@JsonProperty("GhiChu")
	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
}
