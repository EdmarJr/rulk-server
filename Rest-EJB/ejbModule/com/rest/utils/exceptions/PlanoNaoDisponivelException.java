package com.rest.utils.exceptions;

public class PlanoNaoDisponivelException extends SecurityException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlanoNaoDisponivelException() {
		super("O usuário logado não tem permissão para utilizar esse plano.");
	}

}
