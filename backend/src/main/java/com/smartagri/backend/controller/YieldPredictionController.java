package com.smartagri.backend.controller;

import com.smartagri.backend.dto.YieldPredictionRequest;
import com.smartagri.backend.dto.YieldPredictionResponse;
import com.smartagri.backend.service.YieldPredictionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/yield")
@CrossOrigin(origins = "*")
public class YieldPredictionController {

    private final YieldPredictionService yieldPredictionService;

    public YieldPredictionController(YieldPredictionService yieldPredictionService) {
        this.yieldPredictionService = yieldPredictionService;
    }

    /**
     * Predict crop yield based on soil and environmental parameters
     * @param request The yield prediction request with validation
     * @param bindingResult Binding result for validation errors
     * @return YieldPredictionResponse with HTTP 200 status on success
     */
    @PostMapping("/predict")
    public ResponseEntity<?> predictYield(@Valid @RequestBody YieldPredictionRequest request,
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

            // Get prediction from service
            YieldPredictionResponse prediction = yieldPredictionService.predictYield(request);
            return ResponseEntity.ok(prediction);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing yield prediction: " + e.getMessage());
        }
    }

    /**
     * Health check endpoint for yield prediction service
     * @return Status message with HTTP 200
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Yield Prediction service is running");
    }
}
