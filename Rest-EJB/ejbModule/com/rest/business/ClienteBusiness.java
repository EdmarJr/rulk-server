package com.rest.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.entitys.Cliente;

@Stateless
@LocalBean
public class ClienteBusiness extends Business<Cliente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CrudService<Cliente> dao;

	public CrudService<Cliente> getDao() {
		return dao;
	}
	
	
	
	
	
}
