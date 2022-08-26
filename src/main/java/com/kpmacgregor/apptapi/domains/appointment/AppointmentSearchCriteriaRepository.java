package com.kpmacgregor.apptapi.domains.appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class AppointmentSearchCriteriaRepository {
    private Logger logger = Logger.getLogger(AppointmentSearchCriteriaRepository.class);
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public AppointmentSearchCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Appointment> findAllWithFilters(AppointmentSearchCriteria appointmentSearchCriteria) {
        CriteriaQuery<Appointment> criteriaQuery = criteriaBuilder.createQuery(Appointment.class);
        Root<Appointment> appointmentRoot = criteriaQuery.from(Appointment.class);
        Predicate predicate = getPredicate(appointmentSearchCriteria, appointmentRoot);
        criteriaQuery.where(predicate);

        // Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
        // Root<Long> subRoot = subquery.from();
        // subquery.select(expression)
        // criteriaQuery.select(appointmentRoot)
        // .where(appointmentRoot.get("id"))
        // .in

        TypedQuery<Appointment> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    private Predicate getPredicate(AppointmentSearchCriteria searchCriteria, Root<Appointment> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(searchCriteria.getTitles())) {
            In<List<String>> predicate = this.criteriaBuilder.in(root.get("title"));
            predicate.value(searchCriteria.getTitles());
            predicates.add(predicate);
        }

        if (Objects.nonNull(searchCriteria.getTopics())) {
            In<Long> inClause = criteriaBuilder.in(root.get("id"));

            /*
             * We have a list of topics we're searching for,
             ** and each Appointment has a list of topics.
             ** If the intersection of those lists is non empty,
             ** we want to match that appointment.
             */
            // root.get
            // List<String> topics = root.<List<String>>get("topics");

            // List<String> intersection = searchCriteria.getTopics()
            // .stream()
            // .filter(topics::contains)
            // .collect(Collectors.toList());

            // Predicate predicate = this.criteriaBuilder.isNotEmpty(
            // root.get(root.getModel().getList("topics").

            // ));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
