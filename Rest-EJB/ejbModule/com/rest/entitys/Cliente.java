package com.rest.entitys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.rest.enums.PeriodoPagamentoEnum;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "usuario_id", referencedColumnName = "email")
public class Cliente extends Usuario {
	@ManyToOne
	@JoinColumn(name = "plano_id", referencedColumnName = "id")
	private Plano plano;

	@Column(name = "descontoPorcentagem")
	private List<Desconto> descontos;

	@Enumerated(EnumType.STRING)
	private PeriodoPagamentoEnum tipoPagamento;

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public List<Desconto> getDescontos() {
		return descontos;
	}

	public void setDescontos(List<Desconto> descontos) {
		this.descontos = descontos;
	}

	public PeriodoPagamentoEnum getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(PeriodoPagamentoEnum tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	
	
	

}
