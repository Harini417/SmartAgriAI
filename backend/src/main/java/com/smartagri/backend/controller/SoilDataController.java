package com.smartagri.backend.controller;

import com.smartagri.backend.entity.SoilData;
import com.smartagri.backend.service.SoilDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/soil-data")
@CrossOrigin(origins = "*")
public class SoilDataController {

    private final SoilDataService soilDataService;

    public SoilDataController(SoilDataService soilDataService) {
        this.soilDataService = soilDataService;
    }

    /**
     * Get all soil data records
     * @return List of all soil data records with HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<SoilData>> getAllSoilData() {
        List<SoilData> soilDataList = soilDataService.getAllSoilData();
        return ResponseEntity.ok(soilDataList);
    }

    /**
     * Get soil data by ID
     * @param id The soil data's ID
     * @return The soil data object if found, HTTP 404 otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<SoilData> getSoilDataById(@PathVariable Long id) {
        Optional<SoilData> soilData = soilDataService.getSoilDataById(id);
        return soilData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get all soil data records for a specific location
     * @param location The location name
     * @return List of soil data records for that location with HTTP 200 status
     */
    @GetMapping("/location/{location}")
    public ResponseEntity<List<SoilData>> getSoilDataByLocation(@PathVariable String location) {
        List<SoilData> soilDataList = soilDataService.getSoilDataByLocation(location);
        return ResponseEntity.ok(soilDataList);
    }

    /**
     * Add new soil monitoring data
     * @param soilData The soil data object to be created
     * @return The created soil data with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<SoilData> addSoilData(@RequestBody SoilData soilData) {
        SoilData createdSoilData = soilDataService.addSoilData(soilData);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSoilData);
    }

    /**
     * Update existing soil data
     * @param id The soil data's ID to update
     * @param updatedSoilData The updated soil data
     * @return The updated soil data object with HTTP 200 status
     */
    @PutMapping("/{id}")
    public ResponseEntity<SoilData> updateSoilData(@PathVariable Long id, @RequestBody SoilData updatedSoilData) {
        SoilData soilData = soilDataService.updateSoilData(id, updatedSoilData);
        return ResponseEntity.ok(soilData);
    }

    /**
     * Delete soil data by ID
     * @param id The soil data's ID to delete
     * @return HTTP 204 (No Content) on successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoilData(@PathVariable Long id) {
        soilDataService.deleteSoilData(id);
        return ResponseEntity.noContent().build();
    }
}
