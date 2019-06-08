package fr.seblaporte.DTO.google.request;

import fr.seblaporte.DTO.google.DeviceCommandEnum;
import fr.seblaporte.DTO.google.StateEnum;
import lombok.Data;

import java.util.Map;

@Data
public class ExecutionCommandDTO {

    /** The command to execute, with (usually) accompanying parameters. */
    private DeviceCommandEnum command;

    private Map<StateEnum, Object> params;
}
