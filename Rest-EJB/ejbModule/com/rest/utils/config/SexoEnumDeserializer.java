package com.rest.utils.config;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.node.TextNode;

import com.rest.enums.SexoEnum;

public class SexoEnumDeserializer extends JsonDeserializer<SexoEnum> {

	@Override
	public SexoEnum deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		TextNode node = (TextNode) oc.readTree(jp);
		String valorString = node.getTextValue();

		SexoEnum[] values = SexoEnum.values();
		for (SexoEnum value : values) {
			if (valorString.equals(value.name())) {
				return value;
			}
		}
		return null;
	}

}
