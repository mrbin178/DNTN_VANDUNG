package ts24.com.vn.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ts24.com.vn.dal.dao.HddtGioihanHienthiDAO;
import ts24.com.vn.dal.model.HddtGioihanHienthi;
import ts24.com.vn.dal.model.HoaDon;
import ts24.com.vn.dal.model.LoginAdmin;
import ts24.com.vn.dal.model.UserRule;
import ts24.com.vn.dal.model.nonentity.HddtGioiHanHienThiView;
import ts24.com.vn.web.util.Utilities;

public class HddtGioihanHienthiDAOImpl extends BaseDAOImpl<HddtGioihanHienthi> implements HddtGioihanHienthiDAO{
	
	final Logger logger = LoggerFactory.getLogger(HddtGioihanHienthiDAOImpl.class);

	private EntityManager entity = null;
	Utilities Util= new Utilities();
	@PersistenceContext(name = "pu1")
	public void setEntityManager(EntityManager em) {
		this.entity = em;
	}

	@Override
	public HddtGioihanHienthi findById(String id) throws Exception {
		String hSql = "FROM HddtGioihanHienthi WHERE id =:id";
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hSql);
			query.setParameter("id", Integer.parseInt(id));
		    return (HddtGioihanHienthi) query.uniqueResult();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	public int save(HddtGioihanHienthi obj) throws Exception {
		Session session = null;
		Transaction tx = null;
		try{			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();			
			int save =  (Integer)session.save(obj);	
			tx.commit();
			return save;
		}catch(Exception e){
			rollbackTransaction(tx);
			e.printStackTrace();
			return 0;
		}finally{
			closeSession(session);
		}
	}
	
	public void update(HddtGioihanHienthi obj) throws Exception {
		Session session = null;
		Transaction tx = null;
		try{			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();			
			session.update(obj);	
			tx.commit();
		}catch(Exception e){
			rollbackTransaction(tx);
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public List<HddtGioiHanHienThiView> getGioihanHienthiSelected(String sMst, String sTinhTrang, String sortName, int startIndex, int pageSize) throws Exception{
		List<HddtGioiHanHienThiView> listGH = new ArrayList<HddtGioiHanHienThiView>();

		String hql = "SELECT * FROM hddt_gioihan_hienthi WHERE 1=1" ;
		if(sMst != null && !sMst.equals("")){
			hql += " AND MST = '" + sMst +"'";
		}
		if(sTinhTrang != null && !sTinhTrang.equals("")){
			hql += " AND TinhTrang = '" + sTinhTrang +"'";
		}
		hql += " ORDER BY " + sortName;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql).addEntity(HddtGioihanHienthi.class);
			if (startIndex > 0){
				query.setFirstResult(startIndex);
			}
			if (pageSize > 0){
				query.setMaxResults(pageSize);
			}
			List<HddtGioihanHienthi> projectList = query.list();
			if (projectList != null && projectList.size() > 0){
				for (HddtGioihanHienthi hddtGioihanHienthi : projectList) {
					HddtGioiHanHienThiView obj = new HddtGioiHanHienThiView();
					hddtGioihanHienthi.setNgayBd(Util.formatNgayThangNam__ddmmyyyy(hddtGioihanHienthi.getNgayBd()));
					hddtGioihanHienthi.setNgayKt(Util.formatNgayThangNam__ddmmyyyy(hddtGioihanHienthi.getNgayKt()));
					hddtGioihanHienthi.setNgayTao(Util.formatNgayThangNam__ddmmyyyy(hddtGioihanHienthi.getNgayTao()));
					BeanUtils.copyProperties(hddtGioihanHienthi, obj);
					listGH.add(obj);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return listGH;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HddtGioihanHienthi> findByMst(String mst) throws Exception{
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM HddtGioihanHienthi WHERE mst=:mst AND tinhTrang=:tinhTrang");
			query.setParameter("mst", mst);
			query.setParameter("tinhTrang", 1);
			List<HddtGioihanHienthi> list = query.list();
			if (list != null && list.size() > 0){
				return list;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	@Override
	public int delete(String id) throws Exception {
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();			
			Query query = session.createQuery("DELETE FROM HddtGioihanHienthi where id = :id");
			query.setParameter("id", Integer.parseInt(id));
			query.executeUpdate();
			tx.commit();
		}catch(Exception e){
			rollbackTransaction(tx);
			e.printStackTrace();
			return 2 ;
		}finally{
			closeSession(session);
		}
		return 1 ;
	}

}
