package ts24.com.vn.dal.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name="roles")
public class Role {
	private int roleId;
	private String roleName;
	private String description;
	//private Set<User> users;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RoleID")
	@JsonProperty("RoleID")
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Column(name="RoleName")
	@JsonProperty("RoleName")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name="Description")
	@JsonProperty("Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy="role")
	@JsonIgnore
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}*/
	
	

}
