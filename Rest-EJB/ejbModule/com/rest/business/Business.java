package com.rest.business;

import java.util.List;

import com.rest.dao.CrudService;
import com.rest.entitys.Unidade;
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
		Class<T> clazz = obterClassNoContexto();
		return getDao().find(clazz, id);
	}

	public List<T> obterTodos() {
		return getDao().obterTodos(obterClassNoContexto());

	}

	@SuppressWarnings("unchecked")
	private Class<T> obterClassNoContexto() {
		return (Class<T>) Unidade.class;
	}
}