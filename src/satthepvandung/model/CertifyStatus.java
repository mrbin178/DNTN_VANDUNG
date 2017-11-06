package satthepvandung.model;

public enum CertifyStatus {
	CERTIFY_ERROR(-1, "Lỗi gửi xác thực"),
	CERTIFY_SUCCESS(1, "Đã xác thực"),
	CERTIFY_WAIT(2, "Đang gửi xác thực"),
	CERTIFY_NOT_YET(0, "Chưa xác thực");
	
	private int statusTypeId;
	private String statusTypeDes;
	public int getStatusTypeId( )
	{
		return statusTypeId;
	}
	public void setStatusTypeId( int statusTypeId )
	{
		this.statusTypeId = statusTypeId;
	}
	public String getStatusTypeDes( ){
		return statusTypeDes;
	}
	public void setStatusTypeDes( String statusTypeDes ){
		this.statusTypeDes = statusTypeDes;
	}
	private CertifyStatus(int statusTypeId, String statusTypeDes){
		this.statusTypeId = statusTypeId;
		this.statusTypeDes = statusTypeDes;
	}
	public static CertifyStatus getStatusTypeById(int statusTypeId){
		CertifyStatus type = null;
		for (CertifyStatus statusType : CertifyStatus.values()){
			if (statusType.getStatusTypeId( ) == statusTypeId){
				type = statusType;
				break;
			}
		}
		return type;
	}
}
