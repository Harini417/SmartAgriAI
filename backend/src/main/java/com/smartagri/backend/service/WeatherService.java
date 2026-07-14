package com.smartagri.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.smartagri.backend.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String OPENWEATHERMAP_API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(OPENWEATHERMAP_API_URL).build();
    }

    /**
     * Fetch real-time weather data for a specific city from OpenWeatherMap API
     * @param city The name of the city
     * @return WeatherResponse containing weather data
     * @throws RuntimeException if city not found or API error occurs
     */
    public WeatherResponse getWeatherByCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be empty");
        }

        try {
            JsonNode response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("q", city)
                            .queryParam("appid", apiKey)
                            .queryParam("units", "metric")
                            .build())
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            if (response == null) {
                throw new RuntimeException("No response received from weather API");
            }

            return parseWeatherResponse(response, city);

        } catch (WebClientResponseException.NotFound e) {
            throw new RuntimeException("City not found: " + city);
        } catch (WebClientResponseException.Unauthorized e) {
            throw new RuntimeException("Invalid API key for weather service");
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Weather API error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch weather data: " + e.getMessage());
        }
    }

    /**
     * Parse the JSON response from OpenWeatherMap API into WeatherResponse object
     * @param response The JSON response from API
     * @param city The city name
     * @return WeatherResponse object with parsed data
     */
    private WeatherResponse parseWeatherResponse(JsonNode response, String city) {
        try {
            WeatherResponse weather = new WeatherResponse();
            weather.setCity(city);

            // Extract main weather data
            JsonNode main = response.get("main");
            if (main != null) {
                weather.setTemperature(main.get("temp").asDouble());
                weather.setHumidity(main.get("humidity").asInt());
                weather.setPressure(main.get("pressure").asDouble());
            }

            // Extract wind data
            JsonNode wind = response.get("wind");
            if (wind != null) {
                weather.setWindSpeed(wind.get("speed").asDouble());
            }

            // Extract weather condition
            JsonNode weatherArray = response.get("weather");
            if (weatherArray != null && weatherArray.isArray() && weatherArray.size() > 0) {
                weather.setWeatherCondition(weatherArray.get(0).get("main").asText());
            }

            // Extract rainfall if available (OpenWeatherMap provides rain in mm for last hour)
            JsonNode rain = response.get("rain");
            if (rain != null && rain.has("1h")) {
                weather.setRainfall(rain.get("1h").asDouble());
            } else {
                weather.setRainfall(0.0);
            }

            return weather;

        } catch (Exception e) {
            throw new RuntimeException("Error parsing weather data: " + e.getMessage());
        }
    }

    /**
     * Get weather asynchronously (non-blocking)
     * @param city The name of the city
     * @return Mono<WeatherResponse> for reactive operations
     */
    public Mono<WeatherResponse> getWeatherByCityAsync(String city) {
        if (city == null || city.trim().isEmpty()) {
            return Mono.error(new IllegalArgumentException("City name cannot be empty"));
        }

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> parseWeatherResponse(response, city))
                .onErrorMap(WebClientResponseException.NotFound.class,
                        e -> new RuntimeException("City not found: " + city))
                .onErrorMap(WebClientResponseException.Unauthorized.class,
                        e -> new RuntimeException("Invalid API key for weather service"))
                .onErrorMap(WebClientResponseException.class,
                        e -> new RuntimeException("Weather API error: " + e.getStatusCode()));
    }
}
