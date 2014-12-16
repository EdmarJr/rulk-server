package com.rest.test;

import java.net.URI;
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

import com.rest.business.UnidadeBusiness;
import com.rest.entitys.Unidade;
import com.rest.exceptions.BusinessException;
import com.rest.login.LoginService;

@ApplicationScoped
@Path(value = "/unidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnidadeRest extends Rest {
	@Context
	private UriInfo uriInfo;

	@Inject
	private UnidadeBusiness unidadeBusiness;
	@Inject
	private LoginService loginService;
	
	@GET
	public List<Unidade> obterTodos() {
		return loginService.obterUsuarioLogado().getEmpresa().getUnidades();
		
	}
	
	@POST
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
	@Path("/{id}")
	public Unidade getUnidade(@PathParam("id") Long id) {
		return unidadeBusiness.obterPorId(id);
	}
	

}