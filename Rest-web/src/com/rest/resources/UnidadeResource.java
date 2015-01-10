package com.rest.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
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

import com.rest.authentication.SecurityContext;
import com.rest.business.UnidadeBusiness;
import com.rest.entitys.Plano;
import com.rest.entitys.Unidade;
import com.rest.utils.exceptions.BusinessException;

@ApplicationScoped
@Path(value = "/unidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnidadeResource extends Rest {
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private SecurityContext securityContext;
	
	@Inject
	private UnidadeBusiness unidadeBusiness;
	
	@GET
	@RolesAllowed("ADMIN")
	public List<Unidade> obterTodos() {
		List<Unidade> unidades = new ArrayList<>();
		unidades.add(securityContext.getUsuarioLogado().getUnidade());
		return unidades;
		
	}
	
	@POST
	@RolesAllowed("ADMIN")
	public Response incluir(Unidade unidade) {
		try {
			unidadeBusiness.incluir(unidade);
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		URI uri = uriInfo.getAbsolutePathBuilder()
				.path(unidade.getId().toString()).build();
		return Response.created(uri).build();
	}

	@GET
	@Path("/{id}/planos")
	public List<Plano> getUnidade(@PathParam("id") Long id) {
		return unidadeBusiness.obterPorIdComEagerPlanos(id).getPlanos();
	}
	

}
