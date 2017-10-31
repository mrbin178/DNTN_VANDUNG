package ts24.com.vn.model;

public enum WebServiceType {
	WS_HOSPITAL(1, 	"Webservice bện biện"),
	WS_TS24(2, "Webservice TS24"),
	WS_VNPT(3, "Webservice VNPT");
	private int code;
	private String name;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private WebServiceType(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public static WebServiceType getInvoiceType(int code){
		WebServiceType type = null;
		for (WebServiceType actionType : WebServiceType.values()){
			if (actionType.getCode() == code){
				type = actionType;
				break;
			}
		}
		return type;
	}
}
