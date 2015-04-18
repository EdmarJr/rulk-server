package com.rest.business;

import java.util.List;

import javax.inject.Inject;

import com.rest.authentication.SecurityContext;
import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Unidade;
import com.rest.utils.exceptions.BusinessException;
import com.rest.utils.list.VerificadorLista;
import com.rest.utils.string.Constantes;

public class UnidadeBusiness extends Business<Unidade> {

	@Inject
	private SecurityContext securityContext;

	@Inject
	private CrudService<Unidade> dao;

	@Override
	public CrudService<Unidade> getDao() {
		return dao;
	}

	@Override
	public void incluir(Unidade unidade) throws BusinessException {
		unidade.setEmpresa(securityContext.getUsuarioLogado().getEmpresa());
		dao.create(unidade);
	}

	public Unidade obterPorIdComEagerPlanos(Long id) {
		List<Unidade> retorno = dao.findWithNamedQuery(
				Constantes.UNIDADE_POR_ID_COM_EAGER_PLANOS, QueryParameter
						.with("id", id).parameters());
		return VerificadorLista.sePossuiUmElemento(retorno) ? retorno.get(0)
				: null;
	}
	
//	public void validarInclusaoUnidade(Unidade unidade) {
//		securityContext.getUsuarioLogado().get
//	}

}
