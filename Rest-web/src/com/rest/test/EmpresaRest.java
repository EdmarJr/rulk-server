package com.rest.test;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
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

import com.rest.business.EmpresaBusiness;
import com.rest.entitys.Empresa;
import com.rest.exceptions.BusinessException;



@ApplicationScoped
@Path("/empresas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaRest {
	
	@Inject 
	private EmpresaBusiness business;
	@Context
	private UriInfo uriInfo;
	
	@GET
	public List<Empresa> obterUsuarios() {
		return null;
	}

	@GET
	@Path("/{id}")
	public Empresa obterUsuarioMock(@PathParam("id") Long id) {
		return null;
	}
	
	@POST
	public Response adicionar(@Valid Empresa empresa) {
		try {
			business.incluir(empresa);
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.created(uriInfo.getAbsolutePathBuilder().path(empresa.getId().toString()).build()).build();
	}
}