package ts24.com.vn.model;

public enum CertifyStatusResult {
	STATUS_02("02", "Thành công"),
	STATUS_03("03", "Không thành công");
	
	private String code;
	private String des;
	private CertifyStatusResult(String code, String des) {
		this.code = code;
		this.des = des;
	}

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

	public static CertifyStatusResult getStatusTypeById(String code){
		CertifyStatusResult type = null;
		for (CertifyStatusResult status : CertifyStatusResult.values()){
			if (status.getCode().equals(code)){
				type = status;
				break;
			}
		}
		return type;
	}
}
