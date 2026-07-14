package com.smartagri.backend.dto;

import java.util.Objects;

public class DashboardResponse {

    private Long totalCrops;
    private Long totalSoilRecords;
    private Long healthySoilCount;
    private Long unhealthySoilCount;
    private Double averageSoilPh;
    private Double averageTemperature;
    private Double averageMoisture;
    private String mostCultivatedCrop;
    private String weatherStatus;
    private String recommendedCrop;

    public DashboardResponse() {
    }

    public DashboardResponse(Long totalCrops, Long totalSoilRecords, Long healthySoilCount,
                            Long unhealthySoilCount, Double averageSoilPh, Double averageTemperature,
                            Double averageMoisture, String mostCultivatedCrop, String weatherStatus,
                            String recommendedCrop) {
        this.totalCrops = totalCrops;
        this.totalSoilRecords = totalSoilRecords;
        this.healthySoilCount = healthySoilCount;
        this.unhealthySoilCount = unhealthySoilCount;
        this.averageSoilPh = averageSoilPh;
        this.averageTemperature = averageTemperature;
        this.averageMoisture = averageMoisture;
        this.mostCultivatedCrop = mostCultivatedCrop;
        this.weatherStatus = weatherStatus;
        this.recommendedCrop = recommendedCrop;
    }

    public Long getTotalCrops() {
        return totalCrops;
    }

    public void setTotalCrops(Long totalCrops) {
        this.totalCrops = totalCrops;
    }

    public Long getTotalSoilRecords() {
        return totalSoilRecords;
    }

    public void setTotalSoilRecords(Long totalSoilRecords) {
        this.totalSoilRecords = totalSoilRecords;
    }

    public Long getHealthySoilCount() {
        return healthySoilCount;
    }

    public void setHealthySoilCount(Long healthySoilCount) {
        this.healthySoilCount = healthySoilCount;
    }

    public Long getUnhealthySoilCount() {
        return unhealthySoilCount;
    }

    public void setUnhealthySoilCount(Long unhealthySoilCount) {
        this.unhealthySoilCount = unhealthySoilCount;
    }

    public Double getAverageSoilPh() {
        return averageSoilPh;
    }

    public void setAverageSoilPh(Double averageSoilPh) {
        this.averageSoilPh = averageSoilPh;
    }

    public Double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(Double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public Double getAverageMoisture() {
        return averageMoisture;
    }

    public void setAverageMoisture(Double averageMoisture) {
        this.averageMoisture = averageMoisture;
    }

    public String getMostCultivatedCrop() {
        return mostCultivatedCrop;
    }

    public void setMostCultivatedCrop(String mostCultivatedCrop) {
        this.mostCultivatedCrop = mostCultivatedCrop;
    }

    public String getWeatherStatus() {
        return weatherStatus;
    }

    public void setWeatherStatus(String weatherStatus) {
        this.weatherStatus = weatherStatus;
    }

    public String getRecommendedCrop() {
        return recommendedCrop;
    }

    public void setRecommendedCrop(String recommendedCrop) {
        this.recommendedCrop = recommendedCrop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DashboardResponse that = (DashboardResponse) o;
        return Objects.equals(totalCrops, that.totalCrops) &&
                Objects.equals(totalSoilRecords, that.totalSoilRecords) &&
                Objects.equals(mostCultivatedCrop, that.mostCultivatedCrop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCrops, totalSoilRecords, mostCultivatedCrop);
    }

    @Override
    public String toString() {
        return "DashboardResponse{" +
                "totalCrops=" + totalCrops +
                ", totalSoilRecords=" + totalSoilRecords +
                ", healthySoilCount=" + healthySoilCount +
                ", unhealthySoilCount=" + unhealthySoilCount +
                ", averageSoilPh=" + averageSoilPh +
                ", averageTemperature=" + averageTemperature +
                ", averageMoisture=" + averageMoisture +
                ", mostCultivatedCrop='" + mostCultivatedCrop + '\'' +
                ", weatherStatus='" + weatherStatus + '\'' +
                ", recommendedCrop='" + recommendedCrop + '\'' +
                '}';
    }
}
