package satthepvandung.model;

public enum CategoryInvoicesType {
	TYPE_01GTKT("01GTKT", "Hóa đơn GTGT"),
	TYPE_02GTTT("02GTTT", "Hóa đơn bán hàng"),
	TYPE_07KPTQ("07KPTQ", "Hóa đơn bán hàng khu phi thuế quan"),
	TYPE_03XKNB("03XKNB", "Phiếu xuất kho kiêm vận chuyển nội bộ"),
	TYPE_04HGDL("04HGDL", "Phiếu xuất kho hàng gửi bán đại lý"),
	TYPE_01GTKT_KHT("01GTKT-KHT", "Hóa đơn GTGT kiêm hoàn thuế");
	
	private String invoiceCode;
	private String invoiceName;
	
	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	private CategoryInvoicesType(String invoiceCode, String invoiceName){
		this.invoiceCode = invoiceCode;
		this.invoiceName = invoiceName;
	}
	
	public static CategoryInvoicesType getInvoiceType(String invoiceCode){
		CategoryInvoicesType type = null;
		for (CategoryInvoicesType transactionType : CategoryInvoicesType.values()){
			if (transactionType.getInvoiceCode().equals(invoiceCode)){
				type = transactionType;
				break;
			}
		}
		return type;
	}
}
