package com.smartagri.backend.service;

import com.smartagri.backend.dto.YieldPredictionRequest;
import com.smartagri.backend.dto.YieldPredictionResponse;
import org.springframework.stereotype.Service;

@Service
public class YieldPredictionService {

    private static final double OPTIMAL_NPK = 50.0;
    private static final double IDEAL_PH_MIN = 6.0;
    private static final double IDEAL_PH_MAX = 7.5;

    /**
     * Predict crop yield based on soil and environmental parameters
     * @param request The yield prediction request with crop and environmental data
     * @return YieldPredictionResponse with expected yield and productivity assessment
     */
    public YieldPredictionResponse predictYield(YieldPredictionRequest request) {
        validateRequest(request);

        // Calculate base yield using crop-specific factors
        double baseYield = calculateBaseYield(request);

        // Calculate soil quality score (0-100)
        double soilQualityScore = calculateSoilQualityScore(request);

        // Calculate environmental suitability score (0-100)
        double environmentalScore = calculateEnvironmentalScore(request);

        // Calculate overall productivity score
        double overallProductivityScore = (soilQualityScore + environmentalScore) / 2.0;

        // Adjust yield based on quality and environmental factors
        double adjustedYield = adjustYield(baseYield, soilQualityScore, environmentalScore, request.getFarmArea());

        // Determine productivity level
        String productivityLevel = determineProductivityLevel(overallProductivityScore);

        // Build recommendations
        String recommendations = buildRecommendations(request, soilQualityScore, environmentalScore);

        return new YieldPredictionResponse(
                (double) Math.round(adjustedYield * 100.0) / 100.0,
                "kg/hectare",
                (double) Math.round(overallProductivityScore),
                productivityLevel,
                recommendations
        );
    }

    /**
     * Calculate base yield using crop-specific typical yields
     */
    private double calculateBaseYield(YieldPredictionRequest request) {
        String crop = request.getCropName().toLowerCase();

        // Typical yields per hectare (in kg)
        if (crop.contains("rice")) {
            return 5000.0;
        } else if (crop.contains("wheat")) {
            return 4500.0;
        } else if (crop.contains("maize")) {
            return 6000.0;
        } else if (crop.contains("cotton")) {
            return 2000.0;
        } else if (crop.contains("sugarcane")) {
            return 70000.0;
        } else if (crop.contains("potato")) {
            return 20000.0;
        } else if (crop.contains("tomato")) {
            return 40000.0;
        } else if (crop.contains("onion")) {
            return 35000.0;
        } else if (crop.contains("groundnut")) {
            return 2000.0;
        } else if (crop.contains("millet")) {
            return 1500.0;
        } else {
            return 3000.0; // Default yield
        }
    }

    /**
     * Calculate soil quality score based on pH and NPK levels
     */
    private double calculateSoilQualityScore(YieldPredictionRequest request) {
        double score = 0;

        // pH scoring (optimal 6.0-7.5)
        if (request.getSoilPh() >= IDEAL_PH_MIN && request.getSoilPh() <= IDEAL_PH_MAX) {
            score += 35;
        } else if (request.getSoilPh() >= 5.5 && request.getSoilPh() <= 8.0) {
            score += 20;
        } else if (request.getSoilPh() >= 5.0 && request.getSoilPh() <= 8.5) {
            score += 10;
        }

        // Nitrogen scoring (optimal > 50 mg/kg)
        if (request.getNitrogenLevel() > OPTIMAL_NPK) {
            score += 25;
        } else if (request.getNitrogenLevel() > 30) {
            score += 15;
        } else if (request.getNitrogenLevel() > 15) {
            score += 8;
        }

        // Phosphorus scoring (optimal > 50 mg/kg)
        if (request.getPhosphorusLevel() > OPTIMAL_NPK) {
            score += 20;
        } else if (request.getPhosphorusLevel() > 30) {
            score += 12;
        } else if (request.getPhosphorusLevel() > 15) {
            score += 6;
        }

        // Potassium scoring (optimal > 50 mg/kg)
        if (request.getPotassiumLevel() > OPTIMAL_NPK) {
            score += 20;
        } else if (request.getPotassiumLevel() > 30) {
            score += 12;
        } else if (request.getPotassiumLevel() > 15) {
            score += 6;
        }

        return Math.min(score, 100.0);
    }

    /**
     * Calculate environmental suitability score based on temperature, humidity, rainfall
     */
    private double calculateEnvironmentalScore(YieldPredictionRequest request) {
        double score = 0;

        // Temperature scoring (varies by crop)
        double tempScore = calculateTemperatureScore(request);
        score += tempScore;

        // Humidity scoring
        if (request.getHumidity() >= 40 && request.getHumidity() <= 80) {
            score += 25;
        } else if (request.getHumidity() >= 30 && request.getHumidity() <= 90) {
            score += 15;
        } else if (request.getHumidity() >= 20 && request.getHumidity() <= 95) {
            score += 8;
        }

        // Rainfall scoring
        double rainfallScore = calculateRainfallScore(request);
        score += rainfallScore;

        return Math.min(score, 100.0);
    }

    /**
     * Calculate temperature score based on crop requirements
     */
    private double calculateTemperatureScore(YieldPredictionRequest request) {
        String crop = request.getCropName().toLowerCase();
        double temp = request.getTemperature();

        if (crop.contains("rice")) {
            if (temp >= 20 && temp <= 32) return 30;
            if (temp >= 18 && temp <= 35) return 15;
            return 5;
        } else if (crop.contains("wheat")) {
            if (temp >= 15 && temp <= 25) return 30;
            if (temp >= 12 && temp <= 28) return 15;
            return 5;
        } else if (crop.contains("maize")) {
            if (temp >= 20 && temp <= 30) return 30;
            if (temp >= 18 && temp <= 32) return 15;
            return 5;
        } else if (crop.contains("cotton")) {
            if (temp >= 25 && temp <= 35) return 30;
            if (temp >= 23 && temp <= 37) return 15;
            return 5;
        } else if (crop.contains("potato")) {
            if (temp >= 10 && temp <= 20) return 30;
            if (temp >= 8 && temp <= 22) return 15;
            return 5;
        } else if (crop.contains("tomato")) {
            if (temp >= 20 && temp <= 28) return 30;
            if (temp >= 18 && temp <= 30) return 15;
            return 5;
        } else {
            // Default temperature range
            if (temp >= 15 && temp <= 30) return 30;
            if (temp >= 10 && temp <= 35) return 15;
            return 5;
        }
    }

    /**
     * Calculate rainfall score based on crop water requirements
     */
    private double calculateRainfallScore(YieldPredictionRequest request) {
        String crop = request.getCropName().toLowerCase();
        double rainfall = request.getRainfall();

        if (crop.contains("rice")) {
            if (rainfall >= 100 && rainfall <= 150) return 30;
            if (rainfall >= 80 && rainfall <= 160) return 15;
            return 5;
        } else if (crop.contains("wheat")) {
            if (rainfall >= 50 && rainfall <= 100) return 30;
            if (rainfall >= 40 && rainfall <= 110) return 15;
            return 5;
        } else if (crop.contains("maize")) {
            if (rainfall >= 60 && rainfall <= 100) return 30;
            if (rainfall >= 50 && rainfall <= 120) return 15;
            return 5;
        } else if (crop.contains("cotton")) {
            if (rainfall >= 40 && rainfall <= 80) return 30;
            if (rainfall >= 30 && rainfall <= 90) return 15;
            return 5;
        } else if (crop.contains("potato")) {
            if (rainfall >= 50 && rainfall <= 90) return 30;
            if (rainfall >= 40 && rainfall <= 100) return 15;
            return 5;
        } else if (crop.contains("millet")) {
            if (rainfall >= 20 && rainfall <= 50) return 30;
            if (rainfall >= 15 && rainfall <= 60) return 15;
            return 5;
        } else {
            // Default rainfall range
            if (rainfall >= 50 && rainfall <= 100) return 30;
            if (rainfall >= 40 && rainfall <= 120) return 15;
            return 5;
        }
    }

    /**
     * Adjust yield based on quality and environmental factors
     */
    private double adjustYield(double baseYield, double soilQuality, double environmental, double farmArea) {
        // Apply quality adjustment (soil and environmental factors)
        double qualityMultiplier = (soilQuality + environmental) / 200.0; // 0 to 1.0
        double adjustedYield = baseYield * qualityMultiplier;

        // Apply farm area normalization (yield is per hectare, so multiply by area)
        adjustedYield = adjustedYield * farmArea;

        return adjustedYield;
    }

    /**
     * Determine productivity level based on overall score
     */
    private String determineProductivityLevel(double overallScore) {
        if (overallScore >= 80) {
            return "EXCELLENT";
        } else if (overallScore >= 60) {
            return "HIGH";
        } else if (overallScore >= 40) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }

    /**
     * Build detailed recommendations based on analysis
     */
    private String buildRecommendations(YieldPredictionRequest request, double soilScore, double envScore) {
        StringBuilder recommendations = new StringBuilder();

        // Soil recommendations
        if (request.getSoilPh() < IDEAL_PH_MIN) {
            recommendations.append("Soil is too acidic. Add lime to increase pH. ");
        } else if (request.getSoilPh() > IDEAL_PH_MAX) {
            recommendations.append("Soil is too alkaline. Add sulfur or acidifying fertilizers. ");
        }

        if (request.getNitrogenLevel() < OPTIMAL_NPK) {
            recommendations.append("Nitrogen levels are low. Apply nitrogenous fertilizers (Urea or DAP). ");
        }

        if (request.getPhosphorusLevel() < OPTIMAL_NPK) {
            recommendations.append("Phosphorus levels are low. Apply phosphate fertilizers. ");
        }

        if (request.getPotassiumLevel() < OPTIMAL_NPK) {
            recommendations.append("Potassium levels are low. Apply potash fertilizers. ");
        }

        // Environmental recommendations
        if (request.getTemperature() < 10 || request.getTemperature() > 35) {
            recommendations.append("Temperature is outside optimal range. Consider crop protection or early planting. ");
        }

        if (request.getHumidity() < 40) {
            recommendations.append("Low humidity detected. Implement irrigation to increase soil moisture. ");
        } else if (request.getHumidity() > 85) {
            recommendations.append("High humidity increases disease risk. Ensure proper ventilation and drainage. ");
        }

        if (request.getRainfall() < 30) {
            recommendations.append("Rainfall is below recommended levels. Plan supplemental irrigation. ");
        } else if (request.getRainfall() > 150) {
            recommendations.append("Excessive rainfall detected. Ensure proper drainage to prevent waterlogging. ");
        }

        // General recommendations for high productivity
        if (soilScore >= 70 && envScore >= 70) {
            recommendations.append("Excellent conditions! Maintain current practices and monitor crop health. ");
        }

        // Crop-specific recommendations
        String crop = request.getCropName().toLowerCase();
        if (crop.contains("rice")) {
            recommendations.append("Rice thrives in high moisture. Maintain standing water during growing season. ");
        } else if (crop.contains("wheat")) {
            recommendations.append("Wheat needs well-drained soil. Avoid waterlogging. ");
        } else if (crop.contains("cotton")) {
            recommendations.append("Cotton is sensitive to excess moisture. Ensure good drainage and adequate aeration. ");
        }

        return recommendations.toString().trim().isEmpty() ? 
               "Conditions are suitable for crop growth. Continue with regular monitoring and maintenance." : 
               recommendations.toString().trim();
    }

    /**
     * Validate the incoming request
     */
    private void validateRequest(YieldPredictionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
    }
}
