package com.rest.entitys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.rest.entitys.interfaces.ObjetoComExclusaoLogica;
import com.rest.entitys.listeners.AtivoInativoListener;
import com.rest.utils.string.Constantes;

@Entity
@Table(name = "colaborador")
@PrimaryKeyJoinColumn(name = "email", referencedColumnName = "email")
@EntityListeners(AtivoInativoListener.class)
@NamedQueries(@NamedQuery(name = Constantes.COLABORADOR_POR_ID_COM_EAGER_UNIDADES_PERMITIDAS, query = "SELECT c from Colaborador c LEFT JOIN FETCH c.unidadesComPermissoes where c.email = :email"))
public class Colaborador extends Usuario implements ObjetoComExclusaoLogica {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4928994378186406678L;
	@Column(name = "dataInicio")
	private LocalDate dataInicio;
	@Column(name = "dataFim")
	private LocalDate dataFim;
	@Column(name = "ativo")
	private Boolean ativo;
	@OneToMany(mappedBy = "colaborador")
	private List<ColaboradorComPermissaoUnidade> unidadesComPermissoes;

	@JsonIgnore
	public List<Unidade> getUnidadesComPermissoesParaOColaborador() {
		ArrayList<Unidade> unidades = new ArrayList<Unidade>();
		unidadesComPermissoes.forEach((u) -> unidades.add(u.getUnidade()));
		return unidades;
	}

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

	@JsonIgnore
	public Boolean sePossuiPermissaoEmUnidade(Unidade unidade) {
		List<ColaboradorComPermissaoUnidade> unidades = getUnidadesComPermissoes();
		for (ColaboradorComPermissaoUnidade u : unidades) {
			if (unidade.equals(u.getUnidade()))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@JsonIgnore
	public List<ColaboradorComPermissaoUnidade> getUnidadesComPermissoes() {
		return unidadesComPermissoes;
	}

	public void setUnidadesComPermissoes(
			List<ColaboradorComPermissaoUnidade> unidadesComPermissoes) {
		this.unidadesComPermissoes = unidadesComPermissoes;
	}

}
