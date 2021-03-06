package satthepvandung.dal.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import satthepvandung.dal.dao.SanphamDAO;
import satthepvandung.dal.table.Sanpham;

public class SanphamDAOImpl extends BaseDAOImpl<Sanpham> implements SanphamDAO {

	@Override
	public int saveProduct(Sanpham obj) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();
		} catch (Exception e) {
			rollbackTransaction(tx);
			e.printStackTrace();
			return 0;
		} finally {
			closeSession(session);
		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	public List<Sanpham> getAll() throws Exception {
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM Sanpham WHERE trangThai IN ('1','2') ORDER BY ngayTao ASC");
			List<Sanpham> listSP = query.list();
			if (listSP != null && listSP.size() > 0){
				return listSP;
			}
		}finally{
			closeSession(session);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Sanpham getById(String id) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM Sanpham where id=:id AND trangThai IN ('1','2') ORDER BY ngayTao ASC");
			query.setParameter("id", Integer.parseInt(id));
			List<Sanpham> listSP = query.list();
			if (listSP != null && listSP.size() > 0) {
				return listSP.get(0);
			}
		} finally {
			closeSession(session);
		}
		return null;
	}

}
