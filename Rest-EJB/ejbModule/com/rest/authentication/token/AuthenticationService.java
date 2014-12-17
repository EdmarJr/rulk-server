package com.rest.authentication.token;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Usuario;
import com.rest.exceptions.AutenticacaoException;
import com.rest.exceptions.HashException;
import com.rest.hash.HashGenerator;
import com.rest.list.VerificadorLista;
import com.rest.string.Constantes;

@Singleton
@LocalBean
public class AuthenticationService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CrudService<Usuario> dao;
	
	private Hashtable<String, SecurityContext> mapaDeUsuariosLogados;
	
	public AuthenticationService() {
		mapaDeUsuariosLogados = new Hashtable<String, SecurityContext>();
	}
	
	@Inject
	private SecurityContext securityContext;


	public TokenSecurity autenticarUsuario(String email,String senha) throws AutenticacaoException {
		List<Usuario> result;
		try {
			result = obterUsuariosPorEmailEHashSenha(email,senha);
			if(VerificadorLista.sePossuiUmElemento(result))
				registrarContextoUsuarioLogado(result.get(0), result);
			else
				throw new AutenticacaoException();
			String token =  HashGenerator.generateHash(securityContext.toString());
			mapaDeUsuariosLogados.put(token, securityContext);
			return new TokenSecurity().setToken(token);
		} catch (HashException e) {
			throw new AutenticacaoException(e);
		}
	}


	private void registrarContextoUsuarioLogado(Usuario usuario,
			List<Usuario> result) {
		if(VerificadorLista.sePossuiUmElemento(result)) {
			securityContext.setUsuario(usuario).setDataUltimoAcesso(LocalDate.now());
		}
	}
	
	public void registrarUltimoAcessoNoMapa(String token) {
		mapaDeUsuariosLogados.get(token).setDataUltimoAcesso(LocalDate.now());
	}
	

	private List<Usuario> obterUsuariosPorEmailEHashSenha(String email, String hashSenha) {
		return dao.findWithNamedQuery(Constantes.USUARIO_AUTENTICAR, QueryParameter.with("email", email).and("hashSenha", hashSenha).parameters());
	}
}
