package ts24.com.vn.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ts24.com.vn.dal.dao.CoQuanThueDAO;
import ts24.com.vn.dal.dao.impl.CoQuanThueDAOImpl;
import ts24.com.vn.dal.model.CoQuanThue;
import ts24.com.vn.service.CoQuanThueService;

public class CoQuanThueServiceImpl extends BaseServiceImpl<CoQuanThue> implements CoQuanThueService{

	 private CoQuanThueDAO coquanthueDao;
	    
	    public CoQuanThueServiceImpl(CoQuanThueDAO dao){
	    	this.coquanthueDao = dao;
	    }
	    
	    public CoQuanThueServiceImpl(){
	    	this.coquanthueDao = new CoQuanThueDAOImpl();
	    }
	    
	@Override
	public List<CoQuanThue> getListTinh() throws Exception {
		// TODO Auto-generated method stub
		return coquanthueDao.getListTinh();
	}

	@Override
	public List<CoQuanThue> getListQuanTheoTinh(String maTinh) throws Exception {
		// TODO Auto-generated method stub
		return coquanthueDao.getListQuanTheoTinh(maTinh);
	}
	
	@Override
	public CoQuanThue getCoQuanThue(String maCoQuan) throws Exception {
		String hSql = "From CoQuanThue u where u.maCoQuanThue =:maCoQuan";
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hSql);
			query.setParameter("maCoQuan", maCoQuan);
		    return (CoQuanThue) query.uniqueResult();
		}finally{
			closeSession(session);
		}
	}
	
	@Override
	public List<CoQuanThue> getListLimitTinh(String maTinh) throws Exception {
		// TODO Auto-generated method stub
		return coquanthueDao.getListLimitTinh(maTinh);
	}
	
	@Override
	public List<CoQuanThue> getListLimitQuan(String maQuan) throws Exception {
		// TODO Auto-generated method stub
		return coquanthueDao.getListLimitQuan(maQuan);
	}
	
}
