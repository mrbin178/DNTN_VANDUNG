package satthepvandung.dal.view;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
public class ReportTBaoPhatHanhView implements Serializable {
	private String id = "", stt = "", maSoThue = "", mauHoaDon = "",
			kyHieu = "", soLuong = "", tuSo = "", denSo = "",
			ngayLapPhieu = "", ngayBatDauSuDung = "";

	@JsonProperty("ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JsonProperty("STT")
	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}
	@JsonProperty("MaSoThue")
	public String getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}
	@JsonProperty("MauHoaDon")
	public String getMauHoaDon() {
		return mauHoaDon;
	}

	public void setMauHoaDon(String mauHoaDon) {
		this.mauHoaDon = mauHoaDon;
	}
	@JsonProperty("KyHieu")
	public String getKyHieu() {
		return kyHieu;
	}

	public void setKyHieu(String kyHieu) {
		this.kyHieu = kyHieu;
	}
	@JsonProperty("SoLuong")
	public String getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
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
	@JsonProperty("NgayLapPhieu")
	public String getNgayLapPhieu() {
		return ngayLapPhieu;
	}

	public void setNgayLapPhieu(String ngayLapPhieu) {
		this.ngayLapPhieu = ngayLapPhieu;
	}
	@JsonProperty("NgayBatDauSuDung")
	public String getNgayBatDauSuDung() {
		return ngayBatDauSuDung;
	}

	public void setNgayBatDauSuDung(String ngayBatDauSuDung) {
		this.ngayBatDauSuDung = ngayBatDauSuDung;
	}

}
