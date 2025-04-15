package com.example.agro.repository;

import com.example.agro.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID>, JpaSpecificationExecutor<Company> {
    List<Company> findBySector(String sector);
    List<Company> findByDepartment(String department);
    List<Company> findByCompanySize(String companySize);
} 