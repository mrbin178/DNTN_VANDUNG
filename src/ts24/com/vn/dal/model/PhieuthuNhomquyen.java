package ts24.com.vn.dal.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "phieuthu_nhomquyen")
@SuppressWarnings("serial")
public class PhieuthuNhomquyen implements Serializable {

	private String maNhomQuyen;
	private String tenNhomQuyen;

	@Id
	@Column(name = "MaNhomQuyen")
	@Basic
	@JsonProperty("MaNhomQuyen")
	public String getMaNhomQuyen() {
		return this.maNhomQuyen;
	}

	public void setMaNhomQuyen(String maNhomQuyen) {
		this.maNhomQuyen = maNhomQuyen;
	}

	@Basic
	@JsonProperty("TenNhomQuyen")
	@Column(name = "TenNhomQuyen")
	public String getTenNhomQuyen() {
		return this.tenNhomQuyen;
	}

	public void setTenNhomQuyen(String tenNhomQuyen) {
		this.tenNhomQuyen = tenNhomQuyen;
	}

}
