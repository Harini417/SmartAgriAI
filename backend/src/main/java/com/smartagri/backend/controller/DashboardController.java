package com.smartagri.backend.controller;

import com.smartagri.backend.dto.DashboardResponse;
import com.smartagri.backend.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<DashboardResponse> getDashboardAnalytics(
            @PathVariable Long userId) {

        try {
            DashboardResponse analytics =
                    dashboardService.getDashboardAnalytics(userId);

            return ResponseEntity.ok(analytics);

        } catch (Exception e) {

            DashboardResponse defaultResponse = new DashboardResponse(
                    0L,
                    0L,
                    0L,
                    0L,
                    7.0,
                    25.0,
                    50.0,
                    "No crops recorded",
                    "Data not available",
                    "Collect soil data for recommendations"
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(defaultResponse);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Dashboard service is running");
    }
}