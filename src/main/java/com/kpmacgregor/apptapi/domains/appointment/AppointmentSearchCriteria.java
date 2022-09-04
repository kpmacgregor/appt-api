package com.kpmacgregor.apptapi.domains.appointment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class AppointmentSearchCriteria {
    private List<String> titles;
    private List<String> topics;
    private List<String> participantNames;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private List<LocalDateTime> after;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private List<LocalDateTime> before;
}
