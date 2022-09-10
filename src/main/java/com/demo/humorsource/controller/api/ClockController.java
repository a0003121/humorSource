package com.demo.humorsource.controller.api;

import com.demo.humorsource.model.ClockRaw;
import com.demo.humorsource.service.ClockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
public class ClockController {
    private final ClockService clockService;

    /////////////////打卡紀錄///////////////////

    //取得個人某日的打卡和請假紀錄
    @GetMapping("/clockRecord")
    public String getRecordByEmpnoAndDate(int empNo, String date) throws ParseException {
        return clockService.getRecordByEmpnoAndDate(empNo, date);
    }

    //取得打卡紀錄
    @GetMapping("/clock")
    public String getClock(String start, String end) throws ParseException {
        return clockService.getClock(start, end);
    }

    //檔案範本
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        clockService.downloadTemplate(response);
    }

    //打卡紀錄上傳
    @PostMapping("/fileUpload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipart, Boolean send) throws IOException {
        return clockService.uploadFile(multipart, send);
    }

    //打卡比對
    @Transactional
    @GetMapping("/compareClock")
    public ResponseEntity<String> compareClock(String end, String start) throws ParseException {
        clockService.compareClock(end, start);
        return ResponseEntity.ok("{\"status\":\"success!\"}");
    }

    @PostMapping("/clockRaw")
    public ClockRaw createClockTime(int empNo, String time) throws ParseException {
        return clockService.createClockTime(empNo, time);
    }

    @DeleteMapping("/clockRaw")
    public ResponseEntity<String> deleteEmployee(int id) {
        clockService.deleteEmployee(id);
        return ResponseEntity.ok("{\"delete\":\"success!\"}");
    }
}
