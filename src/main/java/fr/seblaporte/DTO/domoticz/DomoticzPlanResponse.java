package fr.seblaporte.DTO.domoticz;

import java.util.List;

public class DomoticzPlanResponse {

    private List<DomoticzPlan> result;

    private String status;

    private String title;

    public List<DomoticzPlan> getResult() {
        return result;
    }

    public void setResult(List<DomoticzPlan> result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
