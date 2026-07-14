package com.smartagri.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Crop name is required")
    private String cropName;

    @NotBlank(message = "Crop type is required")
    private String cropType;

    @NotNull(message = "Planting date is required")
    private LocalDate plantingDate;

    private LocalDate harvestDate;

    @NotBlank(message = "Soil type is required")
    private String soilType;

    @NotNull(message = "Water requirement is required")
    @Positive(message = "Water requirement must be greater than 0")
    private Double waterRequirement;
    @ManyToOne
@JoinColumn(name = "user_id")
private User user;
    @NotBlank(message = "Country is required")
private String country;

@NotBlank(message = "State is required")
private String state;
    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Status is required")
    private String status;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    public Crop() {
    }

    @PrePersist
    public void onCreate() {
        createdDateTime = LocalDateTime.now();
        updatedDateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public Double getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(Double waterRequirement) {
        this.waterRequirement = waterRequirement;
    }
    public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}
    public String getCountry() {
    return country;
}

public void setCountry(String country) {
    this.country = country;
}

public String getState() {
    return state;
}

public void setState(String state) {
    this.state = state;
}
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}