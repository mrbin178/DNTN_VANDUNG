package satthepvandung.dal.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "hddt_hoadon")
@SuppressWarnings("serial")

public class HoaDonDienTuView implements Serializable {

	private String id;
	private String maSoThue;
	private String hoTenNguoiMua;
	private String mstNguoiMua;
	private String dienThoaiNguoiMua;
	private String emailNguoiMua;
	private String soHoaDon;
	private String ngayTao;
	private String ngayXHDon;
	
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
	
}
