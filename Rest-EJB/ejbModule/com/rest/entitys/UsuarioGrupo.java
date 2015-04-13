package com.rest.entitys;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="usuario_grupo")
public class UsuarioGrupo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UsuarioGrupoId usuarioGrupoId;
	
	@ManyToOne
	@MapsId("grupoId")
	@JoinColumn(name="grupo_id",referencedColumnName="nome")
	private Grupo grupo;
	@ManyToOne
	@MapsId("usuarioId")
	@JoinColumn(name="usuario_id",referencedColumnName="email")
	private Usuario usuario;
	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
