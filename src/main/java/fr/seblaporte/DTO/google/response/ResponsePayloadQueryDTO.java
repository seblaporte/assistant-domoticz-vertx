package fr.seblaporte.DTO.google.response;

import lombok.Data;

import java.util.Map;

@Data
public class ResponsePayloadQueryDTO extends ResponsePayloadDTO {

    Map<String, Map<String, Object>> devices;
}
