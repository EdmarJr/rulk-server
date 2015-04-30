package com.rest.utils;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

public class ResourceUtils {
	public static <T> URI obterUri(UriInfo uriInfo, String id) {
		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		return uri;
	}

	interface Acao {
		String getId();
	}
}
