package com.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/static")
public class StaticResource {
	@GET
	@Path("{path:.*}")
	public Response Get(@PathParam("path") String path) {
		return Response.ok().build();
	}
}
