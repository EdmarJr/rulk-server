package com.rest.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Usuario;
import com.rest.exceptions.BusinessException;
import com.rest.string.Constantes;

@Stateless
@LocalBean
public class UsuarioBusiness extends Business<Usuario> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CrudService<Usuario> dao;
	
	@Override
	public CrudService<Usuario> getDao() {
		return dao;
	}
	
	@Override
	public void incluir(Usuario usuario) throws BusinessException {
		usuario.setUnidade(securityContext.getUsuario().getUnidade());
		dao.create(usuario);
	}

	public List<Usuario> obterUsuariosPorEmailEHashSenha(String email, String hashSenha) {
		return dao.findWithNamedQuery(Constantes.USUARIO_AUTENTICAR, QueryParameter.with("email", email).and("hashSenha", hashSenha).parameters());
	}
}
