package ts24.com.vn.dal.dao;

import java.util.List;

import ts24.com.vn.dal.model.NhomQuyen;

public interface NhomQuyenDAO extends BaseDAO<NhomQuyen>{
	 public List<NhomQuyen> getListMhomQuyen() throws Exception;
	}
