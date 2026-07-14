package com.smartagri.backend.controller;

import com.smartagri.backend.dto.CropRecommendationRequest;
import com.smartagri.backend.dto.CropRecommendationResponse;
import com.smartagri.backend.service.CropRecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/recommendation")
@CrossOrigin(origins = "*")
public class CropRecommendationController {

    private final CropRecommendationService cropRecommendationService;

    public CropRecommendationController(CropRecommendationService cropRecommendationService) {
        this.cropRecommendationService = cropRecommendationService;
    }

    /**
     * Get AI-based crop recommendation based on soil and weather parameters
     * @param request The crop recommendation request with validation
     * @param bindingResult Binding result for validation errors
     * @return CropRecommendationResponse with HTTP 200 status on success
     */
    @PostMapping
    public ResponseEntity<?> getCropRecommendation(@Valid @RequestBody CropRecommendationRequest request,
                                                   BindingResult bindingResult) {
        try {
            // Check for validation errors
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getFieldErrors().forEach(error ->
                        errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
                );
                return ResponseEntity.badRequest().body(errorMessage.toString());
            }

            // Get recommendation from service
            CropRecommendationResponse recommendation = cropRecommendationService.getCropRecommendation(request);
            return ResponseEntity.ok(recommendation);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing recommendation: " + e.getMessage());
        }
    }

    /**
     * Health check endpoint for recommendation service
     * @return Status message with HTTP 200
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Crop Recommendation service is running");
    }
}
