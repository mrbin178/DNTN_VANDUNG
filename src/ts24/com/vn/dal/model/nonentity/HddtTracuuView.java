package ts24.com.vn.dal.model.nonentity;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings( "serial" )
public class HddtTracuuView implements Serializable {

	private String id;
	private String idhoaDon;
	private String taiKhoanNhan;
	private Integer loaiHd;
	private String ngayTtrang;
	private Integer tinhTrang;

	@Column(name = "ID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("IDHoaDon")
	public String getIdhoaDon() {
		return this.idhoaDon;
	}

	public void setIdhoaDon(String idhoaDon) {
		this.idhoaDon = idhoaDon;
	}

	@JsonProperty("TaiKhoanNhan")
	public String getTaiKhoanNhan() {
		return this.taiKhoanNhan;
	}

	public void setTaiKhoanNhan(String taiKhoanNhan) {
		this.taiKhoanNhan = taiKhoanNhan;
	}

	@JsonProperty("LoaiHD")
	public Integer getLoaiHd() {
		return this.loaiHd;
	}

	public void setLoaiHd(Integer loaiHd) {
		this.loaiHd = loaiHd;
	}

	@JsonProperty("NgayTTrang")
	public String getNgayTtrang() {
		return this.ngayTtrang;
	}

	public void setNgayTtrang(String ngayTtrang) {
		this.ngayTtrang = ngayTtrang;
	}

	@JsonProperty("TinhTrang")
	public Integer getTinhTrang() {
		return this.tinhTrang;
	}

	public void setTinhTrang(Integer tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

}
