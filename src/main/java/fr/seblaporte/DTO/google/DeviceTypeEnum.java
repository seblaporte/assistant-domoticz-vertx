package fr.seblaporte.DTO.google;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DeviceTypeEnum {

    @JsonProperty("action.devices.types.LIGHT")
    LIGHT,
    @JsonProperty("action.devices.types.OUTLET")
    OUTLET,
    @JsonProperty("action.devices.types.SWITCH")
    SWITCH,
    @JsonProperty("action.devices.types.BLINDS")
    WINDOW
}
