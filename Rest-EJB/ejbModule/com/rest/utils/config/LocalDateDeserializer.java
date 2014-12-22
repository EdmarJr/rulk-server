package com.rest.utils.config;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.node.TextNode;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		TextNode node = (TextNode) oc.readTree(jp);
		String dateString = node.getTextValue();

		Instant instant = Instant.parse(dateString);
		LocalDateTime dateTime = LocalDateTime.ofInstant(instant,
				ZoneId.systemDefault());
		LocalDate date = LocalDate.of(dateTime.getYear(), dateTime.getMonth(),
				dateTime.getDayOfMonth());
		return date;
	}

}
