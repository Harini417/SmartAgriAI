package com.smartagri.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
@Entity
public class SoilData {

   @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

private String location;
    private Double soilPh;
    private Double nitrogenLevel;
    private Double phosphorusLevel;
    private Double potassiumLevel;
    private Double moistureLevel;
    private Double temperature;
    private LocalDateTime recordedDateTime;

    public SoilData() {
    }

    public SoilData(String location, Double soilPh, Double nitrogenLevel, Double phosphorusLevel,
                    Double potassiumLevel, Double moistureLevel, Double temperature) {
        this.location = location;
        this.soilPh = soilPh;
        this.nitrogenLevel = nitrogenLevel;
        this.phosphorusLevel = phosphorusLevel;
        this.potassiumLevel = potassiumLevel;
        this.moistureLevel = moistureLevel;
        this.temperature = temperature;
        this.recordedDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSoilPh() {
        return soilPh;
    }

    public void setSoilPh(Double soilPh) {
        this.soilPh = soilPh;
    }

    public Double getNitrogenLevel() {
        return nitrogenLevel;
    }

    public void setNitrogenLevel(Double nitrogenLevel) {
        this.nitrogenLevel = nitrogenLevel;
    }

    public Double getPhosphorusLevel() {
        return phosphorusLevel;
    }

    public void setPhosphorusLevel(Double phosphorusLevel) {
        this.phosphorusLevel = phosphorusLevel;
    }

    public Double getPotassiumLevel() {
        return potassiumLevel;
    }

    public void setPotassiumLevel(Double potassiumLevel) {
        this.potassiumLevel = potassiumLevel;
    }

    public Double getMoistureLevel() {
        return moistureLevel;
    }

    public void setMoistureLevel(Double moistureLevel) {
        this.moistureLevel = moistureLevel;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getRecordedDateTime() {
        return recordedDateTime;
    }

    public void setRecordedDateTime(LocalDateTime recordedDateTime) {
        this.recordedDateTime = recordedDateTime;
    }
}
