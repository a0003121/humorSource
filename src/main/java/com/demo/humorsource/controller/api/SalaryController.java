package com.demo.humorsource.controller.api;

import com.demo.humorsource.model.Employee;
import com.demo.humorsource.model.SalaryInfo;
import com.demo.humorsource.model.SalaryStatistics;
import com.demo.humorsource.service.EmployeeService;
import com.demo.humorsource.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalaryController {
    private final EmployeeService employeeService;
    private final SalaryService salaryService;

    //取得薪資計算區間員工名單
    @GetMapping("/salary_member")
    public List<Employee> getAllSalaryCalculateMember(Long startDate, Long endDate) {
        return employeeService.findSalaryMemberByHireDateAndLeaveDate(startDate, endDate);
    }

    @GetMapping("/salaryInfo")
    public List<SalaryInfo> getAllSalaryInfo() {
        return salaryService.getAllSalaryInfo();
    }

    @GetMapping("/salaryStatistics")
    public List<SalaryStatistics> getSalaryStatisticsbyInfoId(int infoId) {
        return salaryService.findStatisticsByInfoId(infoId);
    }

    @Transactional
    @DeleteMapping("/salaryInfo")
    public ResponseEntity<String> deleteSalaryInfo(int id) {
        salaryService.deleteSalaryInfo(id);
        return ResponseEntity.ok("{\"delete\":\"success!\"}");
    }

    //薪資計算
    @Transactional
    @GetMapping("/perform_cal")
    public SalaryInfo performCal(Long startDate, Long endDate, @RequestParam(value = "ids[]") String[] ids, String name, String calcDate) {
        return salaryService.performCal(startDate, endDate, ids, name, calcDate);
    }

}
