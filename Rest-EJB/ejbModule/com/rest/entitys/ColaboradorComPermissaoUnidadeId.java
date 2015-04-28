package com.rest.entitys;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ColaboradorComPermissaoUnidadeId implements Serializable {
	private String colaboradorEmail;
	private Long unidadeId;

	public Long getUnidadeId() {
		return unidadeId;
	}

	public void setUnidadeId(Long unidadeId) {
		this.unidadeId = unidadeId;
	}

	public String getColaboradorEmail() {
		return colaboradorEmail;
	}

	public void setColaboradorEmail(String colaboradorEmail) {
		this.colaboradorEmail = colaboradorEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((colaboradorEmail == null) ? 0 : colaboradorEmail.hashCode());
		result = prime * result
				+ ((unidadeId == null) ? 0 : unidadeId.hashCode());
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
		ColaboradorComPermissaoUnidadeId other = (ColaboradorComPermissaoUnidadeId) obj;
		if (colaboradorEmail == null) {
			if (other.colaboradorEmail != null)
				return false;
		} else if (!colaboradorEmail.equals(other.colaboradorEmail))
			return false;
		if (unidadeId == null) {
			if (other.unidadeId != null)
				return false;
		} else if (!unidadeId.equals(other.unidadeId))
			return false;
		return true;
	}

}
