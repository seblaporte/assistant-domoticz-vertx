package fr.seblaporte.DTO.domoticz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.seblaporte.util.DomoticzDeviceStatusDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomoticzDevice {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("idx")
    private String deviceId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("SubType")
    private String subType;

    @JsonProperty("SwitchType")
    private DomoticzSwitchTypeEnum switchType;

    @JsonProperty("HardwareName")
    private String hardwareName;

    @JsonProperty("HardwareType")
    private String hardwareType;

    @JsonProperty("MaxDimLevel")
    private Integer maxDimLevel;

    @JsonProperty("Image")
    private DomoticzDeviceTypeEnum deviceCategory;

    @JsonProperty("PlanID")
    private String room;

    @JsonProperty("Status")
    @JsonDeserialize(using = DomoticzDeviceStatusDeserializer.class)
    private DomoticzDeviceStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public DomoticzSwitchTypeEnum getSwitchType() {
        return switchType;
    }

    public void setSwitchType(DomoticzSwitchTypeEnum switchType) {
        this.switchType = switchType;
    }

    public String getHardwareName() {
        return hardwareName;
    }

    public void setHardwareName(String hardwareName) {
        this.hardwareName = hardwareName;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType;
    }

    public DomoticzDeviceTypeEnum getDeviceCategory() {
        return deviceCategory;
    }

    public void setDeviceCategory(DomoticzDeviceTypeEnum deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public DomoticzDeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DomoticzDeviceStatus status) {
        this.status = status;
    }
}
