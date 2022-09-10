package com.demo.humorsource.controller.api;

import com.demo.humorsource.model.Dept;
import com.demo.humorsource.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeptController {
    private final DeptService deptService;

    //////////*部門*///////////
    @GetMapping("/depts")
    public List<Dept> getAllDepts() {
        return deptService.getAllDepts();
    }

    @GetMapping("/dept")
    public Dept getOneDept(String id) {
        return deptService.findById(Integer.parseInt(id));
    }

    @PostMapping("/dept")
    public Dept createDept(String name) {
        return deptService.createDept(name);
    }

    @PutMapping("/dept")
    public Dept updateDept(int id, String name, Integer empNo) {
        return deptService.updateDept(id, name, empNo);
    }

    @DeleteMapping("/dept")
    public String deleteDept(String id) {
        deptService.deleteDept(Integer.parseInt(id));
        return "{\"delete\":\"success!\"}";
    }

}
