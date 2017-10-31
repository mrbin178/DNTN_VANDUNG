package ts24.com.vn.service.impl;

import java.util.List;

import ts24.com.vn.dal.dao.NhomQuyenDAO;
import ts24.com.vn.dal.dao.impl.NhomQuyenDAOImpl;
import ts24.com.vn.dal.model.NhomQuyen;
import ts24.com.vn.service.NhomQuyenService;

public class NhomQuyenServiceImpl extends BaseServiceImpl<NhomQuyen> implements NhomQuyenService{

	 private NhomQuyenDAO nhomquyenDao;
	    
	    public NhomQuyenServiceImpl(NhomQuyenDAO dao){
	    	this.nhomquyenDao = dao;
	    }
	    
	    public NhomQuyenServiceImpl(){
	    	this.nhomquyenDao = new NhomQuyenDAOImpl();
	    }
	@Override
	public List<NhomQuyen> getListMhomQuyen() throws Exception {
		// TODO Auto-generated method stub
		return nhomquyenDao.getListMhomQuyen();
	}

}
