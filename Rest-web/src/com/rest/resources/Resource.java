package com.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

public abstract class Resource {
	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(Resource.class);
	@Context
	protected HttpServletRequest httpServletRequest;

	public String obterIp() {
		String remoteHost = httpServletRequest.getRemoteHost();
		String remoteAddr = httpServletRequest.getRemoteAddr();
		int remotePort = httpServletRequest.getRemotePort();
		String ip = remoteHost + " (" + remoteAddr + ":" + remotePort + ")";
		return ip;

	}
}
