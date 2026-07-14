package com.smartagri.backend.controller;

import com.smartagri.backend.dto.WeatherResponse;
import com.smartagri.backend.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Get real-time weather data for a specific city
     * @param city The name of the city
     * @return WeatherResponse with weather data and HTTP 200 status
     * @throws RuntimeException on invalid city or API errors
     */
    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponse> getWeatherByCity(@PathVariable String city) {
        try {
            WeatherResponse weather = weatherService.getWeatherByCity(city);
            return ResponseEntity.ok(weather);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Health check endpoint for weather service
     * @return Status message with HTTP 200
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Weather service is running");
    }
}
