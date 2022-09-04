package com.kpmacgregor.apptapi.data;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.kpmacgregor.apptapi.domains.appointment.Appointment;
import com.kpmacgregor.apptapi.domains.appointment.AppointmentRepository;
import com.kpmacgregor.apptapi.domains.participant.Participant;
import com.kpmacgregor.apptapi.domains.participant.ParticipantRepository;
import com.kpmacgregor.apptapi.domains.topic.Topic;
import com.kpmacgregor.apptapi.domains.topic.TopicRepository;

@Component
public class DataLoader implements CommandLineRunner {

    Logger logger = Logger.getLogger(DataLoader.class.getName());

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    AppointmentRepository appointmentRespository;

    @Autowired
    Environment env;

    @Override
    public void run(String... args) throws Exception {
        List<Participant> participants = ParticipantFactory.createParticipants();
        participantRepository.saveAll(participants);
        List<Participant> savedParticipants = participantRepository.findAll();
        logger.info("Loaded " + savedParticipants.size() + " participants.");

        List<Topic> topics = TopicFactory.createTopics();
        topicRepository.saveAll(topics);
        List<Topic> savedTopics = topicRepository.findAll();
        logger.info("Loaded " + savedTopics.size() + " topics.");

        List<Appointment> appointments = AppointmentFactory.generateRandomAppointments(50, savedParticipants,
                savedTopics);
        appointments.get(0).setTitle("first");
        appointments.get(1).setTitle("second");

        appointmentRespository.saveAll(appointments);
        List<Appointment> savedAppointments = appointmentRespository.findAll();
        logger.info("Loaded " + savedAppointments.size() + " appointments.");

        logger.info("Using spring.datasource.url=" + env.getProperty("SPRING_DATASOURCE_URL"));
    }

}
