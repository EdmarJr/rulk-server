package com.rest.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rest.entitys.interfaces.ObjetoComExclusaoLogica;
import com.rest.entitys.listeners.AtivoInativoListener;

@Entity
@Table(name="colaborador",schema="dbo")
@PrimaryKeyJoinColumn(name = "email", referencedColumnName = "email")
@EntityListeners(AtivoInativoListener.class)
public class Colaborador extends Usuario implements ObjetoComExclusaoLogica {
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataInicio")
	private Date dataInicio;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataFim")
	private Date dataFim;
	@Column(name = "ativo")
	private Boolean ativo;

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}
