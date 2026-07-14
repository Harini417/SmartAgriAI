package com.smartagri.backend.controller;

import com.smartagri.backend.entity.Farmer;
import com.smartagri.backend.service.FarmerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/farmers")
@CrossOrigin(origins = "*")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    /**
     * Get all farmers
     * @return List of all farmers with HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        List<Farmer> farmers = farmerService.getAllFarmers();
        return ResponseEntity.ok(farmers);
    }

    /**
     * Get a farmer by ID
     * @param id The farmer's ID
     * @return The farmer object if found, HTTP 404 otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable Long id) {
        Optional<Farmer> farmer = farmerService.getFarmerById(id);
        return farmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Add a new farmer
     * @param farmer The farmer object to be created
     * @return The created farmer with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Farmer> addFarmer(@RequestBody Farmer farmer) {
        Farmer createdFarmer = farmerService.addFarmer(farmer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFarmer);
    }

    /**
     * Update an existing farmer
     * @param id The farmer's ID to update
     * @param updatedFarmer The updated farmer data
     * @return The updated farmer object with HTTP 200 status
     */
    @PutMapping("/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @RequestBody Farmer updatedFarmer) {
        Farmer farmer = farmerService.updateFarmer(id, updatedFarmer);
        return ResponseEntity.ok(farmer);
    }

    /**
     * Delete a farmer by ID
     * @param id The farmer's ID to delete
     * @return HTTP 204 (No Content) on successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return ResponseEntity.noContent().build();
    }
}