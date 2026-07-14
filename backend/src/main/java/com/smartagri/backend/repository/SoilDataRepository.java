package com.smartagri.backend.repository;

import com.smartagri.backend.entity.SoilData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SoilDataRepository extends JpaRepository<SoilData, Long> {

    List<SoilData> findByLocation(String location);

    List<SoilData> findAllByOrderByRecordedDateTimeDesc();

    List<SoilData> findByUserId(Long userId);
}