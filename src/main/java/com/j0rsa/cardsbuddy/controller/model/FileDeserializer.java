package com.j0rsa.cardsbuddy.controller.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class FileDeserializer extends JsonDeserializer<File> {
    @Override
    public File deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        String data = node.get("data").textValue();

        byte[] decodedBytes = Base64.getDecoder().decode(data);
        File file = new File("factImage");
        FileUtils.writeByteArrayToFile(file, decodedBytes);
        return file;
    }
}
