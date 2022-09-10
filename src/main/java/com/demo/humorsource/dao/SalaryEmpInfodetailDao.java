package com.demo.humorsource.dao;

import com.demo.humorsource.model.SalaryEmpInfodetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryEmpInfodetailDao extends JpaRepository<SalaryEmpInfodetail, Integer> {
    void deleteByInfoId(int infoId);
}
