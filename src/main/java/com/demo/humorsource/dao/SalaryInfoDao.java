package com.demo.humorsource.dao;

import com.demo.humorsource.model.SalaryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryInfoDao extends JpaRepository<SalaryInfo, Integer> {
}
