package com.smartagri.backend.dto;

import java.util.Objects;

public class YieldPredictionResponse {

    private Double expectedYield;
    private String yieldUnit;
    private Double confidenceScore;
    private String productivityLevel;
    private String recommendations;

    public YieldPredictionResponse() {
    }

    public YieldPredictionResponse(Double expectedYield, String yieldUnit, Double confidenceScore,
                                  String productivityLevel, String recommendations) {
        this.expectedYield = expectedYield;
        this.yieldUnit = yieldUnit;
        this.confidenceScore = confidenceScore;
        this.productivityLevel = productivityLevel;
        this.recommendations = recommendations;
    }

    public Double getExpectedYield() {
        return expectedYield;
    }

    public void setExpectedYield(Double expectedYield) {
        this.expectedYield = expectedYield;
    }

    public String getYieldUnit() {
        return yieldUnit;
    }

    public void setYieldUnit(String yieldUnit) {
        this.yieldUnit = yieldUnit;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public String getProductivityLevel() {
        return productivityLevel;
    }

    public void setProductivityLevel(String productivityLevel) {
        this.productivityLevel = productivityLevel;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YieldPredictionResponse that = (YieldPredictionResponse) o;
        return Objects.equals(expectedYield, that.expectedYield) &&
                Objects.equals(productivityLevel, that.productivityLevel) &&
                Objects.equals(confidenceScore, that.confidenceScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expectedYield, productivityLevel, confidenceScore);
    }

    @Override
    public String toString() {
        return "YieldPredictionResponse{" +
                "expectedYield=" + expectedYield +
                ", yieldUnit='" + yieldUnit + '\'' +
                ", confidenceScore=" + confidenceScore +
                ", productivityLevel='" + productivityLevel + '\'' +
                ", recommendations='" + recommendations + '\'' +
                '}';
    }
}
