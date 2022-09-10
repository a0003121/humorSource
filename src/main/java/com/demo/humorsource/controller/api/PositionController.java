package com.demo.humorsource.controller.api;

import com.demo.humorsource.model.Position;
import com.demo.humorsource.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

    //////////*職位*///////////
    @GetMapping("/positions")
    public List<Position> getAllPosition() {
        return positionService.getAllPosition();
    }

    @GetMapping("/position")
    public Position getOnePosition(String id) {
        return positionService.findById(Integer.parseInt(id));
    }

    @PostMapping("/position")
    public Position createPosition(String name, int dept) {
        return positionService.createPosition(name, dept);
    }

    @PutMapping("/position")
    public Position updatePosition(int id, String name, int dept) {
        return positionService.updatePosition(id, name, dept);
    }

    @DeleteMapping("/position")
    public String deletePosition(int id) {
        positionService.deletePosition(id);
        return "{\"delete\":\"success!\"}";
    }

}
