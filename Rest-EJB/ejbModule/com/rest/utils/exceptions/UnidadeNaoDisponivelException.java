package com.rest.utils.exceptions;

public class UnidadeNaoDisponivelException extends SecurityException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnidadeNaoDisponivelException() {
		super("O usuário logado não tem permissão para utilizar essa unidade.");
	}

}
