package com.rest.business;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.authentication.UsuarioLogado;
import com.rest.dao.CrudService;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.BusinessException;

@Stateless
@LocalBean
public class UsuarioBusiness extends Business<Usuario> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4259324396558001495L;

	/**
	 * 
	 */

	@Inject
	private CrudService<Usuario> dao;

	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;

	@Override
	public CrudService<Usuario> getDao() {
		return dao;
	}

	@Override
	public void incluir(Usuario usuario) throws BusinessException {
		usuario.setUnidade(usuarioLogado.getUnidade());
		dao.create(usuario);
	}

}
