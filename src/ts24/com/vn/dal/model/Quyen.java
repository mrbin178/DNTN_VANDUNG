package ts24.com.vn.dal.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;



@Entity
@Table(name = "w_quyen")
@SuppressWarnings("serial")
public class Quyen {

	
    String maNhomQuyen=null;
    int maQuyen = 0;
    String tenQuyen=null;


    @Basic
	@Column(name = "MaNhomQuyen")
	@JsonProperty("MaNhomQuyen")
    public String getMaNhomQuyen(){
        return maNhomQuyen;
    }
    public void setMaNhomQuyen(String maNhomQuyen){
        this.maNhomQuyen = maNhomQuyen;
    }
    
    @Basic
    @Id
	@Column(name = "MaQuyen")
	@JsonProperty("MaQuyen")
    public int getMaQuyen(){
        return maQuyen;
    }
    public void setMaQuyen(int maQuyen){
        this.maQuyen = maQuyen;
    }
    
    @Basic
	@Column(name = "TenQuyen")
	@JsonProperty("TenQuyen")
    public String getTenQuyen(){
        return tenQuyen;
    }
    public void setTenQuyen(String tenQuyen){
        this.tenQuyen = tenQuyen;
    }
    
  
    
    


}
