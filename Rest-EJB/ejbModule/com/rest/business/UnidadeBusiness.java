package com.rest.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.authentication.token.UsuarioLogadoContext;
import com.rest.dao.CrudService;
import com.rest.entitys.Unidade;
import com.rest.exceptions.BusinessException;

@Stateless
@LocalBean
public class UnidadeBusiness extends Business<Unidade> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoContext usuarioLogado;
	
	@Inject
	private CrudService<Unidade> dao;
	
	@Override
	public CrudService<Unidade> getDao() {
		return dao;
	}
	
	@Override
	public void incluir(Unidade unidade) throws BusinessException {
		unidade.setEmpresa(usuarioLogado.getUsuario().getEmpresa());
		dao.create(unidade);
	}
}
