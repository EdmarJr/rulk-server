package com.rest.utils.config;

import java.time.LocalDate;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import com.rest.enums.SexoEnum;

@Provider
public class JacksonConfig
 implements ContextResolver<ObjectMapper>
{

	private ObjectMapper objectMapper;

	public JacksonConfig() {
		objectMapper = new ObjectMapper();
		objectMapper
				.configure(
						DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);

		SimpleModule module = new SimpleModule("MyModule", new Version(1, 0, 0,
				null));

		module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
		module.addDeserializer(SexoEnum.class, new SexoEnumDeserializer());
		objectMapper.registerModule(module);
	}

	public ObjectMapper getContext(Class<?> objectType) {
		return objectMapper;
	}

}
