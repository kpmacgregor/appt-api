package com.kpmacgregor.apptapi.domains.appointment;

import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

public class AppointmentSearchCriteria extends AbstractList<List<String>> {
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

    @Override
    public List<String> get(int arg0) {
        List<List<String>> properties = Arrays.asList(
                this.getTitles(),
                this.getTopics());
        return properties.get(arg0);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 2;
    }

}
