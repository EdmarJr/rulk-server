package com.rest.authentication;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.rest.business.ColaboradorBusiness;
import com.rest.entitys.Colaborador;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.UsuarioNaoEColaboradorException;

@Singleton
public class SecurityContext implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ColaboradorBusiness colaboradorBusiness;

	private Usuario usuarioLogado;
	
	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Colaborador verificarEObterColaboradorLogado()
			throws UsuarioNaoEColaboradorException {
		if (usuarioLogado instanceof Colaborador) {
			return (Colaborador) colaboradorBusiness
					.obterPorEmailComEagerUnidadesPermitidas(usuarioLogado
							.getEmail());
		}

		throw new UsuarioNaoEColaboradorException();

	}

}
