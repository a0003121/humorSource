package com.demo.humorsource.controller.api;

import com.demo.humorsource.model.Employee;
import com.demo.humorsource.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    ////////// *員工*///////////
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee")
    public Employee getOneEmployee(int id) {
        return employeeService.findById(id);
    }

    @PostMapping("/employee")
    public Employee createEmployee(Employee employeeData, String leaveDateN, String salaryN) throws ParseException {
        return employeeService.createEmployee(employeeData, leaveDateN, salaryN);
    }

    @PutMapping("/employee")
    public Employee updateEmployee(Employee employeeData, String leaveDateN, String salaryN) throws ParseException {
        return employeeService.updateEmployee(employeeData, leaveDateN, salaryN);
    }

    @DeleteMapping("/employee")
    public String deleteEmployee(int id) {
        employeeService.deleteEmployee(id);
        return "{\"delete\":\"success!\"}";
    }
}