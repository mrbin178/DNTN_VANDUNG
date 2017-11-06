package satthepvandung.web.model.datatable;

import org.codehaus.jackson.annotate.JsonProperty;

public class JsonResultListBase extends JsonResultBase {
	private long totalRecordsCount;

	@JsonProperty("TotalRecordCount")
	public long getTotalRecordsCount() {
		return totalRecordsCount;
	}

	public void setTotalRecordsCount(long totalRecordsCount) {
		this.totalRecordsCount = totalRecordsCount;
	}
}
