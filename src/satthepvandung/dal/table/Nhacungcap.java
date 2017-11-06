package satthepvandung.dal.table;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name="nhacungcap")
@SuppressWarnings("serial")
public class Nhacungcap implements Serializable {

	private String id;
	private String dienThoai;
	private String maNhaCungCap;
	private String noCanTra;
	private String tenNhaCungCap;
	private int trangThai;

	@Basic
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@JsonProperty("ID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic
	@Column(name = "DienThoai")
	@JsonProperty("DienThoai")
	public String getDienThoai() {
		return this.dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	@Basic
	@Column(name = "MaNhaCungCap")
	@JsonProperty("MaNhaCungCap")
	public String getMaNhaCungCap() {
		return this.maNhaCungCap;
	}

	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	@Basic
	@Column(name = "NoCanTra")
	@JsonProperty("NoCanTra")
	public String getNoCanTra() {
		return this.noCanTra;
	}

	public void setNoCanTra(String noCanTra) {
		this.noCanTra = noCanTra;
	}

	@Basic
	@Column(name = "TenNhaCungCap")
	@JsonProperty("TenNhaCungCap")
	public String getTenNhaCungCap() {
		return this.tenNhaCungCap;
	}

	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}

	@Basic
	@Column(name = "TrangThai")
	@JsonProperty("TrangThai")
	public int getTrangThai() {
		return this.trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

}