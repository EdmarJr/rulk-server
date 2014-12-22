package com.rest.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Colaborador;
import com.rest.utils.list.VerificadorLista;
import com.rest.utils.string.Constantes;

@Stateless
@LocalBean
public class ColaboradorBusiness extends Business<Colaborador> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CrudService<Colaborador> dao;

	@Override
	public CrudService<Colaborador> getDao() {
		return dao;
	}

	public Colaborador obterPorEmailComEagerUnidadesPermitidas(String email) {
		List<Colaborador> resultado = dao.findWithNamedQuery(
				Constantes.COLABORADOR_POR_ID_COM_EAGER_UNIDADES_PERMITIDAS,
				QueryParameter.with("email", email).parameters());
		return VerificadorLista.sePossuiUmElemento(resultado) ? resultado
				.get(0) : null;
	}

}
