package com.smartagri.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import java.util.Objects;

public class YieldPredictionRequest {

    @NotBlank(message = "Crop name is required")
    private String cropName;

    @NotNull(message = "Farm area is required")
    @Positive(message = "Farm area must be positive")
    private Double farmArea;

    @NotNull(message = "Soil pH is required")
    @Min(value = 0, message = "Soil pH must be at least 0")
    @Max(value = 14, message = "Soil pH cannot exceed 14")
    private Double soilPh;

    @NotNull(message = "Nitrogen level is required")
    @Positive(message = "Nitrogen level must be positive")
    private Double nitrogenLevel;

    @NotNull(message = "Phosphorus level is required")
    @Positive(message = "Phosphorus level must be positive")
    private Double phosphorusLevel;

    @NotNull(message = "Potassium level is required")
    @Positive(message = "Potassium level must be positive")
    private Double potassiumLevel;

    @NotNull(message = "Temperature is required")
    private Double temperature;

    @NotNull(message = "Humidity is required")
    @Min(value = 0, message = "Humidity must be at least 0%")
    @Max(value = 100, message = "Humidity cannot exceed 100%")
    private Double humidity;

    @NotNull(message = "Rainfall is required")
    @Positive(message = "Rainfall must be positive")
    private Double rainfall;

    public YieldPredictionRequest() {
    }

    public YieldPredictionRequest(String cropName, Double farmArea, Double soilPh,
                                 Double nitrogenLevel, Double phosphorusLevel, Double potassiumLevel,
                                 Double temperature, Double humidity, Double rainfall) {
        this.cropName = cropName;
        this.farmArea = farmArea;
        this.soilPh = soilPh;
        this.nitrogenLevel = nitrogenLevel;
        this.phosphorusLevel = phosphorusLevel;
        this.potassiumLevel = potassiumLevel;
        this.temperature = temperature;
        this.humidity = humidity;
        this.rainfall = rainfall;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public Double getFarmArea() {
        return farmArea;
    }

    public void setFarmArea(Double farmArea) {
        this.farmArea = farmArea;
    }

    public Double getSoilPh() {
        return soilPh;
    }

    public void setSoilPh(Double soilPh) {
        this.soilPh = soilPh;
    }

    public Double getNitrogenLevel() {
        return nitrogenLevel;
    }

    public void setNitrogenLevel(Double nitrogenLevel) {
        this.nitrogenLevel = nitrogenLevel;
    }

    public Double getPhosphorusLevel() {
        return phosphorusLevel;
    }

    public void setPhosphorusLevel(Double phosphorusLevel) {
        this.phosphorusLevel = phosphorusLevel;
    }

    public Double getPotassiumLevel() {
        return potassiumLevel;
    }

    public void setPotassiumLevel(Double potassiumLevel) {
        this.potassiumLevel = potassiumLevel;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YieldPredictionRequest that = (YieldPredictionRequest) o;
        return Objects.equals(cropName, that.cropName) &&
                Objects.equals(farmArea, that.farmArea) &&
                Objects.equals(soilPh, that.soilPh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cropName, farmArea, soilPh);
    }

    @Override
    public String toString() {
        return "YieldPredictionRequest{" +
                "cropName='" + cropName + '\'' +
                ", farmArea=" + farmArea +
                ", soilPh=" + soilPh +
                ", nitrogenLevel=" + nitrogenLevel +
                ", phosphorusLevel=" + phosphorusLevel +
                ", potassiumLevel=" + potassiumLevel +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", rainfall=" + rainfall +
                '}';
    }
}
