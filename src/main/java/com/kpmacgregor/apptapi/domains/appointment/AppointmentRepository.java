package com.kpmacgregor.apptapi.domains.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {

    // @Query("select a from Appointment a "
    // + "inner join a.topics t "
    // + "where t.name in :topicNames")
    // List<Appointment> filterAppointmentsByTopics(@Param("topicNames")
    // List<String> topicNames);
}
