package fr.seblaporte.DTO.google;

import lombok.Data;

import java.util.List;

@Data
public class Capability {

    private List<DeviceTraitEnum> deviceTraits;

    private DeviceTypeEnum deviceType;
}
