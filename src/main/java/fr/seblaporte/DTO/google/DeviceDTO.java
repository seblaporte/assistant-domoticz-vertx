package fr.seblaporte.DTO.google;

import java.util.List;

public class DeviceDTO {

    /** Device id */
    private String id;

    /** The hardware type of device. */
    private DeviceTypeEnum type;

    /** List of traits this device supports. */
    private List<DeviceTraitEnum> traits;

    /** Names of this device. */
    private DeviceNameDTO name;

    /** Indicates whether this device will have its states updated by the Real Time Feed. */
    private Boolean willReportState;

    /** Provides the current room of the device in the user's home to simplify setup. */
    private String roomHint;

    /** Contains fields describing the device for use in one-off logic if needed */
    private DeviceInfoDTO deviceInfo;

    public DeviceDTO() {
        this.setWillReportState(true);
        this.setDeviceInfo(new DeviceInfoDTO());
        this.setRoomHint("");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceTypeEnum getType() {
        return type;
    }

    public void setType(DeviceTypeEnum type) {
        this.type = type;
    }

    public List<DeviceTraitEnum> getTraits() {
        return traits;
    }

    public void setTraits(List<DeviceTraitEnum> traits) {
        this.traits = traits;
    }

    public DeviceNameDTO getName() {
        return name;
    }

    public void setName(DeviceNameDTO name) {
        this.name = name;
    }

    public Boolean getWillReportState() {
        return willReportState;
    }

    public void setWillReportState(Boolean willReportState) {
        this.willReportState = willReportState;
    }

    public String getRoomHint() {
        return roomHint;
    }

    public void setRoomHint(String roomHint) {
        this.roomHint = roomHint;
    }

    public DeviceInfoDTO getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfoDTO deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

}
