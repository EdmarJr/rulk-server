package com.rest.business;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.authentication.SecurityContext;
import com.rest.dao.CrudService;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.BusinessException;

@Stateless
@LocalBean
public class UsuarioBusiness extends Business<Usuario> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CrudService<Usuario> dao;

	@Inject
	private SecurityContext securityContext;

	@Override
	public CrudService<Usuario> getDao() {
		return dao;
	}

	@Override
	public void incluir(Usuario usuario) throws BusinessException {
		usuario.setUnidade(securityContext.getUsuarioLogado().getUnidade());
		dao.create(usuario);
	}

}
