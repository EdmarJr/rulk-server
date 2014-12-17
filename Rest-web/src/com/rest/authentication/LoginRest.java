package com.rest.authentication;

import java.util.Hashtable;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.dao.QueryParameter;
import com.rest.entitys.Usuario;

@ApplicationScoped
@Path(value = "/unidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRest {
	
	Hashtable<String, Usuario> mapToken;
	
	
	public LoginRest() {
		mapToken = new Hashtable<>();
	}
	
	@POST
	public String autenticarUsuario(Usuario usuario) {
		QueryParameter.with("email", usuario.getEmail()).and("hashSenha", usuario.getSenha());

	}
}
