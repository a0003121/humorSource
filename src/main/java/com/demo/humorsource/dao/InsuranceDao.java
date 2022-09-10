package com.demo.humorsource.dao;

import com.demo.humorsource.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceDao extends JpaRepository<Insurance, Integer> {
    List<Insurance> findByInsuranceType(String insuranceType);
}
