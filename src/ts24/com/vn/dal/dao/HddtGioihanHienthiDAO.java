package ts24.com.vn.dal.dao;

import java.util.List;

import ts24.com.vn.dal.model.HddtGioihanHienthi;
import ts24.com.vn.dal.model.nonentity.HddtGioiHanHienThiView;

public interface HddtGioihanHienthiDAO extends BaseDAO<HddtGioihanHienthi>{

	List<HddtGioiHanHienThiView> getGioihanHienthiSelected(String sMst, String sTinhTrang, String sortName, int startIndex, int pageSize) throws Exception;

	int save(HddtGioihanHienthi obj) throws Exception;

	List<HddtGioihanHienthi> findByMst(String mst) throws Exception;

	int delete(String id) throws Exception;

	HddtGioihanHienthi findById(String id) throws Exception;
	}
