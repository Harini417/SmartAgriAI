package com.smartagri.backend.controller;

import com.smartagri.backend.entity.Crop;
import com.smartagri.backend.service.CropService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/crops")
@CrossOrigin(origins = "*")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }
/**
     * Get all crops
     * @return List of all crops with HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<Crop>> getAllCrops() {
        List<Crop> crops = cropService.getAllCrops();
        return ResponseEntity.ok(crops);
    }
@GetMapping("/user/{userId}")
public ResponseEntity<List<Crop>> getUserCrops(@PathVariable Long userId) {
    List<Crop> crops = cropService.getUserCrops(userId);
    return ResponseEntity.ok(crops);
}
    /**
     * Get a crop by ID
     * @param id The crop's ID
     * @return The crop object if found, HTTP 404 otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Crop> getCropById(@PathVariable Long id) {
        Optional<Crop> crop = cropService.getCropById(id);
        return crop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    

    /**
     * Add a new crop
     * @param crop The crop object to be created
     * @return The created crop with HTTP 201 status
     */
    @PostMapping("/user/{userId}")
public ResponseEntity<Crop> addCrop(
        @PathVariable Long userId,
        @RequestBody Crop crop) {

    Crop createdCrop = cropService.addCrop(crop, userId);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdCrop);
}

    /**
     * Update an existing crop
     * @param id The crop's ID to update
     * @param updatedCrop The updated crop data
     * @return The updated crop object with HTTP 200 status
     */
    @PutMapping("/{id}")
    public ResponseEntity<Crop> updateCrop(@PathVariable Long id, @RequestBody Crop updatedCrop) {
        Crop crop = cropService.updateCrop(id, updatedCrop);
        return ResponseEntity.ok(crop);
    }

    /**
     * Delete a crop by ID
     * @param id The crop's ID to delete
     * @return HTTP 204 (No Content) on successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return ResponseEntity.noContent().build();
    }
}
