package com.demo.humorsource.dao;

import com.demo.humorsource.model.YearCalendar;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface YearCalendarDao extends JpaRepository<YearCalendar, Integer> {
	List<YearCalendar> findByDateBetween(Date start, Date end, Sort sort);
	YearCalendar findByDate(Date date);
}
