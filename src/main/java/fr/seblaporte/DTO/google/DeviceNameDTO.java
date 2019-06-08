package fr.seblaporte.DTO.google;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class DeviceNameDTO {

    /** List of names provided by the partner rather than the user, often manufacturer names, SKUs, etc. */
    private List<String> defaultNames;

    /** Primary name of the device, generally provided by the user. */
    private String name;

    /** Additional names provided by the user for the device */
    private List<String> nicknames;

    public DeviceNameDTO() {
        this.nicknames = Collections.singletonList("");
        this.defaultNames = Collections.singletonList("");
    }

    public DeviceNameDTO(String name) {
        this.name = name;
        this.nicknames = Collections.singletonList(name);
        this.defaultNames = Collections.singletonList(name);
    }
}
