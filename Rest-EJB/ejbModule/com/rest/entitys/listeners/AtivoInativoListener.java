package com.rest.entitys.listeners;

import javax.persistence.PrePersist;

import com.rest.entitys.interfaces.ObjetoComExclusaoLogica;


public class AtivoInativoListener {

	@PrePersist
	public void setAtivo(ObjetoComExclusaoLogica objeto) {
		objeto.setAtivo(Boolean.TRUE);
	}
	
}
