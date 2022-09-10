package com.demo.humorsource.service;

import com.demo.humorsource.dao.LeaveDao;
import com.demo.humorsource.model.Leave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveService {
    private final LeaveDao leaveDAO;

    public List<Leave> getAllLeaves() {
        return leaveDAO.findAll();
    }

    public Leave findById(int id) {
        return leaveDAO.findById(id).get();
    }

    public Leave createLeave(String name, int day) {
        Leave leave = new Leave();
        leave.setId(0);
        leave.setName(name);
        leave.setDay(day);
        return leaveDAO.save(leave);
    }

    public Leave createGeneralLeave(String name, int days, float count) {
        Leave leave = new Leave();
        leave.setName(name);
        leave.setDay(days);
        leave.setSalaryCount(count);
        return leaveDAO.save(leave);
    }

    public Leave updateGeneralLeave(int id, String name, int days, float count) {
        Leave leave = new Leave();
        leave.setId(id);
        leave.setName(name);
        leave.setSalaryCount(count);
        leave.setDay(days);
        return leaveDAO.save(leave);
    }

    public Leave updateLeave(int id, String name, int day) {
        Leave leave = new Leave();
        leave.setId(id);
        leave.setName(name);
        leave.setDay(day);
        return leaveDAO.save(leave);
    }

    public void deleteLeave(int id) {
        leaveDAO.deleteById(id);
    }


}
