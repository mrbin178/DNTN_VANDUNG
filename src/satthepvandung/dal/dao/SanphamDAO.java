package satthepvandung.dal.dao;

import java.util.List;

import satthepvandung.dal.table.CoQuanThue;

public interface SanphamDAO extends BaseDAO<CoQuanThue>{
	 public List<CoQuanThue> getListTinh() throws Exception;
	 public List<CoQuanThue> getListQuanTheoTinh(String maTinh) throws Exception;
	 public List<CoQuanThue> getListLimitTinh(String maTinh) throws Exception;
	 public List<CoQuanThue> getListLimitQuan(String maQuan) throws Exception;
	}
