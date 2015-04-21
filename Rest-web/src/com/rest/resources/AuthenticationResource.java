package com.rest.resources;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.authentication.SecurityContextRulk;
import com.rest.business.UsuarioBusiness;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class AuthenticationResource extends Resource {

	@Inject
	private SecurityContextRulk securityContextRulk;
	@Inject
	private UsuarioBusiness usuarioBusiness;

	@POST
	@Path("login")
	public Response logar(UsuarioVO usuario) {
		try {
			httpServletRequest.login(usuario.getEmail(), usuario.getSenha());
			logger.info("O usuário " + usuario.getEmail()
					+ " se autenticou no instante " + new Date());
			securityContextRulk.definirUsuarioLogado(usuarioBusiness
					.obterPorEmail(usuario.getEmail()));
			return Response.ok().build();
		} catch (ServletException e) {
			logger.info("Usuário tentou se logar com o email: "
					+ usuario.getEmail() + " no instante " + new Date()
					+ " e não obteve sucesso");
			return Response.status(404).build();
		}
	}

	@GET
	@Path("not-logged")
	public Response retornarErroUsuarioNaoLogado() {
		logger.info("Ouve uma tentativa de acesso a um recurso não autorizado vinda do seguinte endereço:"
				+ obterIp());
		return Response.status(401).build();
	}
}
