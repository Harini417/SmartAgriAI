package com.smartagri.backend.service;

import com.smartagri.backend.dto.IrrigationRequest;
import com.smartagri.backend.dto.IrrigationResponse;
import org.springframework.stereotype.Service;

@Service
public class IrrigationService {

    private static final double CRITICAL_MOISTURE_THRESHOLD = 30.0;
    private static final double MODERATE_MOISTURE_THRESHOLD = 50.0;
    private static final double RAINFALL_THRESHOLD = 20.0;
    private static final double HECTARE_TO_LITERS = 10000.0;

    /**
     * Get intelligent irrigation recommendation based on soil and environmental parameters
     * @param request The irrigation request with soil and weather data
     * @return IrrigationResponse with irrigation recommendation and details
     */
    public IrrigationResponse getIrrigationRecommendation(IrrigationRequest request) {
        validateRequest(request);

        // Check if irrigation is needed based on rainfall and soil moisture
        boolean irrigationNeeded = isIrrigationNeeded(request);

        if (!irrigationNeeded) {
            return buildNoIrrigationResponse(request);
        }

        // Calculate water requirement based on crop and soil type
        double waterAmount = calculateWaterRequirement(request);

        // Determine irrigation method based on crop and soil type
        String irrigationMethod = determineIrrigationMethod(request);

        // Determine optimal irrigation time
        String irrigationTime = determineIrrigationTime(request);

        // Determine priority based on soil moisture and temperature
        String priority = determinePriority(request);

        // Build reason message
        String reason = buildReasonMessage(request);

        return new IrrigationResponse(
                true,
                waterAmount,
                irrigationMethod,
                irrigationTime,
                reason,
                priority
        );
    }

    /**
     * Determine if irrigation is needed based on rainfall and soil moisture
     */
    private boolean isIrrigationNeeded(IrrigationRequest request) {
        // If rainfall is sufficient (> 20 mm), no irrigation needed
        if (request.getRainfall() > RAINFALL_THRESHOLD) {
            return false;
        }

        // If soil moisture is below critical threshold, irrigation is needed
        if (request.getSoilMoisture() < CRITICAL_MOISTURE_THRESHOLD) {
            return true;
        }

        // If soil moisture is between critical and moderate, consider crop type
        if (request.getSoilMoisture() < MODERATE_MOISTURE_THRESHOLD) {
            return isHighWaterCrop(request.getCropType());
        }

        return false;
    }

    /**
     * Check if crop requires high water
     */
    private boolean isHighWaterCrop(String cropType) {
        String crop = cropType.toLowerCase();
        return crop.contains("rice") || crop.contains("sugarcane") || 
               crop.contains("cotton") || crop.contains("tomato");
    }

    /**
     * Calculate water requirement in liters based on crop and soil type
     */
    private double calculateWaterRequirement(IrrigationRequest request) {
        double baseWater = 1000; // Base water requirement in liters per hectare

        // Adjust based on soil moisture deficit
        double moistureDeficit = MODERATE_MOISTURE_THRESHOLD - request.getSoilMoisture();
        double moistureAdjustment = (moistureDeficit / 100.0) * 1000;

        // Adjust based on crop type
        double cropAdjustment = getCropWaterAdjustment(request.getCropType());

        // Adjust based on soil type
        double soilAdjustment = getSoilWaterAdjustment(request.getSoilType());

        // Adjust based on temperature (evapotranspiration)
        double temperatureAdjustment = 0;
        if (request.getTemperature() > 30) {
            temperatureAdjustment = 200;
        } else if (request.getTemperature() > 25) {
            temperatureAdjustment = 100;
        }

        double totalWater = baseWater + moistureAdjustment + cropAdjustment + 
                           soilAdjustment + temperatureAdjustment;

        // Ensure minimum and maximum bounds
        return Math.max(500, Math.min(totalWater, 3000));
    }

    /**
     * Get water adjustment factor based on crop type
     */
    private double getCropWaterAdjustment(String cropType) {
        String crop = cropType.toLowerCase();

        if (crop.contains("rice")) {
            return 800; // Rice requires high water
        } else if (crop.contains("sugarcane")) {
            return 700; // Sugarcane requires high water
        } else if (crop.contains("cotton")) {
            return 400; // Cotton requires moderate water
        } else if (crop.contains("maize")) {
            return 300; // Maize requires moderate water
        } else if (crop.contains("tomato")) {
            return 350; // Tomato requires moderate water
        } else if (crop.contains("wheat")) {
            return 200; // Wheat requires low water
        } else if (crop.contains("potato")) {
            return 250; // Potato requires low-moderate water
        } else if (crop.contains("millet")) {
            return -200; // Millet is drought-tolerant
        } else if (crop.contains("groundnut")) {
            return 150; // Groundnut requires low water
        } else {
            return 0; // Default no adjustment
        }
    }

    /**
     * Get water adjustment factor based on soil type
     */
    private double getSoilWaterAdjustment(String soilType) {
        String soil = soilType.toLowerCase();

        if (soil.contains("sandy")) {
            return 300; // Sandy soil needs more frequent irrigation
        } else if (soil.contains("clay")) {
            return -200; // Clay soil retains more water
        } else if (soil.contains("loam")) {
            return 0; // Loam is ideal, no adjustment
        } else if (soil.contains("silt")) {
            return 50; // Slight adjustment for silt
        } else {
            return 0; // Default no adjustment
        }
    }

    /**
     * Determine the best irrigation method based on crop and soil type
     */
    private String determineIrrigationMethod(IrrigationRequest request) {
        String crop = request.getCropType().toLowerCase();
        String soil = request.getSoilType().toLowerCase();

        // Rice needs flood/basin irrigation
        if (crop.contains("rice")) {
            return "Flood/Basin Irrigation - Maintain standing water";
        }

        // Sandy soil needs frequent irrigation - drip is ideal
        if (soil.contains("sandy")) {
            return "Drip Irrigation - Preferred for water conservation";
        }

        // High temperature needs efficient irrigation
        if (request.getTemperature() > 30) {
            return "Drip Irrigation - Minimize evaporation loss";
        }

        // Vegetables benefit from drip
        if (crop.contains("tomato") || crop.contains("potato") || crop.contains("onion")) {
            return "Drip/Sprinkler Irrigation - Efficient and controlled";
        }

        // Default to sprinkler for most crops
        return "Sprinkler Irrigation - Suitable for most crops";
    }

    /**
     * Determine optimal irrigation time based on environmental conditions
     */
    private String determineIrrigationTime(IrrigationRequest request) {
        // High temperature - irrigate early morning or late evening
        if (request.getTemperature() > 28) {
            return "Early morning (5-7 AM) or late evening (6-8 PM) - Minimize evaporation";
        }

        // High humidity - avoid early morning (disease risk)
        if (request.getHumidity() > 75) {
            return "Late morning (9-11 AM) or afternoon (3-5 PM) - Reduce disease risk";
        }

        // Moderate conditions - standard timing
        return "Early morning (6-8 AM) - Optimal absorption and minimal evaporation";
    }

    /**
     * Determine priority level based on soil moisture and temperature
     */
    private String determinePriority(IrrigationRequest request) {
        // Critical conditions
        if (request.getSoilMoisture() < CRITICAL_MOISTURE_THRESHOLD && request.getTemperature() > 30) {
            return "HIGH";
        }

        // Low moisture threshold or high temperature
        if (request.getSoilMoisture() < CRITICAL_MOISTURE_THRESHOLD || request.getTemperature() > 32) {
            return "HIGH";
        }

        // Moderate moisture threshold
        if (request.getSoilMoisture() < MODERATE_MOISTURE_THRESHOLD) {
            return "MEDIUM";
        }

        return "LOW";
    }

    /**
     * Build reason message for irrigation recommendation
     */
    private String buildReasonMessage(IrrigationRequest request) {
        StringBuilder reason = new StringBuilder();

        if (request.getSoilMoisture() < CRITICAL_MOISTURE_THRESHOLD) {
            reason.append("Soil moisture is critically low (").append(request.getSoilMoisture()).append("%). ");
        } else if (request.getSoilMoisture() < MODERATE_MOISTURE_THRESHOLD) {
            reason.append("Soil moisture is moderate (").append(request.getSoilMoisture()).append("%). ");
        }

        String crop = request.getCropType().toLowerCase();
        if (crop.contains("rice")) {
            reason.append("Rice requires continuous water supply. ");
        } else if (crop.contains("cotton")) {
            reason.append("Cotton needs consistent irrigation during flowering. ");
        } else if (crop.contains("millet")) {
            reason.append("Millet is drought-resistant but benefits from irrigation. ");
        }

        String soil = request.getSoilType().toLowerCase();
        if (soil.contains("sandy")) {
            reason.append("Sandy soil drains quickly, requiring more frequent irrigation. ");
        } else if (soil.contains("clay")) {
            reason.append("Clay soil retains water well. ");
        }

        if (request.getTemperature() > 30) {
            reason.append("High temperature increases evapotranspiration. ");
        }

        if (request.getRainfall() > 0 && request.getRainfall() <= RAINFALL_THRESHOLD) {
            reason.append("Recent rainfall is insufficient (").append(request.getRainfall()).append(" mm). ");
        }

        return reason.toString().trim();
    }

    /**
     * Build response when no irrigation is needed
     */
    private IrrigationResponse buildNoIrrigationResponse(IrrigationRequest request) {
        String reason;
        if (request.getRainfall() > RAINFALL_THRESHOLD) {
            reason = "Adequate rainfall received (" + request.getRainfall() + " mm). " +
                    "Soil moisture is sufficient. No irrigation required at this time.";
        } else {
            reason = "Soil moisture is adequate (" + request.getSoilMoisture() + "%). " +
                    "Crop does not require irrigation currently.";
        }

        return new IrrigationResponse(
                false,
                0.0,
                "No irrigation required",
                "Not applicable",
                reason,
                "LOW"
        );
    }

    /**
     * Validate the incoming request
     */
    private void validateRequest(IrrigationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
    }
}
