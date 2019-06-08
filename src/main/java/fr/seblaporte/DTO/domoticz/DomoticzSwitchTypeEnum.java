package fr.seblaporte.DTO.domoticz;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DomoticzSwitchTypeEnum {

    @JsonProperty("On/Off")
    ON_OFF,
    @JsonProperty("Dimmer")
    DIMMER,
    @JsonProperty("Contact")
    CONTACT,
    @JsonProperty("Selector")
    SELECTOR,
    @JsonProperty("Door Contact")
    DOOR_CONTACT
}
