package com.kpmacgregor.apptapi.domains.appointment;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private Logger logger = Logger.getLogger(AppointmentController.class.getName());

    @Autowired
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments(AppointmentSearchCriteria appointmentCriteria) {
        logger.info("Request received: GET /appointments?" + appointmentCriteria.toString());
        List<Appointment> appointments = appointmentService.filterAppointments(appointmentCriteria);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // @GetMapping(value = "/{id}")
    // public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id)
    // {
    // Optional<Appointment> appointment = repository.findById(id);
    // if (appointment.isEmpty()) {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // return new ResponseEntity<Appointment>(appointment.get(), HttpStatus.OK);
    // }
}
