package fr.seblaporte.DTO.domoticz;

public class DomoticzDeviceStatus {

    /* Device status */
    DomoticzDeviceStatusEnum status;

    /* Level for devices with dimmer capability */
    Integer level = 100;

    public DomoticzDeviceStatus(DomoticzDeviceStatusEnum status) {
        this.status = status;
    }

    public DomoticzDeviceStatus(DomoticzDeviceStatusEnum status, Integer level) {
        this.status = status;
        this.level = level;
    }

    public DomoticzDeviceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DomoticzDeviceStatusEnum status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
