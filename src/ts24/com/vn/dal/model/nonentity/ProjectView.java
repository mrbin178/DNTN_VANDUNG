package ts24.com.vn.dal.model.nonentity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "project")
public class ProjectView implements Serializable {
	private static final long serialVersionUID = 1052327540592798066L;
	private String peat;
	private String projectId;
	private String projectName;
	private Date createdDate;
	private String createdUser;
	private String userGroup;
	private int pricePlanStatus;
	private int status;
	private String statusName;

	@JsonProperty("pricePlanStatus")
	public int getPricePlanStatus() {
		return pricePlanStatus;
	}

	public void setPricePlanStatus(int pricePlanStatus) {
		this.pricePlanStatus = pricePlanStatus;
	}

	@JsonProperty("projectId")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	@JsonProperty("projectName")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@JsonProperty("peat")
	public String getPeat() {
		return peat;
	}

	public void setPeat(String peat) {
		this.peat = peat;
	}

	@JsonProperty("createdDate")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@JsonProperty("createdUser")
	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	@JsonProperty("UserGroup")
	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	@JsonProperty("status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
