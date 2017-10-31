package ts24.com.vn.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ts24.com.vn.dal.util.HibernateUtil;
import ts24.com.vn.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T>{
	
	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(Class<T> clazz) throws Exception {
		List<T> result = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
		    result = session.createCriteria(clazz).list();
		}finally{
			closeSession(session);
		}
		return result;
	}
	protected void closeSession(Session session) throws Exception{
		if (session != null && session.isOpen()){
			session.close();
		}
	}
}
