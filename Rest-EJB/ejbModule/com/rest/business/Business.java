package com.rest.business;

import java.lang.reflect.ParameterizedType;

import com.rest.dao.CrudService;
import com.rest.exceptions.BusinessException;

public abstract class Business<T> {
	public void incluir(T t) throws BusinessException {
		getDao().create(t);
	}

	public void alterar(T t) throws BusinessException {
		getDao().create(t);
	}

	public abstract CrudService<T> getDao();
	
	public T obterPorId(Long id) {
		@SuppressWarnings("unchecked")
		Class<T> clazz = ((Class<T>) ((ParameterizedType) getDao().getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		return getDao().obterPorId(clazz, id);
	}
}
