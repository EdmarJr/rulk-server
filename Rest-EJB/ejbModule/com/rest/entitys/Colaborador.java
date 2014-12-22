package com.rest.entitys;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.rest.entitys.interfaces.ObjetoComExclusaoLogica;
import com.rest.entitys.listeners.AtivoInativoListener;

@Entity
@Table(name = "colaborador", schema = "dbo")
@PrimaryKeyJoinColumn(name = "email", referencedColumnName = "email")
@EntityListeners(AtivoInativoListener.class)
public class Colaborador extends Usuario implements ObjetoComExclusaoLogica {
	@Column(name = "dataInicio")
	private LocalDate dataInicio;
	@Column(name = "dataFim")
	private LocalDate dataFim;
	@Column(name = "ativo")
	private Boolean ativo;
	@ManyToMany
	@JoinTable(name = "colaborador_has_permission_in_unidades", schema = "dbo", joinColumns = @JoinColumn(name = "unidade_id", referencedColumnName = "email"), inverseJoinColumns = @JoinColumn(name = "colaborador_id", referencedColumnName = "id"))
	private List<Unidade> unidadesComPermissoes;

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public List<Unidade> getUnidadesComPermissoes() {
		return unidadesComPermissoes;
	}

	public void setUnidadesComPermissoes(List<Unidade> unidadesComPermissoes) {
		this.unidadesComPermissoes = unidadesComPermissoes;
	}

	public Boolean sePossuiPermissaoEmUnidade(Unidade unidade) {
		List<Unidade> unidades = getUnidadesComPermissoes();
		for (Unidade u : unidades) {
			if (unidade.equals(u))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	
	

}
