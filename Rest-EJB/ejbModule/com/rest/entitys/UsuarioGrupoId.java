package com.rest.entitys;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UsuarioGrupoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String usuarioId;
	private String grupoId;

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(String grupoId) {
		this.grupoId = grupoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grupoId == null) ? 0 : grupoId.hashCode());
		result = prime * result
				+ ((usuarioId == null) ? 0 : usuarioId.hashCode());
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
		UsuarioGrupoId other = (UsuarioGrupoId) obj;
		if (grupoId == null) {
			if (other.grupoId != null)
				return false;
		} else if (!grupoId.equals(other.grupoId))
			return false;
		if (usuarioId == null) {
			if (other.usuarioId != null)
				return false;
		} else if (!usuarioId.equals(other.usuarioId))
			return false;
		return true;
	}

}
