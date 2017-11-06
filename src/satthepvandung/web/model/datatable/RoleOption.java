package satthepvandung.web.model.datatable;

import org.codehaus.jackson.annotate.JsonProperty;

import satthepvandung.dal.table.Role;

public class RoleOption extends JsonOption<Role>{
    
	@Override
	@JsonProperty("DisplayText")
	public String getDisplayText() {
		return record.getRoleName();
	}

	@Override
	@JsonProperty("Value")
	public Integer getValue() {
		return record.getRoleId();
	}

}
