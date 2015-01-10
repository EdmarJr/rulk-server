package com.rest.entitys;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rest.enums.PositivoNegativoEnum;

@Entity
@Table(name = "desconto")
public class Desconto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoDesconto")
	private PositivoNegativoEnum tipoDesconto;

	@Column(name="descricao")
	private String descricao;
	
	@Column(name = "dataInicio")
	private LocalDate dataInicio;
	
	@Column(name = "dataFim")
	private LocalDate dataFim;

	@Column(name="valorDesconto")
	private Double valorDesconto;
	
	@ManyToOne
	@JoinColumn(name="cliente_id",referencedColumnName="email")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PositivoNegativoEnum getTipoDesconto() {
		return tipoDesconto;
	}

	public void setTipoDesconto(PositivoNegativoEnum tipoDesconto) {
		this.tipoDesconto = tipoDesconto;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
}
