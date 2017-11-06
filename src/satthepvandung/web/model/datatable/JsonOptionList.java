package satthepvandung.web.model.datatable;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class JsonOptionList<T extends JsonOption> extends JsonResultBase {
	private List<T> options;

	@JsonProperty("Options")
	public List<T> getOptions() {
		return options;
	}

	public void setOptions(List<T> options) {
		this.options = options;
	}

}
