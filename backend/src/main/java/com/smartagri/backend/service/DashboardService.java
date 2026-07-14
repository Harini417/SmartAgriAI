package com.smartagri.backend.service;

import com.smartagri.backend.dto.DashboardResponse;
import com.smartagri.backend.entity.Crop;
import com.smartagri.backend.entity.SoilData;
import com.smartagri.backend.repository.CropRepository;
import com.smartagri.backend.repository.SoilDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final CropRepository cropRepository;
    private final SoilDataRepository soilDataRepository;

    public DashboardService(CropRepository cropRepository,
                            SoilDataRepository soilDataRepository) {
        this.cropRepository = cropRepository;
        this.soilDataRepository = soilDataRepository;
    }

    public DashboardResponse getDashboardAnalytics(Long userId) {

   List<Crop> allCrops = cropRepository.findByUserId(userId);
List<SoilData> allSoilData = soilDataRepository.findByUserId(userId);
        if(allSoilData.isEmpty()) {

    allSoilData = allCrops.stream()
        .map(crop -> {

            SoilData soil = new SoilData();

            soil.setLocation(crop.getLocation());

            if(crop.getSoilType().equals("Red Soil")) {
                soil.setSoilPh(6.5);
                soil.setMoistureLevel(45.0);
                soil.setTemperature(28.0);
            }

            else if(crop.getSoilType().equals("Sandy Loam")) {
                soil.setSoilPh(6.4);
                soil.setMoistureLevel(50.0);
                soil.setTemperature(27.0);
            }

            else {
                soil.setSoilPh(6.8);
                soil.setMoistureLevel(60.0);
                soil.setTemperature(26.0);
            }

            return soil;

        })
        .toList();
}
        Long totalCrops = (long) allCrops.size();
        Long totalSoilRecords = (long) allSoilData.size();

        Long healthySoilCount = allSoilData.stream()
                .filter(soil -> soil.getSoilPh() != null)
                .filter(soil -> soil.getMoistureLevel() != null)
                .filter(soil -> soil.getSoilPh() >= 6.0 && soil.getSoilPh() <= 7.5)
                .filter(soil -> soil.getMoistureLevel() >= 40 && soil.getMoistureLevel() <= 70)
                .count();

        Long unhealthySoilCount = totalSoilRecords - healthySoilCount;

        Double averageSoilPh = allSoilData.stream()
                .filter(s -> s.getSoilPh() != null)
                .mapToDouble(SoilData::getSoilPh)
                .average()
                .orElse(7.0);

        Double averageTemperature = allSoilData.stream()
                .filter(s -> s.getTemperature() != null)
                .mapToDouble(SoilData::getTemperature)
                .average()
                .orElse(25.0);

        Double averageMoisture = allSoilData.stream()
                .filter(s -> s.getMoistureLevel() != null)
                .mapToDouble(SoilData::getMoistureLevel)
                .average()
                .orElse(50.0);

        String mostCultivatedCrop = findMostCultivatedCrop(allCrops);

        String weatherStatus;

        if (averageMoisture < 30) {
            weatherStatus = "Dry";
        } else if (averageMoisture > 75) {
            weatherStatus = "Wet";
        } else {
            weatherStatus = "Normal";
        }

        String recommendedCrop;

        if (averageSoilPh >= 5.5 && averageSoilPh <= 6.5 && averageMoisture > 60) {
            recommendedCrop = "Rice";
        } else if (averageTemperature >= 20 && averageTemperature <= 30) {
            recommendedCrop = "Maize";
        } else {
            recommendedCrop = mostCultivatedCrop;
        }

        return new DashboardResponse(
                totalCrops,
                totalSoilRecords,
                healthySoilCount,
                unhealthySoilCount,
                averageSoilPh,
                averageTemperature,
                averageMoisture,
                mostCultivatedCrop,
                weatherStatus,
                recommendedCrop
        );
    }

    private String findMostCultivatedCrop(List<Crop> crops) {

        if (crops.isEmpty()) {
            return "No crops";
        }

        return crops.stream()
                .collect(Collectors.groupingBy(
                        Crop::getCropType,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No crops");
    }

}