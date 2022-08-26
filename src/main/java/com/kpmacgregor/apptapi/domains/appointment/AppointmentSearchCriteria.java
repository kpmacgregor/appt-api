package com.kpmacgregor.apptapi.domains.appointment;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentSearchCriteria {
    private List<String> titles;
    private List<String> topics;

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "AppointmentSearchCriteria [title=" + titles + ", topic=" + topics + "]";
    }

}
