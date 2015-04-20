package com.rest.resources;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
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

import com.rest.authentication.UsuarioLogado;
import com.rest.business.UsuarioBusiness;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.BusinessException;

@RequestScoped
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7947423924943041089L;
	@Inject
	private UsuarioBusiness usuarioBusiness;
	@Context
	private UriInfo uriInfo;
	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;

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
			return Response.created(
					uriInfo.getAbsolutePathBuilder().path(usuario.getEmail())
							.build()).build();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/logado")
	public Response obterUsuarioLogado() {
		return usuarioLogado != null ? Response.ok(usuarioLogado).build()
				: Response.noContent().build();
	}

}