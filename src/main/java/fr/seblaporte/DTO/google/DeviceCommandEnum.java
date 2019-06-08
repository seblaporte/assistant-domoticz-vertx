package fr.seblaporte.DTO.google;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DeviceCommandEnum {

    @JsonProperty("action.devices.commands.OnOff")
    ON_OFF,
    @JsonProperty("action.devices.commands.BrightnessAbsolute")
    BRIGHTNESS,
    @JsonProperty("action.devices.commands.ColorAbsolute")
    COLOR
}
