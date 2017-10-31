package ts24.com.vn.dal.dao.impl;

import java.math.BigDecimal;
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

import ts24.com.vn.dal.dao.HoaDonDao;
import ts24.com.vn.dal.model.HoaDon;
import ts24.com.vn.dal.model.HoaDonChiTiet;
import ts24.com.vn.dal.model.nonentity.BaoCaoBanHangView;
import ts24.com.vn.dal.model.nonentity.HoaDonView;
import ts24.com.vn.dal.model.nonentity.ReportBangKeBanRaView;
import ts24.com.vn.dal.model.nonentity.ReportTBaoPhatHanhView;
import ts24.com.vn.dal.model.nonentity.ReportTongHopView;
import ts24.com.vn.dal.model.nonentity.ReportUseOfInvoicesView;
import ts24.com.vn.model.CategoryInvoicesType;
import ts24.com.vn.model.InvoiceForm;
import ts24.com.vn.model.objSearchInvoice;
import ts24.com.vn.model.objSearchPhatHanh;
import ts24.com.vn.web.util.ConstantValue;
import ts24.com.vn.web.util.Path;
import ts24.com.vn.web.util.Utilities;

import com.mysql.jdbc.StringUtils;

@Repository
@Transactional(readOnly = true)
public class HoaDonDaoImpl extends BaseDAOImpl<HoaDon> implements HoaDonDao {
	final Logger logger = LoggerFactory.getLogger(HoaDonDaoImpl.class);

	private EntityManager entity = null;
	Utilities Util= new Utilities();
	@PersistenceContext(name = "pu1")
	public void setEntityManager(EntityManager em) {
		this.entity = em;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public HoaDon save(HoaDon objCty) {
		try{
			return entity.merge(objCty);
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null ;
		}
	}
	@Override
	public HoaDon searchHoaDon(InvoiceForm form) throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String maNhanHoaDon = form.getMaNhanHD(), soHoaDon = form.getSoHoaDon();
			String ngayXuat = form.getNgayXuat();
	//		String maSoThueDonVi = form.getMaSoThueDonVi();
			String mauHDon = form.getMauHDon(), kyHieuHDon = form.getKyHieuHDon();
	//		String keyFullSearch = Path.KEY_FULL_SEARCH;
			String mstNguoiBan = form.getMstNguoiBan();
			
			String hql = "SELECT * FROM hoadon WHERE 1=1 ";
			if(maNhanHoaDon != null && !maNhanHoaDon.equals("") )
				hql += " AND (IdHoaDon = '" +  maNhanHoaDon + "' OR MaNhanHDon = '"+ maNhanHoaDon +"')";
			
			if (kyHieuHDon != null && !kyHieuHDon.equals("") ){
				hql += " AND KyHieuHDon = '" + kyHieuHDon + "'";
			}
			if (mauHDon != null && !mauHDon.equals("") ){
				if( mauHDon.contains( "/" )){
					hql += " AND MauHDon = '" + mauHDon.split( "/" )[0] + "'";
					hql += " AND SoThuTu = '" + mauHDon.split( "/" )[1] + "'";
				}else{
					return null;
				}	
			}
			if(soHoaDon != null && !soHoaDon.equals("") )
				hql += " AND SoHoaDon = '" +  soHoaDon + "'";
			
			if(ngayXuat != null && !ngayXuat.equals("") )
				hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) = '" +  ngayXuat + "'";
			
			
			if(mstNguoiBan != null && !mstNguoiBan.equals("") )
				hql += " AND MaSoThue = '" +  mstNguoiBan + "'";
			
			// if key equals key configuration then allow search all invoice
	//		if(!form.getAccessKey().equals(keyFullSearch)){
	//			if(maSoThueDonVi == null || maSoThueDonVi.equals("")){
	//				return null;
	//			}
	//			sql += " AND MaSoThue = '" +  maSoThueDonVi + "'"; // MaSoThue nguoi ban
	//		}
			
			/**'1: Gốc; 3: Thay thế; 5: Điều chỉnh; ; 9: Điều chỉnh chiết khấu.'
			* Không lấy 7: Xóa bỏ
			**/ 
			hql += " AND TinhTrangHDon IN('1','3','5','9')";
			
			hql += " ORDER BY NgayTao DESC " ;
			
			SQLQuery query = session.createSQLQuery(hql).addEntity(HoaDon.class);
			List<HoaDon> list = query.list();
			if(list != null && list.size() > 0){
				HoaDon obj = list.get(0);
				if(obj.getNgayXHDon() != null && !obj.getNgayXHDon().equals("")){
					obj.setNgayXHDon(Utilities.formatNgaySo4(obj.getNgayXHDon().split(" ")[0]));
					// check file exist
					String filePath = Path.PATH_DOWNLOAD + obj.getFileHDon();
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
	
	@Override
	public HoaDon getObjByID(String id) throws Exception{
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM HoaDon WHERE ID=:id ");
			query.setParameter("id", id);
			List<HoaDon> hddt = query.list();
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
	public List<HoaDonView> getAllHoaDonNew(objSearchInvoice objSearch, String maCQLimit,String mstLimit,int role,String sortName, int startIndex, int pageSize) throws Exception{
		
		List<HoaDonView> listHD = new ArrayList<HoaDonView>(); 
		List<HoaDon> result = null;
		Session session = null;
		String tuNgay = "" ,denNgay = "" ;
	    tuNgay =	objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
		String hql = "SELECT * FROM hoadon WHERE 1 = 1";
		hql = queryFilterInvoice(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, hql, false);
		
		if (!StringUtils.isNullOrEmpty(sortName)){
			hql += " ORDER BY " + sortName;
		}
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql).addEntity(HoaDon.class);
			if (startIndex > 0){
				query.setFirstResult(startIndex);
			}
			if (pageSize > 0){
				query.setMaxResults(pageSize);
			}
			List<HoaDon> projectList = query.list();
			if (projectList != null && projectList.size() > 0){
				for (HoaDon hoaDon : projectList) {
					HoaDonView obj = new HoaDonView();
					BeanUtils.copyProperties(hoaDon, obj);
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

	private String queryFilterInvoice(objSearchInvoice objSearch,
			String maCQLimit, String mstLimit, int role, String tuNgay,
			String denNgay, String hql, boolean isReportUseOfInvoices) {
		if(tuNgay != null && !tuNgay.equals("") &&  (denNgay == null || denNgay.equals(""))  )
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) >= '" +  tuNgay + "'";
		
		if(denNgay != null && !denNgay.equals("") &&  (tuNgay == null || tuNgay.equals(""))  )
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) <= '" + denNgay+ "'";
		
		if(tuNgay != null && !tuNgay.equals("") && denNgay != null && !denNgay.equals(""))			
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) >= '" +  tuNgay +	"' AND SUBSTRING_INDEX(NgayXHDon,' ', 1) <= '" + denNgay + "'";

		if (!StringUtils.isNullOrEmpty(objSearch.getSoHoaDon())){
			hql += " AND SoHoaDon = '" + objSearch.getSoHoaDon() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getKyHieu())){
			hql += " AND KyHieuHDon = '" + objSearch.getKyHieu() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getStt())){
			hql += " AND SoThuTu = '" + objSearch.getStt() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getMauHoaDon())){
			hql += " AND MauHDon = '" + objSearch.getMauHoaDon() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getMstNguoiMua())){
			hql += " AND MSTNguoiMua = '" + objSearch.getMstNguoiMua() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getMstNguoiBan())){
			hql += " AND MaSoThue = '" + objSearch.getMstNguoiBan() +"'";
		}
		if(!StringUtils.isNullOrEmpty(objSearch.getMaNhanHDon())){
//			hql += " AND MaNhanHDon = '" + objSearch.getMaNhanHDon() +"'";
			hql += " AND (IdHoaDon = '" +  objSearch.getMaNhanHDon() + "' OR MaNhanHDon = '"+ objSearch.getMaNhanHDon() +"')";
		}
		
		if(!isReportUseOfInvoices){
			hql += " AND TinhTrangHDon IN('1','3','5','9')";
		}
		
		if(role == 1){
			if (!StringUtils.isNullOrEmpty(objSearch.getMaTinh())){
				hql += " AND MaCQT like '" + objSearch.getMaTinh() +"%'";
			}
			if (!StringUtils.isNullOrEmpty(objSearch.getMaCoQuan())){
				hql += " AND MaCQT = '" + objSearch.getMaCoQuan() +"'";
			}
		}else{
			if (!StringUtils.isNullOrEmpty(objSearch.getMaTinh())){
				hql += " AND MaCQT like '" + objSearch.getMaTinh() +"%'";
			}else{
				hql += " AND MaCQT like '" + maCQLimit +"%'";
			}
			if (!StringUtils.isNullOrEmpty(objSearch.getMaCoQuan())){
				hql += " AND MaCQT = '" + objSearch.getMaCoQuan() +"'";
			}else{
				hql += " AND MaCQT like '" + maCQLimit +"%'";
			}
		}
		
		if(mstLimit != null && !mstLimit.equals("")){
			hql += " AND MaSoThue IN('" + mstLimit.replaceAll(",", "','") +"')";
		}
		
		if (!StringUtils.isNullOrEmpty(objSearch.getLoaiHDon())){
			int loaiHD = Integer.parseInt(objSearch.getLoaiHDon());
			if(loaiHD == 1 || loaiHD == 2 || loaiHD == 3){ // 1:Dien tu; 2: Xac thuc; 3: HDDT-Xác thực chống giả (Điện tử xác thực)
				hql += " AND LoaiHDon =" + loaiHD;
			}
		}
		return hql;
	}
	
	@Override
	public long getRecordCountInvoice(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role) throws Exception{
		long totalRecords = 0;
		List<HoaDonView> listHD = new ArrayList<HoaDonView>(); 
		List<HoaDon> result = null;
		Session session = null;
		String tuNgay = "" ,denNgay = "" ;
	    tuNgay =	objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
		String hql = "SELECT COUNT(hoadon.IdHoaDon) FROM hoadon WHERE 1 = 1 ";
		hql = queryFilterInvoice(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, hql, false);
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
	
	@Override
	public List<ReportBangKeBanRaView> getReportBangKeBanRa(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize) throws Exception{
//		String hql = "SELECT a.*, MaSoThue, CONCAT(MauHDon,'/',SoThuTu) as KyHieuMauSo, KyHieuHDon, a.SoHoaDon, a.NgayXHDon, HoTenNguoiMua,MSTNguoiMua, "
//				+ "b.TenHang, b.ThanhTien DSBHCT, b.ThueSuat, b.TienThue as ThueGTGT, "
//				+ "(Case WHEN b.TienThue is null THEN b.ThanhTien ELSE b.ThanhTien + b.TienThue END) AS TongCong "
//				+ "FROM hoadon a INNER JOIN hoadon_chitiet b ON a.IdHoaDon = b.MaHoaDon";
	    List<ReportBangKeBanRaView> listHD = new ArrayList<ReportBangKeBanRaView>();
		int count = 0;
		String tuNgay = "" ,denNgay = "" ;
	    tuNgay = objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
	    if(tuNgay != null && !tuNgay.equals("")){
			tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
		}
		if(denNgay != null && !denNgay.equals("")){
			denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
		}
//		String hql = "SELECT MaSoThue, CONCAT(MauHDon,'/',SoThuTu) as KyHieuMauSo, KyHieuHDon, a.SoHoaDon, a.NgayXHDon, HoTenNguoiMua, MSTNguoiMua, "
//				+ "b.TenHang, b.ThanhTien DSBHCT, b.ThueSuat, b.TienThue as ThueGTGT, b.KhuyenMai "
//				+ "FROM hoadon a INNER JOIN hoadon_chitiet b ON a.IdHoaDon = b.MaHoaDon";
		String hql = "SELECT  MaSoThue, CONCAT(SUBSTRING(MauHDon, 1, LENGTH(MauHDon)-1), '/', SoThuTu) as KyHieuMauSo, KyHieuHDon, a.SoHoaDon, a.NgayXHDon, HoTenNguoiMua, MSTNguoiMua," 
						+ " b.TenHang, b.ThanhTien DSBHCT, b.ThueSuat,"
						//+ " b.TienThue as ThueGTGT,"
						+ "		CASE "
						+ "			WHEN b.TienThue is null THEN '' " 
						+ "			ELSE b.TienThue"
						+ "		END as ThueGTGT, "
						+ " (Case" 
						+ "	WHEN b.KhuyenMai = -1 THEN"
						+ "		CASE "
						+ "			WHEN b.TienThue is null THEN b.ThanhTien" 
						+ "			ELSE b.TienThue + b.ThanhTien"
						+ "		END"
						+ "	ELSE '0' "
						+ " END) AS ChietKhauGiamTru"
						+ " FROM hoadon a INNER JOIN hoadon_chitiet b ON a.IdHoaDon = b.MaHoaDon";
		hql = queryFilterInvoice(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, hql, false);
		if (!StringUtils.isNullOrEmpty(sortName)){
			hql += " ORDER BY " + sortName;
		}
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql);
			List<Object[]> rows = query.list();
			if (rows.size() > 0){
				for (Object[] row: rows){
					count = count + 1;
					ReportBangKeBanRaView obj = new ReportBangKeBanRaView();
					String thueSuat = "", chiecKhauGiamTru = "";
					thueSuat = String.valueOf(row[9]); // thue xuat
					chiecKhauGiamTru = String.valueOf(row[11]); // khuyen mai
					
					obj.setMstNguoiBan(String.valueOf(row[0])); // masothue
					obj.setMauHoaDon(String.valueOf(row[1])); // KyHieuMauSo
					obj.setKyHieuHoaDon(String.valueOf(row[2])); //KyHieuHDon
					obj.setSoHoaDon(String.valueOf(row[3])); ////SoHoaDon
					obj.setNgayThangNamPhatHanh(Util.formatDateTime2ddMMyyyy(String.valueOf(row[4])).replaceAll("-", "/")); // NgayXHDon
					obj.setTenNguoiMua(String.valueOf(row[5]));//HoTenNguoiMua
					obj.setMstNguoiMua(String.valueOf(row[6]));//MSTNguoiMua
					obj.setMatHang(String.valueOf(row[7])); //TenHang
					obj.setDoanhSoBanChuaCoThue(String.valueOf(row[8])); //ThanhTien DSBHCT
					if(thueSuat.equals("-1")){//ThueSuat
						thueSuat ="KCT";
					}
					obj.setThueSuat(thueSuat);
					obj.setThueGTGT(String.valueOf(row[10])); //ThueGTGT
					obj.setChietKhauGiamTru(chiecKhauGiamTru);
					obj.setStt(String.valueOf(count));
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
	@Override
	public List<ReportBangKeBanRaView> getReportBangKeBanRaNotJoin(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize) throws Exception{
		List<ReportBangKeBanRaView> listHD = new ArrayList<ReportBangKeBanRaView>(); 
		Session session = null;
		int count = 0;
	    String tuNgay="",denNgay = ""; 
		tuNgay = objSearch.getTuNgay();
		denNgay = objSearch.getDenNgay();
		if(tuNgay != null && !tuNgay.equals("")){
			tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
		}
		if(denNgay != null && !denNgay.equals("")){
			denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
		}
		String hql = "SELECT * FROM hoadon WHERE 1 = 1";
		hql = queryFilterInvoiceBCBH(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, hql);
		
		if (!StringUtils.isNullOrEmpty(sortName)){
			hql += " ORDER BY " + sortName;
		}
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql).addEntity(HoaDon.class);
			if (startIndex > 0){
				query.setFirstResult(startIndex);
			}
			if (pageSize > 0){
				query.setMaxResults(pageSize);
			}
			List<HoaDon> list = query.list();
			if (list != null && list.size() > 0){
				for (HoaDon hoaDon : list) {
					List<HoaDonChiTiet> listCT = getListChiTietHD(hoaDon.getIdHoaDon());
					if(listCT != null && listCT.size() > 0){
						for (HoaDonChiTiet objCT : listCT) {
							count = count +1;
							ReportBangKeBanRaView obj = new ReportBangKeBanRaView();
							obj.setMstNguoiBan(hoaDon.getMaSoThue());
							obj.setMauHoaDon(hoaDon.getMauHDon() + "/" + hoaDon.getSoThuTu());
							obj.setKyHieuHoaDon(hoaDon.getKyHieuHDon());
							obj.setSoHoaDon(hoaDon.getSoHoaDon());
							obj.setNgayThangNamPhatHanh(Util.formatDateTime2ddMMyyyy(hoaDon.getNgayXHDon()).replaceAll("-", "/"));
							obj.setTenNguoiMua(hoaDon.getHoTenNguoiMua());
							obj.setMstNguoiMua(hoaDon.getMstNguoiMua());
							obj.setMatHang(objCT.getTenHang());
							obj.setDoanhSoBanChuaCoThue(objCT.getThanhTien());
							if(objCT.getThueSuat().equals("-1")){
								obj.setThueSuat("KCT");
							}else{
								obj.setThueSuat(objCT.getThueSuat());
							}
							obj.setThueGTGT(objCT.getTienThue());
							if (objCT.getKhuyenMai() == -1){
								obj.setChietKhauGiamTru(String.valueOf(Integer.parseInt(objCT.getThanhTien()) + Integer.parseInt(objCT.getTienThue())));
							}else{
								obj.setChietKhauGiamTru("0");
							}
							obj.setStt(String.valueOf(count));
							listHD.add(obj);
						}
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return listHD;
	}
	
	@Override
	public long getRecordCountBangKeBanRa(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role) throws Exception{
		long totalRecords = 0;
		Session session = null;
		String tuNgay="",denNgay = ""; 
		tuNgay = objSearch.getTuNgay();
		denNgay = objSearch.getDenNgay();
		if(tuNgay != null && !tuNgay.equals("")){
			tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
		}
		if(denNgay != null && !denNgay.equals("")){
			denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
		}
		String hql = "SELECT * FROM hoadon WHERE 1 = 1 ";
		hql = queryFilterInvoiceBCBH(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, hql);
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql).addEntity(HoaDon.class);
			List<HoaDon> list = query.list();
			if (list != null && list.size() > 0){
				for(HoaDon hoaDon : list) {
					List<HoaDonChiTiet> listCT = getListChiTietHD(hoaDon.getIdHoaDon());
					if(listCT != null && listCT.size() > 0){
						totalRecords+= listCT.size();
					}
				}
			}
		}finally{ 
			closeSession(session);
		}
		return totalRecords;
	}
	
	
	// bao cao ban hang
	@SuppressWarnings("unchecked")
	@Override
	public List<BaoCaoBanHangView> getAllHoaDonBaoCaoBanHang(objSearchInvoice objSearch, String maCQLimit,String mstLimit,int role,String sortName, int startIndex, int pageSize) throws Exception{
		
		List<BaoCaoBanHangView> listHD = new ArrayList<BaoCaoBanHangView>(); 
		List<HoaDon> result = null;
		Session session = null;
		String tuNgay = "" ,denNgay = "" ;
	    tuNgay =	objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
	    if(tuNgay != null && !tuNgay.equals("")){
			tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
		}
		if(denNgay != null && !denNgay.equals("")){
			denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
		}
		String hql = "SELECT * FROM hoadon WHERE 1 = 1";
		hql = queryFilterInvoiceBCBH(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, hql);
		
		if (!StringUtils.isNullOrEmpty(sortName)){
			hql += " ORDER BY " + sortName;
		}
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql).addEntity(HoaDon.class);
			if (startIndex > 0){
				query.setFirstResult(startIndex);
			}
			if (pageSize > 0){
				query.setMaxResults(pageSize);
			}
			List<HoaDon> projectList = query.list();
			if (projectList != null && projectList.size() > 0){
				for (HoaDon hoaDon : projectList) {
					List<HoaDonChiTiet> listCT = getListChiTietHD(hoaDon.getIdHoaDon());
					if(listCT != null && listCT.size() > 0){
						for (HoaDonChiTiet objCT : listCT) {
							BaoCaoBanHangView obj = new BaoCaoBanHangView();
							int thanhTien = 0, tienThue = 0;
							BeanUtils.copyProperties(objCT, obj);
							obj.setMaSoThueBan(hoaDon.getMaSoThue());
							obj.setMaSoThueMua(hoaDon.getMstNguoiMua());
							obj.setSoHD(hoaDon.getSoHoaDon());
							if(hoaDon.getNgayXHDon() != null && !hoaDon.getNgayXHDon().equals("")){
								obj.setNgayXHDon(Util.formatNgayThangNam__ddmmyyyy(hoaDon.getNgayXHDon().split(" ")[0]));
							}							
							obj.setDoanhThu(objCT.getThanhTien());
							if(objCT.getThanhTien() != null && !objCT.getThanhTien().equals("")){
								thanhTien = Integer.parseInt(objCT.getThanhTien());
							}
							if(objCT.getTienThue() != null && !objCT.getTienThue().equals("")){
								tienThue = Integer.parseInt(objCT.getTienThue());
							}
							obj.setThanhTien(thanhTien +tienThue+"");
							if(objCT.getThueSuat().equals("-1")){
								obj.setThueSuat("KCT");
							}
							listHD.add(obj);
						}
						
					}
					
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return listHD;
	}

	private String queryFilterInvoiceBCBH(objSearchInvoice objSearch,
			String maCQLimit, String mstLimit, int role, String tuNgay,
			String denNgay, String hql) {
		if(tuNgay != null && !tuNgay.equals("") &&  (denNgay == null || denNgay.equals(""))  )
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) >= '" +  tuNgay + "'";
		
		if(denNgay != null && !denNgay.equals("") &&  (tuNgay == null || tuNgay.equals(""))  )
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) <= '" + denNgay+ "'";
		
		if(tuNgay != null && !tuNgay.equals("") && denNgay != null && !denNgay.equals(""))			
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) >= '" +  tuNgay +	"' AND SUBSTRING_INDEX(NgayXHDon,' ', 1) <= '" + denNgay + "'";

//		if (!StringUtils.isNullOrEmpty(objSearch.getSoHoaDon())){
//			hql += " AND SoHoaDon = '" + objSearch.getSoHoaDon() +"'";
//		}
		if (!StringUtils.isNullOrEmpty(objSearch.getKyHieu())){
			hql += " AND KyHieuHDon = '" + objSearch.getKyHieu() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getStt())){
			hql += " AND SoThuTu = '" + objSearch.getStt() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getMauHoaDon())){
			hql += " AND MauHDon = '" + objSearch.getMauHoaDon() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getMstNguoiMua())){
			hql += " AND MSTNguoiMua = '" + objSearch.getMstNguoiMua() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getMstNguoiBan())){
			hql += " AND MaSoThue = '" + objSearch.getMstNguoiBan() +"'";
		}
		
		hql += " AND TinhTrangHDon IN('1','3','5','9')";
		
		if(role == 1){
			if (!StringUtils.isNullOrEmpty(objSearch.getMaTinh())){
				hql += " AND MaCQT like '" + objSearch.getMaTinh() +"%'";
			}
			if (!StringUtils.isNullOrEmpty(objSearch.getMaCoQuan())){
				hql += " AND MaCQT = '" + objSearch.getMaCoQuan() +"'";
			}
		}else{
			if (!StringUtils.isNullOrEmpty(objSearch.getMaTinh())){
				hql += " AND MaCQT like '" + objSearch.getMaTinh() +"%'";
			}else{
				hql += " AND MaCQT like '" + maCQLimit +"%'";
			}
			if (!StringUtils.isNullOrEmpty(objSearch.getMaCoQuan())){
				hql += " AND MaCQT = '" + objSearch.getMaCoQuan() +"'";
			}else{
				hql += " AND MaCQT like '" + maCQLimit +"%'";
			}
		}
		
		if(mstLimit != null && !mstLimit.equals("")){
			hql += " AND MaSoThue IN('" + mstLimit.replaceAll(",", "','") +"')";
		}
		
		if (!StringUtils.isNullOrEmpty(objSearch.getLoaiHDon())){
			int loaiHD = Integer.parseInt(objSearch.getLoaiHDon());
			if(loaiHD == 1 || loaiHD == 2 || loaiHD == 3){ // 1:Dien tu; 2: Xac thuc; 3: HDDT-Xác thực chống giả (Điện tử xác thực)
				hql += " AND LoaiHDon =" + loaiHD;
			}
		}
		return hql;
	}
	
	@Override
	public long getRecordCountInvoiceBCBH(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role) throws Exception{
		long totalRecords = 0;
		List<BaoCaoBanHangView> listHD = new ArrayList<BaoCaoBanHangView>(); 
		List<HoaDon> result = null;
		Session session = null;
		String tuNgay = "" ,denNgay = "" ;
	    tuNgay =	objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
		String hql = "SELECT * FROM hoadon WHERE 1 = 1 ";
		hql = queryFilterInvoiceBCBH(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, hql);
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql).addEntity(HoaDon.class);
		//	SQLQuery query = session.createSQLQuery(hql);
			//List<Object> resultSet = query.list();
			List<HoaDon> projectList = query.list();
			if (projectList != null && projectList.size() > 0){
				for (HoaDon hoaDon : projectList) {
					List<HoaDonChiTiet> listCT = getListChiTietHD(hoaDon.getIdHoaDon());
					if(listCT != null && listCT.size() > 0){
						
						totalRecords+= listCT.size();
					}
					
				}
			}
			
		//	totalRecords = Long.parseLong(resultSet.get(0).toString());
		}finally{ 
			closeSession(session);
		}
		return totalRecords;
	}
	
	private  List<HoaDonChiTiet> getListChiTietHD(String idHoaDon) throws Exception{
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM HoaDonChiTiet WHERE maHoaDon=:idHoaDon ");
			query.setParameter("idHoaDon", idHoaDon);
			List<HoaDonChiTiet> hddt = query.list();
			if (hddt != null && hddt.size() > 0){
				return hddt;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}

	@Override
	public String getSQLDateTime() throws Exception {
		return getSQLDateTime("SELECT NOW()");
	}

	@Override
	public List<ReportUseOfInvoicesView> getReportUseOfInvoices(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize) throws Exception{
		List<ReportUseOfInvoicesView> listHD = new ArrayList<ReportUseOfInvoicesView>();
		int count = 0;
		String tuNgay = "" ,denNgay = "", where = "";
	    tuNgay = objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
	    if(tuNgay != null && !tuNgay.equals("")){
			tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
		}
		if(denNgay != null && !denNgay.equals("")){
			denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
		}
		
		where = queryFilterInvoice(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, where, true);
		String hql = "SELECT ts.MaSoThue ct2, SUBSTRING(ts.MauHDon, 1, LENGTH(ts.MauHDon)-1) ct3,"
				+ " CONCAT(SUBSTRING(ts.MauHDon, 1, LENGTH(ts.MauHDon)-1), '/', ts.SoThuTu) ct4, ts.KyHieuHDon ct6, MIN(ts.SoHoaDon) as ct7,"
				+ " MAX(ts.SoHoaDon) ct8, MAX(ts.SoHoaDon) - MIN(ts.SoHoaDon) +1 ct9 ,ts1.ct10 ct10, ts2.ct11 ct11 , ts2.ct12 ct12"
				+ " FROM hoadon ts"
				+ " LEFT JOIN (SELECT MaSoThue, MauHDon, SoThuTu, KyHieuHDon, COUNT(MauHDon) ct10 FROM hoadon"
				+ " WHERE TinhTrangHDon <>7" + where
				+ " GROUP BY MauHDon, SoThuTu, KyHieuHDon, MaSoThue) ts1"
				+ " ON ts.MauHDon = ts1.MauHDon AND ts.SoThuTu = ts1.SoThuTu and ts.KyHieuHDon = ts1.KyHieuHDon AND ts.MaSoThue = ts1.MaSoThue"
				+ " LEFT JOIN (SELECT MaSoThue, MauHDon, SoThuTu, KyHieuHDon, COUNT(MauHDon) ct11, GROUP_CONCAT(SoHoaDon) ct12"
				+ " FROM hoadon"
				+ " WHERE TinhTrangHDon =7" + where
				+ " GROUP BY MauHDon, SoThuTu, KyHieuHDon, MaSoThue) ts2"
				+ " ON ts.MauHDon = ts2.MauHDon and ts.SoThuTu=ts2.SoThuTu AND ts.KyHieuHDon=ts2.KyHieuHDon AND ts.MaSoThue = ts2.MaSoThue"
				+ " WHERE 1=1";
		if(!StringUtils.isNullOrEmpty(where)){
			if(where.contains("MaSoThue")){
				hql +=  where.replaceAll("MaSoThue", "ts.MaSoThue");
			}else{
				hql += where;
			}
		}else{
			hql += where;
		}
		
		hql += " GROUP BY ts.MauHDon, ts.SoThuTu, ts.KyHieuHDon, ts.MaSoThue";
		if (!StringUtils.isNullOrEmpty(sortName)){
			if(sortName.contains("MaLoaiHoaDon")){
				sortName = sortName.replaceAll("MaLoaiHoaDon", "MauHDon");
			}
			hql += " ORDER BY ts." + sortName;
		}
		
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql);
			List<Object[]> rows = query.list();
			if (rows.size() > 0){
				for (Object[] row: rows){
					count = count + 1;
					ReportUseOfInvoicesView obj = new ReportUseOfInvoicesView();
					String soLuongXoaBo = "", soXoaBo = "", maLoaiHDon = "", tenHoaDon = "";
					soLuongXoaBo  = String.valueOf(row[8]);
					soXoaBo = String.valueOf(row[9]);
					tenHoaDon = maLoaiHDon = String.valueOf(row[1]);
					obj.setStt(String.valueOf(count));
					obj.setMstNguoiBan(String.valueOf(row[0])); // masothue ct2
					obj.setMaLoaiHoaDon(maLoaiHDon); // MaLoaiHDon ct3
					if(StringUtils.isNullOrEmpty(tenHoaDon) || tenHoaDon.equals("")){
						tenHoaDon = "";
					}else{
						tenHoaDon = CategoryInvoicesType.getInvoiceType(tenHoaDon).getInvoiceName();
					}
					obj.setTenHoaDon(tenHoaDon); // MaLoaiHDon ct3
					obj.setMauHoaDon(String.valueOf(row[2])); // MauHDon ct4
					obj.setKyHieuHoaDon(String.valueOf(row[3])); // KyHieuHDon ct6
					obj.setTuSo(String.valueOf(row[4])); // tuSo ct7 
					obj.setDenSo(String.valueOf(row[5])); //DenSo ct8
					obj.setTongCong(String.valueOf((row[6])).replaceAll("\\.0", "")); // tongCong ct9
					obj.setSoLuongDaSuDung(String.valueOf(row[7])); // SoLuongDaSuDung ct10
					if(StringUtils.isNullOrEmpty(soLuongXoaBo) || soLuongXoaBo.equals("null")){
						soLuongXoaBo = "0";
					}
					obj.setSoLuongXoaBo(soLuongXoaBo); // SoLuongXoaBo ct11
					
					if(StringUtils.isNullOrEmpty(soXoaBo) || soXoaBo.equals("null")){
						soXoaBo = "";
					}
					obj.setSoXoaBo(soXoaBo); // soXoaBo ct12
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
	
	// bao bao ban hang moi
	@SuppressWarnings("unchecked")
	@Override
	public List<BaoCaoBanHangView> getAllHoaDonBaoCaoBanHang_new(objSearchInvoice objSearch, String maCQLimit,String mstLimit,int role,String sortName, int startIndex, int pageSize) throws Exception{
		
		List<BaoCaoBanHangView> listHD = new ArrayList<BaoCaoBanHangView>(); 
		int count = 0;
		List<HoaDon> result = null;
		Session session = null;
		String tuNgay = "" ,denNgay = "" ;
	    tuNgay =	objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
	    if(tuNgay != null && !tuNgay.equals("")){
			tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
		}
		if(denNgay != null && !denNgay.equals("")){
			denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
		}
		//String hql = "SELECT * FROM hoadon WHERE 1 = 1";
		String hql = "SELECT MaSoThue, MSTNguoiMua, a.SoHoaDon, a.NgayXHDon, "
				+ "b.MaHang, b.TenHang, b.DonViTinh, b.SoLuong , b.DonGia, b.DonGiaGoc, b.ThanhTien, b.ThueSuat, b.TienThue, b.TongTien, b.KhuyenMai, b.GhiChu, b.LoaiDieuChinh, a.MauHDon, a.SoThuTu, a.KyHieuHDon "
				+ "FROM hoadon a INNER JOIN hoadon_chitiet b ON a.IdHoaDon = b.MaHoaDon";
		hql = queryFilterInvoiceBCBH(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, hql);
		
		//if (!StringUtils.isNullOrEmpty(sortName)){
		//	hql += " ORDER BY " + sortName;
		//}
		hql += " ORDER BY  MaSoThue, b.MaHang";
		
		try{
			session = sessionFactory.openSession();
			//SQLQuery query = session.createSQLQuery(hql).addEntity(HoaDon.class);
			SQLQuery query = session.createSQLQuery(hql);
			List<Object[]> rows = query.list();
			
			if (rows.size() > 0){
				for (Object[] row: rows){
					count = count + 1;
					BaoCaoBanHangView obj = new BaoCaoBanHangView();
					//double thanhTien = 0, tienThue = 0;
					BigDecimal thanhTien = new BigDecimal(0);
					BigDecimal tienThue = new BigDecimal(0);
					BigDecimal tongThanhTien = new BigDecimal(0);
				//	BeanUtils.copyProperties(objCT, obj);
					obj.setMaSoThueBan(String.valueOf(row[0]));
					obj.setMaSoThueMua(String.valueOf(row[1]));
					obj.setSoHD(String.valueOf(row[2]));
					if(String.valueOf(row[3]) != null && !String.valueOf(row[3]).equals("")){
						obj.setNgayXHDon(Util.formatNgayThangNam__ddmmyyyy(String.valueOf(row[3]).split(" ")[0]));
					}
					obj.setMaHang(String.valueOf(row[4]));//ma hang
					obj.setTenHang(String.valueOf(row[5]));
					obj.setDonViTinh(String.valueOf(row[6]));
					obj.setSoLuong(String.valueOf(row[7]));//so luong
					obj.setDonGia(String.valueOf(row[8]));
					obj.setDonGiaGoc(String.valueOf(row[9]));//don gia goc					
					obj.setDoanhThu(String.valueOf(row[10]));//thanh tien
					
					if(String.valueOf(row[10]) != null && !String.valueOf(row[10]).equals("") && !String.valueOf(row[10]).equals("null")){
						thanhTien = thanhTien.add(new BigDecimal(String.valueOf(row[10]).replace(",", ".")));
					}
					if(String.valueOf(row[12]) != null && !String.valueOf(row[12]).equals("") && !String.valueOf(row[12]).equals("null")){
						tienThue = tienThue.add(new BigDecimal(String.valueOf(row[12]).replace(",", ".")));
					}
					tongThanhTien = tongThanhTien.add(thanhTien).add(tienThue);
					obj.setThanhTien(tongThanhTien+"");//thanh tien
					if(String.valueOf(row[11]).equals("-1")){
						obj.setThueSuat("KCT");
					}else{
						obj.setThueSuat(String.valueOf(row[11]));//thue suat
					}
					if(String.valueOf(row[12]) != null && !String.valueOf(row[12]).equals("") && !String.valueOf(row[12]).equals("null")){
						obj.setTienThue(String.valueOf(row[12]));//tien thue
					}else{
						obj.setTienThue("");//tien thue
					}
					
					obj.setTongTien(String.valueOf(row[13]));//tong tien
					/*if(String.valueOf(row[14]) != null && !String.valueOf(row[14]).equals("") && !String.valueOf(row[14]).equals("null")){
					  obj.setKhuyenMai(Integer.parseInt(String.valueOf(row[14])));
					}else{
						obj.setKhuyenMai(0);
					}*/
					obj.setLoaiDieuChinh(String.valueOf(row[15]));
					
					obj.setMauHDon(String.valueOf(row[17]));//mau hoa don
					obj.setSoThuTu(String.valueOf(row[18])); // so thu tu
					obj.setKyHieuHDon(String.valueOf(row[19])); // ky hieu hoa don
					
					obj.setStt(String.valueOf(count));
					listHD.add(obj);
					
				}
			}
						
			//////////====================
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return listHD;
	}
	//======================
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BaoCaoBanHangView> getAllHoaDonBaoCaoBanHang_new_TH(objSearchInvoice objSearch, String maCQLimit,String mstLimit,int role,String sortName, int startIndex, int pageSize) throws Exception{
		
		List<BaoCaoBanHangView> listHD = new ArrayList<BaoCaoBanHangView>(); 
		int count = 0;
		List<HoaDon> result = null;
		Session session = null;
		String tuNgay = "" ,denNgay = "" ;
	    tuNgay =	objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
	    if(tuNgay != null && !tuNgay.equals("")){
			tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
		}
		if(denNgay != null && !denNgay.equals("")){
			denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
		}
		//String hql = "SELECT * FROM hoadon WHERE 1 = 1";
		String hql = "SELECT MaSoThue, MSTNguoiMua, a.SoHoaDon, a.NgayXHDon, "
				+ "b.MaHang, b.TenHang, b.DonViTinh, CAST(sum(b.SoLuong) AS DECIMAL) SoLuong , CAST((ThanhTien/SoLuong) AS DECIMAL) DonGia, b.DonGiaGoc, CAST(sum(b.ThanhTien) AS DECIMAL) ThanhTien, b.ThueSuat, CAST(sum(b.TienThue) AS DECIMAL) TienThue, b.TongTien, b.KhuyenMai, b.GhiChu, b.LoaiDieuChinh, a.MauHDon, a.SoThuTu, a.KyHieuHDon "
				+ "FROM hoadon a INNER JOIN hoadon_chitiet b ON a.IdHoaDon = b.MaHoaDon";
		hql = queryFilterInvoiceBCBH(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, hql);
		
		//if (!StringUtils.isNullOrEmpty(sortName)){
		//	hql += " ORDER BY " + sortName;
		//}
		hql += " GROUP BY MaSoThue, b.MaHang ORDER BY  MaSoThue, b.MaHang ";
		//==========lay chi tiet
		/*String hql1 = "SELECT MaSoThue, MSTNguoiMua, a.SoHoaDon, a.NgayXHDon, "
				+ "b.MaHang, b.TenHang, b.DonViTinh, b.SoLuong , b.DonGia, b.DonGiaGoc, b.ThanhTien, b.ThueSuat, b.TienThue, b.TongTien, b.KhuyenMai, b.GhiChu, b.LoaiDieuChinh, a.MauHDon, a.SoThuTu, a.KyHieuHDon "
				+ "FROM hoadon a INNER JOIN hoadon_chitiet b ON a.IdHoaDon = b.MaHoaDon";
		hql1 = queryFilterInvoiceBCBH(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, hql1);
		*/
		//if (!StringUtils.isNullOrEmpty(sortName)){
		//	hql += " ORDER BY " + sortName;
		//}
	//	hql1 += " ORDER BY  MaSoThue, b.MaHang";
		//==============
		try{
			session = sessionFactory.openSession();
			//SQLQuery query = session.createSQLQuery(hql).addEntity(HoaDon.class);
			SQLQuery query = session.createSQLQuery(hql);
			List<Object[]> rows = query.list();
			
			/*SQLQuery query1 = session.createSQLQuery(hql1);
			List<Object[]> rows1 = query1.list();
			*/
			
			if (rows.size() > 0){
				for (Object[] row: rows){
					//if (rows1.size() > 0){
						count = count + 1;
						BaoCaoBanHangView obj = new BaoCaoBanHangView();
						//double thanhTien = 0, tienThue = 0, soLuong = 0,doanhThu = 0,tongThanhTien = 0;
						BigDecimal thanhTien = new BigDecimal(0);
						BigDecimal tienThue = new BigDecimal(0);
						BigDecimal soLuong = new BigDecimal(0);
						BigDecimal doanhThu = new BigDecimal(0);
						BigDecimal tongThanhTien = new BigDecimal(0);
						BigDecimal dongia = new BigDecimal(0);
							/*for(Object[] row1: rows1){
							
		                        if(String.valueOf(row1[0]).equals(String.valueOf(row[0])) && String.valueOf(row1[4]).equals(String.valueOf(row[4]))){
		                        	if(String.valueOf(row1[7]) != null && !String.valueOf(row1[7]).equals("") && !String.valueOf(row1[7]).equals("null")){										
		                        		soLuong = soLuong.add(new BigDecimal(String.valueOf(row1[7]).replace(",", ".")));
		                        	}
		                        	if(String.valueOf(row1[10]) != null && !String.valueOf(row1[10]).equals("") && !String.valueOf(row1[10]).equals("null")){										
		                        		doanhThu = doanhThu.add(new BigDecimal(String.valueOf(row1[10])));
		                        	}
		                        	//soLuong += Double.parseDouble(String.valueOf(row1[7]));
		                        	//doanhThu += Double.parseDouble(String.valueOf(row1[10]));
		                        	if(String.valueOf(row1[10]) != null && !String.valueOf(row1[10]).equals("") && !String.valueOf(row1[10]).equals("null")){
										//thanhTien = Double.parseDouble(String.valueOf(row1[10]));
										thanhTien = thanhTien.add(new BigDecimal(String.valueOf(row1[10])));
									}
									if(String.valueOf(row1[12]) != null && !String.valueOf(row1[12]).equals("") && !String.valueOf(row1[12]).equals("null")){
										//tienThue = Double.parseDouble(String.valueOf(row1[12]));
										tienThue = tienThue.add(new BigDecimal(String.valueOf(row1[12])));
									}
								//	tongThanhTien += thanhTien +tienThue ;
									tongThanhTien = tongThanhTien.add(thanhTien).add(tienThue);
		                        }
																																											
							}*/
						if(String.valueOf(row[7]) != null && !String.valueOf(row[7]).equals("") && !String.valueOf(row[7]).equals("null")){										
                    		soLuong = soLuong.add(new BigDecimal(String.valueOf(row[7]).replace(",", ".")));
                    	}
                    	if(String.valueOf(row[10]) != null && !String.valueOf(row[10]).equals("") && !String.valueOf(row[10]).equals("null")){										
                    		doanhThu = doanhThu.add(new BigDecimal(String.valueOf(row[10]).replace(",", ".")));
                    	}
                    	if(String.valueOf(row[10]) != null && !String.valueOf(row[10]).equals("") && !String.valueOf(row[10]).equals("null")){
							//thanhTien = Double.parseDouble(String.valueOf(row1[10]));
							thanhTien = thanhTien.add(new BigDecimal(String.valueOf(row[10]).replace(",", ".")));
						}
						if(String.valueOf(row[12]) != null && !String.valueOf(row[12]).equals("") && !String.valueOf(row[12]).equals("null")){
							//tienThue = Double.parseDouble(String.valueOf(row1[12]));
							tienThue = tienThue.add(new BigDecimal(String.valueOf(row[12]).replace(",", ".")));
						}
					//	tongThanhTien += thanhTien +tienThue ;
						tongThanhTien = tongThanhTien.add(thanhTien).add(tienThue);
						if(String.valueOf(row[8]) != null && !String.valueOf(row[8]).equals("") && !String.valueOf(row[8]).equals("null")){
							
							dongia = dongia.add(new BigDecimal(String.valueOf(row[8]).replace(",", ".")));;
						}
							// set cac gia tri
							obj.setMaSoThueBan(String.valueOf(row[0]));							
							obj.setMaHang(String.valueOf(row[4]));//ma hang
							obj.setTenHang(String.valueOf(row[5]));
							obj.setDonViTinh(String.valueOf(row[6]));
							obj.setSoLuong(soLuong+"");//so luong
							obj.setDonGia(dongia +""); // doanh thu chia so luong
							obj.setDoanhThu(doanhThu+"");//thanh tien
							obj.setThanhTien(tongThanhTien+"");//thanh tien
							obj.setTienThue(tienThue+"");//tien thue
														
							obj.setStt(String.valueOf(count));
							listHD.add(obj);
				//	}
					
				}
			}
						
			//////////====================
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return listHD;
	}
	
	@Override
	public List<ReportTongHopView> getReportTongHop(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role) throws Exception{
		List<ReportTongHopView> listHD = new ArrayList<ReportTongHopView>();
		int count = 0;
		String tuNgay = "" ,denNgay = "", where = "";
	    tuNgay = objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
	    if(tuNgay != null && !tuNgay.equals("")){
			tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
		}
		if(denNgay != null && !denNgay.equals("")){
			denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
		}
		
		where = queryFilterInvoice(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, where, false);
		String hql = "SELECT ts.MaSoThue, ts.MauHDon, ts.SoThuTu, ts.KyHieuHDon, ts.SoHoaDon,"+
					    " ts.NgayXHDon, ts.HoTenNguoiMua, ts.MSTNguoiMua, ts.TenHang, CAST(SUM(ts.DSBHCT) AS DECIMAL) DSBHCT,"+
						" CAST(SUM(ts.TS10) AS DECIMAL) TS10, CAST(SUM(ts.TS5) AS DECIMAL) TS5, CAST(SUM(ts.TS0) AS DECIMAL) TS0, CAST(SUM(ts.TSKCT) AS DECIMAL) TSKCT, CAST(SUM(ts.TSKKK) AS DECIMAL) TSKKK,"+
						" CAST(SUM(ts.ThueGTGT) AS DECIMAL) ThueGTGT, CAST(SUM(ts.ChietKhauGiamTru) AS DECIMAL) ChietKhauGiamTru"+
						" FROM"+  
						" (SELECT"+ 
					        " a.MaSoThue, a.MauHDon, a.SoThuTu, a.KyHieuHDon, a.SoHoaDon,"+
					        " a.NgayXHDon, HoTenNguoiMua, MSTNguoiMua, b.TenHang, SUM(b.ThanhTien) DSBHCT,"+
							" CASE WHEN b.ThueSuat ='10' THEN SUM(b.ThanhTien) ELSE 0 END TS10, "+
							" CASE WHEN b.ThueSuat ='5' THEN SUM(b.ThanhTien) ELSE 0 END TS5, "+
							" CASE WHEN b.ThueSuat ='0' THEN SUM(b.ThanhTien) ELSE 0 END TS0, "+
							" CASE WHEN b.ThueSuat ='-1' THEN SUM(b.ThanhTien) ELSE 0 END TSKCT,"+
							" CASE WHEN b.ThueSuat ='-2' THEN SUM(b.ThanhTien) ELSE 0 END TSKKK,"+ 
							" SUM(CASE  WHEN b.TienThue is null THEN '' ELSE b.TienThue  END) AS ThueGTGT,"+
							" SUM((Case WHEN b.KhuyenMai = -1 THEN  CASE "+
								" WHEN b.TienThue is null THEN b.ThanhTien ELSE b.TienThue + b.ThanhTien  END"+ 
								" ELSE '0'  END)) AS ChietKhauGiamTru "+
							" FROM hoadon a "+
							" INNER JOIN"+
							" hoadon_chitiet b ON a.IdHoaDon = b.MaHoaDon"+  
							" WHERE 1=1" + where + 
							" GROUP BY a.MaSoThue, a.MauHDon, a.SoThuTu, a.KyHieuHDon, a.SoHoaDon, b.ThueSuat"+
					    " ORDER BY a.MaSoThue, a.SoHoaDon, b.ID) ts "+
					    " GROUP BY ts.MaSoThue, ts.MauHDon, ts.SoThuTu, ts.KyHieuHDon, ts.SoHoaDon"+
					    " ORDER BY ts.MaSoThue, ts.SoHoaDon";
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql);
			List<Object[]> rows = query.list();
			if (rows.size() > 0){
				for (Object[] row: rows){
					
					BigDecimal DoanhSoBanChuaThue = new BigDecimal(String.valueOf(row[9]));
					BigDecimal ThueSuat10 = new BigDecimal(String.valueOf(row[10]));
					BigDecimal ThueSuat5 = new BigDecimal(String.valueOf(row[11]));
					BigDecimal ThueSuat0 = new BigDecimal(String.valueOf(row[12]));
					BigDecimal ThueSuatKCT = new BigDecimal(String.valueOf(row[13]));
					BigDecimal ThueSuatKhongKeKhai = new BigDecimal(String.valueOf(row[14]));
					BigDecimal ThueGTGT = new BigDecimal(String.valueOf(row[15]));
					BigDecimal ChietKhauGiamTru = new BigDecimal(String.valueOf(row[16]));
					
					count = count + 1;
					ReportTongHopView obj = new ReportTongHopView();
					String mauHD = String.valueOf(row[1]);
					obj.setStt(String.valueOf(count)); // ct1
					obj.setMaSoThue(String.valueOf(row[0])); // ct2
					obj.setMauHDon((mauHD.substring(1, mauHD.length()-1) + "/" + String.valueOf(row[2]))); //  ct3
					obj.setKyHieuHDon(String.valueOf(row[3])); //  ct4
					obj.setSoHoaDon(String.valueOf(row[4])); //  ct5
					if(String.valueOf(row[5]) != null && !String.valueOf(row[5]).equals("")){ //ct6
						obj.setNgayXHDon(Util.formatNgayThangNam__ddmmyyyy(String.valueOf(row[5]).split(" ")[0]));
					}
					obj.setHoTenNguoiMua(String.valueOf(row[6])); //  ct7
					obj.setMstNguoiMua(String.valueOf(row[7])); //  ct8
					obj.setTenHang(String.valueOf(row[8])); //  ct9
					/*obj.setDoanhSoBanChuaThue(new BigDecimal(String.valueOf(row[9]))+""); //  ct10
					obj.setThueSuat10(new BigDecimal(String.valueOf(row[10]))+""); //  ct11
					obj.setThueSuat5(new BigDecimal(String.valueOf(row[11]))+""); //  ct11
					obj.setThueSuat0(new BigDecimal(String.valueOf(row[12]))+""); //  ct11
					obj.setThueSuatKCT(new BigDecimal(String.valueOf(row[13]))+""); //  ct11
					obj.setThueSuatKhongKeKhai(new BigDecimal(String.valueOf(row[14]))+""); //  ct11
					obj.setThueGTGT(new BigDecimal(String.valueOf(row[15]))+""); //  ct12
					obj.setChietKhauGiamTru(new BigDecimal(String.valueOf(row[16]))+""); //  ct13
*/					
					obj.setDoanhSoBanChuaThue(DoanhSoBanChuaThue+""); //  ct10
					obj.setThueSuat10(ThueSuat10+""); //  ct11
					obj.setThueSuat5(ThueSuat5+""); //  ct11
					obj.setThueSuat0(ThueSuat0+""); //  ct11
					obj.setThueSuatKCT(ThueSuatKCT+""); //  ct11
					obj.setThueSuatKhongKeKhai(ThueSuatKhongKeKhai+""); //  ct11
					obj.setThueGTGT(ThueGTGT+""); //  ct12
					obj.setChietKhauGiamTru(ChietKhauGiamTru+""); //  ct13
					
					obj.setGhiChu(""); //  ct14
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
	
	@Override
	public List<ReportTBaoPhatHanhView> getReportTBaoPhatHanh(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role) throws Exception{
		List<ReportTBaoPhatHanhView> listHD = new ArrayList<ReportTBaoPhatHanhView>();
		int count = 0;
		
		String hql = "SELECT b.MaSoThue, CONCAT_WS('',b.MauSo,b.SoLien,'/', b.SoThuTu) MauHoaDon, KyHieu, CAST(b.DenSo AS DECIMAL)-CAST(b.TuSo AS DECIMAL) SoLuong,"
				+ " b.TuSo,b.DenSo,a.NgayLapPhieu,b.NgayLap  FROM thongbao a INNER JOIN phathanh b ON a.ID =b.MaThongBao"
				+ " WHERE 1=1" ;
		if(!StringUtils.isNullOrEmpty(objSearch.getMstNguoiBan())){
			hql += " AND b.MaSoThue='" + objSearch.getMstNguoiBan() + "'";
		}
		if(mstLimit != null && !mstLimit.equals("")){
			hql += " AND b.MaSoThue IN('" + mstLimit.replaceAll(",", "','") +"')";
		}
		
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql);
			List<Object[]> rows = query.list();
			if (rows.size() > 0){
				for (Object[] row: rows){
					count = count + 1;
					ReportTBaoPhatHanhView obj = new ReportTBaoPhatHanhView();
					obj.setStt(String.valueOf(count)); // ct1
					obj.setMaSoThue(String.valueOf(row[0])); // ct2
					obj.setMauHoaDon(String.valueOf(row[1])); //  ct3
					obj.setKyHieu(String.valueOf(row[2])); //  ct4
					obj.setSoLuong(String.valueOf(row[3])); //  ct5
					obj.setTuSo(String.valueOf(row[4])); //  ct6
					obj.setDenSo(String.valueOf(row[5])); //  ct7
					obj.setNgayLapPhieu(String.valueOf(row[6])); //  ct8
					obj.setNgayBatDauSuDung(String.valueOf(row[7])); //  ct9
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
	

	//===========hien lan hai khi chon phat hanh
	
	private String queryFilterInvoice(List<objSearchPhatHanh> listPH) {
		String hql = "" ;
		String kyHieu = "",mauHD="",soTT = "",mst="";
		if(listPH != null && listPH.size() > 0){
			hql += " AND ( " ;
			for(int i = 0 ; i < listPH.size() ; i++){
		//	for(objSearchPhatHanh objSearch:  listPH){
				objSearchPhatHanh objSearch = listPH.get(i);
				hql += " ( " ;
				if (!StringUtils.isNullOrEmpty(objSearch.getKyHieuHoaDon())){
					
					hql +=  " KyHieuHDon = '" + objSearch.getKyHieuHoaDon() +"'";
				}
				if (!StringUtils.isNullOrEmpty(objSearch.getMauHoaDon())){
					if( objSearch.getMauHoaDon().contains( "/" )){
						hql += "  AND MauHDon = '" + objSearch.getMauHoaDon().split( "/" )[0] + "'";
						hql += "  AND SoThuTu = '" + objSearch.getMauHoaDon().split( "/" )[1] + "'";
					}	
				}
							
				if (!StringUtils.isNullOrEmpty(objSearch.getMaSoThueBan())){
					hql += "  AND MaSoThue = '" + objSearch.getMaSoThueBan() +"'";
				}
				hql += " ) " ;
				if(i < listPH.size()- 1){
					hql += " OR" ;
				}
			}
			
			hql += " ) " ;
		}
		
		/*if (!StringUtils.isNullOrEmpty(kyHieu)){
			hql += " AND (" + kyHieu.substring(0, kyHieu.length()- 2)+")";
		}
		if (!StringUtils.isNullOrEmpty(mauHD)){
			
			hql += " AND ( "+mauHD.substring(0, mauHD.length() - 2)+")";
			
		}
		if (!StringUtils.isNullOrEmpty(soTT)){
			
			hql += " AND ("+soTT.substring(0, soTT.length() -2 )+")";			
		}			
		if (!StringUtils.isNullOrEmpty(mst)){
			hql += " AND ("+mst.substring(0, mst.length() - 2)+")";
		}	*/						
		return hql;
	}
	
	@Override
	public List<objSearchPhatHanh> getDanhSachPhatHanh(String arrID) throws Exception{
		List<objSearchPhatHanh> listHD = new ArrayList<objSearchPhatHanh>();
		int count = 0;
		
		String hql = "SELECT MaSoThue, MauSo, SoThuTu, KyHieu, SoLien"
				+ " FROM phathanh "
				+ " WHERE 1=1" ;
		
		if(arrID != null && !arrID.equals("")){
			hql += " AND ID IN('" + arrID.replaceAll(",", "','") +"')";
		}else{
			return null ;
		}
		
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql);
			List<Object[]> rows = query.list();
			if (rows.size() > 0){
				for (Object[] row: rows){
					count = count + 1;
					objSearchPhatHanh obj = new objSearchPhatHanh();
					obj.setMaSoThueBan(String.valueOf(row[0])); // ct1
					obj.setMauHoaDon(String.valueOf(row[1])+String.valueOf(row[4])+"/"+String.valueOf(row[2]));// ct2
					obj.setKyHieuHoaDon(String.valueOf(row[3])); //  ct3					
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
	
	
	@Override
	public List<ReportTongHopView> getReportTongHopPhatHanh(List<objSearchPhatHanh> listPH,objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role) throws Exception{
		List<ReportTongHopView> listHD = new ArrayList<ReportTongHopView>();
		int count = 0;
		String tuNgay = "" ,denNgay = "", where = "", wherePH = "";
	    tuNgay = objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
	    if(tuNgay != null && !tuNgay.equals("")){
			tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
		}
		if(denNgay != null && !denNgay.equals("")){
			denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
		}
		wherePH = queryFilterInvoice(listPH);
		where = queryFilterInvoice(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, where, false);
		String hql = "SELECT ts.MaSoThue, ts.MauHDon, ts.SoThuTu, ts.KyHieuHDon, ts.SoHoaDon,"+
					    " ts.NgayXHDon, ts.HoTenNguoiMua, ts.MSTNguoiMua, ts.TenHang, CAST(SUM(ts.DSBHCT) AS DECIMAL) DSBHCT,"+
						" CAST(SUM(ts.TS10) AS DECIMAL) TS10, CAST(SUM(ts.TS5) AS DECIMAL) TS5, CAST(SUM(ts.TS0) AS DECIMAL) TS0, CAST(SUM(ts.TSKCT) AS DECIMAL) TSKCT, CAST(SUM(ts.TSKKK) AS DECIMAL) TSKKK,"+
						" CAST(SUM(ts.ThueGTGT) AS DECIMAL) ThueGTGT, CAST(SUM(ts.ChietKhauGiamTru) AS DECIMAL) ChietKhauGiamTru"+
						" FROM"+  
						" (SELECT"+ 
					        " a.MaSoThue, a.MauHDon, a.SoThuTu, a.KyHieuHDon, a.SoHoaDon,"+
					        " a.NgayXHDon, HoTenNguoiMua, MSTNguoiMua, b.TenHang, SUM(b.ThanhTien) DSBHCT,"+
							" CASE WHEN b.ThueSuat ='10' THEN SUM(b.ThanhTien) ELSE 0 END TS10, "+
							" CASE WHEN b.ThueSuat ='5' THEN SUM(b.ThanhTien) ELSE 0 END TS5, "+
							" CASE WHEN b.ThueSuat ='0' THEN SUM(b.ThanhTien) ELSE 0 END TS0, "+
							" CASE WHEN b.ThueSuat ='-1' THEN SUM(b.ThanhTien) ELSE 0 END TSKCT,"+
							" CASE WHEN b.ThueSuat ='-2' THEN SUM(b.ThanhTien) ELSE 0 END TSKKK,"+ 
							" SUM(CASE  WHEN b.TienThue is null THEN '' ELSE b.TienThue  END) AS ThueGTGT,"+
							" SUM((Case WHEN b.KhuyenMai = -1 THEN  CASE "+
								" WHEN b.TienThue is null THEN b.ThanhTien ELSE b.TienThue + b.ThanhTien  END"+ 
								" ELSE '0'  END)) AS ChietKhauGiamTru "+
							" FROM hoadon a "+
							" INNER JOIN"+
							" hoadon_chitiet b ON a.IdHoaDon = b.MaHoaDon"+  
							" WHERE 1=1" + where + wherePH +																												
						" GROUP BY a.MaSoThue, a.MauHDon, a.SoThuTu, a.KyHieuHDon, a.SoHoaDon, b.ThueSuat"+
					    " ORDER BY a.MaSoThue, a.SoHoaDon, b.ID) ts "+
					    " GROUP BY ts.MaSoThue, ts.MauHDon, ts.SoThuTu, ts.KyHieuHDon, ts.SoHoaDon"+
					    " ORDER BY ts.MaSoThue, ts.SoHoaDon";
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql);
			List<Object[]> rows = query.list();
			if (rows.size() > 0){
				for (Object[] row: rows){
					
					BigDecimal DoanhSoBanChuaThue = new BigDecimal(String.valueOf(row[9]));
					BigDecimal ThueSuat10 = new BigDecimal(String.valueOf(row[10]));
					BigDecimal ThueSuat5 = new BigDecimal(String.valueOf(row[11]));
					BigDecimal ThueSuat0 = new BigDecimal(String.valueOf(row[12]));
					BigDecimal ThueSuatKCT = new BigDecimal(String.valueOf(row[13]));
					BigDecimal ThueSuatKhongKeKhai = new BigDecimal(String.valueOf(row[14]));
					BigDecimal ThueGTGT = new BigDecimal(String.valueOf(row[15]));
					BigDecimal ChietKhauGiamTru = new BigDecimal(String.valueOf(row[16]));
					
					count = count + 1;
					ReportTongHopView obj = new ReportTongHopView();
					String mauHD = String.valueOf(row[1]);
					obj.setStt(String.valueOf(count)); // ct1
					obj.setMaSoThue(String.valueOf(row[0])); // ct2
					obj.setMauHDon((mauHD.substring(1, mauHD.length()-1) + "/" + String.valueOf(row[2]))); //  ct3
					obj.setKyHieuHDon(String.valueOf(row[3])); //  ct4
					obj.setSoHoaDon(String.valueOf(row[4])); //  ct5
					if(String.valueOf(row[5]) != null && !String.valueOf(row[5]).equals("")){ //ct6
						obj.setNgayXHDon(Util.formatNgayThangNam__ddmmyyyy(String.valueOf(row[5]).split(" ")[0]));
					}
					obj.setHoTenNguoiMua(String.valueOf(row[6])); //  ct7
					obj.setMstNguoiMua(String.valueOf(row[7])); //  ct8
					obj.setTenHang(String.valueOf(row[8])); //  ct9
					/*obj.setDoanhSoBanChuaThue(new BigDecimal(String.valueOf(row[9]))+""); //  ct10
					obj.setThueSuat10(new BigDecimal(String.valueOf(row[10]))+""); //  ct11
					obj.setThueSuat5(new BigDecimal(String.valueOf(row[11]))+""); //  ct11
					obj.setThueSuat0(new BigDecimal(String.valueOf(row[12]))+""); //  ct11
					obj.setThueSuatKCT(new BigDecimal(String.valueOf(row[13]))+""); //  ct11
					obj.setThueSuatKhongKeKhai(new BigDecimal(String.valueOf(row[14]))+""); //  ct11
					obj.setThueGTGT(new BigDecimal(String.valueOf(row[15]))+""); //  ct12
					obj.setChietKhauGiamTru(new BigDecimal(String.valueOf(row[16]))+""); //  ct13
*/					
					obj.setDoanhSoBanChuaThue(DoanhSoBanChuaThue+""); //  ct10
					obj.setThueSuat10(ThueSuat10+""); //  ct11
					obj.setThueSuat5(ThueSuat5+""); //  ct11
					obj.setThueSuat0(ThueSuat0+""); //  ct11
					obj.setThueSuatKCT(ThueSuatKCT+""); //  ct11
					obj.setThueSuatKhongKeKhai(ThueSuatKhongKeKhai+""); //  ct11
					obj.setThueGTGT(ThueGTGT+""); //  ct12
					obj.setChietKhauGiamTru(ChietKhauGiamTru+""); //  ct13
					
					obj.setGhiChu(""); //  ct14
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
	
	@Override
	public List<ReportUseOfInvoicesView> getReportUseOfInvoicesPhatHanhTongHop(List<objSearchPhatHanh> listPH,objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize) throws Exception{
		List<ReportUseOfInvoicesView> listHD = new ArrayList<ReportUseOfInvoicesView>();
		int count = 0;
		String tuNgay = "" ,denNgay = "", where = "", wherePH = "", whereParent= "";
	    tuNgay = objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
	    if(tuNgay != null && !tuNgay.equals("")){
			tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
		}
		if(denNgay != null && !denNgay.equals("")){
			denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
		}
		wherePH = queryFilterInvoice(listPH);
		where = queryFilterInvoice(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, where, true);
		String hql = "SELECT ts.MaSoThue ct2, SUBSTRING(ts.MauHDon, 1, LENGTH(ts.MauHDon)-1) ct3,"
				+ " CONCAT(SUBSTRING(ts.MauHDon, 1, LENGTH(ts.MauHDon)-1), '/', ts.SoThuTu) ct4, ts.KyHieuHDon ct6, MIN(ts.SoHoaDon) as ct7,"
				+ " MAX(ts.SoHoaDon) ct8, MAX(ts.SoHoaDon) - MIN(ts.SoHoaDon) +1 ct9 ,ts1.ct10 ct10, ts2.ct11 ct11 , ts2.ct12 ct12"
				+ " FROM hoadon ts"
				+ " LEFT JOIN (SELECT MaSoThue, MauHDon, SoThuTu, KyHieuHDon, COUNT(MauHDon) ct10 FROM hoadon"
				+ " WHERE TinhTrangHDon <>7" + where + wherePH
				+ " GROUP BY MauHDon, SoThuTu, KyHieuHDon, MaSoThue) ts1"
				+ " ON ts.MauHDon = ts1.MauHDon AND ts.SoThuTu = ts1.SoThuTu and ts.KyHieuHDon = ts1.KyHieuHDon AND ts.MaSoThue = ts1.MaSoThue"
				+ " LEFT JOIN (SELECT MaSoThue, MauHDon, SoThuTu, KyHieuHDon, COUNT(MauHDon) ct11, GROUP_CONCAT(SoHoaDon) ct12"
				+ " FROM hoadon"
				+ " WHERE TinhTrangHDon =7" + where + wherePH 
				+ " GROUP BY MauHDon, SoThuTu, KyHieuHDon, MaSoThue) ts2"
				+ " ON ts.MauHDon = ts2.MauHDon and ts.SoThuTu=ts2.SoThuTu AND ts.KyHieuHDon=ts2.KyHieuHDon AND ts.MaSoThue = ts2.MaSoThue"
				+ " WHERE 1=1";
		whereParent = queryFilterInvoiceTableName(listPH, "ts.");
		if(!StringUtils.isNullOrEmpty(where)){
			if(where.contains("MaSoThue")){
				hql +=  where.replaceAll("MaSoThue", "ts.MaSoThue") + whereParent;
			}else{
				hql += where + whereParent;
			}
		}else{
			hql += where + whereParent;
		}
		
		hql += " GROUP BY ts.MauHDon, ts.SoThuTu, ts.KyHieuHDon, ts.MaSoThue";
		if (!StringUtils.isNullOrEmpty(sortName)){
			if(sortName.contains("MaLoaiHoaDon")){
				sortName = sortName.replaceAll("MaLoaiHoaDon", "MauHDon");
			}
			hql += " ORDER BY ts." + sortName;
		}
		
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql);
			List<Object[]> rows = query.list();
			if (rows.size() > 0){
				for (Object[] row: rows){
					count = count + 1;
					ReportUseOfInvoicesView obj = new ReportUseOfInvoicesView();
					String soLuongXoaBo = "", soXoaBo = "", maLoaiHDon = "", tenHoaDon = "";
					soLuongXoaBo  = String.valueOf(row[8]);
					soXoaBo = String.valueOf(row[9]);
					tenHoaDon = maLoaiHDon = String.valueOf(row[1]);
					obj.setStt(String.valueOf(count));
					obj.setMstNguoiBan(String.valueOf(row[0])); // masothue ct2
					obj.setMaLoaiHoaDon(maLoaiHDon); // MaLoaiHDon ct3
					if(StringUtils.isNullOrEmpty(tenHoaDon) || tenHoaDon.equals("")){
						tenHoaDon = "";
					}else{
						tenHoaDon = CategoryInvoicesType.getInvoiceType(tenHoaDon).getInvoiceName();
					}
					obj.setTenHoaDon(tenHoaDon); // MaLoaiHDon ct3
					obj.setMauHoaDon(String.valueOf(row[2])); // MauHDon ct4
					obj.setKyHieuHoaDon(String.valueOf(row[3])); // KyHieuHDon ct6
					obj.setTuSo(String.valueOf(row[4])); // tuSo ct7 
					obj.setDenSo(String.valueOf(row[5])); //DenSo ct8
					obj.setTongCong(String.valueOf((row[6])).replaceAll("\\.0", "")); // tongCong ct9
					obj.setSoLuongDaSuDung(String.valueOf(row[7])); // SoLuongDaSuDung ct10
					if(StringUtils.isNullOrEmpty(soLuongXoaBo) || soLuongXoaBo.equals("null")){
						soLuongXoaBo = "0";
					}
					obj.setSoLuongXoaBo(soLuongXoaBo); // SoLuongXoaBo ct11
					
					if(StringUtils.isNullOrEmpty(soXoaBo) || soXoaBo.equals("null")){
						soXoaBo = "";
					}
					obj.setSoXoaBo(soXoaBo); // soXoaBo ct12
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
	
	
	@Override
	public List<ReportTBaoPhatHanhView> reportTBaoPhatHanhSearch(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String sortName, int startIndex, int pageSize) throws Exception{
		List<ReportTBaoPhatHanhView> listHD = new ArrayList<ReportTBaoPhatHanhView>();
		int count = 0;
		String tuNgay = "" ,denNgay = "" ;
	    tuNgay = objSearch.getTuNgay();
	    denNgay = objSearch.getDenNgay();
	    if(tuNgay != null && !tuNgay.equals("")){
			tuNgay = Utilities.formatNgaySo4(tuNgay.replaceAll("/", ConstantValue.MINUS));
		}
		if(denNgay != null && !denNgay.equals("")){
			denNgay = Utilities.formatNgaySo4(denNgay.replaceAll("/", ConstantValue.MINUS));
		}
		String hql = "SELECT b.MaSoThue, CONCAT_WS('',b.MauSo,b.SoLien,'/', b.SoThuTu) MauHoaDon, KyHieu, CAST(b.DenSo AS DECIMAL)-CAST(b.TuSo AS DECIMAL) SoLuong,"
				+ " b.TuSo,b.DenSo,a.NgayLapPhieu,b.NgayLap, b.ID FROM thongbao a INNER JOIN phathanh b ON a.ID =b.MaThongBao"
				+ " WHERE 1=1" ;
		hql = queryFilterThongBaoPhatHanh(objSearch, maCQLimit, mstLimit, role, tuNgay, denNgay, hql, "b.");
		
		if (!StringUtils.isNullOrEmpty(sortName)){
			if(sortName.contains("MauHoaDon")){
				sortName = sortName.replaceAll("MauHoaDon", "MauSo");
			}else if(sortName.contains("NgayBatDauSuDung")){
				sortName = sortName.replaceAll("NgayBatDauSuDung", "NgayLap");
			}
			
			hql += " ORDER BY " + sortName;
		}
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql);
			List<Object[]> rows = query.list();
			if (rows.size() > 0){
				for (Object[] row: rows){
					count = count + 1;
					ReportTBaoPhatHanhView obj = new ReportTBaoPhatHanhView();
					obj.setStt(String.valueOf(count)); // ct1
					obj.setMaSoThue(String.valueOf(row[0])); // ct2
					obj.setMauHoaDon(String.valueOf(row[1])); //  ct3
					obj.setKyHieu(String.valueOf(row[2])); //  ct4
					obj.setSoLuong(String.valueOf(row[3])); //  ct5
					obj.setTuSo(String.valueOf(row[4])); //  ct6
					obj.setDenSo(String.valueOf(row[5])); //  ct7
					obj.setNgayLapPhieu(String.valueOf(row[6])); //  ct8
					obj.setNgayBatDauSuDung(String.valueOf(row[7])); //  ct9
					obj.setId(String.valueOf(row[8]));
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
	
	private String queryFilterThongBaoPhatHanh(objSearchInvoice objSearch, String maCQLimit, String mstLimit, int role, String tuNgay, String denNgay, String hql, String tableName) {
//		if(tuNgay != null && !tuNgay.equals("") &&  (denNgay == null || denNgay.equals(""))  )
//			hql += " AND STR_TO_DATE(NgayLap, '%m/%d/%Y') >= '" +  tuNgay + "'";
//		
//		if(denNgay != null && !denNgay.equals("") &&  (tuNgay == null || tuNgay.equals(""))  )
//			hql += " AND STR_TO_DATE(NgayLap, '%m/%d/%Y') <= '" + denNgay+ "'";
//		
//		if(tuNgay != null && !tuNgay.equals("") && denNgay != null && !denNgay.equals(""))			
//			hql += " AND STR_TO_DATE(NgayLap, '%m/%d/%Y') >= '" +  tuNgay +	"' AND STR_TO_DATE(NgayLap, '%m/%d/%Y') <= '" + denNgay + "'";
		
		if(tuNgay != null && !tuNgay.equals("") &&  (denNgay == null || denNgay.equals(""))  )
			hql += " AND NgayLap >= '" +  tuNgay + "'";
		
		if(denNgay != null && !denNgay.equals("") &&  (tuNgay == null || tuNgay.equals(""))  )
			hql += " AND NgayLap <= '" + denNgay+ "'";
		
		if(tuNgay != null && !tuNgay.equals("") && denNgay != null && !denNgay.equals(""))			
			hql += " AND NgayLap >= '" +  tuNgay +	"' AND NgayLap <= '" + denNgay + "'";

		if (!StringUtils.isNullOrEmpty(objSearch.getKyHieu())){
			hql += " AND KyHieu = '" + objSearch.getKyHieu() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getStt())){
			hql += " AND SoThuTu = '" + objSearch.getStt() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getMauHoaDon())){
			hql += " AND MauSo = '" + objSearch.getMauHoaDon() +"'";
		}
		if (!StringUtils.isNullOrEmpty(objSearch.getMstNguoiBan())){
			hql += " AND MaSoThue = '" + objSearch.getMstNguoiBan() +"'";
		}
		
		if(role == 1){
			if (!StringUtils.isNullOrEmpty(objSearch.getMaTinh())){
				hql += " AND MaCQT like '" + objSearch.getMaTinh() +"%'";
			}
			if (!StringUtils.isNullOrEmpty(objSearch.getMaCoQuan())){
				hql += " AND MaCQT = '" + objSearch.getMaCoQuan() +"'";
			}
		}else{
			if (!StringUtils.isNullOrEmpty(objSearch.getMaTinh())){
				hql += " AND MaCQT like '" + objSearch.getMaTinh() +"%'";
			}else{
				hql += " AND MaCQT like '" + maCQLimit +"%'";
			}
			if (!StringUtils.isNullOrEmpty(objSearch.getMaCoQuan())){
				hql += " AND MaCQT = '" + objSearch.getMaCoQuan() +"'";
			}else{
				hql += " AND MaCQT like '" + maCQLimit +"%'";
			}
		}
		
		if(mstLimit != null && !mstLimit.equals("")){
			hql += " AND MaSoThue IN('" + mstLimit.replaceAll(",", "','") +"')";
		}
		
		if (!StringUtils.isNullOrEmpty(objSearch.getLoaiHDon())){
			int loaiHD = Integer.parseInt(objSearch.getLoaiHDon());
			if(loaiHD == 1 || loaiHD == 2){ // 1:Dien tu; 2: Xac thuc
				hql += " AND "+tableName+"HOADONXACTHUC =" + loaiHD;
			}
		}
		return hql;
	}
	
	
	private String queryFilterInvoiceTableName(List<objSearchPhatHanh> listPH, String tableName) {
		String hql = "" ;
		if(listPH != null && listPH.size() > 0){
			hql += " AND ( " ;
			for(int i = 0 ; i < listPH.size() ; i++){
				objSearchPhatHanh objSearch = listPH.get(i);
				hql += " ( " ;
				if (!StringUtils.isNullOrEmpty(objSearch.getKyHieuHoaDon())){
					
					hql +=  tableName+ "KyHieuHDon = '" + objSearch.getKyHieuHoaDon() +"'";
				}
				if (!StringUtils.isNullOrEmpty(objSearch.getMauHoaDon())){
					if( objSearch.getMauHoaDon().contains( "/" )){
						hql += "  AND "+tableName+"MauHDon = '" + objSearch.getMauHoaDon().split( "/" )[0] + "'";
						hql += "  AND "+tableName+"SoThuTu = '" + objSearch.getMauHoaDon().split( "/" )[1] + "'";
					}	
				}
							
				if (!StringUtils.isNullOrEmpty(objSearch.getMaSoThueBan())){
					hql += "  AND "+tableName+"MaSoThue = '" + objSearch.getMaSoThueBan() +"'";
				}
				hql += " ) " ;
				if(i < listPH.size()- 1){
					hql += " OR" ;
				}
			}
			
			hql += " ) " ;
		}
		
		return hql;
	}
	
	
	@Override
	public List<ReportTBaoPhatHanhView> getDanhSachPhatHanhSelected(String arrID, String sortName) throws Exception{
		List<ReportTBaoPhatHanhView> listHD = new ArrayList<ReportTBaoPhatHanhView>();
		int count = 0;
		String hql = "SELECT b.MaSoThue, CONCAT_WS('',b.MauSo,b.SoLien,'/', b.SoThuTu) MauHoaDon, KyHieu, CAST(b.DenSo AS DECIMAL)-CAST(b.TuSo AS DECIMAL) SoLuong,"
				+ " b.TuSo,b.DenSo,a.NgayLapPhieu,b.NgayLap, b.ID FROM thongbao a INNER JOIN phathanh b ON a.ID =b.MaThongBao"
				+ " WHERE 1=1" ;
		if(arrID != null && !arrID.equals("")){
			hql += " AND b.ID IN('" + arrID.replaceAll(",", "','") +"')";
		}else{
			return null;
		}
		
		if (!StringUtils.isNullOrEmpty(sortName)){
			if(sortName.contains("MauHoaDon")){
				sortName = sortName.replaceAll("MauHoaDon", "MauSo");
			}else if(sortName.contains("NgayBatDauSuDung")){
				sortName = sortName.replaceAll("NgayBatDauSuDung", "NgayLap");
			}
			hql += " ORDER BY " + sortName;
		}
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql);
			List<Object[]> rows = query.list();
			if (rows.size() > 0){
				for (Object[] row: rows){
					count = count + 1;
					ReportTBaoPhatHanhView obj = new ReportTBaoPhatHanhView();
					obj.setStt(String.valueOf(count)); // ct1
					obj.setMaSoThue(String.valueOf(row[0])); // ct2
					obj.setMauHoaDon(String.valueOf(row[1])); //  ct3
					obj.setKyHieu(String.valueOf(row[2])); //  ct4
					obj.setSoLuong(String.valueOf(row[3])); //  ct5
					obj.setTuSo(String.valueOf(row[4])); //  ct6
					obj.setDenSo(String.valueOf(row[5])); //  ct7
					obj.setNgayLapPhieu(String.valueOf(row[6])); //  ct8
					obj.setNgayBatDauSuDung(String.valueOf(row[7])); //  ct9
					obj.setId(String.valueOf(row[8]));
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HoaDonView> getHoaDonByMaNhanHD(String maNhanHD, String tuNgay, String denNgay, String sortName, int startIndex, int pageSize) throws Exception{
		
		List<HoaDonView> listHD = new ArrayList<HoaDonView>(); 
		Session session = null;
		String hql = "SELECT * FROM hoadon WHERE 1 = 1";
		hql = queryFilterHoaDonByMaNhanHD(maNhanHD,tuNgay, denNgay, hql, false);
		
		if (!StringUtils.isNullOrEmpty(sortName)){
			hql += " ORDER BY " + sortName;
		}
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(hql).addEntity(HoaDon.class);
			if (startIndex > 0){
				query.setFirstResult(startIndex);
			}
			if (pageSize > 0){
				query.setMaxResults(pageSize);
			}
			List<HoaDon> projectList = query.list();
			if (projectList != null && projectList.size() > 0){
				for (HoaDon hoaDon : projectList) {
					HoaDonView obj = new HoaDonView();
					BeanUtils.copyProperties(hoaDon, obj);
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
	
	private String queryFilterHoaDonByMaNhanHD(String maNhanHD, String tuNgay, String denNgay, String hql, boolean isReportUseOfInvoices) {
		
		if(tuNgay != null && !tuNgay.equals("") &&  (denNgay == null || denNgay.equals(""))  )
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) >= '" +  tuNgay + "'";
		
		if(denNgay != null && !denNgay.equals("") &&  (tuNgay == null || tuNgay.equals(""))  )
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) <= '" + denNgay+ "'";
		
		if(tuNgay != null && !tuNgay.equals("") && denNgay != null && !denNgay.equals(""))			
			hql += " AND SUBSTRING_INDEX(NgayXHDon,' ', 1) >= '" +  tuNgay +	"' AND SUBSTRING_INDEX(NgayXHDon,' ', 1) <= '" + denNgay + "'";

		if(!StringUtils.isNullOrEmpty(maNhanHD)){
			hql += " AND (IdHoaDon IN ('" +  maNhanHD + "') OR MaNhanHDon IN ('" +  maNhanHD + "'))";
		}
		
		if(!isReportUseOfInvoices){
			hql += " AND TinhTrangHDon IN('1','3','5','9')";
		}
		return hql;
	}
	
	@Override
	public long getCountAllHoaDon(String idHDLimit, String tuNgay, String denNgay) throws Exception{
		long totalRecords = 0;
		Session session = null;
		String hql = "SELECT COUNT(ID) FROM hoadon WHERE 1 = 1 ";
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