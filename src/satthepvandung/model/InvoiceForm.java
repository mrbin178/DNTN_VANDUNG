package satthepvandung.model;


public class InvoiceForm
{
	private String id;
	private String maNhanHD;
	private String soHoaDon;
	private String ngayXuat;
	private String accessKey;
	private String maSoThueDonVi, mauHDon="", kyHieuHDon="", fileNameBrowse="";
	private String mstNguoiBan;
	private String apply;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaNhanHD() {
		return maNhanHD;
	}
	public void setMaNhanHD(String maNhanHD) {
		this.maNhanHD = maNhanHD;
	}
	public String getSoHoaDon() {
		return soHoaDon;
	}
	public void setSoHoaDon(String soHoaDon) {
		this.soHoaDon = soHoaDon;
	}
	public String getNgayXuat() {
		return ngayXuat;
	}
	public void setNgayXuat(String ngayXuat) {
		this.ngayXuat = ngayXuat;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getMaSoThueDonVi() {
		return maSoThueDonVi;
	}
	public void setMaSoThueDonVi(String maSoThueDonVi) {
		this.maSoThueDonVi = maSoThueDonVi;
	}
	public String getMauHDon() {
		return mauHDon;
	}
	public void setMauHDon(String mauHDon) {
		this.mauHDon = mauHDon;
	}
	public String getKyHieuHDon() {
		return kyHieuHDon;
	}
	public void setKyHieuHDon(String kyHieuHDon) {
		this.kyHieuHDon = kyHieuHDon;
	}
	public String getFileNameBrowse() {
		return fileNameBrowse;
	}
	public void setFileNameBrowse(String fileNameBrowse) {
		this.fileNameBrowse = fileNameBrowse;
	}
	public String getMstNguoiBan() {
		return mstNguoiBan;
	}
	public void setMstNguoiBan(String mstNguoiBan) {
		this.mstNguoiBan = mstNguoiBan;
	}
	public String getApply() {
		return apply;
	}
	public void setApply(String apply) {
		this.apply = apply;
	}
}