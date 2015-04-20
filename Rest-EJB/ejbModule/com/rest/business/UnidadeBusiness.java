package com.rest.business;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.authentication.UsuarioLogado;
import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Unidade;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.BusinessException;
import com.rest.utils.list.VerificadorLista;
import com.rest.utils.security.SecurityRoles;
import com.rest.utils.string.Constantes;

@Stateless
@LocalBean
public class UnidadeBusiness extends Business<Unidade> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5354644477885362607L;
	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;
	@Inject
	private CrudService<Unidade> dao;

	@Override
	public CrudService<Unidade> getDao() {
		return dao;
	}

	@Override
	@RolesAllowed(SecurityRoles.DONO_DE_EMPRESA)
	public void incluir(Unidade unidade) throws BusinessException {
		unidade.setEmpresa(usuarioLogado.getEmpresa());
		dao.create(unidade);
	}

	public Unidade obterPorIdComEagerPlanos(Long id) {
		List<Unidade> retorno = dao.findWithNamedQuery(
				Constantes.UNIDADE_POR_ID_COM_EAGER_PLANOS, QueryParameter
						.with("id", id).parameters());
		return VerificadorLista.sePossuiUmElemento(retorno) ? retorno.get(0)
				: null;
	}

}
