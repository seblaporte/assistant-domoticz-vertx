package fr.seblaporte.DTO.domoticz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomoticzDevicesResponse {

    /** Devices informations */
    private List<DomoticzDevice> result;

    public List<DomoticzDevice> getResult() {
        return result;
    }

    public void setResult(List<DomoticzDevice> result) {
        this.result = result;
    }
}
