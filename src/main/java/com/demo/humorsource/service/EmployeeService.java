package com.demo.humorsource.service;

import com.demo.humorsource.dao.EmployeeDao;
import com.demo.humorsource.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDAO;

    public List<Employee> getAllEmployees() {
        return employeeDAO.findAllByOrderByEmpNoAsc();
    }

    public Employee findById(int id) {
        return employeeDAO.findById(id).get();
    }

    public Employee createEmployee(Employee employeeData, String leaveDateN, String salaryN) throws ParseException {
        if (!"".equals(salaryN.trim())) {
            employeeData.setSalary(Integer.parseInt(salaryN));
        }
        if (!"".equals(leaveDateN.trim())) {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(leaveDateN);
            employeeData.setLeaveDate(new Date(date.getTime()));
        }

        Employee temp;
        try {
            temp = employeeDAO.save(employeeData);
        } catch (DataIntegrityViolationException e) {
            return new Employee();
        }

        return temp;
    }

    public Employee updateEmployee(Employee employeeData, String leaveDateN, String salaryN) throws ParseException {
        Employee result;

        if (!"".equals(salaryN.trim())) {
            employeeData.setSalary(Integer.parseInt(salaryN));
        }
        if (!"".equals(leaveDateN.trim())) {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(leaveDateN);
            employeeData.setLeaveDate(new Date(date.getTime()));
        }
        try {
            result = employeeDAO.save(employeeData);
        } catch (DataIntegrityViolationException e) {
            return new Employee();
        }
        return result;
    }

    public Employee updateEmployeeInsurance(int id, int work, int health, int compensation) {
        Employee employee = employeeDAO.findById(id).get();

        employee.setCompensation(compensation);
        employee.setWorkInsurance(work);
        employee.setHealthInsurance(health);
        Employee result;
        try {
            result = employeeDAO.save(employee);
        } catch (DataIntegrityViolationException e) {
            return new Employee();
        }
        return result;
    }

    public void deleteEmployee(int id) {
        employeeDAO.deleteById(id);
    }

    public List<Employee> findByStatus(int status) {
        return employeeDAO.findByStatus(status);
    }

    public List<Employee> findByEmpNo(int empNo) {
        return employeeDAO.findByEmpNo(empNo);
    }

    public List<Employee> findSalaryMemberByHireDateAndLeaveDate(long startDate, long endDate) {
        return employeeDAO.findSalaryMemberByHireDateAndLeaveDate(new Date(startDate), new Date(endDate));
    }


}
