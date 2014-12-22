package com.rest.entitys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.rest.utils.string.Constantes;

@Entity
@Table(name = "plano", schema = "dbo")
@NamedQueries(@NamedQuery(name = Constantes.PLANOS_OBTER_POR_UNIDADE, query = "SELECT p from Plano p WHERE p.unidade.id=:unidade_id"))
public class Plano {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "unidade_id", referencedColumnName = "id")
	private Unidade unidade;

	@Column(name = "nome")
	private String nome;

	@Column(name = "valor_mensal")
	private Double valorMensal;

	@ManyToMany
	@JoinTable(name = "plano_has_modalidade", joinColumns = @JoinColumn(name = "modalidade_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "plano_id", referencedColumnName = "id"))
	private List<Modalidade> modalidadesDisponiveis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(Double valorMensal) {
		this.valorMensal = valorMensal;
	}

	@JsonIgnore
	public List<Modalidade> getModalidadesDisponiveis() {
		return modalidadesDisponiveis;
	}

	public void setModalidadesDisponiveis(
			List<Modalidade> modalidadesDisponiveis) {
		this.modalidadesDisponiveis = modalidadesDisponiveis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Plano other = (Plano) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
