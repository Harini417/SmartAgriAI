package com.smartagri.backend.service;

import com.smartagri.backend.entity.SoilData;
import com.smartagri.backend.repository.SoilDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SoilDataService {

    private final SoilDataRepository soilDataRepository;

    public SoilDataService(SoilDataRepository soilDataRepository) {
        this.soilDataRepository = soilDataRepository;
    }

    /**
     * Retrieve all soil data records from the database
     * @return List of all soil data records
     */
    public List<SoilData> getAllSoilData() {
        return soilDataRepository.findAllByOrderByRecordedDateTimeDesc();
    }

    /**
     * Get soil data by its ID
     * @param id The soil data's ID
     * @return Optional containing the soil data if found, empty otherwise
     */
    public Optional<SoilData> getSoilDataById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid soil data ID");
        }
        return soilDataRepository.findById(id);
    }

    /**
     * Get all soil data records for a specific location
     * @param location The location name
     * @return List of soil data records for that location
     */
    public List<SoilData> getSoilDataByLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }
        return soilDataRepository.findByLocation(location);
    }

    /**
     * Add new soil monitoring data to the database
     * @param soilData The soil data object to be saved
     * @return The saved soil data with generated ID
     */
    public SoilData addSoilData(SoilData soilData) {
        validateSoilData(soilData);
        if (soilData.getRecordedDateTime() == null) {
            soilData.setRecordedDateTime(LocalDateTime.now());
        }
        return soilDataRepository.save(soilData);
    }

    /**
     * Update existing soil data information
     * @param id The soil data's ID to update
     * @param updatedSoilData The updated soil data
     * @return The updated soil data object
     */
    public SoilData updateSoilData(Long id, SoilData updatedSoilData) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid soil data ID");
        }

        SoilData existingSoilData = soilDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soil data not found with ID: " + id));

        if (updatedSoilData.getLocation() != null && !updatedSoilData.getLocation().trim().isEmpty()) {
            existingSoilData.setLocation(updatedSoilData.getLocation());
        }
        if (updatedSoilData.getSoilPh() != null) {
            existingSoilData.setSoilPh(updatedSoilData.getSoilPh());
        }
        if (updatedSoilData.getNitrogenLevel() != null) {
            existingSoilData.setNitrogenLevel(updatedSoilData.getNitrogenLevel());
        }
        if (updatedSoilData.getPhosphorusLevel() != null) {
            existingSoilData.setPhosphorusLevel(updatedSoilData.getPhosphorusLevel());
        }
        if (updatedSoilData.getPotassiumLevel() != null) {
            existingSoilData.setPotassiumLevel(updatedSoilData.getPotassiumLevel());
        }
        if (updatedSoilData.getMoistureLevel() != null) {
            existingSoilData.setMoistureLevel(updatedSoilData.getMoistureLevel());
        }
        if (updatedSoilData.getTemperature() != null) {
            existingSoilData.setTemperature(updatedSoilData.getTemperature());
        }

        return soilDataRepository.save(existingSoilData);
    }

    /**
     * Delete soil data by its ID
     * @param id The soil data's ID to delete
     */
    public void deleteSoilData(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid soil data ID");
        }

        if (!soilDataRepository.existsById(id)) {
            throw new RuntimeException("Soil data not found with ID: " + id);
        }

        soilDataRepository.deleteById(id);
    }

    /**
     * Validate soil data before saving
     * @param soilData The soil data to validate
     */
    private void validateSoilData(SoilData soilData) {
        if (soilData.getLocation() == null || soilData.getLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }
        if (soilData.getSoilPh() == null || soilData.getSoilPh() < 0 || soilData.getSoilPh() > 14) {
            throw new IllegalArgumentException("Soil pH must be between 0 and 14");
        }
        if (soilData.getNitrogenLevel() == null || soilData.getNitrogenLevel() < 0) {
            throw new IllegalArgumentException("Nitrogen level cannot be negative");
        }
        if (soilData.getPhosphorusLevel() == null || soilData.getPhosphorusLevel() < 0) {
            throw new IllegalArgumentException("Phosphorus level cannot be negative");
        }
        if (soilData.getPotassiumLevel() == null || soilData.getPotassiumLevel() < 0) {
            throw new IllegalArgumentException("Potassium level cannot be negative");
        }
        if (soilData.getMoistureLevel() == null || soilData.getMoistureLevel() < 0 || soilData.getMoistureLevel() > 100) {
            throw new IllegalArgumentException("Moisture level must be between 0 and 100");
        }
        if (soilData.getTemperature() == null) {
            throw new IllegalArgumentException("Temperature is required");
        }
    }
}
