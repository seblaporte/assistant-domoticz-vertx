package fr.seblaporte.DTO.google;

import lombok.Data;

@Data
public class DeviceInfoDTO {

    /** Especially useful when the partner is a hub for other devices. */
    private String manufacturer;

    /** The model or SKU identifier of the particular device. */
    private String model;

    /** Specific version number attached to the hardware if available. */
    private String hwVersion;

    /** Specific version number attached to the software/firmware, if available. */
    private String swVersion;

    public DeviceInfoDTO() {
        this.manufacturer = "unknown";
        this.model = "unknown";
        this.hwVersion = "unknown";
        this.swVersion = "unknown";
    }
}
