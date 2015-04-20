package com.rest.business.test;

import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.rest.business.UnidadeBusiness;
import com.rest.business.test.interceptors.DonoEmpresaInterceptor;
import com.rest.business.test.utilstest.JavaArchiveBuilder;
import com.rest.entitys.Unidade;

@RunWith(Arquillian.class)
public class UnidadeBusinessTeste {

	@Deployment
	public static JavaArchive createDeployment() {
		return JavaArchiveBuilder.buildJavaArchiveCompleto().addClasses(
				UnidadeBusiness.class, DonoEmpresaInterceptor.class);
	}

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	@Inject
	private UnidadeBusiness unidadeBusiness;

	@Inject
	private DonoEmpresaInterceptor donoEmpresa;

	@Test
	public void incluirUnidadeDonoEmpresa() throws Exception {
		donoEmpresa.call(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				unidadeBusiness.incluir(new Unidade());
				return null;
			}
		});
	}
}
