package com.rest.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Unidade;
import com.rest.exceptions.BusinessException;
import com.rest.list.VerificadorLista;
import com.rest.login.LoginService;
import com.rest.string.Constantes;

@Stateless
@LocalBean
public class UnidadeBusiness extends Business<Unidade> {
	
	@Inject
	private LoginService loginService;
	
	@Inject
	private CrudService<Unidade> dao;
	
	@Override
	public CrudService<Unidade> getDao() {
		return dao;
	}
	
	@Override
	public void incluir(Unidade unidade) throws BusinessException {
		unidade.setEmpresa(loginService.obterUsuarioLogado().getEmpresa());
		dao.create(unidade);
	}
	
	public Unidade obterPorId(Long id) {
		List<Unidade> retornoQuery = dao.findWithNamedQuery(Constantes.UNIDADE_BUSCAR_POR_ID, QueryParameter.with("id", id).parameters(), 1);
		return VerificadorLista.sePossuiUmElemento(retornoQuery) ? retornoQuery.get(0) : null;
	}
}
