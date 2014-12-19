package com.rest.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Plano;
import com.rest.entitys.Unidade;
import com.rest.utils.string.Constantes;

@Stateless
@LocalBean
public class PlanoBusiness extends Business<Plano> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private CrudService<Plano> dao;

	@Override
	public CrudService<Plano> getDao() {
		return dao;
	}

	public List<Plano> obterPlanosPorUnidade(Unidade unidade) {
		return dao
				.findWithNamedQuery(Constantes.PLANOS_OBTER_POR_UNIDADE,
				QueryParameter.with("unidade_id", unidade.getId()).parameters());
	}

}
