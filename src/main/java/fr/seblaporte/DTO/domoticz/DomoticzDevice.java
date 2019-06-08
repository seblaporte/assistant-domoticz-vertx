package fr.seblaporte.DTO.domoticz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.seblaporte.util.DomoticzDeviceStatusDeserializer;
import lombok.Data;

@Data
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
    private DomoticzSubTypeEnum subType;

    @JsonProperty("SwitchType")
    private DomoticzSwitchTypeEnum switchType;

    @JsonProperty("HardwareName")
    private String hardwareName;

    @JsonProperty("HardwareType")
    private DomoticzHardwareEnum hardwareType;

    @JsonProperty("Image")
    private DomoticzDeviceTypeEnum deviceCategory;

    @JsonProperty("PlanID")
    private String room;

    @JsonProperty("Status")
    @JsonDeserialize(using = DomoticzDeviceStatusDeserializer.class)
    private DomoticzDeviceStatus status;
}
