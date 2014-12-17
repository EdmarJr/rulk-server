package com.rest.entitys;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.rest.string.Constantes;

@Entity
@Table(name = "usuario", schema = "dbo")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(@NamedQuery(name = Constantes.USUARIO_AUTENTICAR, query = "SELECT u FROM Usuario u where u.email = :email AND u.hashSenha = :hashSenha"))
public class Usuario {

	@Id
	@Column(name = "email")
	private String email;
	@Column(name = "hashSenha")
	private String hashSenha;
	@Column(name = "foto")
	private String foto;
	@Column(name = "cpf")
	private String cpf;
	@ElementCollection
	@CollectionTable(name = "telefones", schema = "dbo")
	private List<String> telefones;
	@ManyToOne
	@JoinColumn(name = "unidade_id", referencedColumnName = "id")
	private Unidade unidade;
	@Transient
	private String senha;

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
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}
