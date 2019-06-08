package fr.seblaporte.DTO.google.response;

import lombok.Data;

/**
 * Response object.
 */
@Data
public class ResponseDTO {

    /** Id of RequestDTO for ease of tracing */
    private String requestId;

    /** Response content */
    private ResponsePayloadDTO payload;
}
