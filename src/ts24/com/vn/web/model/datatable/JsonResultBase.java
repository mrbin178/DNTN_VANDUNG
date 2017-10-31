package ts24.com.vn.web.model.datatable;

import org.codehaus.jackson.annotate.JsonProperty;

public class JsonResultBase {
	private String result;
	private Object message;
	
	@JsonProperty("Message")
	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
	
	@JsonProperty("Result")
	public String getResult() {
		return result;
	}
	
	public void setResult(ResultEnum result) {
		this.result = result.toString();
	}
}
