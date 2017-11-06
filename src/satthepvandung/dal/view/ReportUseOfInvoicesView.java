package satthepvandung.dal.view;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
public class ReportUseOfInvoicesView implements Serializable {
	private String stt, mstNguoiBan = "", mauHoaDon = "", kyHieuHoaDon = "",
			soHoaDon = "", maLoaiHoaDon = "", tenHoaDon = "";
	private String tuSo = "";
	private String denSo = "";
	private String tongCong = "";
	private String soLuongDaSuDung = "";
	private String soLuongXoaBo = "";
	private String soXoaBo = "";
	private String soLuongHuy = "";
	private String soHuy = "";
	private String soLuongMat = "";
	private String soMat = "";

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

	@JsonProperty("MaLoaiHoaDon")
	public String getMaLoaiHoaDon() {
		return maLoaiHoaDon;
	}

	public void setMaLoaiHoaDon(String maLoaiHoaDon) {
		this.maLoaiHoaDon = maLoaiHoaDon;
	}

	@JsonProperty("TenHoaDon")
	public String getTenHoaDon() {
		return tenHoaDon;
	}

	public void setTenHoaDon(String tenHoaDon) {
		this.tenHoaDon = tenHoaDon;
	}

	@JsonProperty("TuSo")
	public String getTuSo() {
		return tuSo;
	}

	public void setTuSo(String tuSo) {
		this.tuSo = tuSo;
	}
	@JsonProperty("DenSo")
	public String getDenSo() {
		return denSo;
	}

	public void setDenSo(String denSo) {
		this.denSo = denSo;
	}
	@JsonProperty("TongCong")
	public String getTongCong() {
		return tongCong;
	}

	public void setTongCong(String tongCong) {
		this.tongCong = tongCong;
	}
	@JsonProperty("SoLuongDaSuDung")
	public String getSoLuongDaSuDung() {
		return soLuongDaSuDung;
	}

	public void setSoLuongDaSuDung(String soLuongDaSuDung) {
		this.soLuongDaSuDung = soLuongDaSuDung;
	}
	@JsonProperty("SoLuongXoaBo")
	public String getSoLuongXoaBo() {
		return soLuongXoaBo;
	}

	public void setSoLuongXoaBo(String soLuongXoaBo) {
		this.soLuongXoaBo = soLuongXoaBo;
	}
	@JsonProperty("SoXoaBo")
	public String getSoXoaBo() {
		return soXoaBo;
	}

	public void setSoXoaBo(String soXoaBo) {
		this.soXoaBo = soXoaBo;
	}
	@JsonProperty("SoLuongHuy")
	public String getSoLuongHuy() {
		return soLuongHuy;
	}

	public void setSoLuongHuy(String soLuongHuy) {
		this.soLuongHuy = soLuongHuy;
	}
	@JsonProperty("SoHuy")
	public String getSoHuy() {
		return soHuy;
	}

	public void setSoHuy(String soHuy) {
		this.soHuy = soHuy;
	}
	@JsonProperty("SoLuongMat")
	public String getSoLuongMat() {
		return soLuongMat;
	}

	public void setSoLuongMat(String soLuongMat) {
		this.soLuongMat = soLuongMat;
	}
	@JsonProperty("SoMat")
	public String getSoMat() {
		return soMat;
	}

	public void setSoMat(String soMat) {
		this.soMat = soMat;
	}

}
