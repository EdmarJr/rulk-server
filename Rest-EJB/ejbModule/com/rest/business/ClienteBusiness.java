package com.rest.business;

import java.io.Serializable;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.entitys.Cliente;
import com.rest.entitys.Unidade;
import com.rest.utils.exceptions.BusinessException;
import com.rest.utils.exceptions.PlanoNaoDisponivelException;
import com.rest.utils.exceptions.UnidadeNaoDisponivelException;
import com.rest.utils.exceptions.UsuarioNaoEColaboradorException;
import com.rest.utils.security.SecurityRoles;

@Stateless
@LocalBean
public class ClienteBusiness extends Business<Cliente> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5115945647928612317L;
	@Inject
	private CrudService<Cliente> dao;
	@Inject
	private UnidadeBusiness unidadeBusiness;

	public CrudService<Cliente> getDao() {
		return dao;
	}

	@Override
	@RolesAllowed({ SecurityRoles.DONO_DE_EMPRESA,
			SecurityRoles.GERENTE_DE_UNIDADE })
	public void incluir(Cliente cliente)
			throws UsuarioNaoEColaboradorException,
			UnidadeNaoDisponivelException, PlanoNaoDisponivelException,
			BusinessException {
		validarCliente(cliente);
		super.incluir(cliente);
	}

	private void validarCliente(Cliente cliente)
			throws UsuarioNaoEColaboradorException,
			UnidadeNaoDisponivelException, PlanoNaoDisponivelException {
		Unidade unidade = unidadeBusiness.obterPorIdComEagerPlanos(cliente
				.getUnidade().getId());

		// Colaborador colaboradorLogado = securityContext
		// .verificarEObterColaboradorLogado();
		// if (!colaboradorLogado.sePossuiPermissaoEmUnidade(unidade))
		// throw new UnidadeNaoDisponivelException();
		// if (!unidade.sePossuiPlano(cliente.getPlano()))
		// throw new PlanoNaoDisponivelException();

	}

}
