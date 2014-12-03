package com.rest.test;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entitys.Usuario;

@ApplicationScoped
@Path("/")
public class UsuarioRest {
	
	@GET
	@Path("usuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario obterUsuarioMock() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("teste");
		return usuario;
	}
}
