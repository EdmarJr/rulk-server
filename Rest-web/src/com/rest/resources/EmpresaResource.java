package com.rest.resources;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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

import com.rest.business.CompanyBusiness;
import com.rest.entitys.Company;
import com.rest.utils.exceptions.BusinessException;



@RequestScoped
@Path("/empresas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaResource {
	
	@EJB
	private CompanyBusiness business;
	@Context
	private UriInfo uriInfo;
	
	@GET
	@PermitAll
	public List<Company> obterUsuarios() {
		return business.obterTodos();
	}

	@GET
	@Path("/{id}")
	public Company obterUsuarioMock(@PathParam("id") Long id) {
		return null;
	}
	
	@POST
	public Response adicionar(@Valid Company empresa) {
		try {
			business.incluir(empresa);
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.created(uriInfo.getAbsolutePathBuilder().path(empresa.getId().toString()).build()).build();
	}
}