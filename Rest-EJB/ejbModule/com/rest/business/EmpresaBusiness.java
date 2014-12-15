package com.rest.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.entitys.Empresa;

@Stateless
@LocalBean
public class EmpresaBusiness extends Business<Empresa>{

	@Inject
	private CrudService<Empresa> dao;

	public CrudService<Empresa> getDao() {
		return dao;
	}
	
	
	
	
	
}
