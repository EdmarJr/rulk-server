package com.rest.business;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.entitys.Company;

@Stateless
@LocalBean
public class CompanyBusiness extends Business<Company> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8025076701204516529L;
	@Inject
	private CrudService<Company> dao;

	public CrudService<Company> getDao() {
		return dao;
	}

}
