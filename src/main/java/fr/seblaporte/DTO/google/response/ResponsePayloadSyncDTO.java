package fr.seblaporte.DTO.google.response;

import fr.seblaporte.DTO.google.DeviceDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResponsePayloadSyncDTO extends ResponsePayloadDTO {

    /** Reﬂects the unique (and immutable) user ID on the agent's platform. */
    private String agentUserId;

    /** Array of devices. Zero or more devices are returned. */
    private List<DeviceDTO> devices;
}
