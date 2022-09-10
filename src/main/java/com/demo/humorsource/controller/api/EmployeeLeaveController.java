package com.demo.humorsource.controller.api;

import com.demo.humorsource.service.CalendarService;
import com.demo.humorsource.service.EmployeeLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
public class EmployeeLeaveController {
    private final CalendarService calendarService;
    private final EmployeeLeaveService employeeLeaveService;

    ////////// *員工休假*///////////
    @GetMapping("/employeeLeaves")
    public String getAllEmployeeLeaves() {
        return employeeLeaveService.getAllEmployeeLeaves();
    }

    @GetMapping("/employeeLeave")
    public String getOneEmployeeLeave(String id) {
        return employeeLeaveService.getOneEmployeeLeave(id);
    }

    @PostMapping("/employeeLeave")
    public String createEmployeeLeave(int status, int employeeId, int leaveId, String date, float hours, long startTime, long endTime) throws ParseException {

        return employeeLeaveService.createEmployeeLeave(status, employeeId, leaveId, date, hours, startTime, endTime);
    }

    @PutMapping("/employeeLeave")
    public String updateEmployeeLeave(int id, int leaveId, float hours, String date, long startTime, long endTime) throws ParseException {
        return employeeLeaveService.updateEmployeeLeave(id, leaveId, hours, date, startTime, endTime);
    }

    @DeleteMapping("/employeeLeaves")
    public ResponseEntity<String> deleteEmployeeLeave(int id) {
        employeeLeaveService.deleteEmployeeLeave(id);
        return ResponseEntity.ok("{\"delete\":\"success!\"}");
    }

    // 取得請假天數
    @GetMapping("/leaveDays")
    public String getleaveDays(Long startTime, Long endTime) {
        return Double.toString(calendarService.getWorkingHour(startTime, endTime));
    }

}
