select appointment.id, appointment.title, topic."name" from appointment
    inner join appointment_topics ON appointment_topics.appointments_id = appointment.id
    inner join topic ON topic.id = appointment_topics.topics_id
    where topic."name" in ('java', 'react');