package com.kpmacgregor.apptapi.domains.appointment;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private Logger logger = Logger.getLogger(AppointmentController.class.getName());

    @Autowired
    private AppointmentSearchCriteriaRepository criteriaRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentSearchCriteriaRepository criteriaRepository,
            AppointmentRepository appointmentRepository,
            AppointmentServiceImpl appointmentService) {
        this.criteriaRepository = criteriaRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments(AppointmentSearchCriteria appointmentCriteria) {
        // List<Appointment> appointments =
        // appointmentService.getByTitles(appointmentCriteria.getTitles());
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
