package com.rest.utils.exceptions;

public class UsuarioNaoEColaboradorException extends SecurityException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEColaboradorException() {
		super("Usuário não é colaborador.");
	}

}
