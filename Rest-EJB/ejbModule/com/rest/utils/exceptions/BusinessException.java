package com.rest.utils.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException() {
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String msg) {
		super(msg);
	}

}
