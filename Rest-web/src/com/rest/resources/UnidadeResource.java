package com.rest.resources;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import com.rest.business.UnidadeBusiness;
import com.rest.entitys.Colaborador;
import com.rest.entitys.Plano;
import com.rest.entitys.Unidade;
import com.rest.entitys.Usuario;
import com.rest.utils.ResourceUtils;
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

	interface AcaoSalvar {
		void salvar(Unidade unidade) throws BusinessException;
	}

	@PUT
	@POST
	public Response salvar(Unidade unidade) {
		return unidade.getId() == null ? salvar(unidade,
				(u) -> unidadeBusiness.incluir(u)) : salvar(unidade,
				(u) -> unidadeBusiness.alterar(u));
	}

	private Response salvar(Unidade unidade, AcaoSalvar acao) {
		try {
			acao.salvar(unidade);
			return Response
					.created(
							ResourceUtils.obterUri(uriInfo, unidade.getId()
									.toString())).entity(unidade).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	public Response excluir(Unidade unidade) {
		try {
			unidadeBusiness.excluir(unidade);
			return Response.ok().build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.serverError().build();
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
		return Response.ok().entity(unidadeBusiness.obterPorId(id)).build();

	}

}
