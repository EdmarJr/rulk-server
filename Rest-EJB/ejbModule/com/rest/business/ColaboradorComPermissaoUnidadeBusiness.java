package com.rest.business;

import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.entitys.ColaboradorComPermissaoUnidade;
import com.rest.utils.exceptions.BusinessException;

@Stateless
@LocalBean
public class ColaboradorComPermissaoUnidadeBusiness extends
		Business<ColaboradorComPermissaoUnidade> {

	@Inject
	private CrudService crudService;

	@Override
	public CrudService getDao() {
		// TODO Auto-generated method stub
		return crudService;
	}

	@Override
	@PermitAll
	public void incluir(ColaboradorComPermissaoUnidade t)
			throws BusinessException {
		// TODO Auto-generated method stub
		super.incluir(t);
	}

}
