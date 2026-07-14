package com.smartagri.backend.dto;

import java.util.Objects;

public class CropRecommendationResponse {

    private String recommendedCrop;
    private Double confidenceScore;
    private String reason;
    private String irrigationAdvice;
    private String fertilizerRecommendation;

    public CropRecommendationResponse() {
    }

    public CropRecommendationResponse(String recommendedCrop, Double confidenceScore, String reason,
                                      String irrigationAdvice, String fertilizerRecommendation) {
        this.recommendedCrop = recommendedCrop;
        this.confidenceScore = confidenceScore;
        this.reason = reason;
        this.irrigationAdvice = irrigationAdvice;
        this.fertilizerRecommendation = fertilizerRecommendation;
    }

    public String getRecommendedCrop() {
        return recommendedCrop;
    }

    public void setRecommendedCrop(String recommendedCrop) {
        this.recommendedCrop = recommendedCrop;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getIrrigationAdvice() {
        return irrigationAdvice;
    }

    public void setIrrigationAdvice(String irrigationAdvice) {
        this.irrigationAdvice = irrigationAdvice;
    }

    public String getFertilizerRecommendation() {
        return fertilizerRecommendation;
    }

    public void setFertilizerRecommendation(String fertilizerRecommendation) {
        this.fertilizerRecommendation = fertilizerRecommendation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CropRecommendationResponse that = (CropRecommendationResponse) o;
        return Objects.equals(recommendedCrop, that.recommendedCrop) &&
                Objects.equals(confidenceScore, that.confidenceScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recommendedCrop, confidenceScore);
    }

    @Override
    public String toString() {
        return "CropRecommendationResponse{" +
                "recommendedCrop='" + recommendedCrop + '\'' +
                ", confidenceScore=" + confidenceScore +
                ", reason='" + reason + '\'' +
                ", irrigationAdvice='" + irrigationAdvice + '\'' +
                ", fertilizerRecommendation='" + fertilizerRecommendation + '\'' +
                '}';
    }
}
