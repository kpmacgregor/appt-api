package com.kpmacgregor.apptapi.domains.appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.kpmacgregor.apptapi.domains.topic.Topic;
import com.kpmacgregor.apptapi.domains.topic.Topic_;

public class AppointmentSpecifications {

    public static Specification<Appointment> matchesFilters(AppointmentSearchCriteria appointmentSearchCriteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(appointmentSearchCriteria.getTitles())) {
                predicates.add(
                        titleIsIn(appointmentSearchCriteria.getTitles()).toPredicate(root, query, criteriaBuilder));
            }

            if (Objects.nonNull(appointmentSearchCriteria.getTopics())) {
                predicates.add(
                        topicsMatch(appointmentSearchCriteria.getTopics()).toPredicate(root, query, criteriaBuilder));
            }

            System.out.println(predicates);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Appointment> titleIsIn(List<String> titles) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Appointment_.TITLE)).value(titles);
    }

    public static Specification<Appointment> topicsMatch(List<String> topics) {
        return (root, query, criteriaBuilder) -> {
            Join<Appointment, Topic> appointmentTopics = root.join(Appointment_.TOPICS);
            return criteriaBuilder.in(appointmentTopics.get(Topic_.NAME)).value(topics);
        };
    }
}
