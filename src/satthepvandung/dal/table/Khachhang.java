package satthepvandung.dal.table;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name="khachhang")
@SuppressWarnings("serial")
public class Khachhang implements Serializable {

	private String id;
	private String dienThoai;
	private String email;
	private String ghiChu;
	private String gioiTinh;
	private int loaiKhachHang;
	private String maKhachHang;
	private String mst;
	private String ngaySinh;
	private String phuongXa;
	private String tenCongTy;
	private String tenKhachHang;
	private String tinhTP;
	private String tongTienBan;
	private String tongTienBanTruTraLai;
	private String tongTienNoHienTai;
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
	@Column(name = "Email")
	@JsonProperty("Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "GhiChu")
	@JsonProperty("GhiChu")
	public String getGhiChu() {
		return this.ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Basic
	@Column(name = "GioiTinh")
	@JsonProperty("GioiTinh")
	public String getGioiTinh() {
		return this.gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Basic
	@Column(name = "LoaiKhachHang")
	@JsonProperty("LoaiKhachHang")
	public int getLoaiKhachHang() {
		return this.loaiKhachHang;
	}

	public void setLoaiKhachHang(int loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}

	@Basic
	@Column(name = "MaKhachHang")
	@JsonProperty("MaKhachHang")
	public String getMaKhachHang() {
		return this.maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	@Basic
	@Column(name = "Mst")
	@JsonProperty("Mst")
	public String getMst() {
		return this.mst;
	}

	public void setMst(String mst) {
		this.mst = mst;
	}

	@Basic
	@Column(name = "NgaySinh")
	@JsonProperty("NgaySinh")
	public String getNgaySinh() {
		return this.ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	@Basic
	@Column(name = "PhuongXa")
	@JsonProperty("PhuongXa")
	public String getPhuongXa() {
		return this.phuongXa;
	}

	public void setPhuongXa(String phuongXa) {
		this.phuongXa = phuongXa;
	}

	@Basic
	@Column(name = "TenCongTy")
	@JsonProperty("TenCongTy")
	public String getTenCongTy() {
		return this.tenCongTy;
	}

	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
	}

	@Basic
	@Column(name = "TenKhachHang")
	@JsonProperty("TenKhachHang")
	public String getTenKhachHang() {
		return this.tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	@Basic
	@Column(name = "TinhTP")
	@JsonProperty("TinhTP")
	public String getTinhTP() {
		return this.tinhTP;
	}

	public void setTinhTP(String tinhTP) {
		this.tinhTP = tinhTP;
	}

	@Basic
	@Column(name = "TongTienBan")
	@JsonProperty("TongTienBan")
	public String getTongTienBan() {
		return this.tongTienBan;
	}

	public void setTongTienBan(String tongTienBan) {
		this.tongTienBan = tongTienBan;
	}

	@Basic
	@Column(name = "TongTienBanTruTraLai")
	@JsonProperty("TongTienBanTruTraLai")
	public String getTongTienBanTruTraLai() {
		return this.tongTienBanTruTraLai;
	}

	public void setTongTienBanTruTraLai(String tongTienBanTruTraLai) {
		this.tongTienBanTruTraLai = tongTienBanTruTraLai;
	}

	@Basic
	@Column(name = "TongTienNoHienTai")
	@JsonProperty("TongTienNoHienTai")
	public String getTongTienNoHienTai() {
		return this.tongTienNoHienTai;
	}

	public void setTongTienNoHienTai(String tongTienNoHienTai) {
		this.tongTienNoHienTai = tongTienNoHienTai;
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