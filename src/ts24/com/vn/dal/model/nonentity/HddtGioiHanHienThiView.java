package ts24.com.vn.dal.model.nonentity;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
public class HddtGioiHanHienThiView implements Serializable {
	
	private int id;
	private String mst;
	private String ngayBd;
	private String ngayKt;
	private String ngayTao;
	private String ngayCapNhat;
	private String nguoiTao;
	private int tinhTrang;
	
	@JsonProperty("ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@JsonProperty("MST")
	public String getMst() {
		return mst;
	}
	public void setMst(String mst) {
		this.mst = mst;
	}
	@JsonProperty("NgayBD")
	public String getNgayBd() {
		return ngayBd;
	}
	public void setNgayBd(String ngayBd) {
		this.ngayBd = ngayBd;
	}
	@JsonProperty("NgayKT")
	public String getNgayKt() {
		return ngayKt;
	}
	public void setNgayKt(String ngayKt) {
		this.ngayKt = ngayKt;
	}
	@JsonProperty("NgayTao")
	public String getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}
	@JsonProperty("NgayCapNhat")
	public String getNgayCapNhat() {
		return ngayCapNhat;
	}
	public void setNgayCapNhat(String ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}
	@JsonProperty("NguoiTao")
	public String getNguoiTao() {
		return nguoiTao;
	}
	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}
	@JsonProperty("TinhTrang")
	public int getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

}
