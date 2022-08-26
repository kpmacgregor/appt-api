package com.kpmacgregor.apptapi.domains.appointment;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public class AppointmentSpecifications {
    public static Specification<Appointment> titleIsIn(List<String> titles) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Appointment_.TITLE)).value(titles);
    }
}
