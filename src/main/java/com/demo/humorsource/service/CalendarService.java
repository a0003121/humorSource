package com.demo.humorsource.service;

import com.demo.humorsource.dao.YearCalendarDao;
import com.demo.humorsource.model.YearCalendar;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class
CalendarService {
    private final YearCalendarDao yearCalendarDAO;

    public String getCalendarbyYearAndType(String year) throws ParseException {
        SimpleDateFormat converter = new SimpleDateFormat("yyyyMMdd");
        java.util.Date dateStart = converter.parse(year + "0101");
        java.util.Date dateEnd = converter.parse(year + "1231");

        List<YearCalendar> yearCalendars = yearCalendarDAO.findByDateBetween(new java.sql.Date(dateStart.getTime()),
                new java.sql.Date(dateEnd.getTime()), Sort.by("date").ascending());

        JSONArray jsonArray = new JSONArray();
        for (YearCalendar yearCalendar : yearCalendars) {
            JSONObject obj = new JSONObject();
            obj.put("id", yearCalendar.getId());
            obj.put("title", yearCalendar.getNote());
            obj.put("start", yearCalendar.getDate());
            obj.put("type", yearCalendar.getType());
            if (yearCalendar.getType() == 0) {
                obj.put("color", "#3f51b5");
            } else {
                obj.put("color", "#f8610b");
            }
            jsonArray.put(obj);
        }

        return jsonArray.toString();
    }

    public YearCalendar updateCalendar(String id, int type, String note) {
        YearCalendar yearCalendar = yearCalendarDAO.findById(Integer.parseInt(id)).get();
        yearCalendar.setType(type);
        yearCalendar.setNote(note);
        return yearCalendarDAO.save(yearCalendar);
    }

    public double getWorkingHour(Long startTime, Long endTime) {
        // ??????
        float startBreak = 12f; // ????????????
        float endBreak = 13f; // ????????????
        float workHourStart = 9; // ????????????
        float workHourEnd = 18; // ????????????
        Date startDate = new Date(startTime);
        Date endDate = new Date(endTime);
        java.util.Calendar startCal = new GregorianCalendar();
        startCal.setTimeInMillis(startTime);
        java.util.Calendar endCal = new GregorianCalendar();
        endCal.setTimeInMillis(endTime);
        float leaveStart = startCal.get(java.util.Calendar.HOUR_OF_DAY)
                + (startCal.get(java.util.Calendar.MINUTE) / 60f);
        float leaveEnd = endCal.get(java.util.Calendar.HOUR_OF_DAY) + (endCal.get(java.util.Calendar.MINUTE) / 60f);
        float totalHours = 0;
        // ??????????????????????????????
        leaveStart = leaveStart < workHourStart ? workHourStart : leaveStart;
        // ??????????????????????????????
        leaveEnd = leaveEnd > workHourEnd ? workHourEnd : leaveEnd;
        // ?????????????????????
        leaveStart = leaveStart > startBreak && leaveStart < endBreak ? startBreak : leaveStart;
        // ?????????????????????
        leaveEnd = leaveEnd > startBreak && leaveEnd < endBreak ? endBreak : leaveEnd;

        List<YearCalendar> yearCalendars = yearCalendarDAO.findByDateBetween(startDate, endDate, Sort.by("date").ascending());

        // ????????????????????????
        if (yearCalendars.size() == 0 || (yearCalendars.size() == 1 && yearCalendars.get(0).getType() == 2)) {
            return 0;
        }

        // ???????????????????????????
        if (yearCalendars.size() == 1) {

            // ??????????????????????????????
            if (leaveStart > workHourEnd || leaveEnd < workHourStart) {
                return 0;
            }

            // ??????????????????????????????????????????
            if (leaveEnd <= startBreak)
                totalHours = leaveEnd - leaveStart;
                // ??????????????????????????????????????????
            else if (leaveStart >= endBreak)
                totalHours = leaveEnd - leaveStart;
                // ????????????
            else
                totalHours = leaveEnd - leaveStart - (endBreak - startBreak);

            return Math.ceil(totalHours * 2) / 2;
        }

        // ??????????????????????????????
        for (YearCalendar yearCalendar : yearCalendars) {
            if (yearCalendar.getType() == 0) { // ?????????

                java.util.Calendar checkCal = new GregorianCalendar();
                checkCal.setTime(yearCalendar.getDate());

                if (startCal.get(java.util.Calendar.YEAR) == checkCal.get(java.util.Calendar.YEAR)
                        && startCal.get(java.util.Calendar.MONTH) == checkCal.get(java.util.Calendar.MONTH)
                        && startCal.get(java.util.Calendar.DATE) == checkCal.get(java.util.Calendar.DATE)) {

                    if (leaveStart > workHourEnd)
                        leaveStart = workHourEnd;
                    // ??????????????????????????????
                    if (leaveStart <= startBreak) {
                        totalHours += workHourEnd - leaveStart - (endBreak - startBreak);
                    } else {// ??????????????????????????????
                        totalHours += workHourEnd - leaveStart;
                    }

                } else if (endCal.get(java.util.Calendar.YEAR) == checkCal.get(java.util.Calendar.YEAR)
                        && endCal.get(java.util.Calendar.MONTH) == checkCal.get(java.util.Calendar.MONTH)
                        && endCal.get(java.util.Calendar.DATE) == checkCal.get(java.util.Calendar.DATE)) {

                    if (leaveEnd < workHourStart)
                        leaveEnd = workHourStart;
                    // ??????????????????????????????
                    if (leaveEnd <= startBreak) {
                        totalHours += leaveEnd - workHourStart;
                    } else { // ??????????????????????????????
                        totalHours += leaveEnd - workHourStart - (endBreak - startBreak);
                    }
                } else {
                    totalHours += 8;
                }
            }
        }
        return Math.ceil(totalHours * 2) / 2;
    }

    public List<YearCalendar> findByDateBetween(long dateStart, long dateEnd, Sort sort) {
        return yearCalendarDAO.findByDateBetween(new Date(dateStart),
                new Date(dateEnd), sort);
    }

}
