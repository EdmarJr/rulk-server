package com.rest.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class AuthenticationResource {
	
	@POST
	@Path("login")
	public Response logar(@Context HttpServletRequest hsr,UsuarioVO usuario) {
		try {
			hsr.login(usuario.getEmail(), usuario.getSenha());
			return Response.ok().build();
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
	}
}
