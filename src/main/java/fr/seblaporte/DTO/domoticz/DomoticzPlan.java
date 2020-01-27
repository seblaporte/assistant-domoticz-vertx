package fr.seblaporte.DTO.domoticz;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DomoticzPlan {

    @JsonProperty("idx")
    private String id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Devices")
    private String numberOfDevices;

    @JsonProperty("Order")
    private String order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfDevices() {
        return numberOfDevices;
    }

    public void setNumberOfDevices(String numberOfDevices) {
        this.numberOfDevices = numberOfDevices;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
