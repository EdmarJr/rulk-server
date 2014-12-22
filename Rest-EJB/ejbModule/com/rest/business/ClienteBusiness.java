package com.rest.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.authentication.SecurityContext;
import com.rest.dao.CrudService;
import com.rest.entitys.Cliente;
import com.rest.entitys.Colaborador;
import com.rest.entitys.Unidade;
import com.rest.utils.exceptions.BusinessException;
import com.rest.utils.exceptions.PlanoNaoDisponivelException;
import com.rest.utils.exceptions.UnidadeNaoDisponivelException;
import com.rest.utils.exceptions.UsuarioNaoEColaboradorException;

@Stateless
@LocalBean
public class ClienteBusiness extends Business<Cliente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CrudService<Cliente> dao;
	@Inject
	private SecurityContext securityContext;
	@Inject
	private UnidadeBusiness unidadeBusiness;

	public CrudService<Cliente> getDao() {
		return dao;
	}

	@Override
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

		Colaborador colaboradorLogado = securityContext
				.verificarEObterColaboradorLogado();
		if (!colaboradorLogado.sePossuiPermissaoEmUnidade(unidade))
			throw new UnidadeNaoDisponivelException();
		if (!unidade.sePossuiPlano(cliente.getPlano()))
			throw new PlanoNaoDisponivelException();

	}

}
