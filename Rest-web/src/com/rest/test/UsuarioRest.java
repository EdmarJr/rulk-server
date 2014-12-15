package com.rest.test;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.rest.business.UsuarioBusiness;
import com.rest.entitys.Usuario;
import com.rest.exceptions.BusinessException;



@ApplicationScoped
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioRest {
	
	@Inject
	private UsuarioBusiness usuarioBusiness;
	
	@GET
	public List<Usuario> obterUsuarios() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario = new Usuario();
		usuarios.add(usuario);
		usuarios.add(usuario);
		usuarios.add(usuario);
		return usuarios;
	}

	@GET
	@Path("/{id}")
	public Usuario obterUsuarioMock(@PathParam("id") Long id) {
		return usuarioBusiness.obterPorId(id);
	}
	
	@POST
	public Response criarUsuario(Usuario usuario) {
		try {
			usuarioBusiness.incluir(usuario);
			return Response.status(Status.CREATED).build();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}