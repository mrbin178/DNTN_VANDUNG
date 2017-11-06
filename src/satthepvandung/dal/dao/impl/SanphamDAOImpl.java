package satthepvandung.dal.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import satthepvandung.dal.dao.SanphamDAO;
import satthepvandung.dal.table.CoQuanThue;
import satthepvandung.dal.table.Sanpham;
import satthepvandung.dal.table.UserRule;

public class SanphamDAOImpl extends BaseDAOImpl<Sanpham> implements SanphamDAO {

	public void saveProduct(Sanpham obj) throws Exception {
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
		} finally {
			closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sanpham> getAll() throws Exception {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM Sanpham ORDER BY ngayTao ASC");
			List<Sanpham> listSP = query.list();
			if (listSP != null && listSP.size() > 0) {
				return listSP;
			}
		} finally {
			closeSession(session);
		}
		return null;
	}

	public List<CoQuanThue> getListQuanTheoTinh(String maTinh) throws Exception {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(
					"FROM CoQuanThue where maTinhThue=:maTinh AND maLoai=:maloai AND kKQM=:kkqm ORDER BY maCoQuanThue ASC");
			query.setParameter("maloai", "1");
			query.setParameter("kkqm", 1);
			query.setParameter("maTinh", maTinh);
			List<CoQuanThue> listThue = query.list();
			if (listThue != null && listThue.size() > 0) {
				return listThue;
			}
		} finally {
			closeSession(session);
		}
		return null;
	}

	public List<CoQuanThue> getListLimitTinh(String maTinh) throws Exception {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(
					"FROM CoQuanThue where maLoai=:maloai AND kKQM=:kkqm AND maTinhThue=:maTinh ORDER BY maTinhThue ASC");
			query.setParameter("maloai", "0");
			query.setParameter("kkqm", 1);
			query.setParameter("maTinh", maTinh);
			List<CoQuanThue> listThue = query.list();
			if (listThue != null && listThue.size() > 0) {
				return listThue;
			}
		} finally {
			closeSession(session);
		}
		return null;
	}

	public List<CoQuanThue> getListLimitQuan(String maQuan) throws Exception {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(
					"FROM CoQuanThue where maLoai=:maloai AND kKQM=:kkqm AND maCoQuanThue=:maQuan ORDER BY maCoQuanThue ASC");
			query.setParameter("maloai", "1");
			query.setParameter("kkqm", 1);
			query.setParameter("maQuan", maQuan);
			List<CoQuanThue> listThue = query.list();
			if (listThue != null && listThue.size() > 0) {
				return listThue;
			}
		} finally {
			closeSession(session);
		}
		return null;
	}

}
