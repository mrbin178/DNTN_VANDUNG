package ts24.com.vn.service;

import java.util.List;

import ts24.com.vn.dal.model.CoQuanThue;
import ts24.com.vn.dal.model.LoginAdmin;


public interface CoQuanThueService {
	public List<CoQuanThue> getListTinh() throws Exception;
	public List<CoQuanThue> getListQuanTheoTinh(String maTinh) throws Exception;
	public CoQuanThue getCoQuanThue(String maCoQuan) throws Exception;
	public List<CoQuanThue> getListLimitTinh(String maTinh) throws Exception;
	public List<CoQuanThue> getListLimitQuan(String maQuan) throws Exception;
}
