package com.rest.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.authentication.SecurityContext;
import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.BusinessException;
import com.rest.utils.string.Constantes;

@Stateless
@LocalBean
public class UserBusiness extends Business<Usuario> {
	
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

	public List<Usuario> obterUsuariosPorEmailEHashSenha(String email, String hashSenha) {
		return dao.findWithNamedQuery(Constantes.USUARIO_AUTENTICAR, QueryParameter.with("email", email).and("hashSenha", hashSenha).parameters());
	}
}
