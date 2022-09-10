package com.demo.humorsource.controller.api;

import com.demo.humorsource.model.Seat;
import com.demo.humorsource.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    //////////*座位表*///////////
    @GetMapping("/seats")
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/seat")
    public Seat getOneSeat(String id) {
        return seatService.findById(Integer.parseInt(id));
    }

    @PostMapping("/seat")
    public Seat createSeat(String content) {
        return seatService.createSeat(content);
    }

    @PutMapping("/seat")
    public Seat updateSeat(int id, String content) {
        return seatService.updateSeat(id, content);
    }

    @DeleteMapping("/seat")
    public String deleteSeat(String id) {
        seatService.deleteSeat(Integer.parseInt(id));
        return "{\"delete\":\"success!\"}";
    }

}
