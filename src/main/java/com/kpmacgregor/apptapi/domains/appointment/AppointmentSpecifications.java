package com.kpmacgregor.apptapi.domains.appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.kpmacgregor.apptapi.domains.participant.Participant;
import com.kpmacgregor.apptapi.domains.participant.Participant_;
import com.kpmacgregor.apptapi.domains.topic.Topic;
import com.kpmacgregor.apptapi.domains.topic.Topic_;

public class AppointmentSpecifications {

    public static Specification<Appointment> matchesFilters(AppointmentSearchCriteria appointmentSearchCriteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(appointmentSearchCriteria.getTitles())) {
                predicates.add(
                        titleIsIn(appointmentSearchCriteria.getTitles())
                                .toPredicate(root, query, criteriaBuilder));
            }

            if (Objects.nonNull(appointmentSearchCriteria.getTopics())) {
                predicates.add(
                        topicsMatch(appointmentSearchCriteria.getTopics())
                                .toPredicate(root, query, criteriaBuilder));
            }

            if (Objects.nonNull(appointmentSearchCriteria.getParticipantNames())) {
                predicates.add(
                        anyNameIsIn(appointmentSearchCriteria.getParticipantNames())
                                .toPredicate(root, query, criteriaBuilder));
            }

            if (Objects.nonNull(appointmentSearchCriteria.getAfter())
                    && !appointmentSearchCriteria.getAfter().isEmpty()) {
                predicates.add(
                        startDateTimeIsAfter(appointmentSearchCriteria.getAfter())
                                .toPredicate(root, query, criteriaBuilder));
            }

            if (Objects.nonNull(appointmentSearchCriteria.getBefore())
                    && !appointmentSearchCriteria.getBefore().isEmpty()) {
                predicates.add(
                        endDateTimeIsBefore(appointmentSearchCriteria.getBefore())
                                .toPredicate(root, query, criteriaBuilder));
            }

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

    public static Specification<Appointment> firstNameIsIn(List<String> firstNames) {
        return (root, query, criteriaBuilder) -> {
            Join<Appointment, Participant> appointmentParticipants = root.join(Appointment_.PARTICIPANTS);
            return criteriaBuilder.in(appointmentParticipants.get(Participant_.FIRST_NAME)).value(firstNames);
        };
    }

    public static Specification<Appointment> lastNameIsIn(List<String> lastNames) {
        return (root, query, criteriaBuilder) -> {
            Join<Appointment, Participant> appointmentParticipants = root.join(Appointment_.PARTICIPANTS);
            return criteriaBuilder.in(appointmentParticipants.get(Participant_.LAST_NAME)).value(lastNames);
        };
    }

    public static Specification<Appointment> anyNameIsIn(List<String> names) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.or(
                    firstNameIsIn(names).toPredicate(root, query, criteriaBuilder),
                    lastNameIsIn(names).toPredicate(root, query, criteriaBuilder));
        };
    }

    public static Specification<Appointment> startDateTimeIsAfter(List<LocalDateTime> after) {
        // The appointment dates we select should be later than
        // all of the dates in the `List<LocalDateTime> after` parameter
        after.sort(Comparator.reverseOrder());
        LocalDateTime latestDateTime = after.get(0);
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get(Appointment_.START_DATE_TIME), latestDateTime);
        };
    }

    public static Specification<Appointment> endDateTimeIsBefore(List<LocalDateTime> before) {
        before.sort(Comparator.naturalOrder());
        LocalDateTime earliestDateTime = before.get(0);
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(root.get(Appointment_.END_DATE_TIME), earliestDateTime);
        };
    }
}
