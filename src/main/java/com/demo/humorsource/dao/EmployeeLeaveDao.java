package com.demo.humorsource.dao;

import com.demo.humorsource.model.EmployeeLeave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EmployeeLeaveDao extends JpaRepository<EmployeeLeave, Integer> {
    List<EmployeeLeave> findByEmployeeId(Integer employeeId);
    List<EmployeeLeave> findByEmployeeIdAndLeaveDate(Integer employeeId, Date leaveDate);
    List<EmployeeLeave> findByEmployeeIdAndLeaveDateBetween(Integer employeeId, Date leaveStart, Date leaveEnd);
}
