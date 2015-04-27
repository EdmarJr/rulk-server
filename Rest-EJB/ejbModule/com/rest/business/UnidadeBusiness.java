package com.rest.business;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.authentication.qualifiers.UsuarioLogado;
import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Unidade;
import com.rest.entitys.Usuario;
import com.rest.utils.exceptions.BusinessException;
import com.rest.utils.list.VerificadorLista;
import com.rest.utils.security.SecurityRoles;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class UnidadeBusiness extends Business<Unidade> implements Serializable {

	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;
	@Inject
	private CrudService dao;

	@Override
	public CrudService getDao() {
		return dao;
	}

	@Override
	@RolesAllowed({ SecurityRoles.DONO_DE_EMPRESA })
	public void incluir(Unidade unidade) throws BusinessException {
		unidade.setEmpresa(usuarioLogado.getEmpresa());
		dao.create(unidade);
	}

	@RolesAllowed({ SecurityRoles.COLABORADOR, SecurityRoles.DONO_DE_EMPRESA,
			SecurityRoles.GERENTE_DE_UNIDADE })
	public Unidade obterPorIdComEagerPlanos(Long id) {
		List<Unidade> retorno = dao.findWithNamedQuery(
				Unidade.UNIDADE_POR_ID_COM_EAGER_PLANOS,
				QueryParameter.with("id", id).parameters());
		return VerificadorLista.sePossuiUmElemento(retorno) ? retorno.get(0)
				: null;
	}

	@PermitAll
	public Unidade obterPorId(Long id) {
		// TODO Auto-generated method stub
		return super.obterPorId(id);
	}

}
