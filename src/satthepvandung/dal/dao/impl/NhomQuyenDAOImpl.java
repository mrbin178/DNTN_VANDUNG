package satthepvandung.dal.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import satthepvandung.dal.dao.NhomQuyenDAO;
import satthepvandung.dal.table.NhomQuyen;

public class NhomQuyenDAOImpl extends BaseDAOImpl<NhomQuyen> implements NhomQuyenDAO{

	 public List<NhomQuyen> getListMhomQuyen() throws Exception {
			Session session = null;
			
			try{
				session = sessionFactory.openSession();
				Query query = session.createQuery("FROM NhomQuyen ORDER BY maNhomQuyen ASC");
				
				List<NhomQuyen> listThue = query.list();
				if (listThue != null && listThue.size() > 0){
					return listThue;
				}
			}finally{
				closeSession(session);
			}
			return null;
		}


}
