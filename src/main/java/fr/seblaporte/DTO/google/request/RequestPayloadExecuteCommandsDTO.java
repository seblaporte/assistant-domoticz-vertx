package fr.seblaporte.DTO.google.request;

import fr.seblaporte.DTO.google.DeviceDTO;
import lombok.Data;

import java.util.List;

@Data
public class RequestPayloadExecuteCommandsDTO {

    private List<DeviceDTO> devices;

    private List<ExecutionCommandDTO> execution;
}
