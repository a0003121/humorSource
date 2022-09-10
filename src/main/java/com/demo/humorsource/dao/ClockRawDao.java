package com.demo.humorsource.dao;

import com.demo.humorsource.model.ClockRaw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ClockRawDao extends JpaRepository<ClockRaw, Integer> {
    List<ClockRaw> findByTimeBetweenOrderByEmpNoAscTimeAsc(Timestamp start, Timestamp end);
    List<ClockRaw> findByTimeBetweenAndEmpNoOrderByTimeAsc(Timestamp start, Timestamp end, Integer empNo);

}
