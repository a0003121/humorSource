package com.demo.humorsource.service;

import com.demo.humorsource.dao.SeatDao;
import com.demo.humorsource.model.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatDao seatDAO;

    public List<Seat> getAllSeats() {
        return seatDAO.findAll();
    }

    public Seat findById(int id) {
        return seatDAO.findById(id).get();
    }

    public Seat createSeat(String content) {
        Seat seat = new Seat();
        seat.setId(0);
        seat.setContent(content);
        return seatDAO.save(seat);
    }

    public Seat updateSeat(int id, String content) {
        Seat seat = new Seat();
        seat.setId(id);
        seat.setContent(content);
        return seatDAO.save(seat);
    }

    public void deleteSeat(int id) {
        seatDAO.deleteById(id);
    }

}
