package ts24.com.vn.model;


public enum StatusSystem {
	
	ERR_NOT_EXISTS_IDHOADON("TS064", "Hóa đơn không này không tồn tại trên hệ thống."),
	ERR_CERTIFICATE_VNPT_NOT_SEND("TS065", "Hóa đơn chưa gửi cấp mã chống giả."),
	ERR_CERTIFICATE_VNPT_PENDING_SEND("TS066", "Hóa đơn đang gửi đi cấp mã chống giả"),
	ERR_CERTIFICATE_VNPT_SEND("TS067", "Hóa đơn gửi cấp mã chống giả bị lỗi"),
	
	SUCCESSFULLY("TS111", "Xử lý dữ liệu thành công"),
	UNSUCCESSFULLY("TS222", "Xử lý dữ liệu không thành công"),
	ERR_DATA_INPUT_REQUIRED("TS888", "Dữ liệu đầu vào bắt buộc không được rỗng"),
	ERR_CONNECTION_WEB_SERVICES("TS777", "Lỗi kết nối web services"),
	ERROR_CATCH("TS999", "Lỗi trong quá trình xử lý dữ liệu");
	
	
	
	private String code;
	private String des;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	private StatusSystem(String code, String des) {
		this.code = code;
		this.des = des;
	}

	public static StatusSystem getStatusTypeById(String code){
		StatusSystem type = null;
		for (StatusSystem status : StatusSystem.values()){
			if (status.getCode().equals(code)){
				type = status;
				break;
			}
		}
		return type;
	}
}
