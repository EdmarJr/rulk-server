package com.rest.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.authentication.qualifiers.UsuarioLogado;
import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.BusinessException;

@Stateless
@LocalBean
public class UsuarioBusiness extends Business<Usuario> {

	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;

	@Inject
	private CrudService dao;

	@Override
	public CrudService getDao() {
		return dao;
	}

	@Override
	public void incluir(Usuario usuario) throws BusinessException {
		usuario.setUnidade(usuarioLogado.getUnidade());
		dao.create(usuario);
	}

	public Usuario obterPorEmail(String email) {
		return dao.findSingleResultWithNamedQuery(Usuario.OBTER_POR_EMAIL,
				QueryParameter.with("email", email).parameters());
	}

}
