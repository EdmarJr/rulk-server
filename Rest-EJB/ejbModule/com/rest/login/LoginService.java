package com.rest.login;

import com.rest.entitys.Empresa;
import com.rest.entitys.Unidade;
import com.rest.entitys.Usuario;

public interface LoginService {
	public Usuario obterUsuarioLogado();
	public Unidade obterUnidadeNoContexto();
	public Empresa obterEmpresaNoContexto();
}

