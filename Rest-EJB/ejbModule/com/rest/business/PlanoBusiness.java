package com.rest.business;

import java.io.Serializable;
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
public class PlanoBusiness extends Business<Plano> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3188320421516497391L;
	@Inject
	private CrudService dao;

	@Override
	public CrudService getDao() {
		return dao;
	}

	public List<Plano> obterPlanosPorUnidade(Unidade unidade) {
		return dao
				.findWithNamedQuery(Constantes.PLANOS_OBTER_POR_UNIDADE,
						QueryParameter.with("unidade_id", unidade.getId())
								.parameters());
	}

	public Boolean sePlanoDisponivel(Plano plano, Unidade unidade) {
		List<Plano> planosDisponiveis = obterPlanosPorUnidade(unidade);
		for (Plano p : planosDisponiveis) {
			if (p.equals(plano))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
