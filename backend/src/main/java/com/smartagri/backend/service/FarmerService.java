package com.smartagri.backend.service;

import com.smartagri.backend.entity.Farmer;
import com.smartagri.backend.repository.FarmerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    /**
     * Retrieve all farmers from the database
     * @return List of all farmers
     */
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    /**
     * Add a new farmer to the database
     * @param farmer The farmer object to be saved
     * @return The saved farmer with generated ID
     */
    public Farmer addFarmer(Farmer farmer) {
        if (farmer.getName() == null || farmer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Farmer name cannot be empty");
        }
        return farmerRepository.save(farmer);
    }

    /**
     * Get a farmer by their ID
     * @param id The farmer's ID
     * @return Optional containing the farmer if found, empty otherwise
     */
    public Optional<Farmer> getFarmerById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid farmer ID");
        }
        return farmerRepository.findById(id);
    }

    /**
     * Update an existing farmer's information
     * @param id The farmer's ID to update
     * @param updatedFarmer The updated farmer data
     * @return The updated farmer object
     */
    public Farmer updateFarmer(Long id, Farmer updatedFarmer) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid farmer ID");
        }

        Farmer existingFarmer = farmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found with ID: " + id));

        if (updatedFarmer.getName() != null && !updatedFarmer.getName().trim().isEmpty()) {
            existingFarmer.setName(updatedFarmer.getName());
        }
        if (updatedFarmer.getLocation() != null && !updatedFarmer.getLocation().trim().isEmpty()) {
            existingFarmer.setLocation(updatedFarmer.getLocation());
        }
        if (updatedFarmer.getCrop() != null && !updatedFarmer.getCrop().trim().isEmpty()) {
            existingFarmer.setCrop(updatedFarmer.getCrop());
        }
        if (updatedFarmer.getSoilType() != null && !updatedFarmer.getSoilType().trim().isEmpty()) {
            existingFarmer.setSoilType(updatedFarmer.getSoilType());
        }
        if (updatedFarmer.getWaterAvailability() != null && !updatedFarmer.getWaterAvailability().trim().isEmpty()) {
            existingFarmer.setWaterAvailability(updatedFarmer.getWaterAvailability());
        }

        return farmerRepository.save(existingFarmer);
    }

    /**
     * Delete a farmer by their ID
     * @param id The farmer's ID to delete
     */
    public void deleteFarmer(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid farmer ID");
        }

        if (!farmerRepository.existsById(id)) {
            throw new RuntimeException("Farmer not found with ID: " + id);
        }

        farmerRepository.deleteById(id);
    }
}
