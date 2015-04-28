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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import com.rest.entitys.interfaces.ObjetoComExclusaoLogica;
import com.rest.entitys.listeners.AtivoInativoListener;

@Entity
@Table(name = "unidade")
@EntityListeners(AtivoInativoListener.class)
@NamedQueries({ @NamedQuery(name = Unidade.UNIDADE_POR_ID_COM_EAGER_PLANOS, query = "SELECT u from Unidade u JOIN FETCH u.planos WHERE u.id = :id ") })
public class Unidade implements ObjetoComExclusaoLogica {

	public static final String UNIDADE_POR_ID_COM_EAGER_PLANOS = "buscarUnidadePorIdComJoinPlanos";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	@NotNull
	private String nome;
	@ManyToOne
	@JoinColumn(name = "empresa_id", referencedColumnName = "id")
	private Company empresa;
	@OneToOne
	@JoinColumn(name = "responsavel_id", referencedColumnName = "email")
	@JsonManagedReference
	private Colaborador responsavel;
	@Column(name = "ativo")
	private Boolean ativo;
	@OneToMany(mappedBy = "unidade")
	private List<Usuario> clientes;
	@OneToMany(mappedBy = "unidade")
	private List<Aparelho> aparelhos;
	@OneToMany(mappedBy = "unidade")
	private List<Plano> planos;

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

	public Company getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Company empresa) {
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
	public List<Plano> getPlanos() {
		return planos;
	}

	public void setPlanos(List<Plano> planos) {
		this.planos = planos;
	}

	public Colaborador getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Colaborador responsavel) {
		this.responsavel = responsavel;
	}

	public Boolean sePossuiPlano(Plano plano) {
		List<Plano> planos = getPlanos();
		for (Plano p : planos) {
			if (p.equals(plano))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
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
		Unidade other = (Unidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
