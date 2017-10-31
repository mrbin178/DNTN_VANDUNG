package ts24.com.vn.dal.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
@Entity
@Table(name = "hddt_gioihan_hienthi")
public class HddtGioihanHienthi implements Serializable {

	private int id;
	private String mst;
	private String ngayBd;
	private String ngayKt;
	private String ngayTao;
	private String ngayCapNhat;
	private String nguoiTao;
	private int tinhTrang;

	@Basic
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	@JsonProperty("ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@JsonProperty("MST")
	@Column(name = "MST", length = 15)
	public String getMst() {
		return this.mst;
	}

	public void setMst(String mst) {
		this.mst = mst;
	}

	@Basic
	@JsonProperty("NgayBD")
	@Column(name = "NgayBD", length = 0)
	public String getNgayBd() {
		return this.ngayBd;
	}

	public void setNgayBd(String ngayBd) {
		this.ngayBd = ngayBd;
	}

	@Basic
	@JsonProperty("NgayKT")
	@Column(name = "NgayKT", length = 0)
	public String getNgayKt() {
		return this.ngayKt;
	}

	public void setNgayKt(String ngayKt) {
		this.ngayKt = ngayKt;
	}

	@Basic
	@JsonProperty("NgayTao")
	@Column(name = "NgayTao", length = 0)
	public String getNgayTao() {
		return this.ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	@Basic
	@JsonProperty("NgayCapNhat")
	@Column(name = "NgayCapNhat", length = 0)
	public String getNgayCapNhat() {
		return this.ngayCapNhat;
	}

	public void setNgayCapNhat(String ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	@Basic
	@JsonProperty("NguoiTao")
	@Column(name = "NguoiTao", length = 100)
	public String getNguoiTao() {
		return this.nguoiTao;
	}

	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}

	@Basic
	@JsonProperty("TinhTrang")
	@Column(name = "TinhTrang")
	public int getTinhTrang() {
		return this.tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

}
