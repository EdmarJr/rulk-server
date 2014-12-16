package com.rest.entitys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.rest.entitys.interfaces.ObjetoComExclusaoLogica;
import com.rest.entitys.listeners.AtivoInativoListener;

@Entity
@Table(name = "unidade", schema = "dbo")
@EntityListeners(AtivoInativoListener.class)
public class Unidade implements ObjetoComExclusaoLogica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	@NotNull
	private String nome;
	@ManyToOne
	@JoinColumn(name = "empresa_id", referencedColumnName = "id")
	private Empresa empresa;
	@OneToOne
	@JoinColumn(name="responsavel_id",referencedColumnName="email")
	private Colaborador responsavel;
	@Column(name = "ativo")
	private Boolean ativo;
	@OneToMany(mappedBy = "unidade")
	private List<Usuario> clientes;
	@OneToMany(mappedBy="unidade")
	private List<Aparelho> aparelhos;
	@OneToMany(mappedBy="unidade")
	private List<Plano> planosDisponiveis;

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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	@JsonIgnore
	public List<Usuario> getClientes() {
		return clientes;
	}

	public void setClientes(List<Usuario> clientes) {
		this.clientes = clientes;
	}
	@JsonIgnore
	public List<Aparelho> getAparelhos() {
		return aparelhos;
	}

	public void setAparelhos(List<Aparelho> aparelhos) {
		this.aparelhos = aparelhos;
	}
	@JsonIgnore
	public List<Plano> getPlanosDisponiveis() {
		return planosDisponiveis;
	}

	public void setPlanosDisponiveis(List<Plano> planosDisponiveis) {
		this.planosDisponiveis = planosDisponiveis;
	}

	public Colaborador getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Colaborador responsavel) {
		this.responsavel = responsavel;
	}
	
	

	
	
}
