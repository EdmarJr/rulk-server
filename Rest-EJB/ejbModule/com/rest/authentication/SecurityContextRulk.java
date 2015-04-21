package com.rest.authentication;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.rest.authentication.qualifiers.ColaboradorLogado;
import com.rest.authentication.qualifiers.UsuarioLogado;
import com.rest.business.ColaboradorBusiness;
import com.rest.entitys.Colaborador;
import com.rest.entitys.Usuario;

@SessionScoped
public class SecurityContextRulk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5966322154012915361L;

	private Usuario usuarioLogado;
	private Colaborador colaboradorLogado;

	@Inject
	private ColaboradorBusiness colaboradorBusiness;

	@Produces
	@UsuarioLogado
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	@Produces
	@ColaboradorLogado
	public Colaborador getColaboradorLogador() {
		return colaboradorLogado;
	}

	public void definirUsuarioLogado(Usuario usuario) {
		if (usuario != null && usuario instanceof Colaborador) {
			colaboradorLogado = (Colaborador) colaboradorBusiness
					.obterPorEmailComEagerUnidadesPermitidas(usuario.getEmail());
			usuarioLogado = colaboradorLogado;
			return;
		}
		usuarioLogado = usuario;
	}
}
