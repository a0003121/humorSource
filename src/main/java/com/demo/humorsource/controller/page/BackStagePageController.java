package com.demo.humorsource.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BackStagePageController {

    @RequestMapping("/backheader")
    public String header() {
        return "/admin/header";
    }

    @GetMapping("/employee.do")
    public String gotoEmployeePage() {
        return "/admin/employee";
    }

    @GetMapping("/dept.do")
    public String gotoDeptPage() {
        return "/admin/dept";
    }

    @GetMapping("/position.do")
    public String gotoPositionPage() {
        return "/admin/position";
    }

    @GetMapping("/insurance.do")
    public String gotoInsurancePage() {
        return "/admin/insurance";
    }

    @GetMapping("/leave.do")
    public String gotoLeavePage() {
        return "/admin/employee_leave";
    }

    @GetMapping("/seating.do")
    public String gotoSeatingPage() {
        return "/admin/seating";
    }

    @GetMapping("/calendar.do")
    public String gotoCalendarPage() {
        return "/admin/calendar";
    }

    @GetMapping("/salary.do")
    public String gotoSalaryPage() {
        return "/admin/salary";
    }

    @GetMapping("/generalLeave.do")
    public String gotogeneralLeavePage() {
        return "/admin/generalLeave";
    }

    @GetMapping("/specialLeave.do")
    public String gotoSpecialLeavePage() {
        return "/admin/specialLeave";
    }

    @GetMapping("/clock.do")
    public String gotoClockPage() {
        return "/admin/clock";
    }
}
