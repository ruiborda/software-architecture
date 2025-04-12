package com.example.licenses.repository;

import com.example.licenses.model.MpsOperatingLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MpsOperatingLicenseRepository extends JpaRepository<MpsOperatingLicense, UUID>, JpaSpecificationExecutor<MpsOperatingLicense> {
    List<MpsOperatingLicense> findByDepartment(String department);
    List<MpsOperatingLicense> findByProvince(String province);
    List<MpsOperatingLicense> findByDistrict(String district);
} 