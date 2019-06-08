package fr.seblaporte.DTO.google.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Command {

    private List<String> ids;

    private DeviceStatusEnum status;

    private String errorCode;

    private String debugString;

    public Command() {
        this.errorCode = "";
        this.debugString = "";
        this.ids = new ArrayList<>();
    }

    public Command(String deviceId, DeviceStatusEnum deviceStatus) {

        this.ids = new ArrayList<>();
        this.ids.add(deviceId);
        this.status = deviceStatus;
        this.errorCode = "";
        this.debugString = "";
    }
}
