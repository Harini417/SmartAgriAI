package com.smartagri.backend.service;

import com.smartagri.backend.dto.CropRecommendationRequest;
import com.smartagri.backend.dto.CropRecommendationResponse;
import org.springframework.stereotype.Service;

@Service
public class CropRecommendationService {

    /**
     * Get AI-based crop recommendation based on soil and weather parameters
     * @param request The crop recommendation request with soil and weather data
     * @return CropRecommendationResponse with recommended crop and advice
     */
    public CropRecommendationResponse getCropRecommendation(CropRecommendationRequest request) {
        validateRequest(request);

        // Calculate match scores for each crop
        double riceScore = calculateRiceScore(request);
        double wheatScore = calculateWheatScore(request);
        double maizeScore = calculateMaizeScore(request);
        double cottonScore = calculateCottonScore(request);
        double sugarcaneScore = calculateSugarcaneScore(request);
        double milletScore = calculateMilletScore(request);
        double groundnutScore = calculateGroundnutScore(request);
        double tomatoScore = calculateTomatoScore(request);
        double potatoScore = calculatePotatoScore(request);
        double onionScore = calculateOnionScore(request);

        // Find the crop with highest score
        double maxScore = Math.max(
                Math.max(riceScore, Math.max(wheatScore, maizeScore)),
                Math.max(Math.max(cottonScore, sugarcaneScore), 
                         Math.max(Math.max(milletScore, groundnutScore), 
                                Math.max(tomatoScore, Math.max(potatoScore, onionScore))))
        );

        return buildRecommendation(request, maxScore, riceScore, wheatScore, maizeScore, 
                                  cottonScore, sugarcaneScore, milletScore, groundnutScore, 
                                  tomatoScore, potatoScore, onionScore);
    }

    /**
     * Calculate match score for Rice
     * Rice → High rainfall, high moisture, pH 5.5–6.5
     */
    private double calculateRiceScore(CropRecommendationRequest request) {
        double score = 0;
        
        if (request.getRainfall() > 100) score += 25;
        else if (request.getRainfall() > 60) score += 15;
        
        if (request.getHumidity() > 70) score += 25;
        else if (request.getHumidity() > 60) score += 15;
        
        if (request.getSoilPh() >= 5.5 && request.getSoilPh() <= 6.5) score += 25;
        else if (request.getSoilPh() >= 5.0 && request.getSoilPh() <= 7.0) score += 15;
        
        if (request.getNitrogenLevel() > 30) score += 15;
        
        return score;
    }

    /**
     * Calculate match score for Wheat
     * Wheat → Moderate rainfall, pH 6.0–7.0
     */
    private double calculateWheatScore(CropRecommendationRequest request) {
        double score = 0;
        
        if (request.getRainfall() >= 40 && request.getRainfall() <= 100) score += 25;
        else if (request.getRainfall() > 30) score += 10;
        
        if (request.getSoilPh() >= 6.0 && request.getSoilPh() <= 7.0) score += 30;
        else if (request.getSoilPh() >= 5.5 && request.getSoilPh() <= 7.5) score += 15;
        
        if (request.getTemperature() >= 15 && request.getTemperature() <= 25) score += 20;
        
        if (request.getNitrogenLevel() > 20) score += 15;
        
        return score;
    }

    /**
     * Calculate match score for Maize
     * Maize → Warm temperature, medium rainfall
     */
    private double calculateMaizeScore(CropRecommendationRequest request) {
        double score = 0;
        
        if (request.getTemperature() >= 20 && request.getTemperature() <= 30) score += 30;
        else if (request.getTemperature() >= 18 && request.getTemperature() <= 32) score += 15;
        
        if (request.getRainfall() >= 50 && request.getRainfall() <= 100) score += 25;
        else if (request.getRainfall() > 40) score += 10;
        
        if (request.getNitrogenLevel() > 25) score += 20;
        
        if (request.getPhosphorusLevel() > 15) score += 15;
        
        return score;
    }

    /**
     * Calculate match score for Cotton
     * Cotton → Low rainfall, high temperature
     */
    private double calculateCottonScore(CropRecommendationRequest request) {
        double score = 0;
        
        if (request.getRainfall() < 60) score += 30;
        else if (request.getRainfall() <= 80) score += 15;
        
        if (request.getTemperature() >= 25 && request.getTemperature() <= 35) score += 30;
        else if (request.getTemperature() >= 23) score += 15;
        
        if (request.getHumidity() < 60) score += 15;
        
        if (request.getPotassiumLevel() > 20) score += 15;
        
        return score;
    }

    /**
     * Calculate match score for Sugarcane
     * Sugarcane → High water requirement
     */
    private double calculateSugarcaneScore(CropRecommendationRequest request) {
        double score = 0;
        
        if (request.getRainfall() > 100) score += 30;
        else if (request.getRainfall() > 75) score += 20;
        
        if (request.getHumidity() > 70) score += 20;
        
        if (request.getTemperature() >= 20 && request.getTemperature() <= 30) score += 25;
        
        if (request.getNitrogenLevel() > 40) score += 15;
        
        if (request.getSoilPh() >= 5.5 && request.getSoilPh() <= 8.0) score += 10;
        
        return score;
    }

    /**
     * Calculate match score for Millet
     * Millet → Dry climate
     */
    private double calculateMilletScore(CropRecommendationRequest request) {
        double score = 0;
        
        if (request.getRainfall() < 50) score += 35;
        else if (request.getRainfall() <= 70) score += 15;
        
        if (request.getHumidity() < 60) score += 25;
        
        if (request.getTemperature() >= 25 && request.getTemperature() <= 35) score += 20;
        
        if (request.getSoilPh() >= 5.0 && request.getSoilPh() <= 8.0) score += 10;
        
        return score;
    }

    /**
     * Calculate match score for Groundnut
     * Groundnut → Sandy soil, moderate rainfall
     */
    private double calculateGroundnutScore(CropRecommendationRequest request) {
        double score = 0;
        
        if (request.getRainfall() >= 40 && request.getRainfall() <= 90) score += 25;
        
        if (request.getTemperature() >= 22 && request.getTemperature() <= 32) score += 25;
        
        if (request.getNitrogenLevel() > 15) score += 20;
        
        if (request.getPhosphorusLevel() > 15) score += 15;
        
        if (request.getPotassiumLevel() > 15) score += 15;
        
        return score;
    }

    /**
     * Calculate match score for Tomato
     * Tomato → Moderate temperature
     */
    private double calculateTomatoScore(CropRecommendationRequest request) {
        double score = 0;
        
        if (request.getTemperature() >= 20 && request.getTemperature() <= 28) score += 35;
        else if (request.getTemperature() >= 18 && request.getTemperature() <= 30) score += 20;
        
        if (request.getNitrogenLevel() > 30) score += 20;
        
        if (request.getPhosphorusLevel() > 20) score += 15;
        
        if (request.getHumidity() >= 50 && request.getHumidity() <= 80) score += 15;
        
        if (request.getRainfall() >= 40 && request.getRainfall() <= 90) score += 10;
        
        return score;
    }

    /**
     * Calculate match score for Potato
     * Potato → Cool climate
     */
    private double calculatePotatoScore(CropRecommendationRequest request) {
        double score = 0;
        
        if (request.getTemperature() >= 10 && request.getTemperature() <= 20) score += 35;
        else if (request.getTemperature() >= 8 && request.getTemperature() <= 22) score += 20;
        
        if (request.getNitrogenLevel() > 40) score += 20;
        
        if (request.getPotassiumLevel() > 30) score += 20;
        
        if (request.getHumidity() >= 60 && request.getHumidity() <= 85) score += 15;
        
        return score;
    }

    /**
     * Calculate match score for Onion
     * Onion → Well-drained soil
     */
    private double calculateOnionScore(CropRecommendationRequest request) {
        double score = 0;
        
        if (request.getTemperature() >= 12 && request.getTemperature() <= 22) score += 25;
        
        if (request.getNitrogenLevel() > 35) score += 25;
        
        if (request.getPhosphorusLevel() > 20) score += 20;
        
        if (request.getRainfall() >= 40 && request.getRainfall() <= 80) score += 15;
        
        if (request.getHumidity() >= 50 && request.getHumidity() <= 75) score += 15;
        
        return score;
    }

    /**
     * Build the recommendation response based on calculated scores
     */
    private CropRecommendationResponse buildRecommendation(CropRecommendationRequest request, 
                                                           double maxScore,
                                                           double riceScore, double wheatScore,
                                                           double maizeScore, double cottonScore,
                                                           double sugarcaneScore, double milletScore,
                                                           double groundnutScore, double tomatoScore,
                                                           double potatoScore, double onionScore) {
        String recommendedCrop;
        String reason;
        String irrigationAdvice;
        String fertilizerRecommendation;
        double confidenceScore = (maxScore / 100.0) * 100; // Normalize to 0-100

        if (maxScore == riceScore) {
            recommendedCrop = "Rice";
            reason = "High rainfall and humidity match rice requirements. pH is in optimal range for rice cultivation.";
            irrigationAdvice = "Maintain water level of 5-10 cm throughout the growing season. Continuous flooding is recommended.";
            fertilizerRecommendation = "Apply 100 kg N, 50 kg P, 40 kg K per hectare. Use split application of nitrogen for better yield.";
        } else if (maxScore == wheatScore) {
            recommendedCrop = "Wheat";
            reason = "Moderate rainfall and soil pH are ideal for wheat cultivation. Temperature range is suitable.";
            irrigationAdvice = "Provide 4-5 irrigations during the growing season. Critical stages are tillering and grain filling.";
            fertilizerRecommendation = "Apply 80 kg N, 40 kg P, 30 kg K per hectare. Use DAP at sowing and urea for top dressing.";
        } else if (maxScore == maizeScore) {
            recommendedCrop = "Maize";
            reason = "Warm temperature and moderate rainfall are perfect for maize. Good nitrogen content available.";
            irrigationAdvice = "Require 3-4 irrigations. Critical periods are tasseling and silking stages.";
            fertilizerRecommendation = "Apply 120 kg N, 60 kg P, 40 kg K per hectare. Use 50% N at sowing and 50% at V6 stage.";
        } else if (maxScore == cottonScore) {
            recommendedCrop = "Cotton";
            reason = "Low rainfall and high temperature are favorable for cotton. Humidity levels support growth.";
            irrigationAdvice = "Provide 8-12 irrigations during the growing season. Drip irrigation is recommended for efficiency.";
            fertilizerRecommendation = "Apply 100 kg N, 50 kg P, 50 kg K per hectare. Ensure adequate potassium for fiber quality.";
        } else if (maxScore == sugarcaneScore) {
            recommendedCrop = "Sugarcane";
            reason = "High rainfall and warm temperature support vigorous sugarcane growth. High nitrogen requirement can be met.";
            irrigationAdvice = "Sugarcane requires 5-6 irrigations or 1200-1500 mm annual rainfall. Furrow irrigation is ideal.";
            fertilizerRecommendation = "Apply 120-150 kg N, 60 kg P, 60 kg K per hectare. Divided doses during growing season are beneficial.";
        } else if (maxScore == milletScore) {
            recommendedCrop = "Millet";
            reason = "Dry climate with low rainfall makes millet suitable. Extreme drought tolerance matches conditions.";
            irrigationAdvice = "Millet is drought-tolerant. Provide 1-2 irrigations during critical growth stages if available.";
            fertilizerRecommendation = "Apply 40 kg N, 20 kg P, 20 kg K per hectare. Organic manure supplementation is beneficial.";
        } else if (maxScore == groundnutScore) {
            recommendedCrop = "Groundnut";
            reason = "Moderate rainfall and temperature are ideal for groundnut. Soil nutrient balance is suitable.";
            irrigationAdvice = "Provide 3-4 irrigations. Critical stages are flowering and pod development for irrigation.";
            fertilizerRecommendation = "Apply 20 kg N, 40 kg P, 40 kg K per hectare. Gypsum application (400 kg/ha) is recommended.";
        } else if (maxScore == tomatoScore) {
            recommendedCrop = "Tomato";
            reason = "Moderate temperature conditions are perfect for tomato cultivation. Adequate nutrients available.";
            irrigationAdvice = "Provide regular irrigation, 15-20 mm per week. Drip irrigation recommended to prevent diseases.";
            fertilizerRecommendation = "Apply 80 kg N, 50 kg P, 60 kg K per hectare. Mulching helps retain soil moisture and nutrients.";
        } else if (maxScore == potatoScore) {
            recommendedCrop = "Potato";
            reason = "Cool temperature range is optimal for potato cultivation. High potassium availability supports tuber development.";
            irrigationAdvice = "Provide 4-5 irrigations. Critical periods are 45-90 days after planting for irrigation scheduling.";
            fertilizerRecommendation = "Apply 150 kg N, 50 kg P, 100 kg K per hectare. Potassium is crucial for potato quality.";
        } else {
            recommendedCrop = "Onion";
            reason = "Moderate temperature and good nutrient availability support onion growth. Rainfall is in acceptable range.";
            irrigationAdvice = "Provide 10-12 irrigations during the growing season. Maintain consistent soil moisture without waterlogging.";
            fertilizerRecommendation = "Apply 100 kg N, 50 kg P, 40 kg K per hectare. Apply nitrogen in splits at 30 and 60 days after planting.";
        }

        return new CropRecommendationResponse(
                recommendedCrop,
                confidenceScore,
                reason,
                irrigationAdvice,
                fertilizerRecommendation
        );
    }

    /**
     * Validate the incoming request
     */
    private void validateRequest(CropRecommendationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
    }
}
