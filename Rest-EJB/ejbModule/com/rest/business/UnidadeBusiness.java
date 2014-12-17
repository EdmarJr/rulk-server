package com.rest.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.entitys.Unidade;
import com.rest.exceptions.BusinessException;
import com.rest.login.LoginService;

@Stateless
@LocalBean
public class UnidadeBusiness extends Business<Unidade> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private LoginService loginService;
	
	@Inject
	private CrudService<Unidade> dao;
	
	@Override
	public CrudService<Unidade> getDao() {
		return dao;
	}
	
	@Override
	public void incluir(Unidade unidade) throws BusinessException {
		unidade.setEmpresa(loginService.obterUsuarioLogado().getEmpresa());
		dao.create(unidade);
	}
}
