package com.rest.utils.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class AutenticacaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutenticacaoException() {
		// TODO Auto-generated constructor stub
	}

	public AutenticacaoException(Throwable t) {
		super(t);
	}

}
