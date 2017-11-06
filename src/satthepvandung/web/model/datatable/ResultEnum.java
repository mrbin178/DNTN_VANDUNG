package satthepvandung.web.model.datatable;

public enum ResultEnum {
	OK("OK"), ERROR("ERROR");
	
	private String value;
    private ResultEnum(String value){
    	this.value = value;
    }
    
    public String toString(){
    	return value;
    }
}
