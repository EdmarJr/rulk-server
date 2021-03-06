package com.rest.entitys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.rest.entitys.interfaces.ObjetoComExclusaoLogica;
import com.rest.entitys.listeners.AtivoInativoListener;

@Entity
@Table(name = "empresa")
@EntityListeners(AtivoInativoListener.class)
public class Company implements ObjetoComExclusaoLogica {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long id;
	@NotNull
	@Column(name="nome")
	private String nome;
	@Column(name="email")
	@NotNull
	private String email;
	@OneToOne
	@JoinColumn(name="responsavel_id",referencedColumnName="email")
	private Colaborador responsavel;
	@NotNull
	@Column(name="telefone")
	private String telefone;
	@OneToMany(mappedBy="empresa")
	private List<Unidade> unidades;
	@Column(name="ativo")
	private Boolean ativo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@JsonIgnore
	public List<Unidade> getUnidades() {
		return unidades;
	}
	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Colaborador getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Colaborador responsavel) {
		this.responsavel = responsavel;
	}
	
	
	
}
