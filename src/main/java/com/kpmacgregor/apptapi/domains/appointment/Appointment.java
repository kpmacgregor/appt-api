package com.kpmacgregor.apptapi.domains.appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.kpmacgregor.apptapi.domains.entity.BaseEntity;
import com.kpmacgregor.apptapi.domains.participant.Participant;
import com.kpmacgregor.apptapi.domains.topic.Topic;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "Appointment [id=" + this.getId() + ", description=" + description + ", endDateTime=" + endDateTime
                + ", participants="
                + participants.toString() + ", startDateTime=" + startDateTime + ", title=" + title + ", topics="
                + topics + "]";
    }

}
