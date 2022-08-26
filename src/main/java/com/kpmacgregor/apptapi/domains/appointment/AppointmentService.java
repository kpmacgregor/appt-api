package com.kpmacgregor.apptapi.domains.appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getByTitles(List<String> titles);
}
