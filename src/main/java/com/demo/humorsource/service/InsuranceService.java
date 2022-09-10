package com.demo.humorsource.service;

import com.demo.humorsource.dao.InsuranceDao;
import com.demo.humorsource.model.Insurance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceDao insuranceDAO;

    public List<Insurance> getInsurances() {
        return insuranceDAO.findAll();
    }

}
