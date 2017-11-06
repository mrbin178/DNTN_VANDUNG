package satthepvandung.model;

public enum InvoiceElectronicType {
	//1: Điện tử; 2: Xác thực; 3: HDDT-Xác thực chống giả (Điện tử xác thực)
	DIEN_TU(1, "Điện tử"),
	XAC_THUC(2, "Xác thực"),
	HOADON_VNPT(4, "HDDT-VNPT"),
	XAC_THUC_VNPT(3, "HDDT-Xác thực chống giả (Điện tử xác thực)");
	
	private int code;
	private String des;
	private InvoiceElectronicType(int code, String des) {
		this.code = code;
		this.des = des;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public static InvoiceElectronicType getInvoiceTypeByCode(int code){
		InvoiceElectronicType type = null;
		for (InvoiceElectronicType invoiceType : InvoiceElectronicType.values()){
			if (invoiceType.getCode() == code){
				type = invoiceType;
				break;
			}
		}
		return type;
	}
}
