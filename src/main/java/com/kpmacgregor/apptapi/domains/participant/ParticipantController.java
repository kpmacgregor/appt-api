package com.kpmacgregor.apptapi.domains.participant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpmacgregor.apptapi.domains.appointment.Appointment;

@RestController
@RequestMapping("/participants")
public class ParticipantController {
    @Autowired
    ParticipantRepository participantRepository;

    public ParticipantController(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @GetMapping
    public ResponseEntity<List<Participant>> getParticipants() {
        return new ResponseEntity<>(participantRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        Optional<Participant> participant = participantRepository.findById(id);
        if (participant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(participant.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsByParticipantId(@PathVariable Long id) {
        Optional<Participant> participant = participantRepository.findById(id);
        if (participant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Appointment> appointments = participant.get().getAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
