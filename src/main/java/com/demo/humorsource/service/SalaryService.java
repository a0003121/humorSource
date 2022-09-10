package com.demo.humorsource.service;

import com.demo.humorsource.dao.SalaryEmpInfodetailDao;
import com.demo.humorsource.dao.SalaryInfoDao;
import com.demo.humorsource.dao.SalaryStatisticsDao;
import com.demo.humorsource.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaryService {
    private final SalaryInfoDao salaryInfoDao;
    private final SalaryStatisticsDao salaryStatisticsDao;
    private final SalaryEmpInfodetailDao salaryEmpInfodetailDao;
    private final LeaveService leaveService;
    private final EmployeeService employeeService;
    private final EmployeeLeaveService employeeLeaveService;

    public List<SalaryInfo> getAllSalaryInfo() {
        return salaryInfoDao.findAll();
    }

    public List<SalaryStatistics> findStatisticsByInfoId(int infoId) {
        return salaryStatisticsDao.findByInfoId(infoId);
    }

    public void deleteSalaryInfo(int id) {
        salaryStatisticsDao.deleteByInfoId(id);
        salaryEmpInfodetailDao.deleteByInfoId(id);
        salaryInfoDao.deleteById(id);
    }

    public SalaryInfo performCal(Long startDate, Long endDate, String[] ids, String name, String calcDate) {
        //取得計算天數
        SimpleDateFormat converter = new SimpleDateFormat("yyyy-MM-dd");
        String[] startStr = converter.format(new Date(startDate)).split("-");
        String[] endStr = converter.format(new Date(endDate)).split("-");

        int monthCount = 0;
        while (!startStr[0].equals(endStr[0]) && Integer.parseInt(startStr[1]) <= Integer.parseInt(endStr[1])) {
            monthCount++;
            if ("12".equals(startStr[1])) {
                startStr[0] = Integer.toString(Integer.parseInt(startStr[0]) + 1);
                startStr[1] = "01";
            } else {
                startStr[1] = Integer.toString(Integer.parseInt(startStr[1]) + 1);
            }
        }

        float salaryMultiply = 0;
        float daysInMonth;
        YearMonth yearMonthObject;
        if (monthCount == 0) {
            yearMonthObject = YearMonth.of(Integer.parseInt(startStr[0]), Integer.parseInt(startStr[1]));
            daysInMonth = yearMonthObject.lengthOfMonth();
            salaryMultiply = (Integer.parseInt(endStr[2]) - Integer.parseInt(startStr[2]) + 1) / daysInMonth;
        } else {
            for (int i = 0; i <= monthCount; i++) {
                if (i == 1) {
                    String[] tempSart = converter.format(new Date(startDate)).split("-");
                    yearMonthObject = YearMonth.of(Integer.parseInt(tempSart[0]), Integer.parseInt(tempSart[1]));
                    daysInMonth = yearMonthObject.lengthOfMonth();
                    salaryMultiply += (daysInMonth - Integer.parseInt(tempSart[2]) + 1) / daysInMonth;
                } else if (i == monthCount) {
                    salaryMultiply += 1;
                } else {
                    yearMonthObject = YearMonth.of(Integer.parseInt(endStr[0]), Integer.parseInt(endStr[1]));
                    daysInMonth = yearMonthObject.lengthOfMonth();
                    salaryMultiply += Integer.parseInt(endStr[2]) / daysInMonth;
                }
            }
        }


        //新增資料到salary info
        SalaryInfo salaryInfo = new SalaryInfo(0, calcDate, new Timestamp(startDate), new Timestamp(endDate), name);
        salaryInfo = salaryInfoDao.save(salaryInfo);

        //新增資料到salary statistics
        List<Leave> leaveReference = leaveService.getAllLeaves();
        Map<Integer, Leave> referenceMap = leaveReference.stream().collect(Collectors.toMap(Leave::getId, Function.identity()));
        for (String id : ids) {

            //員工資料
            Employee employee = employeeService.findById(Integer.parseInt(id));
            int salary = employee.getSalary();


            //請假資料
            float leaveTotal = 0;
            List<EmployeeLeave> leaveList = employeeLeaveService.findByEmployeeIdAndLeaveDateBetween(employee.getEmpNo(), new Date(startDate), new Date(endDate));
            for (EmployeeLeave employeeLeave : leaveList) {
                int leaveType = employeeLeave.getLeaveId();
                float leaveHour = referenceMap.get(leaveType).getSalaryCount() * employeeLeave.getHours();

                leaveTotal += leaveHour;

                SalaryEmpInfodetail salaryEmpInfodetail = new SalaryEmpInfodetail();
                salaryEmpInfodetail.setEmpNo(employee.getEmpNo());
                salaryEmpInfodetail.setLeaveHours(leaveHour);
                salaryEmpInfodetail.setLeaveType(referenceMap.get(leaveType).getName());
                salaryEmpInfodetail.setTotal(salary / 30f / 8f * leaveHour);
                salaryEmpInfodetail.setInfoId(salaryInfo.getInfoId());
                salaryEmpInfodetailDao.save(salaryEmpInfodetail);
            }
            SalaryStatistics salaryStatistics = new SalaryStatistics();
            //勞健保計算
            int workInsurance = 0;
            int healthInsurance = 0;
            Integer empWorkInsurance = employee.getWorkInsurance();
            Integer empHealthInsurance = employee.getHealthInsurance();
            if (empWorkInsurance != null) {
                //月投保薪資×11.5%(勞保費率)×20%(勞工負擔比例)
                workInsurance = (int) Math.ceil(empWorkInsurance * 0.115 * 0.2 * salaryMultiply);
                salaryStatistics.setWorkInsurance(workInsurance);
            }
            if (empHealthInsurance != null) {
                //投保薪資×5.17%(健保費率)×30%(勞工負擔比例)×(本人+眷屬人數)
                int healthMonthCount = 0;
                String[] healthStartStr;
                String[] healthEndStr;
                if (startDate > employee.getHireDate().getTime())
                    healthStartStr = converter.format(new Date(startDate)).split("-");
                else healthStartStr = converter.format(employee.getHireDate()).split("-");

                if (employee.getLeaveDate() != null && endDate < employee.getLeaveDate().getTime())
                    healthEndStr = converter.format(employee.getLeaveDate()).split("-");
                else healthEndStr = converter.format(new Date(endDate)).split("-");

                if (healthStartStr[1].equals(healthEndStr[1])) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Integer.parseInt(healthEndStr[0]), Integer.parseInt(healthEndStr[1]) - 1, Integer.parseInt(healthEndStr[2]));

                    if (calendar.get(Calendar.DATE) == calendar.getActualMaximum(Calendar.DATE)) healthMonthCount = 1;
                } else {
                    while (!healthStartStr[0].equals(healthEndStr[0]) && Integer.parseInt(healthStartStr[1]) <= Integer.parseInt(healthEndStr[1])) {
                        healthMonthCount++;
                        if ("12".equals(startStr[1])) {
                            healthStartStr[0] = Integer.toString(Integer.parseInt(healthStartStr[0]) + 1);
                            healthStartStr[1] = "01";
                        } else {
                            healthStartStr[1] = Integer.toString(Integer.parseInt(healthStartStr[1]) + 1);
                        }
                    }
                }
                healthInsurance = (int) Math.ceil(empHealthInsurance * 0.0517 * 0.3 * healthMonthCount);
                salaryStatistics.setHealthInsurance(healthInsurance);
            }

            //計算最後金額
            int leaveTotalRound = Math.round(salary / 30f / 8f * leaveTotal);
            int salaryTotalRound = Math.round(salary * salaryMultiply);

            //存入資料
            salaryStatistics.setId(0);
            salaryStatistics.setInfoId(salaryInfo.getInfoId());
            salaryStatistics.setEmpNo(employee.getEmpNo());
            salaryStatistics.setRoughSalary(salaryTotalRound);
            salaryStatistics.setLeaveCount(leaveTotalRound);
            salaryStatistics.setSalaryResult(salaryTotalRound - leaveTotalRound - workInsurance - healthInsurance);
            salaryStatistics.setHealthInsurance(healthInsurance);
            salaryStatistics.setWorkInsurance(workInsurance);
            salaryStatisticsDao.save(salaryStatistics);
        }
        return salaryInfo;
    }


}
