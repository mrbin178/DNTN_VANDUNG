package ts24.com.vn.dal.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ts24.com.vn.dal.dao.NhomQuyenDAO;
import ts24.com.vn.dal.model.NhomQuyen;

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
