package fr.seblaporte.DTO.domoticz;

import lombok.Data;

import java.util.List;

@Data
public class DomoticzPlanResponse {

    private List<DomoticzPlan> result;

    private String status;

    private String title;
}
