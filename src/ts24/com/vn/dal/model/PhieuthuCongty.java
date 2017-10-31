package ts24.com.vn.dal.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
@Entity
@Table(name = "phieuthu_congty")
public class PhieuthuCongty implements Serializable {

	private String id;
	private String maSoThue;
	private String tenCongTy;
	private String diaChi;
	private String email;
	private String dienThoai;
	private String taiKhoanNhan;
	private String taiKhoanKySoNhan;
	private int tinhTrang;
	private String loaiDnghiep;
	private String file;
	private String ngayDangKy;
	private String ngayThayDoi;
	private String tenDangNhap;
	private String khoaXacThuc;
	private String maCqt;
	private String coQuanQuanLy;
	private String denNgay;
	private String fax;
	private String matKhau;
	private String nganHang;
	private String ngayHetHan;
	private String ngayKichHoat;
	private String nhaCungCap;
	private String quanHuyen;
	private String serial;
	private int soLuongHdhienTai;
	private int soNgay;
	private String taiKhoanNh;
	private String tenChungThuSo;
	private String tinh;
	private int tongSoHdxuat;
	private String tuNgay;

	@Basic
	@JsonProperty("ID")
	@Id
	@Column(name = "ID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic
	@JsonProperty("MaSoThue")
	@Column(name = "MaSoThue")
	public String getMaSoThue() {
		return this.maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	@Basic
	@JsonProperty("TenCongTy")
	@Column(name = "TenCongTy")
	public String getTenCongTy() {
		return this.tenCongTy;
	}

	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
	}

	@Basic
	@JsonProperty("DiaChi")
	@Column(name = "DiaChi")
	public String getDiaChi() {
		return this.diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Basic
	@JsonProperty("Rule")
	@Column(name = "Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@JsonProperty("DienThoai")
	@Column(name = "DienThoai")
	public String getDienThoai() {
		return this.dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
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
	@JsonProperty("TaiKhoanKySoNhan")
	@Column(name = "TaiKhoanKySoNhan")
	public String getTaiKhoanKySoNhan() {
		return this.taiKhoanKySoNhan;
	}

	public void setTaiKhoanKySoNhan(String taiKhoanKySoNhan) {
		this.taiKhoanKySoNhan = taiKhoanKySoNhan;
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

	@Basic
	@JsonProperty("LoaiDNghiep")
	@Column(name = "LoaiDNghiep")
	public String getLoaiDnghiep() {
		return this.loaiDnghiep;
	}

	public void setLoaiDnghiep(String loaiDnghiep) {
		this.loaiDnghiep = loaiDnghiep;
	}

	@Basic
	@JsonProperty("File")
	@Column(name = "File")
	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Basic
	@JsonProperty("NgayDangKy")
	@Column(name = "NgayDangKy")
	public String getNgayDangKy() {
		return this.ngayDangKy;
	}

	public void setNgayDangKy(String ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

	@Basic
	@JsonProperty("NgayThayDoi")
	@Column(name = "NgayThayDoi")
	public String getNgayThayDoi() {
		return this.ngayThayDoi;
	}

	public void setNgayThayDoi(String ngayThayDoi) {
		this.ngayThayDoi = ngayThayDoi;
	}

	@Basic
	@JsonProperty("TenDangNhap")
	@Column(name = "TenDangNhap")
	public String getTenDangNhap() {
		return this.tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	@Basic
	@JsonProperty("KhoaXacThuc")
	@Column(name = "KhoaXacThuc")
	public String getKhoaXacThuc() {
		return this.khoaXacThuc;
	}

	public void setKhoaXacThuc(String khoaXacThuc) {
		this.khoaXacThuc = khoaXacThuc;
	}

	@Basic
	@JsonProperty("MaCQT")
	@Column(name = "MaCQT")
	public String getMaCqt() {
		return this.maCqt;
	}

	public void setMaCqt(String maCqt) {
		this.maCqt = maCqt;
	}

	@Basic
	@JsonProperty("CoQuanQuanLy")
	@Column(name = "CoQuanQuanLy")
	public String getCoQuanQuanLy() {
		return this.coQuanQuanLy;
	}

	public void setCoQuanQuanLy(String coQuanQuanLy) {
		this.coQuanQuanLy = coQuanQuanLy;
	}

	@Basic
	@JsonProperty("DenNgay")
	@Column(name = "DenNgay")
	public String getDenNgay() {
		return this.denNgay;
	}

	public void setDenNgay(String denNgay) {
		this.denNgay = denNgay;
	}

	@Basic
	@JsonProperty("Fax")
	@Column(name = "Fax")
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Basic
	@JsonProperty("MatKhau")
	@Column(name = "MatKhau")
	public String getMatKhau() {
		return this.matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Basic
	@JsonProperty("NganHang")
	@Column(name = "NganHang")
	public String getNganHang() {
		return this.nganHang;
	}

	public void setNganHang(String nganHang) {
		this.nganHang = nganHang;
	}

	@Basic
	@JsonProperty("NgayHetHan")
	@Column(name = "NgayHetHan")
	public String getNgayHetHan() {
		return this.ngayHetHan;
	}

	public void setNgayHetHan(String ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}

	@Basic
	@JsonProperty("NgayKichHoat")
	@Column(name = "NgayKichHoat")
	public String getNgayKichHoat() {
		return this.ngayKichHoat;
	}

	public void setNgayKichHoat(String ngayKichHoat) {
		this.ngayKichHoat = ngayKichHoat;
	}

	@Basic
	@JsonProperty("NhaCungCap")
	@Column(name = "NhaCungCap")
	public String getNhaCungCap() {
		return this.nhaCungCap;
	}

	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	@Basic
	@JsonProperty("QuanHuyen")
	@Column(name = "QuanHuyen")
	public String getQuanHuyen() {
		return this.quanHuyen;
	}

	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}

	@Basic
	@JsonProperty("Serial")
	@Column(name = "Serial")
	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	@Basic
	@JsonProperty("SoLuongHDHienTai")
	@Column(name = "SoLuongHDHienTai")
	public int getSoLuongHdhienTai() {
		return this.soLuongHdhienTai;
	}

	public void setSoLuongHdhienTai(int soLuongHdhienTai) {
		this.soLuongHdhienTai = soLuongHdhienTai;
	}

	@Basic
	@JsonProperty("SoNgay")
	@Column(name = "SoNgay")
	public int getSoNgay() {
		return this.soNgay;
	}

	public void setSoNgay(int soNgay) {
		this.soNgay = soNgay;
	}

	@Basic
	@JsonProperty("TaiKhoanNH")
	@Column(name = "TaiKhoanNH")
	public String getTaiKhoanNh() {
		return this.taiKhoanNh;
	}

	public void setTaiKhoanNh(String taiKhoanNh) {
		this.taiKhoanNh = taiKhoanNh;
	}

	@Basic
	@JsonProperty("TenChungThuSo")
	@Column(name = "TenChungThuSo")
	public String getTenChungThuSo() {
		return this.tenChungThuSo;
	}

	public void setTenChungThuSo(String tenChungThuSo) {
		this.tenChungThuSo = tenChungThuSo;
	}

	@Basic
	@JsonProperty("Tinh")
	@Column(name = "Tinh")
	public String getTinh() {
		return this.tinh;
	}

	public void setTinh(String tinh) {
		this.tinh = tinh;
	}

	@Basic
	@JsonProperty("TongSoHDXuat")
	@Column(name = "TongSoHDXuat")
	public int getTongSoHdxuat() {
		return this.tongSoHdxuat;
	}

	public void setTongSoHdxuat(int tongSoHdxuat) {
		this.tongSoHdxuat = tongSoHdxuat;
	}

	@Basic
	@JsonProperty("TuNgay")
	@Column(name = "TuNgay")
	public String getTuNgay() {
		return this.tuNgay;
	}

	public void setTuNgay(String tuNgay) {
		this.tuNgay = tuNgay;
	}

}
