package com.rest.authentication;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;

import com.rest.authentication.token.AuthenticationService;
import com.rest.exceptions.AutenticacaoException;

@ApplicationScoped
@Path(value = "/autenticacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRest {

	@Inject
	private AuthenticationService authenticationService;

	@POST
	public Response autenticarUsuario(String email, String senha) {
		try {
			return new ServerResponse(authenticationService.autenticarUsuario(
					email, senha), 401, new Headers<Object>());
		} catch (AutenticacaoException e) {
			e.printStackTrace();
			return Response.status(Status.UNAUTHORIZED).build();
		}

	}
}
