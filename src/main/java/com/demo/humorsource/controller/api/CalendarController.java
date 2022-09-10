package com.demo.humorsource.controller.api;

import com.demo.humorsource.model.YearCalendar;
import com.demo.humorsource.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    ////////// *行事曆*///////////
    @GetMapping("/calendars")
    public String getCalendarbyYearAndType(String year) throws ParseException {
        return calendarService.getCalendarbyYearAndType(year);
    }

    @PutMapping("/calendar")
    public YearCalendar updateCalendar(String id, int type, String note) {
        return calendarService.updateCalendar(id, type, note);
    }

}
