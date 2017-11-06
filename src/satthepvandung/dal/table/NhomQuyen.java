package satthepvandung.dal.table;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;


@Entity
@Table(name = "w_nhomquyen")
@SuppressWarnings("serial")
public class NhomQuyen {

    String maNhomQuyen=null;
    String tenNhomQuyen=null;



    @Basic
    @Id
	@Column(name = "MaNhomQuyen")
	@JsonProperty("TenNhomQuyen")
    public String getMaNhomQuyen(){
        return maNhomQuyen;
    }
    public void setMaNhomQuyen(String maNhomQuyen){
        this.maNhomQuyen = maNhomQuyen;
    }
    
    @Basic
	@Column(name = "TenNhomQuyen")
	@JsonProperty("TenNhomQuyen")
    public String getTenNhomQuyen(){
        return tenNhomQuyen;
    }    
    public void setTenNhomQuyen(String tenNhomQuyen){
        this.tenNhomQuyen = tenNhomQuyen;
    }


}
