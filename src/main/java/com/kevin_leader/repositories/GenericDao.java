package com.kevin_leader.repositories;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.kevin_leader.util.SessionFactoryProvider;

/**
 * Generic DAO revisioned from a design by Paula Waite at Madison College
 * @author Kevin Leader
 * @param <T> the type parameter
 */
public class GenericDao<T> {
	
	private Class<T> type;
	private static final Logger log = Logger.getLogger(GenericDao.class);
	
	/**
	 * Instantiate a new GenericDao
	 * @param type the type of entity
	 */
	public GenericDao(Class<T> type) {
		this.type = type;
	}
	
	/**
     * Return an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }
	
    /**
     * Add an entity
     * @param <T> the type parameter
     * @param entity the entity to be inserted
     * @return the id of the inserted entity
     */
	public int add(T entity) {
		log.debug("Run add(entity)");
		
		Session session = getSession();
		Transaction transaction = null;
		
		int id;
		try {
			transaction = session.beginTransaction();
			// Change returned serializable id to int 
			id = Integer.valueOf(String.valueOf(session.save(entity)));
			transaction.commit();
		} catch (HibernateException e) {
			log.debug("Exception: ", e);
			return -1;
		} finally {
			session.close();
		}
		return id;
	}
	
	/**
	 * Get all entities
	 * @return list of all entities
	 */
	public List<T> getAll() {
		log.debug("Run getAll()");
		
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(type);
		
		query.from(type);
		
		List<T> list = null;
		try {
			list = session.createQuery(query).getResultList();
		} catch (HibernateException e) {
			log.debug("Exception : ", e);
		} finally {
			session.close();
		}
		return list;
	}
	
	/**
	 * Get an entity by id
	 * @param <T> the type parameter
	 * @param id the id to search by
	 * @return the retreived entity
	 */
	public <T>T getById(int id) {
		log.debug("Run get(id)");
		
		Session session = getSession();
		T entity = null;
		
		try {
			entity = (T) session.get(type, id);
		} catch (HibernateException e) {
			log.debug("Exception: ", e);
		} finally {
			session.close();
		}
		return entity;
	}
	
	/**
	 * Update an entity
	 * @param <T> the type parameter
	 * @param entity the entity to update to
	 * @return the updated entity
	 */
	public <T>T update(T entity) {
		log.debug("Run update(entity)");
		
		Session session = getSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
		} catch (HibernateException e) {
			log.debug("Exception: ", e);
			transaction.rollback();
			return null;
		} finally {
			session.close();
		}
		return entity;
	}
	
	/**
	 * Delete an entity
	 * @param <T> the type parameter
	 * @param entity the entity to delete
	 * @return the entity deleted
	 */
	public <T>T delete(T entity) {
		log.debug("Run delete(entity)");
		
		Session session = getSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();
		} catch(HibernateException e) {
			log.debug("Exception: ", e);
			transaction.rollback();
			return null;
		} finally {
			session.close();
		}
		return entity;
	}
	
}
