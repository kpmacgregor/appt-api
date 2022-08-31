package com.kpmacgregor.apptapi.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpmacgregor.apptapi.domains.appointment.Appointment;
import com.kpmacgregor.apptapi.domains.topic.Topic;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getAppointments_Returns200() throws Exception {
        this.mvc.perform(get("/appointments")).andExpect(status().isOk());
    }

    @Test
    public void getAppointments_ReturnsArray() throws Exception {
        MvcResult result = this.mvc.perform(get("/appointments"))
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<Appointment> resultList = mapper.readValue(json,
                mapper.getTypeFactory().constructCollectionType(List.class, Appointment.class));
        assertTrue(resultList.size() > 0);
    }

    @Test
    public void getAppointmentsByTitles_WithSingleTitle_ReturnsMatchingAppointments() throws Exception {
        MvcResult result = this.mvc.perform(get("/appointments?titles=first"))
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<Appointment> resultList = mapper.readValue(json,
                mapper.getTypeFactory().constructCollectionType(List.class, Appointment.class));
        for (Appointment a : resultList) {
            assertEquals("first", a.getTitle());
        }
    }

    @Test
    public void getAppointmentsByTitles_WithMultipleTitles_ReturnsMatchingAppointments() throws Exception {
        List<String> titles = Arrays.asList("first", "second");
        String query = "?";
        for (String title : titles) {
            query += "titles=" + title + "&";
        }
        MvcResult result = this.mvc.perform(get("/appointments" + query))
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<Appointment> resultList = mapper.readValue(json,
                mapper.getTypeFactory().constructCollectionType(List.class, Appointment.class));
        for (Appointment a : resultList) {
            assertTrue(titles.contains(a.getTitle()));
        }
    }

    @Test
    public void getAppointmentsByTopics_WithSingleTopic_ReturnsMatchingAppointments() throws Exception {
        List<String> topics = Arrays.asList("java");
        String query = "?";
        for (String topic : topics) {
            query += "topics=" + topic + "&";
        }
        MvcResult result = this.mvc.perform(get("/appointments" + query))
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<Appointment> resultList = mapper.readValue(json,
                mapper.getTypeFactory().constructCollectionType(List.class, Appointment.class));
        for (Appointment a : resultList) {
            List<Topic> intersection = a.getTopics()
                    .stream()
                    .filter(topic -> topics.contains(topic.getName()))
                    .collect(Collectors.toList());
            assertTrue(intersection.size() > 0);
        }
    }

    @Test
    public void getAppointmentsByTopics_WithMultipleTopics_ReturnsMatchingAppointments() throws Exception {
        List<String> topics = Arrays.asList("java", "react");
        String query = "?";
        for (String topic : topics) {
            query += "topics=" + topic + "&";
        }

        MvcResult result = this.mvc.perform(get("/appointments" + query))
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<Appointment> resultList = mapper.readValue(json,
                mapper.getTypeFactory().constructCollectionType(List.class, Appointment.class));

        for (Appointment a : resultList) {
            List<Topic> intersection = a.getTopics()
                    .stream()
                    .filter(topic -> topics.contains(topic.getName()))
                    .collect(Collectors.toList());
            assertTrue(intersection.size() > 0);
        }
    }
}
