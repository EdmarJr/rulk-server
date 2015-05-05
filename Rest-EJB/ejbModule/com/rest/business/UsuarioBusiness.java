package com.rest.business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.authentication.qualifiers.UsuarioLogado;
import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.BusinessException;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class UsuarioBusiness implements Serializable {

	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;

	@Inject
	private CrudService dao;

	public CrudService getDao() {
		return dao;
	}

	public void incluir(Usuario usuario) throws BusinessException {
		usuario.setUnidade(usuarioLogado.getUnidade());
		dao.create(usuario);
	}

	public Usuario obterPorEmail(String email) {
		return dao.findSingleResultWithNamedQuery(Usuario.OBTER_POR_EMAIL,
				QueryParameter.with("email", email).parameters());
	}

	public Usuario obterPorId(Object id) {
		return getDao().find(Usuario.class, id);
	}

	public List<Usuario> obterTodos(Class<Usuario> t) {
		return getDao().obterTodos(t);

	}

}
