package ts24.com.vn.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;

import ts24.com.vn.dal.dao.PhieuthuDao;
import ts24.com.vn.dal.model.HddtTracuu;
import ts24.com.vn.dal.model.HoaDon;
import ts24.com.vn.dal.model.Phieuthu;
import ts24.com.vn.dal.model.nonentity.HoaDonView;
import ts24.com.vn.dal.model.nonentity.PhieuthuView;
import ts24.com.vn.model.ReceiptForm;
import ts24.com.vn.web.util.Path;
import ts24.com.vn.web.util.Utilities;

@Repository
@Transactional(readOnly = true)
public class PhieuthuDaoImpl extends BaseDAOImpl<Phieuthu> implements PhieuthuDao {
	final Logger logger = LoggerFactory.getLogger(PhieuthuDaoImpl.class);

	private EntityManager entity = null;
	Utilities Util= new Utilities();
	@PersistenceContext(name = "pu1")
	public void setEntityManager(EntityManager em) {
		this.entity = em;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Phieuthu save(Phieuthu objPT) {
		try{
			return entity.merge(objPT);
		}catch (DataIntegrityViolationException e) {

			e.printStackTrace();
			return null ;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Phieuthu searchReceipt(ReceiptForm form) throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String maTraCuu = form.getMaTraCuu();
			String soPhieuThu = form.getSoPhieuThu();
			String ngayThuTien = form.getNgayThuTien();
			String mstNguoiBan = form.getMstNguoiBan();
			
			String hql = "SELECT * FROM phieuthu WHERE 1=1 ";
			if(maTraCuu != null && !maTraCuu.equals("") )
				hql += " AND (IdHoaDon = '" +  maTraCuu + "' OR MaNhanHDon = '"+ maTraCuu +"')";
			
			if(soPhieuThu != null && !soPhieuThu.equals("") )
				hql += " AND SoHoaDon = '" +  soPhieuThu + "'";
			
			if(ngayThuTien != null && !ngayThuTien.equals("") )
				hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) = '" +  ngayThuTien + "'";
			
			
			if(mstNguoiBan != null && !mstNguoiBan.equals("") )
				hql += " AND MaSoThue = '" +  mstNguoiBan + "'";
			
			/**'1: Gốc; 3: Thay thế; 5: Điều chỉnh; ; 9: Điều chỉnh chiết khấu.'
			* Không lấy 7: Xóa bỏ
			**/ 
			hql += " AND TinhTrangHDon IN('1','3','5','9')";
			
			hql += " ORDER BY NgayTao DESC " ;
			
			SQLQuery query = session.createSQLQuery(hql).addEntity(Phieuthu.class);
			List<Phieuthu> list = query.list();
			if(list != null && list.size() > 0){
				Phieuthu obj = list.get(0);
				if(obj.getNgayXhdon() != null && !obj.getNgayXhdon().equals("")){
					obj.setNgayXhdon(Utilities.formatNgaySo4(obj.getNgayXhdon().split(" ")[0]));
					// check file exist
					String filePath = Path.PATH_DOWNLOAD + obj.getFileHdon();
					if(Utilities.checkExitsFile(filePath) == 1){
						obj.setDoiTuong(1); // set temp for check show button download
					}else{
						obj.setDoiTuong(0);
					}
					return obj;
				}
			}
			return null;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Phieuthu getObjByID(String id) throws Exception{
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM Phieuthu WHERE id=:id ");
			query.setParameter("id", id);
			List<Phieuthu> hddt = query.list();
			if (hddt != null && hddt.size() > 0){
				return hddt.get(0);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PhieuthuView> getPhieuThuByMaTraCuu(String maTraCuu, String tuNgay, String denNgay, String sortName, int startIndex, int pageSize) throws Exception{
		
		List<PhieuthuView> listHD = new ArrayList<PhieuthuView>(); 
		Session session = null;
		String hql = "SELECT * FROM phieuthu WHERE 1 = 1";
		hql = queryFilterHoaDonByMaNhanHD(maTraCuu,tuNgay, denNgay, hql, false);
		
		if (!StringUtils.isNullOrEmpty(sortName)){
			hql += " ORDER BY " + sortName;
		}
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql).addEntity(Phieuthu.class);
			if (startIndex > 0){
				query.setFirstResult(startIndex);
			}
			if (pageSize > 0){
				query.setMaxResults(pageSize);
			}
			List<Phieuthu> projectList = query.list();
			if (projectList != null && projectList.size() > 0){
				for (Phieuthu phieuthu : projectList) {
					PhieuthuView obj = new PhieuthuView();
					BeanUtils.copyProperties(phieuthu, obj);
					listHD.add(obj);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return listHD;
	}
	
	private String queryFilterHoaDonByMaNhanHD(String maTraCuu, String tuNgay, String denNgay, String hql, boolean isReportUseOfInvoices) {
		
		if(tuNgay != null && !tuNgay.equals("") &&  (denNgay == null || denNgay.equals(""))  )
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) >= '" +  tuNgay + "'";
		
		if(denNgay != null && !denNgay.equals("") &&  (tuNgay == null || tuNgay.equals(""))  )
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) <= '" + denNgay+ "'";
		
		if(tuNgay != null && !tuNgay.equals("") && denNgay != null && !denNgay.equals(""))			
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) >= '" +  tuNgay +	"' AND SUBSTRING_INDEX(NgayXHDon,' ', 1) <= '" + denNgay + "'";

		if(!StringUtils.isNullOrEmpty(maTraCuu)){
			hql += " AND (IdHoaDon IN ('" +  maTraCuu + "') OR MaNhanHDon IN ('" +  maTraCuu + "'))";
		}
		
		if(!isReportUseOfInvoices){
			hql += " AND TinhTrangHDon IN('1','3','5','9')";
		}
		return hql;
	}
	
	@Override
	public long getCountAllPhieuThu(String idHDLimit, String tuNgay, String denNgay) throws Exception{
		long totalRecords = 0;
		Session session = null;
		String hql = "SELECT COUNT(ID) FROM phieuthu WHERE 1 = 1 ";
		hql = queryFilterHoaDonByMaNhanHD(idHDLimit, tuNgay, denNgay, hql, false);
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql);
			List<Object> resultSet = query.list();
			totalRecords = Long.parseLong(resultSet.get(0).toString());
		}finally{ 
			closeSession(session);
		}
		return totalRecords;
	}
	
}