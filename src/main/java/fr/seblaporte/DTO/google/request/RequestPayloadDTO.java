package fr.seblaporte.DTO.google.request;

import lombok.Data;

import java.util.List;

@Data
public class RequestPayloadDTO {

    private List<RequestPayloadExecuteCommandsDTO> commands;

    private List<RequestPayloadQueryDevicesDTO> devices;
}
