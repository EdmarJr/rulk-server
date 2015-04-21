package com.rest.business.test.utilstest;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import com.rest.authentication.SecurityContextRulk;
import com.rest.business.Business;
import com.rest.dao.CrudService;
import com.rest.dao.QueryParameter;
import com.rest.entitys.Entidade;
import com.rest.entitys.Usuario;
import com.rest.entitys.interfaces.ObjetoComExclusaoLogica;
import com.rest.entitys.listeners.AtivoInativoListener;
import com.rest.enums.SexoEnum;
import com.rest.utils.exceptions.BusinessException;

public class JavaArchiveBuilder {

	private JavaArchive ja;

	public JavaArchiveBuilder() {
		ja = ShrinkWrap.create(JavaArchive.class);
	}

	public JavaArchiveBuilder carregarExceptions() {
		ja = ja.addPackage(BusinessException.class.getPackage());
		return this;
	}

	public JavaArchiveBuilder carregarEntidades() {
		ja = ja.addPackage(Usuario.class.getPackage())
				.addPackage(AtivoInativoListener.class.getPackage())
				.addPackage(ObjetoComExclusaoLogica.class.getPackage());
		return this;

	}

	public JavaArchiveBuilder carregarEnums() {
		ja = ja.addPackage(SexoEnum.class.getPackage());
		return this;
	}

	public JavaArchiveBuilder carregarGenericos() {
		ja = ja.addClass(Entidade.class).addClass(Business.class);
		return this;

	}

	public JavaArchiveBuilder carregarPersistencia() {
		ja = ja.addAsResource("test-persistence.xml",
				"META-INF/persistence.xml").addClass(CrudService.class)
				.addClass(QueryParameter.class);
		return this;
	}

	public JavaArchiveBuilder carregarCdi() {
		ja = ja.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		return this;
	}

	public JavaArchiveBuilder carregarSeguranca() {
		ja = ja.addClass(SecurityContextRulk.class);
		this.carregarPersistencia();
		return this.carregarEntidades();
	}

	public static JavaArchive buildJavaArchiveCompleto() {
		return new JavaArchiveBuilder().carregarEnums().carregarExceptions()
				.carregarGenericos().carregarSeguranca().build();
	}

	public JavaArchive build() {
		return ja;
	}
}
