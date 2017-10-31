package ts24.com.vn.dal.dao;

import java.util.List;

import ts24.com.vn.dal.model.CoQuanThue;

public interface CoQuanThueDAO extends BaseDAO<CoQuanThue>{
	 public List<CoQuanThue> getListTinh() throws Exception;
	 public List<CoQuanThue> getListQuanTheoTinh(String maTinh) throws Exception;
	 public List<CoQuanThue> getListLimitTinh(String maTinh) throws Exception;
	 public List<CoQuanThue> getListLimitQuan(String maQuan) throws Exception;
	}
