package com.demo.humorsource.controller.api;

import com.demo.humorsource.model.Leave;
import com.demo.humorsource.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LeaveController {
    private final LeaveService leaveService;

    //////////*職位*///////////
    @GetMapping("/leaves")
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/leave")
    public Leave getOneLeave(String id) {
        return leaveService.findById(Integer.parseInt(id));
    }

    @PostMapping("/leave")
    public Leave createLeave(String name, int day) {
        return leaveService.createLeave(name, day);
    }

    @PutMapping("/leave")
    public Leave updateLeave(int id, String name, int day) {
        return leaveService.updateLeave(id, name, day);
    }

    @DeleteMapping("/leave")
    public String deletePosition(int id) {
        leaveService.deleteLeave(id);
        return "{\"delete\":\"success!\"}";
    }

}
