package ts24.com.vn.dal.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "phieuthu_quyen")
@SuppressWarnings("serial")
public class PhieuthuQuyen implements Serializable {

	private int maQuyen;
	private String maNhomQuyen;
	private String tenQuyen;


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MaQuyen")
	@Basic
	@JsonProperty("MaQuyen")
	public int getMaQuyen() {
		return this.maQuyen;
	}

	public void setMaQuyen(int maQuyen) {
		this.maQuyen = maQuyen;
	}

	@Basic
	@JsonProperty("MaNhomQuyen")
	@Column(name = "MaNhomQuyen")
	public String getMaNhomQuyen() {
		return this.maNhomQuyen;
	}

	public void setMaNhomQuyen(String maNhomQuyen) {
		this.maNhomQuyen = maNhomQuyen;
	}

	@Basic
	@JsonProperty("TenQuyen")
	@Column(name = "TenQuyen")
	public String getTenQuyen() {
		return this.tenQuyen;
	}

	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

}
