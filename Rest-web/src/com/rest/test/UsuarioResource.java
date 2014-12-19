package com.rest.test;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.rest.business.PlanoBusiness;
import com.rest.business.UserBusiness;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.BusinessException;



@ApplicationScoped
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
	
	@Inject
	private UserBusiness usuarioBusiness;
	@Context
	private UriInfo uriInfo;
	@Inject
	private PlanoBusiness planoBusiness;
	
	@GET
	public List<Usuario> obterUsuarios() {
		return usuarioBusiness.obterTodos();
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
			return Response.created(uriInfo.getAbsolutePathBuilder().path(usuario.getEmail()).build()).build();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}