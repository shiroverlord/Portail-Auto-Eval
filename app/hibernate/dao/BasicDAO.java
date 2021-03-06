package hibernate.dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import hibernate.utils.BDDUtils;
import play.Logger;

public abstract class BasicDAO {

	public static <T> T findById(Class<T> table, Long id) {
		T o = null;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try {
			tx = BDDUtils.beginTransaction(isActive);
			o = BDDUtils.getCurrentSession().get(table, id);
			BDDUtils.commit(isActive, tx);
		} catch(Exception ex) {
			Logger.error("Erreur BasicDAO findById : ", ex);
			BDDUtils.rollback(isActive, tx);
		}
		return o;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getAll(Class<T> table) {
		List<T> all = null;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try {
			tx = BDDUtils.beginTransaction(isActive);
			all = BDDUtils.getCurrentSession().createCriteria(table).list();
			BDDUtils.commit(isActive, tx);
		} catch(Exception ex) {
			Logger.error("Erreur BasicDAO getAll : ", ex);
			BDDUtils.rollback(isActive, tx);
		}
		return all;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getAllOrderAscByColumn(Class<T> table, String columnToOrder) {
		List<T> all = null;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try {
			tx = BDDUtils.beginTransaction(isActive);
			all = BDDUtils.getCurrentSession().createCriteria(table).addOrder(Order.asc(columnToOrder)).list();
			BDDUtils.commit(isActive, tx);
		} catch(Exception ex) {
			Logger.error("Erreur BasicDAO getAllOrderAscByColumn : ", ex);
			BDDUtils.rollback(isActive, tx);
		}
		return all;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getAllOrderDescByColumn(Class<T> table, String columnToOrder) {
		List<T> all = null;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try {
			tx = BDDUtils.beginTransaction(isActive);
			all = BDDUtils.getCurrentSession().createCriteria(table).addOrder(Order.desc(columnToOrder)).list();
			BDDUtils.commit(isActive, tx);
		} catch(Exception ex) {
			Logger.error("Erreur BasicDAO getAllOrderDescByColumn : ", ex);
			BDDUtils.rollback(isActive, tx);
		}
		return all;
	}
	
	public static <T> boolean insertOrUpdate(T object) {
		boolean b = false;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try{
			tx = BDDUtils.beginTransaction(isActive);
			BDDUtils.getCurrentSession().saveOrUpdate(object);
			BDDUtils.commit(isActive, tx);
			b = true;
		} catch(Exception ex) {
			Logger.error("Erreur BasicDAO insertOrUpdate : ", ex);
			BDDUtils.rollback(isActive, tx);
		}
		return b;
	}
	
	public static <T> boolean update(T object) {
		boolean b = false;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try{
			tx = BDDUtils.beginTransaction(isActive);
			BDDUtils.getCurrentSession().update(object);
			BDDUtils.commit(isActive, tx);
			b = true;
		} catch(Exception ex) {
			Logger.error("Erreur BasicDAO update : ", ex);
			BDDUtils.rollback(isActive, tx);
		}
		return b;
	}
	
	public static <T> boolean insert(T object) {
		boolean b = false;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try{
			tx = BDDUtils.beginTransaction(isActive);
			BDDUtils.getCurrentSession().save(object);
			BDDUtils.commit(isActive, tx);
			b = true;
		}
		catch(Exception ex)
		{
			Logger.error("Erreur BasicDAO insert : ", ex);
			BDDUtils.rollback(isActive, tx);
		}
		return b;
	}
	
	public static <T> boolean delete(T object) {
		boolean b = false;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try{
			tx = BDDUtils.beginTransaction(isActive);
			BDDUtils.getCurrentSession().delete(object);
			BDDUtils.commit(isActive, tx);
			b = true;
		} catch(Exception ex) {
			Logger.error("Erreur BasicDAO delete : ", ex);
			BDDUtils.rollback(isActive, tx);
		}
		return b;
	}
}
