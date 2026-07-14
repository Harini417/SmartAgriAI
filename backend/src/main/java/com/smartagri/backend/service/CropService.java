package com.smartagri.backend.service;

import com.smartagri.backend.entity.Crop;
import com.smartagri.backend.entity.User;
import com.smartagri.backend.repository.CropRepository;
import com.smartagri.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropService {

    private final CropRepository cropRepository;
    private final UserRepository userRepository;

    public CropService(CropRepository cropRepository,
                       UserRepository userRepository) {
        this.cropRepository = cropRepository;
        this.userRepository = userRepository;
    }

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public List<Crop> getUserCrops(Long userId) {
        return cropRepository.findByUserId(userId);
    }

    public Optional<Crop> getCropById(Long id) {
        return cropRepository.findById(id);
    }

    public Crop addCrop(Crop crop, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        crop.setUser(user);

        return cropRepository.save(crop);
    }

    public Crop updateCrop(Long id, Crop updatedCrop) {

        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        crop.setCropName(updatedCrop.getCropName());
        crop.setCropType(updatedCrop.getCropType());
        crop.setPlantingDate(updatedCrop.getPlantingDate());
        crop.setHarvestDate(updatedCrop.getHarvestDate());
        crop.setSoilType(updatedCrop.getSoilType());
        crop.setWaterRequirement(updatedCrop.getWaterRequirement());
        crop.setCountry(updatedCrop.getCountry());
        crop.setState(updatedCrop.getState());
        crop.setLocation(updatedCrop.getLocation());
        crop.setStatus(updatedCrop.getStatus());

        return cropRepository.save(crop);
    }

    public void deleteCrop(Long id) {
        cropRepository.deleteById(id);
    }
}