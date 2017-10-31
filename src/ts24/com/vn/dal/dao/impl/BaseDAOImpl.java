package ts24.com.vn.dal.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ts24.com.vn.dal.dao.BaseDAO;
import ts24.com.vn.dal.util.HibernateUtil;

public class BaseDAOImpl<T> implements BaseDAO<T>{
	
	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  
	@SuppressWarnings("unchecked")
	public List<T> executeAndGetList(String hql, Map<String, Object> parameters) throws Exception {
		List<T> result = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			if (parameters != null){
				for(Entry<String,Object> entry: parameters.entrySet()){
					if (entry.getValue() instanceof Collection){
						query.setParameterList(entry.getKey(), (Collection)entry.getValue());
					}else{
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
			}
			result = query.list();
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
	
	
	
	
	protected void rollbackTransaction(Transaction tx) throws Exception{
		if(tx != null) tx.rollback();
	}
	
	
	@Override
	public List<T> getListByPrimaryKeys(Class<T> clazz, String pkProperty, List<?> keys)
			throws Exception {
		List<T> result = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
		    result = session.createCriteria(clazz).add(Restrictions.in(pkProperty, keys)).list();
		}finally{
			closeSession(session);
		}
		return result;
	}

	@Override
	public void createEntitiesBatch(List<T> listEntities) throws Exception {
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for (T entity : listEntities) {
				session.save(entity);
			}
			tx.commit();
		}catch(Exception ex){
			rollbackTransaction(tx);
			throw ex;
		}finally{
			closeSession(session);
		}
	}

	@Override
	public T getEntityById(Class clazz, Object id) throws Exception {
		T result = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			result= (T)session.get(clazz, (Serializable)id);
		}finally{
			closeSession(session);
		}
		return result;
	}

	@Override
	public void update(T entity) throws Exception {
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
		}catch(Exception ex){
			rollbackTransaction(tx);
			throw ex;
		}finally{
			closeSession(session);
		}
	}

	@Override
	public void updateEntityBatch(List<T> listEntities) throws Exception {
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for (T entity : listEntities) {
				session.saveOrUpdate(entity);
			}
			tx.commit();
		}catch(Exception ex){
			rollbackTransaction(tx);
			throw ex;
		}finally{
			closeSession(session);
		}
	}

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

	@Override
	public long getCount(String hql) throws Exception {
		long count = 0;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			count = (Long)query.uniqueResult();
		}finally{
			closeSession(session);
		}
		return count;
	}

	@Override
	public void createEntity(T entity) throws Exception {
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
		}catch(Exception ex){
			rollbackTransaction(tx);
			throw ex;
		}finally{
			closeSession(session);
		}
	}
	
	/*
	 * Author Thong
	 */
	@Override
	public T executeAndGetSingle(String hql) throws Exception {
		// TODO Auto-generated method stub
		List<T> result = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			result = query.list();
		}finally{
			closeSession(session);
		}
		if(!result.isEmpty()){
			return result.get(0);
		}else{
			return (T) result;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> executeSQLQueryAndGetList(String sql,
			Map<String, Object> parameters, Class<?> clazz) throws Exception {
		List<T> result = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(sql).addEntity(clazz);
			if (parameters != null){
				for(Entry<String,Object> entry: parameters.entrySet()){					
					if (entry.getValue() instanceof Collection){
						query.setParameterList(entry.getKey(), (Collection)entry.getValue());
					}else if (entry.getValue() instanceof Double){						
						query.setDouble(entry.getKey(), (Double)entry.getValue());
					}else{
						query.setString(entry.getKey(), entry.getValue().toString());
					}
				}
			}
			result = query.list();
		}finally{
			closeSession(session);
		}
		
		return result;
	}
	
	@Override
	public String getSQLDateTime(String hql) throws Exception {
		List<T> result = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(hql);
			result = query.list();
		}finally{
			closeSession(session);
		}
		if(!result.isEmpty()){
			return result.get(0).toString().replaceAll("\\.0", "");
		}else{
			return null;
		}
		
	}
	
}
