package com.rest.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class CrudService<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7255213568098350137L;

	@PersistenceContext
	private EntityManager em;

	public T create(T t) {
		this.em.persist(t);
		this.em.flush();
		this.em.refresh(t);
		return t;
	}

	public T find(Class<T> type, Object id) {
		return (T) this.em.find(type, id);
	}

	public void delete(Class<T> type, Object id) {
		Object ref = this.em.getReference(type, id);
		this.em.remove(ref);
	}

	public T update(T t) {
		return (T) this.em.merge(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> findWithNamedQuery(String namedQueryName) {
		return this.em.createNamedQuery(namedQueryName).getResultList();
	}

	public List<T> findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters) {
		return findWithNamedQuery(namedQueryName, parameters, 0);
	}

	@SuppressWarnings("unchecked")
	public List<T> findWithNamedQuery(String queryName, int resultLimit) {
		return this.em.createNamedQuery(queryName).setMaxResults(resultLimit)
				.getResultList();
	}

	public T findSingleResultWithNamedQuery(String queryName,
			Map<String, Object> parameters) {
		List<T> resultList = findWithNamedQuery(queryName, parameters);
		return resultList.size() > 0 ? resultList.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNativeQuery(String sql, Class<T> type) {
		return this.em.createNativeQuery(sql, type).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters, int resultLimit) {
		Query query = this.em.createNamedQuery(namedQueryName);
		if (resultLimit > 0)
			query.setMaxResults(resultLimit);

		Iterator<Entry<String, Object>> entries = parameters.entrySet()
				.iterator();
		while (entries.hasNext()) {
			Entry<String, Object> thisEntry = (Entry<String, Object>) entries
					.next();
			query.setParameter(thisEntry.getKey().toString(),
					thisEntry.getValue());
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> obterTodos(Class<T> clazz) {
		Query query = em.createQuery("SELECT t FROM " + clazz.getName() + " t");
		return query.getResultList();
	}

}
