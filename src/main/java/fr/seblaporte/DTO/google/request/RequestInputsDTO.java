package fr.seblaporte.DTO.google.request;

import lombok.Data;

@Data
public class RequestInputsDTO {

    /** RequestDTO type */
    private String intent;

    private RequestPayloadDTO payload;
}
