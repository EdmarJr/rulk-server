package com.rest.resources;

import java.net.URI;
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

import com.rest.authentication.qualifiers.ColaboradorLogado;
import com.rest.authentication.qualifiers.UsuarioLogado;
import com.rest.business.ColaboradorBusiness;
import com.rest.business.ColaboradorComPermissaoUnidadeBusiness;
import com.rest.business.UnidadeBusiness;
import com.rest.entitys.Colaborador;
import com.rest.entitys.ColaboradorComPermissaoUnidade;
import com.rest.entitys.Plano;
import com.rest.entitys.Unidade;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.BusinessException;

@RequestScoped
@Path(value = "/unidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnidadeResource extends Resource {
	@Context
	private UriInfo uriInfo;

	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;
	@Inject
	@ColaboradorLogado
	private Colaborador colaboradorLogado;

	@Inject
	private UnidadeBusiness unidadeBusiness;
	@Inject
	private ColaboradorComPermissaoUnidadeBusiness colaboradorComPermissaoUnidadeBusiness;

	@Inject
	private ColaboradorBusiness colaboradorBusiness;

	@GET
	public Response obterUnidadesDisponiveis() {
		if (colaboradorLogado != null) {
			return Response.ok(
					colaboradorBusiness
							.obterPorEmailComEagerUnidadesPermitidas(
									colaboradorLogado.getEmail())
							.getUnidadesComPermissoesParaOColaborador())
					.build();
		}
		return Response.noContent().build();
	}

	@POST
	public Response incluir(Unidade unidade) {
		try {
			unidadeBusiness.incluir(unidade);
			colaboradorComPermissaoUnidadeBusiness
					.incluir(new ColaboradorComPermissaoUnidade(
							colaboradorLogado, unidade));
			URI uri = uriInfo.getAbsolutePathBuilder()
					.path(unidade.getId().toString()).build();
			return Response.created(uri).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/{id}/planos")
	public List<Plano> getUnidade(@PathParam("id") Long id) {
		return unidadeBusiness.obterPorIdComEagerPlanos(id).getPlanos();
	}

	@GET
	@Path("/{id}")
	public Response obterPorId(@PathParam("id") Long id) {
		return Response.ok(unidadeBusiness.obterPorId(id)).build();

	}

}
