package com.rest;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.rest.authentication.SecurityContext;
import com.rest.business.UserBusiness;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.HashException;
import com.rest.utils.hash.HashGenerator;
import com.rest.utils.list.VerificadorLista;

@Singleton
@Startup
@DependsOn({ "UserBusiness" })
public class Initialize implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityContext context;

	@Inject
	private UserBusiness userBusiness;

	@PostConstruct
	private void inicializar() {
		List<Usuario> usuarios;
		try {
			usuarios = userBusiness.obterUsuariosPorEmailEHashSenha(
					"edmarfagunde@gmail.com",
					HashGenerator.generateHash("123456"));
			if (VerificadorLista.sePossuiUmElemento(usuarios)) {
				context.setUsuarioLogado(usuarios.get(0));
			}
		} catch (HashException e) {
			e.printStackTrace();
		}

	}

}
