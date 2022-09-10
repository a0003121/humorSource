package com.demo.humorsource.dao;

import com.demo.humorsource.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatDao extends JpaRepository<Seat, Integer> {
//	Position findByName(String name);
}
