package com.demo.humorsource.controller.api;

import com.demo.humorsource.dao.LeaveDao;
import com.demo.humorsource.model.Leave;
import com.demo.humorsource.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GeneralLeaveController {
    @Autowired
    LeaveDao leaveDAO;

    private final LeaveService leaveService;

    //////////*假別*///////////
    @GetMapping("/generalLeaves")
    public List<Leave> getAllGeneralLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/generalLeave")
    public Leave getGeneralLeave(String id) {
        return leaveService.findById(Integer.parseInt(id));
    }

    @PostMapping("/generalLeave")
    public Leave createGeneralLeave(String name, int days, float count) {
        return leaveService.createGeneralLeave(name, days, count);
    }

    @PutMapping("/generalLeave")
    public Leave updateGeneralLeave(int id, String name, int days, float count) {
        return leaveService.updateGeneralLeave(id, name, days, count);
    }

    @DeleteMapping("/generalLeave")
    public String deleteGeneralLeave(String id) {
        leaveService.deleteLeave(Integer.parseInt(id));
        return "{\"delete\":\"success!\"}";
    }

}
