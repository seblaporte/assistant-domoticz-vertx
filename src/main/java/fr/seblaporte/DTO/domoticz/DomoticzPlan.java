package fr.seblaporte.DTO.domoticz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DomoticzPlan {

    @JsonProperty("idx")
    private String id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Devices")
    private String numberOfDevices;

    @JsonProperty("Order")
    private String order;
}
