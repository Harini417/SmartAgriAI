package com.smartagri.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import java.util.Objects;

public class IrrigationRequest {

    @NotNull(message = "Soil moisture is required")
    @Min(value = 0, message = "Soil moisture must be at least 0%")
    @Max(value = 100, message = "Soil moisture cannot exceed 100%")
    private Double soilMoisture;

    @NotNull(message = "Temperature is required")
    private Double temperature;

    @NotNull(message = "Humidity is required")
    @Min(value = 0, message = "Humidity must be at least 0%")
    @Max(value = 100, message = "Humidity cannot exceed 100%")
    private Double humidity;

    @NotNull(message = "Rainfall is required")
    @Min(value = 0, message = "Rainfall must be non-negative")
    private Double rainfall;

    @NotBlank(message = "Crop type is required")
    private String cropType;

    @NotBlank(message = "Soil type is required")
    private String soilType;

    public IrrigationRequest() {
    }

    public IrrigationRequest(Double soilMoisture, Double temperature, Double humidity,
                            Double rainfall, String cropType, String soilType) {
        this.soilMoisture = soilMoisture;
        this.temperature = temperature;
        this.humidity = humidity;
        this.rainfall = rainfall;
        this.cropType = cropType;
        this.soilType = soilType;
    }

    public Double getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(Double soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getRainfall() {
        return rainfall;
    }

    public void setRainfall(Double rainfall) {
        this.rainfall = rainfall;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IrrigationRequest that = (IrrigationRequest) o;
        return Objects.equals(soilMoisture, that.soilMoisture) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(cropType, that.cropType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(soilMoisture, temperature, cropType);
    }

    @Override
    public String toString() {
        return "IrrigationRequest{" +
                "soilMoisture=" + soilMoisture +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", rainfall=" + rainfall +
                ", cropType='" + cropType + '\'' +
                ", soilType='" + soilType + '\'' +
                '}';
    }
}
