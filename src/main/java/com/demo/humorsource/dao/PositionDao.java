package com.demo.humorsource.dao;

import com.demo.humorsource.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionDao extends JpaRepository<Position, Integer> {
//	Position findByName(String name);
}
