package com.rest.authentication;

import java.io.Serializable;

import javax.inject.Singleton;

import com.rest.entitys.Usuario;

@Singleton
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

}
