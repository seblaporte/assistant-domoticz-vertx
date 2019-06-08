package fr.seblaporte.DTO.google.request;

import lombok.Data;

import java.util.List;

/**
 * Request object.
 */
@Data
public class RequestDTO {

    /** Id of RequestDTO for ease of tracing */
    private String requestId;

    private List<RequestInputsDTO> inputs;
}
