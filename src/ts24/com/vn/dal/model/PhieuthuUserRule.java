package ts24.com.vn.dal.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "phieuthu_user_rule")
@SuppressWarnings("serial")
public class PhieuthuUserRule implements Serializable {

	private int id;
	private String username;
	private String rule;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	@Basic
	@JsonProperty("ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@JsonProperty("Username")
	@Column(name = "Username")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Basic
	@JsonProperty("Rule")
	@Column(name = "Rule")
	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}
