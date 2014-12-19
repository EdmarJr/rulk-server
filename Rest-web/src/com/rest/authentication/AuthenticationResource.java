package com.rest.authentication;

import java.io.Serializable;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;

import com.rest.entitys.Usuario;
import com.rest.viewmodel.UserAuthenticated;

@ApplicationScoped
@Path(value = "/authentication")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@POST
	@PermitAll
	public Response authenticate(Usuario user) {
		String[] roles = { "ADMIN" };
		UserAuthenticated userAuthenticated = new UserAuthenticated()
				.setUserEmail(user.getEmail())
				.setRoles(roles).setToken("123456");
		return new ServerResponse(userAuthenticated, 201, new Headers<>());
	}
}
