package com.smartagri.backend.dto;

import java.util.Objects;

public class IrrigationResponse {

    private Boolean irrigationNeeded;
    private Double waterAmountLiters;
    private String irrigationMethod;
    private String irrigationTime;
    private String reason;
    private String priority; // LOW, MEDIUM, HIGH

    public IrrigationResponse() {
    }

    public IrrigationResponse(Boolean irrigationNeeded, Double waterAmountLiters,
                             String irrigationMethod, String irrigationTime,
                             String reason, String priority) {
        this.irrigationNeeded = irrigationNeeded;
        this.waterAmountLiters = waterAmountLiters;
        this.irrigationMethod = irrigationMethod;
        this.irrigationTime = irrigationTime;
        this.reason = reason;
        this.priority = priority;
    }

    public Boolean getIrrigationNeeded() {
        return irrigationNeeded;
    }

    public void setIrrigationNeeded(Boolean irrigationNeeded) {
        this.irrigationNeeded = irrigationNeeded;
    }

    public Double getWaterAmountLiters() {
        return waterAmountLiters;
    }

    public void setWaterAmountLiters(Double waterAmountLiters) {
        this.waterAmountLiters = waterAmountLiters;
    }

    public String getIrrigationMethod() {
        return irrigationMethod;
    }

    public void setIrrigationMethod(String irrigationMethod) {
        this.irrigationMethod = irrigationMethod;
    }

    public String getIrrigationTime() {
        return irrigationTime;
    }

    public void setIrrigationTime(String irrigationTime) {
        this.irrigationTime = irrigationTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IrrigationResponse that = (IrrigationResponse) o;
        return Objects.equals(irrigationNeeded, that.irrigationNeeded) &&
                Objects.equals(waterAmountLiters, that.waterAmountLiters) &&
                Objects.equals(priority, that.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(irrigationNeeded, waterAmountLiters, priority);
    }

    @Override
    public String toString() {
        return "IrrigationResponse{" +
                "irrigationNeeded=" + irrigationNeeded +
                ", waterAmountLiters=" + waterAmountLiters +
                ", irrigationMethod='" + irrigationMethod + '\'' +
                ", irrigationTime='" + irrigationTime + '\'' +
                ", reason='" + reason + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
