package com.rest.authentication;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Usuario;

@Stateless
@LocalBean
public class SecurityContext implements Serializable {

	/**
	 * 	
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuarioLogado;

	@Resource
	private SessionContext sessionContext;
	@Inject
	private CrudService<Usuario> crudServiceUsuario;

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) {
			usuarioLogado = crudServiceUsuario.findSingleResultWithNamedQuery(
					Usuario.OBTER_POR_EMAIL,
					QueryParameter.with("email",
							sessionContext.getCallerPrincipal().getName())
							.parameters());
		}
		return usuarioLogado;
	}
}
