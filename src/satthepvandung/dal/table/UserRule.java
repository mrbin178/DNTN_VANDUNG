package satthepvandung.dal.table;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;


@Entity
@Table(name = "w_user_rule")
@SuppressWarnings("serial")
public class UserRule {

	
    String  rule ;
    int id=0;
    String userName=null;

      
    @Basic
	@Column(name = "Username")
	@JsonProperty("Username")
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    @Basic
	@Column(name = "Rule")
	@JsonProperty("Rule")
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@JsonProperty("ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    
   
   
    


}
