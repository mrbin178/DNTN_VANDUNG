package satthepvandung.dal.dao;

import java.util.List;

import satthepvandung.dal.table.Sanpham;

public interface SanphamDAO extends BaseDAO<Sanpham>{

	int saveProduct(Sanpham obj) throws Exception;

	List<Sanpham> getAll() throws Exception;

	Sanpham getById(String id) throws Exception;
	}
