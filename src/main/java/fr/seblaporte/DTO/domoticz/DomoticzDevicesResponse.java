package fr.seblaporte.DTO.domoticz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomoticzDevicesResponse {

    /** Devices informations */
    private List<DomoticzDevice> result;
}
