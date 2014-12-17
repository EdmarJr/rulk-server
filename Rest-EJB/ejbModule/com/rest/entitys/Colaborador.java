package com.rest.entitys;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.rest.entitys.interfaces.ObjetoComExclusaoLogica;
import com.rest.entitys.listeners.AtivoInativoListener;

@Entity
@Table(name="colaborador",schema="dbo")
@PrimaryKeyJoinColumn(name = "email", referencedColumnName = "email")
@EntityListeners(AtivoInativoListener.class)
public class Colaborador extends Usuario implements ObjetoComExclusaoLogica {
	@Column(name = "dataInicio")
	private LocalDate dataInicio;
	@Column(name = "dataFim")
	private LocalDate dataFim;
	@Column(name = "ativo")
	private Boolean ativo;


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
	
	

}
