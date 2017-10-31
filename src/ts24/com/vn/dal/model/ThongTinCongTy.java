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

@Entity
@Table( name = "hddt_thongtincty" )
@SuppressWarnings( "serial" )
public class ThongTinCongTy implements Serializable
{
	private String id;
	private String maSoThue;
	private String tenCongTy;
	private String diaChi;
	private String email;
	private String dienThoai;
	private String ngayDangKy;
	private String ngayThayDoi;
	private String taiKhoanNhan;
	private String taiKhoanKySoNhan;
	private int tinhTrang;
	private String loaiDNghiep;
	private String file;
	private String tenDangNhap ;
	private String khoaXacThuc ;

	@Basic
	@Id		
	@Column(name="ID")
	@JsonProperty("ID")
	public String getId( )
	{
		return id;
	}

	public void setId( String id )
	{
		this.id = id;
	}

	@Basic
	@Column( name = "MaSoThue" )
	@JsonProperty("MaSoThue")
	public String getMaSoThue( )
	{
		return maSoThue;
	}

	public void setMaSoThue( String maSoThue )
	{
		this.maSoThue = maSoThue;
	}

	@Basic
	@Column( name = "TenCongTy" )
	@JsonProperty("TenCongTy")
	public String getTenCongTy( )
	{
		return tenCongTy;
	}

	public void setTenCongTy( String tenCongTy )
	{
		this.tenCongTy = tenCongTy;
	}

	@Basic
	@Column( name = "DiaChi" )
	@JsonProperty("DiaChi")
	public String getDiaChi( )
	{
		return diaChi;
	}

	public void setDiaChi( String diaChi )
	{
		this.diaChi = diaChi;
	}

	@Basic
	@Column( name = "Email" )
	@JsonProperty("Email")
	public String getEmail( )
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	@Basic
	@Column( name = "DienThoai" )
	@JsonProperty("DienThoai")
	public String getDienThoai( )
	{
		return dienThoai;
	}

	public void setDienThoai( String dienThoai )
	{
		this.dienThoai = dienThoai;
	}

	@Basic
	@Column( name = "NgayDangKy" )
	@JsonProperty("NgayDangKy")
	public String getNgayDangKy( )
	{
		return ngayDangKy;
	}

	public void setNgayDangKy( String ngayDangKy )
	{
		this.ngayDangKy = ngayDangKy;
	}

	@Basic
	@Column( name = "NgayThayDoi" )
	@JsonProperty("NgayThayDoi")
	public String getNgayThayDoi( )
	{
		return ngayThayDoi;
	}

	public void setNgayThayDoi( String ngayThayDoi )
	{
		this.ngayThayDoi = ngayThayDoi;
	}

	@Basic
	@Column( name = "TaiKhoanNhan" )
	@JsonProperty("TaiKhoanNhan")
	public String getTaiKhoanNhan( )
	{
		return taiKhoanNhan;
	}

	public void setTaiKhoanNhan( String taiKhoanNhan )
	{
		this.taiKhoanNhan = taiKhoanNhan;
	}

	@Basic
	@Column( name = "TaiKhoanKySoNhan" )
	@JsonProperty("TaiKhoanKySoNhan")
	public String getTaiKhoanKySoNhan( )
	{
		return taiKhoanKySoNhan;
	}

	public void setTaiKhoanKySoNhan( String taiKhoanKySoNhan )
	{
		this.taiKhoanKySoNhan = taiKhoanKySoNhan;
	}

	@Basic
	@Column( name = "TinhTrang" )
	@JsonProperty("TinhTrang")
	public int getTinhTrang( )
	{
		return tinhTrang;
	}

	public void setTinhTrang( int tinhTrang )
	{
		this.tinhTrang = tinhTrang;
	}

	@Basic
	@Column( name = "LoaiDNghiep" )
	@JsonProperty("LoaiDNghiep")
	public String getLoaiDNghiep( )
	{
		return loaiDNghiep;
	}

	public void setLoaiDNghiep( String loaiDNghiep )
	{
		this.loaiDNghiep = loaiDNghiep;
	}

	@Basic
	@Column( name = "File" )
	@JsonProperty("File")
	public String getFile( )
	{
		return file;
	}

	public void setFile( String file )
	{
		this.file = file;
	}

	/**
	 * @param tenDangNhap the tenDangNhap to set
	 */
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	@Basic
	@Column( name = "TenDangNhap" )
	@JsonProperty("TenDangNhap")
	public String getTenDangNhap() {
		return tenDangNhap;
	}

	
	public void setKhoaXacThuc(String khoaXacThuc) {
		this.khoaXacThuc = khoaXacThuc;
	}

	@Basic
	@Column( name = "KhoaXacThuc" )
	@JsonProperty("KhoaXacThuc")
	public String getKhoaXacThuc() {
		return khoaXacThuc;
	}
}