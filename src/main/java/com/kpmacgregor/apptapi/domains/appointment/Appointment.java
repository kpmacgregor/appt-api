package com.kpmacgregor.apptapi.domains.appointment;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.kpmacgregor.apptapi.domains.entity.BaseEntity;
import com.kpmacgregor.apptapi.domains.participant.Participant;
import com.kpmacgregor.apptapi.domains.topic.Topic;

import lombok.Data;

@Data
@Entity
public class Appointment extends BaseEntity {
    private String title;

    @ManyToMany
    private List<Participant> participants;

    @ManyToMany
    private List<Topic> topics;

    @Column(length = 2048)
    private String description;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Appointment() {
    }

    public Appointment(String title, List<Participant> participants, List<Topic> topics, String description,
            LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.title = title;
        this.participants = participants;
        this.topics = topics;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
