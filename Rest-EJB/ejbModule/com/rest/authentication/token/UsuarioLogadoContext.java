package com.rest.authentication.token;

import java.time.LocalDate;

import javax.enterprise.context.RequestScoped;

import com.rest.entitys.Usuario;

@RequestScoped
public class UsuarioLogadoContext {
	
	private LocalDate dataUltimoAcesso;
	private Usuario usuario;
	
	public LocalDate getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public UsuarioLogadoContext setDataUltimoAcesso(LocalDate dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
		return this;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public UsuarioLogadoContext setUsuario(Usuario usuario) {
		this.usuario = usuario;
		return this;
	}

	@Override
	public String toString() {
		return "UsuarioLogado [dataUltimoAcesso=" + dataUltimoAcesso
				+ ", getEmail()=" + usuario != null ? usuario.getEmail() : null + ", getHashSenha()="
				+ usuario != null ? usuario.getHashSenha() : null+ "]";
	}
	
	
	
	
}
