package com.demo.humorsource.service;

import com.demo.humorsource.dao.EmployeeLeaveDao;
import com.demo.humorsource.model.EmployeeLeave;
import com.demo.humorsource.util.Constant;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeLeaveService {
    private final EmployeeLeaveDao employeeLeaveDAO;

    public String getAllEmployeeLeaves() {
        List<EmployeeLeave> list = employeeLeaveDAO.findAll();
        JSONArray jsonArray = new JSONArray();
        for (EmployeeLeave employeeLeave : list) {
            JSONObject object = new JSONObject(employeeLeave);
            switch (employeeLeave.getStatus()) {
                case Constant.LEAVE_HRADD -> object.put("status", "系統新增");
                case Constant.LEAVE_UNCOMPLETE -> object.put("status", "未完成");
                case Constant.LEAVE_COMPLETED -> object.put("status", "已完成");
            }
            jsonArray.put(object);
        }
        return jsonArray.toString();
    }

    public String getOneEmployeeLeave(String id) {
        EmployeeLeave employeeLeave = employeeLeaveDAO.findById(Integer.parseInt(id)).get();
        JSONObject object = new JSONObject(employeeLeave);
        switch (employeeLeave.getStatus()) {
            case Constant.LEAVE_HRADD -> object.put("status", "系統新增");
            case Constant.LEAVE_UNCOMPLETE -> object.put("status", "未完成");
            case Constant.LEAVE_COMPLETED -> object.put("status", "已完成");
        }
        return object.toString();
    }

    public String createEmployeeLeave(int status, int employeeId, int leaveId, String date, float hours, long startTime, long endTime) throws ParseException {
        //檢查有沒有重複
        List<EmployeeLeave> checkList = employeeLeaveDAO.findByEmployeeIdAndLeaveDate(employeeId, new SimpleDateFormat("yyyy-MM-dd").parse(date));
        for (EmployeeLeave employeeLeave : checkList) {
            long startCheck = employeeLeave.getStartTime().getTime();
            long endCheck = employeeLeave.getEndTime().getTime();
            if ((startTime < startCheck && endTime > startCheck) || (startCheck < startTime && endCheck > startTime)) { //請假時間重複
                JSONObject obj = new JSONObject();
                obj.put("repeat", true);
                return obj.toString();
            }
        }
        EmployeeLeave employeeLeave = new EmployeeLeave();
        employeeLeave.setId(0);
        employeeLeave.setEmployeeId(employeeId);
        employeeLeave.setLeaveId(leaveId);
        employeeLeave.setHours(hours);
        employeeLeave.setStatus(status);
        employeeLeave.setLeaveDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        employeeLeave.setStartTime(new Timestamp(startTime));
        employeeLeave.setEndTime(new Timestamp(endTime));
        return new JSONObject(employeeLeaveDAO.save(employeeLeave)).toString();
    }

    public String updateEmployeeLeave(int id, int leaveId, float hours, String date, long startTime, long endTime) throws ParseException {
        EmployeeLeave employeeLeave = employeeLeaveDAO.findById(id).get();
        //檢查有沒有重複
        List<EmployeeLeave> checkList = employeeLeaveDAO.findByEmployeeIdAndLeaveDate(employeeLeave.getEmployeeId(), new SimpleDateFormat("yyyy-MM-dd").parse(date));
        for (EmployeeLeave employeeLeave1 : checkList) {
            long startCheck = employeeLeave1.getStartTime().getTime();
            long endCheck = employeeLeave1.getEndTime().getTime();

            if (employeeLeave1.getId() != employeeLeave.getId()) {
                if ((startTime < startCheck && endTime > startCheck) || (startCheck < startTime && endCheck > startTime)) { //請假時間重複
                    JSONObject obj = new JSONObject();
                    obj.put("repeat", true);
                    return obj.toString();
                }
            }
        }

        employeeLeave.setLeaveId(leaveId);
        employeeLeave.setHours(hours);
        employeeLeave.setLeaveDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        employeeLeave.setStartTime(new Timestamp(startTime));
        employeeLeave.setEndTime(new Timestamp(endTime));
        employeeLeave = employeeLeaveDAO.save(employeeLeave);
        return new JSONObject(employeeLeaveDAO.save(employeeLeave)).toString();
    }

    public void deleteEmployeeLeave(int id) {
        employeeLeaveDAO.deleteById(id);
    }

    public List<EmployeeLeave> findByEmployeeIdAndLeaveDate(Integer employeeId, Date leaveDate) {
        return employeeLeaveDAO.findByEmployeeIdAndLeaveDate(employeeId, leaveDate);
    }

    public List<EmployeeLeave> findByEmployeeIdAndLeaveDateBetween(Integer employeeId, Date leaveStart, Date leaveEnd) {
        return employeeLeaveDAO.findByEmployeeIdAndLeaveDateBetween(employeeId, leaveStart, leaveEnd);
    }


}
