package com.exposit.datetime_test.controller;

import com.exposit.datetime_test.model.Time;
import com.exposit.datetime_test.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.util.calendar.ZoneInfo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.function.Supplier;

@RestController
@RequestMapping("/times")
public class TimeController
{
    @Autowired
    private TimeRepository timeRepository;

    @GetMapping
    public ResponseEntity<List<Time>> getTimes(){
        return ResponseEntity.ok(timeRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Time> create(@RequestBody Time time, @RequestParam(required = false) String timezone){
        Time savedTime = doInTimezone(timezone, time, (time1) -> timeRepository.save(time1));
        return ResponseEntity.ok(savedTime);
    }

    private Time doInTimezone(String timezone, Time time, Function<Time, Time> timeTimeFunction){
        if (timezone == null) {
            return doWithTime(time, timeTimeFunction);
        }
        System.out.println("IN Old TZ without TZ:"  + time.getWithoutTz().toString());
        System.out.println("IN Old TZ  with TZ:"  + time.getWithTz().toString());
        TimeZone oldTz = TimeZone.getDefault();

        TimeZone.setDefault(TimeZone.getTimeZone(timezone));

        Time savedTime = doWithTime(time, timeTimeFunction);
        System.out.println("IN Old TZ without TZ:"  + savedTime.getWithoutTz().toString());
        System.out.println("IN Old TZ  with TZ:"  + savedTime.getWithTz().toString());

        TimeZone.setDefault(oldTz);
        return savedTime;
    }

    private Time doWithTime(Time time, Function<Time, Time> timeTimeFunction){
        return timeTimeFunction.apply(time);
    }

    @PostMapping("/now")
    public ResponseEntity<Time> createNow(){
        Time time = new Time();
        time.setWithoutTz(new Timestamp(new Date().getTime()));
        time.setWithTz(new Timestamp(new Date().getTime()));
        time.setDescription("NOW() from BackendServer");
        time.setTimezone(TimeZone.getDefault().toZoneId().getId());

        System.out.println("NOW without TZ:"  + time.getWithoutTz().toString());
        System.out.println("NOW with TZ:"  + time.getWithTz().toString());

        timeRepository.save(time);
        return ResponseEntity.ok(time);
    }

    @PostMapping("/change_timezone")
    public ResponseEntity<TimeZone> changeTimezone(@RequestBody String timezone){
        try {
            TimeZone.setDefault(TimeZone.getTimeZone(timezone));
            System.out.println(TimeZone.getTimeZone(timezone));
            System.out.println(ZoneInfo.getTimeZone(timezone));
            System.out.println(TimeZone.getDefault());
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<TimeZone>(TimeZone.getDefault(), HttpStatus.OK);
    }

    @GetMapping("/now")
    public ResponseEntity<Timestamp> now(){

        return ResponseEntity.ok(new Timestamp(new Date().getTime()));
    }
}
