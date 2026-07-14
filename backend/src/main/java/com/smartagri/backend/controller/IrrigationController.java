package com.smartagri.backend.controller;

import com.smartagri.backend.dto.IrrigationRequest;
import com.smartagri.backend.dto.IrrigationResponse;
import com.smartagri.backend.service.IrrigationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/irrigation")
@CrossOrigin(origins = "*")
public class IrrigationController {

    private final IrrigationService irrigationService;

    public IrrigationController(IrrigationService irrigationService) {
        this.irrigationService = irrigationService;
    }

    /**
     * Get intelligent irrigation recommendation based on soil and environmental parameters
     * @param request The irrigation request with validation
     * @param bindingResult Binding result for validation errors
     * @return IrrigationResponse with HTTP 200 status on success
     */
    @PostMapping("/recommendation")
    public ResponseEntity<?> getIrrigationRecommendation(@Valid @RequestBody IrrigationRequest request,
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
            IrrigationResponse recommendation = irrigationService.getIrrigationRecommendation(request);
            return ResponseEntity.ok(recommendation);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing irrigation recommendation: " + e.getMessage());
        }
    }

    /**
     * Health check endpoint for irrigation service
     * @return Status message with HTTP 200
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Irrigation service is running");
    }
}
