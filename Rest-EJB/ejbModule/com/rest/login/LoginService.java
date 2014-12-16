package com.rest.login;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.rest.entitys.Empresa;
import com.rest.entitys.Unidade;
import com.rest.entitys.Usuario;
import com.rest.string.Constantes;

@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class LoginService implements Serializable  {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager manager;

	private Usuario usuarioLogado;

	public Usuario obterUsuarioLogado() {
		gerenciarCampoUsuario();
		return usuarioLogado;
	}

	public Unidade obterUnidadeNoContexto() {
		gerenciarCampoUsuario();
		return usuarioLogado.getUnidade();

	}	

	public Empresa obterEmpresaNoContexto() {
		gerenciarCampoUsuario();
		return usuarioLogado.getEmpresa();
	}

	public void gerenciarCampoUsuario() {
		if (usuarioLogado != null) {
			return;
		}
		definirUsuarioLogado();
	}

	private void definirUsuarioLogado() {
		this.usuarioLogado = (Usuario) manager
				.createNamedQuery(Constantes.USUARIO_LISTAR_TODOS)
				.getResultList().get(0);
	}
}
