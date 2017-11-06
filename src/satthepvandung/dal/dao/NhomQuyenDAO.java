package satthepvandung.dal.dao;

import java.util.List;

import satthepvandung.dal.table.NhomQuyen;

public interface NhomQuyenDAO extends BaseDAO<NhomQuyen>{
	 public List<NhomQuyen> getListMhomQuyen() throws Exception;
	}
