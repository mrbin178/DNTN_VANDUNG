package ts24.com.vn.model;

public class ReceiptForm
{
	private String id;
	private String maTraCuu;
	private String soPhieuThu;
	private String ngayThuTien;
	private String fileNameBrowse="";
	private String mstNguoiBan;
	private String accessKey;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getMaTraCuu() {
		return maTraCuu;
	}
	public void setMaTraCuu(String maTraCuu) {
		this.maTraCuu = maTraCuu;
	}
	public String getSoPhieuThu() {
		return soPhieuThu;
	}
	public void setSoPhieuThu(String soPhieuThu) {
		this.soPhieuThu = soPhieuThu;
	}
	public String getNgayThuTien() {
		return ngayThuTien;
	}
	public void setNgayThuTien(String ngayThuTien) {
		this.ngayThuTien = ngayThuTien;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	
}