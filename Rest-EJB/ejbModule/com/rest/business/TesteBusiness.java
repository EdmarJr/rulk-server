package com.rest.business;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class TesteBusiness {
	@RolesAllowed("teste")
	public String incluir() {
		return "teste";

	}
}
