package com.rest.business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.rest.dao.CrudService;
import com.rest.utils.exceptions.BusinessException;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public abstract class Business<T> implements Serializable {

	public void incluir(T t) throws BusinessException {
		getDao().create(t);
	}

	public void alterar(T t) throws BusinessException {
		getDao().create(t);
	}

	public abstract CrudService getDao();

	public T obterPorId(Class<T> t, Object id) {
		return getDao().find(t, id);
	}

	public List<T> obterTodos(Class<T> t) {
		return getDao().obterTodos(t);

	}
}
