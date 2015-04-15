package com.rest.authentication;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.inject.Inject;

import com.rest.business.ColaboradorBusiness;
import com.rest.entitys.Colaborador;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.UsuarioNaoEColaboradorException;

public class SecurityContext implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuarioLogado;
	
	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Colaborador verificarEObterColaboradorLogado()
			throws UsuarioNaoEColaboradorException {
		return null;

		/*throw new UsuarioNaoEColaboradorException();*/

	}

}
