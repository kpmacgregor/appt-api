package com.kpmacgregor.apptapi.domains.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.Specification.*;
import org.springframework.stereotype.Service;

import static com.kpmacgregor.apptapi.domains.appointment.AppointmentSpecifications.*;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getByTitles(List<String> titles) {
        return appointmentRepository.findAll(
                where(titleIsIn(titles))
        // .and()
        );
    }

}
