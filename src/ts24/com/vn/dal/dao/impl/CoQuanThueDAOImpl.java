package ts24.com.vn.dal.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ts24.com.vn.dal.dao.CoQuanThueDAO;
import ts24.com.vn.dal.model.CoQuanThue;

public class CoQuanThueDAOImpl extends BaseDAOImpl<CoQuanThue> implements CoQuanThueDAO{

	 public List<CoQuanThue> getListTinh() throws Exception {
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM CoQuanThue where maLoai=:maloai AND kKQM=:kkqm AND maTinhThue !=:cucthue ORDER BY maTinhThue ASC");
			query.setParameter("maloai", "0");
			query.setParameter("kkqm", 1);
			query.setParameter("cucthue", "000");
			List<CoQuanThue> listThue = query.list();
			if (listThue != null && listThue.size() > 0){
				return listThue;
			}
		}finally{
			closeSession(session);
		}
		return null;
	}

	 public List<CoQuanThue> getListQuanTheoTinh(String maTinh) throws Exception {
			Session session = null;
			
			try{
				session = sessionFactory.openSession();
				Query query = session.createQuery("FROM CoQuanThue where maTinhThue=:maTinh AND maLoai=:maloai AND kKQM=:kkqm ORDER BY maCoQuanThue ASC");
				query.setParameter("maloai", "1");
				query.setParameter("kkqm", 1);
				query.setParameter("maTinh", maTinh);
				List<CoQuanThue> listThue = query.list();
				if (listThue != null && listThue.size() > 0){
					return listThue;
				}
			}finally{
				closeSession(session);
			}
			return null;
		}
	 
	 public List<CoQuanThue> getListLimitTinh(String maTinh) throws Exception {
			Session session = null;
			
			try{
				session = sessionFactory.openSession();
				Query query = session.createQuery("FROM CoQuanThue where maLoai=:maloai AND kKQM=:kkqm AND maTinhThue=:maTinh ORDER BY maTinhThue ASC");
				query.setParameter("maloai", "0");
				query.setParameter("kkqm", 1);
				query.setParameter("maTinh", maTinh);
				List<CoQuanThue> listThue = query.list();
				if (listThue != null && listThue.size() > 0){
					return listThue;
				}
			}finally{
				closeSession(session);
			}
			return null;
		}
	 
	 public List<CoQuanThue> getListLimitQuan(String maQuan) throws Exception {
			Session session = null;
			
			try{
				session = sessionFactory.openSession();
				Query query = session.createQuery("FROM CoQuanThue where maLoai=:maloai AND kKQM=:kkqm AND maCoQuanThue=:maQuan ORDER BY maCoQuanThue ASC");
				query.setParameter("maloai", "1");
				query.setParameter("kkqm", 1);
				query.setParameter("maQuan", maQuan);
				List<CoQuanThue> listThue = query.list();
				if (listThue != null && listThue.size() > 0){
					return listThue;
				}
			}finally{
				closeSession(session);
			}
			return null;
		}
	 
}
