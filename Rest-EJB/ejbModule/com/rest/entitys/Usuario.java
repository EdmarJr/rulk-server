package com.rest.entitys;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import com.rest.enums.SexoEnum;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(@NamedQuery(name = Usuario.OBTER_POR_EMAIL, query = "SELECT u FROM Usuario u where u.email = :email"))
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", defaultImpl = Cliente.class)
@JsonSubTypes({ @Type(value = Cliente.class, name = "Cliente"),
		@Type(value = Colaborador.class, name = "Colaborador") })
public abstract class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8590414821260034395L;

	public static final String OBTER_POR_EMAIL = "obterUsuarioPorEmail";

	@Id
	@Column(name = "email")
	private String email;
	@Column(name = "nome")
	private String nome;
	@Column(name = "rg")
	private String rg;
	@Column(name = "orgaoExpedidor")
	private String orgaoExpedidor;
	@Column(name = "hashSenha")
	private String hashSenha;
	@Column(name = "foto")
	private String foto;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "sexo")
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	@ElementCollection
	@CollectionTable(name = "telefones")
	private List<String> telefones;
	@ManyToOne
	@JoinColumn(name = "unidade_id", referencedColumnName = "id")
	private Unidade unidade;
	@Transient
	private String password;
	@Column(name = "dataNascimento")
	private LocalDate dataNascimento;
	@Transient
	private String type;
	@OneToMany(mappedBy = "usuario")
	private List<UsuarioGrupo> usuarioGrupos;

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

	public Company getEmpresa() {
		return this.getUnidade().getEmpresa();
	}

	@JsonIgnore
	public String getHashSenha() {
		return hashSenha;
	}

	public void setHashSenha(String hashSenha) {
		this.hashSenha = hashSenha;
	}

	@JsonIgnore
	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonIgnore
	public List<UsuarioGrupo> getUsuarioGrupos() {
		return usuarioGrupos;
	}

	public void setUsuarioGrupos(List<UsuarioGrupo> usuarioGrupos) {
		this.usuarioGrupos = usuarioGrupos;
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

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", hashSenha=" + hashSenha + "]";
	}

}
