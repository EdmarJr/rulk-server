package com.rest.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.entitys.Usuario;
import com.rest.exceptions.BusinessException;
import com.rest.login.LoginService;

@Stateless
@LocalBean
public class UsuarioBusiness extends Business<Usuario> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private LoginService loginService;
	
	@Inject
	private CrudService<Usuario> dao;
	
	@Override
	public CrudService<Usuario> getDao() {
		return dao;
	}
	
	@Override
	public void incluir(Usuario usuario) throws BusinessException {
		usuario.setUnidade(loginService.obterUnidadeNoContexto());
		dao.create(usuario);
	}
}
