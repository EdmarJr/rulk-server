package com.rest.list;

import java.util.List;

public class VerificadorLista {
	public static Boolean sePossuiUmElemento(List<?> lista) {
		if(lista != null && lista.size() >= 1 && lista.get(0) != null) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;

	}
}
