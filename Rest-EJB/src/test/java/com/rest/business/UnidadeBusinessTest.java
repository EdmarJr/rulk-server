package com.rest.business;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.rest.dao.CrudService;
import com.rest.entitys.Grupo;

@RunWith(Arquillian.class)
public class UnidadeBusinessTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class)
				.addClass(CrudService.class)
				.addClass(Grupo.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}


	@Inject
	private CrudService<Grupo> crudService;

	@PersistenceContext
	EntityManager em;

	@Inject
	UserTransaction utx;

	@Test
	public void incluirUnidade() throws Exception {
		startTransaction();
		Grupo grupo = new Grupo();
		grupo.setNome("teste");
		em.persist(grupo);
		utx.commit();
		Assert.assertEquals(crudService.find(Grupo.class, grupo.getNome()).getNome(),
				"teste");
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
