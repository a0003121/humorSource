package com.demo.humorsource.controller.api;

import com.demo.humorsource.model.Employee;
import com.demo.humorsource.model.Insurance;
import com.demo.humorsource.service.EmployeeService;
import com.demo.humorsource.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService insuranceService;
    private final EmployeeService employeeService;

    //////////*保險*///////////

    @GetMapping("/insurances")
    public List<Insurance> getInsurances() {
        return insuranceService.getInsurances();
    }

    @PutMapping("/insurance")
    public Employee updateEmployee(int id, int work, int health, int compensation) {
        return employeeService.updateEmployeeInsurance(id, work, health, compensation);
    }

}
