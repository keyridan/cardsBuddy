package com.j0rsa.cardsbuddy.controller.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.j0rsa.cardsbuddy.tinycards.model.ImageType;

import java.io.IOException;

public class ImageTypeDeserializer extends JsonDeserializer<ImageType.Code> {
    @Override
    public ImageType.Code deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        String data = node.textValue();

        return ImageType.Code.of(data).orElse(null);
    }
}
