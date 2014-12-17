package com.rest.authentication;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.authentication.token.AuthenticationService;
import com.rest.exceptions.AutenticacaoException;

@ApplicationScoped
@Path(value = "/unidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRest {
	
	@Inject
	private AuthenticationService authenticationService;
	
	@POST
	public String autenticarUsuario(String email,String senha) {
		try {
			authenticationService.autenticarUsuario(email,senha);
		} catch (AutenticacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
