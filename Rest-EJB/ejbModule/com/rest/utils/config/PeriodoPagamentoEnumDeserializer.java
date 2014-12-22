package com.rest.utils.config;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.node.TextNode;

import com.rest.enums.PeriodoPagamentoEnum;

public class PeriodoPagamentoEnumDeserializer extends
		JsonDeserializer<PeriodoPagamentoEnum> {

	@Override
	public PeriodoPagamentoEnum deserialize(JsonParser jp,
			DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		TextNode node = (TextNode) oc.readTree(jp);
		String valorString = node.getTextValue();

		PeriodoPagamentoEnum[] values = PeriodoPagamentoEnum.values();
		for (PeriodoPagamentoEnum value : values) {
			if (valorString.equalsIgnoreCase(value.name())) {
				return value;
			}
		}
		return null;
	}

}
