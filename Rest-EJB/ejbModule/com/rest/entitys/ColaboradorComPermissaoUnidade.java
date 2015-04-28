package com.rest.entitys;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name = "colaborador_has_permission_in_unidades")
public class ColaboradorComPermissaoUnidade {
	public ColaboradorComPermissaoUnidade() {
		colaboradorComPermissaoUnidadeId = new ColaboradorComPermissaoUnidadeId();
	}

	public ColaboradorComPermissaoUnidade(Colaborador colaborador,
			Unidade unidade) {
		this();
		setUnidade(unidade);
		setColaborador(colaborador);
	}

	@EmbeddedId
	private ColaboradorComPermissaoUnidadeId colaboradorComPermissaoUnidadeId;

	@ManyToOne
	@MapsId("colaboradorEmail")
	@JoinColumn(name = "colaboradorEmail", referencedColumnName = "email")
	@JsonManagedReference
	private Colaborador colaborador;

	@ManyToOne
	@MapsId("unidadeId")
	@JoinColumn(name = "unidadeId", referencedColumnName = "id")
	@JsonManagedReference
	private Unidade unidade;

	public ColaboradorComPermissaoUnidadeId getColaboradorComPermissaoUnidadeId() {
		return colaboradorComPermissaoUnidadeId;
	}

	public void setColaboradorComPermissaoUnidadeId(
			ColaboradorComPermissaoUnidadeId colaboradorComPermissaoUnidadeId) {
		this.colaboradorComPermissaoUnidadeId = colaboradorComPermissaoUnidadeId;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

}
