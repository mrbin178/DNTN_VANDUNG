package satthepvandung.web.model.datatable;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class JsonResultList<T> extends JsonResultListBase{
	private List<T> records;
	
	public JsonResultList(){}
	
	@JsonProperty("Records")
	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
	
	


	
	


	
	
	
}
