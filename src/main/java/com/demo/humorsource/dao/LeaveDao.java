package com.demo.humorsource.dao;

import com.demo.humorsource.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveDao extends JpaRepository<Leave, Integer> {
//	Position findByName(String name);
}
