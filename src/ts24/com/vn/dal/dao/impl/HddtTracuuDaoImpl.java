package ts24.com.vn.dal.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ts24.com.vn.dal.dao.HddtTracuuDao;
import ts24.com.vn.dal.model.HddtTracuu;
import ts24.com.vn.web.util.Utilities;

import com.mysql.jdbc.StringUtils;

@Repository
@Transactional(readOnly = true)
public class HddtTracuuDaoImpl extends BaseDAOImpl<HddtTracuu> implements HddtTracuuDao {
	final Logger logger = LoggerFactory.getLogger(HddtTracuuDaoImpl.class);

	private EntityManager entity = null;
	Utilities Util= new Utilities();
	@PersistenceContext(name = "pu1")
	public void setEntityManager(EntityManager em) {
		this.entity = em;
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public HddtTracuu save(HddtTracuu obj) {
		try{
			return entity.merge(obj);
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null ;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<HddtTracuu> searchHddtTracuu(String tkNhan, String maNhanHD) throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			
			String hql = "SELECT * FROM hddt_tracuu WHERE 1=1 ";
			
			if (!StringUtils.isNullOrEmpty(tkNhan)){
				hql += " AND TaiKhoanNhan = '" + tkNhan +"'";
			}
			
			if (!StringUtils.isNullOrEmpty(maNhanHD)){
				hql += " AND IDHoaDon = '" + maNhanHD +"'";
			}
			
			SQLQuery query = session.createSQLQuery(hql).addEntity(HddtTracuu.class);
			List<HddtTracuu> list = query.list();
			if(list != null && list.size() > 0){
				return list;
			}
			return null;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
}