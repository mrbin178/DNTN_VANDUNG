package ts24.com.vn.dal.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "hddt_tracuu")
@SuppressWarnings( "serial" )
public class HddtTracuu implements Serializable {

	private String id;
	private String idhoaDon;
	private String taiKhoanNhan;
	private Integer loaiHd;
	private String ngayTtrang;
	private Integer tinhTrang;

	@Basic
	@Id
	@Column(name = "ID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic
	@JsonProperty("IDHoaDon")
	@Column(name = "IDHoaDon")
	public String getIdhoaDon() {
		return this.idhoaDon;
	}

	public void setIdhoaDon(String idhoaDon) {
		this.idhoaDon = idhoaDon;
	}

	@Basic
	@JsonProperty("TaiKhoanNhan")
	@Column(name = "TaiKhoanNhan")
	public String getTaiKhoanNhan() {
		return this.taiKhoanNhan;
	}

	public void setTaiKhoanNhan(String taiKhoanNhan) {
		this.taiKhoanNhan = taiKhoanNhan;
	}

	@Basic
	@JsonProperty("LoaiHD")
	@Column(name = "LoaiHD")
	public Integer getLoaiHd() {
		return this.loaiHd;
	}

	public void setLoaiHd(Integer loaiHd) {
		this.loaiHd = loaiHd;
	}

	@Basic
	@JsonProperty("NgayTTrang")
	@Column(name = "NgayTTrang")
	public String getNgayTtrang() {
		return this.ngayTtrang;
	}

	public void setNgayTtrang(String ngayTtrang) {
		this.ngayTtrang = ngayTtrang;
	}

	@Basic
	@JsonProperty("TinhTrang")
	@Column(name = "TinhTrang")
	public Integer getTinhTrang() {
		return this.tinhTrang;
	}

	public void setTinhTrang(Integer tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

}
