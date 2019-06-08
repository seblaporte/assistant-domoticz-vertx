package fr.seblaporte.DTO.domoticz;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DomoticzDeviceStatus {

    /* Device status */
    DomoticzDeviceStatusEnum status;

    /* Level for devices with dimmer capability */
    Integer level = 100;

    public DomoticzDeviceStatus(DomoticzDeviceStatusEnum status) {
        this.status = status;
    }

}
