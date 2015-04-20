package com.rest.business.test.interceptors;

import java.util.concurrent.Callable;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

import com.rest.utils.security.SecurityRoles;

@Stateless
@RunAs(SecurityRoles.DONO_DE_EMPRESA)
@PermitAll
public class DonoEmpresaInterceptor {
	public <V> V call(Callable<V> callable) throws Exception {
		return callable.call();
	}
}
