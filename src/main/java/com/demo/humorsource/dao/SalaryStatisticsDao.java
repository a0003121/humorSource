package com.demo.humorsource.dao;

import com.demo.humorsource.model.SalaryStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryStatisticsDao extends JpaRepository<SalaryStatistics, Integer> {
    void deleteByInfoId(Integer infoId);

    List<SalaryStatistics> findByInfoId(Integer infoId);
}
