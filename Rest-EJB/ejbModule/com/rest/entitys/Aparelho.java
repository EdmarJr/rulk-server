package com.rest.entitys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rest.entitys.interfaces.ObjetoComExclusaoLogica;
import com.rest.entitys.listeners.AtivoInativoListener;

@Entity
@Table(name = "aparelho")
@EntityListeners(AtivoInativoListener.class)
public class Aparelho implements ObjetoComExclusaoLogica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@ManyToMany
	@JoinTable(name = "aparelho_has_musculo", joinColumns = @JoinColumn(name = "musculo_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "aparelho_id", referencedColumnName = "id"))
	private List<Musculo> musculosAtivos;
	@Column(name = "ativo")
	private Boolean ativo;
	@ManyToOne
	@JoinColumn(name="unidade_id",referencedColumnName="id")
	private Unidade unidade;

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

	public List<Musculo> getMusculosAtivos() {
		return musculosAtivos;
	}

	public void setMusculosAtivos(List<Musculo> musculosAtivos) {
		this.musculosAtivos = musculosAtivos;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	

	
}
