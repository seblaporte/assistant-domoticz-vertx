package fr.seblaporte.DTO.domoticz;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DomoticzDeviceTypeEnum {

    @JsonProperty("Light")
    LIGHT,
    @JsonProperty("Generic")
    SWITCH,
    @JsonProperty("Amplifier")
    AMPLIFIER,
    @JsonProperty("Speaker")
    SPEAKER,
    @JsonProperty("TV")
    TV,
    @JsonProperty("xiaomi-mi-robot-vacuum-icon")
    MI_ROBOT
}
