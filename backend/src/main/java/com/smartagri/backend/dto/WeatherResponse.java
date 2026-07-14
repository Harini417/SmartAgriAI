package com.smartagri.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class WeatherResponse {

    private String city;
    private Double temperature;
    private Integer humidity;
    private Double pressure;

    @JsonProperty("wind_speed")
    private Double windSpeed;

    @JsonProperty("weather_condition")
    private String weatherCondition;

    private Double rainfall;

    public WeatherResponse() {
    }

    public WeatherResponse(String city, Double temperature, Integer humidity, Double pressure,
                          Double windSpeed, String weatherCondition, Double rainfall) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.weatherCondition = weatherCondition;
        this.rainfall = rainfall;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public Double getRainfall() {
        return rainfall;
    }

    public void setRainfall(Double rainfall) {
        this.rainfall = rainfall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherResponse that = (WeatherResponse) o;
        return Objects.equals(city, that.city) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(humidity, that.humidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, temperature, humidity);
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "city='" + city + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", windSpeed=" + windSpeed +
                ", weatherCondition='" + weatherCondition + '\'' +
                ", rainfall=" + rainfall +
                '}';
    }
}
