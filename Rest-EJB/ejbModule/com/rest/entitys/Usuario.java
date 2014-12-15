package com.rest.entitys;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.rest.string.Constantes;

@Entity
@Table(name="usuario",schema="dbo")
@NamedQueries({@NamedQuery(name=Constantes.USUARIO_LISTAR_TODOS, query = "SELECT u FROM Usuario u")})
public class Usuario {
	
	@Id
	@Column(name="email")
	private String email;
	@Column(name="hashSenha")
	private String hashSenha;
	@Column(name="foto")
	private String foto;
	@Column(name="cpf")
	private String cpf;
	@ElementCollection
	@CollectionTable(name="telefones",schema="dbo")
	private List<String> telefones;
	@ManyToOne
	@JoinColumn(name="unidade_id",referencedColumnName="id")
	private Unidade unidade;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Empresa getEmpresa() {
		return this.getUnidade().getEmpresa();
	}
	public String getHashSenha() {
		return hashSenha;
	}
	public void setHashSenha(String hashSenha) {
		this.hashSenha = hashSenha;
	}
	public List<String> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}
	
	
	
	
	
	
	
	
}
