package com.rest.test;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.rest.business.ClienteBusiness;
import com.rest.entitys.Cliente;
import com.rest.utils.exceptions.BusinessException;

@ApplicationScoped
@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource implements Serializable {

	@Context
	private UriInfo uriInfo;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteBusiness business;

	@POST
	public Response incluir(Cliente cliente) {
		try {
			business.incluir(cliente);
			return Response.created(
					uriInfo.getAbsolutePathBuilder().build(cliente)).build();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
