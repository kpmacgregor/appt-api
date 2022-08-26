package com.kpmacgregor.apptapi.domains.appointment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {

    @Query("select a from Appointment a "
            + "inner join a.topics t "
            + "where t.name in :topicNames")
    List<Appointment> filterAppointmentsByTopics(@Param("topicNames") List<String> topicNames);
}
