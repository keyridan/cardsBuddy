package com.j0rsa.cardsbuddy.tinycards.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.j0rsa.cardsbuddy.controller.model.FileDeserializer;
import com.j0rsa.cardsbuddy.controller.model.ImageTypeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Details {
    @JsonDeserialize(using = ImageTypeDeserializer.class)
    private ImageType.Code imageType;
    @JsonDeserialize(using = FileDeserializer.class)
    private byte[] data;
}
