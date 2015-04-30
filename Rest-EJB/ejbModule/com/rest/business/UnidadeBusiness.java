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
import com.rest.entitys.Colaborador;
import com.rest.entitys.ColaboradorComPermissaoUnidade;
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
	private ColaboradorComPermissaoUnidadeBusiness colaboradorComPermissaoUnidadeBusiness;
	@Inject
	private CrudService dao;
	@Inject
	private ColaboradorBusiness colaboradorBusiness;

	@Override
	public CrudService getDao() {
		return dao;
	}

	@Override
	@RolesAllowed({ SecurityRoles.DONO_DE_EMPRESA })
	public void incluir(Unidade unidade) throws BusinessException {
		Colaborador colaboradorLogado = colaboradorBusiness
				.obterColaboradorLogado();
		dao.create(unidade);
		unidade.setResponsavel(colaboradorLogado);
		if (colaboradorLogado != null) {
			colaboradorComPermissaoUnidadeBusiness
					.incluir(new ColaboradorComPermissaoUnidade(
							colaboradorLogado, unidade));
		}
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

	public void excluir(Unidade unidade) throws BusinessException {
		unidade = obterPorId(unidade.getId());
		unidade.setAtivo(Boolean.FALSE);
		dao.update(unidade);
	}

	@PermitAll
	public Unidade obterPorId(Long id) {
		return super.obterPorId(Unidade.class, id);
	}

}
