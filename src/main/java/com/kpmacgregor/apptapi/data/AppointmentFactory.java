package com.kpmacgregor.apptapi.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.kpmacgregor.apptapi.domains.appointment.Appointment;
import com.kpmacgregor.apptapi.domains.participant.Participant;
import com.kpmacgregor.apptapi.domains.topic.Topic;

public class AppointmentFactory {

    private static Random random = new Random();
    private static List<String> lipsumParagraphs = Arrays.asList(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Id aliquet lectus proin nibh nisl condimentum id. Ac tortor vitae purus faucibus ornare suspendisse sed. Faucibus interdum posuere lorem ipsum dolor sit. Augue neque gravida in fermentum et sollicitudin ac. Lorem ipsum dolor sit amet consectetur. Lorem mollis aliquam ut porttitor leo. Duis at tellus at urna. Et sollicitudin ac orci phasellus egestas tellus rutrum. Aliquam id diam maecenas ultricies mi eget mauris pharetra et.",
            "Nullam eget felis eget nunc lobortis mattis. Sodales ut etiam sit amet nisl purus in. Sem fringilla ut morbi tincidunt augue. Mauris a diam maecenas sed enim ut sem. Commodo elit at imperdiet dui accumsan. Libero id faucibus nisl tincidunt eget nullam non. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Ullamcorper eget nulla facilisi etiam dignissim diam quis. In hendrerit gravida rutrum quisque non tellus orci ac auctor. Eu sem integer vitae justo eget magna. Venenatis tellus in metus vulputate eu scelerisque felis imperdiet proin. Lacus laoreet non curabitur gravida. Et malesuada fames ac turpis egestas sed. Fringilla urna porttitor rhoncus dolor purus non enim praesent. Viverra vitae congue eu consequat ac.",
            "Nibh nisl condimentum id venenatis a condimentum. Adipiscing elit duis tristique sollicitudin nibh sit amet commodo nulla. Lectus arcu bibendum at varius vel pharetra vel turpis nunc. Elementum facilisis leo vel fringilla est ullamcorper eget. Pellentesque massa placerat duis ultricies lacus sed. In metus vulputate eu scelerisque felis imperdiet proin. Diam maecenas sed enim ut sem viverra. Arcu dictum varius duis at consectetur lorem donec massa. Quisque sagittis purus sit amet volutpat. Venenatis urna cursus eget nunc scelerisque.",
            "Senectus et netus et malesuada fames ac turpis egestas integer. Aliquam nulla facilisi cras fermentum. Egestas egestas fringilla phasellus faucibus scelerisque eleifend donec pretium. Ac auctor augue mauris augue neque gravida in. Scelerisque eleifend donec pretium vulputate sapien nec sagittis aliquam malesuada. Rutrum tellus pellentesque eu tincidunt tortor aliquam nulla. Integer eget aliquet nibh praesent tristique. Augue mauris augue neque gravida in fermentum. Justo laoreet sit amet cursus sit amet. Scelerisque fermentum dui faucibus in. Vel elit scelerisque mauris pellentesque pulvinar pellentesque. Non tellus orci ac auctor augue mauris augue. Volutpat sed cras ornare arcu dui.",
            "Nunc mattis enim ut tellus elementum sagittis vitae. Aliquet nec ullamcorper sit amet risus nullam eget felis eget. Elit eget gravida cum sociis natoque penatibus. Nulla posuere sollicitudin aliquam ultrices sagittis. Porttitor eget dolor morbi non arcu risus quis varius. Venenatis lectus magna fringilla urna porttitor rhoncus dolor purus. Tellus in metus vulputate eu scelerisque. Arcu dui vivamus arcu felis bibendum ut. Nascetur ridiculus mus mauris vitae ultricies leo integer malesuada nunc. Vulputate ut pharetra sit amet aliquam id diam. Nunc faucibus a pellentesque sit amet porttitor. In tellus integer feugiat scelerisque. Eget arcu dictum varius duis at. Justo donec enim diam vulputate ut pharetra sit amet. Mauris augue neque gravida in fermentum et sollicitudin.");

    public static List<Appointment> generateRandomAppointments(int numAppts, List<Participant> participants,
            List<Topic> topics) {
        List<Appointment> appointments = new ArrayList<>();
        for (int i = 0; i < numAppts; ++i) {
            appointments.add(createRandomAppointment(Long.valueOf(i), participants, topics));
        }
        return appointments;
    }

    public static Appointment createRandomAppointment(Long id, List<Participant> participants, List<Topic> topics) {
        List<Participant> pList = getParticipants(participants);
        String title = getTitle(pList);
        LocalDateTime sTime = getStartTime();
        LocalDateTime eTime = getEndTime(sTime);
        List<Topic> selectedTopics = getTopics(topics);
        String description = getDescription();
        return new Appointment(title, pList, selectedTopics, description, sTime, eTime);
    }

    private static String getDescription() {
        return lipsumParagraphs.get(random.nextInt(lipsumParagraphs.size()));
    }

    private static List<Topic> getTopics(List<Topic> topics) {
        int nTopics = random.nextInt(topics.size());
        List<Topic> selectedTopics = new ArrayList<>();
        while (selectedTopics.size() < nTopics) {
            selectedTopics.add(topics.get(random.nextInt(topics.size())));
        }
        return selectedTopics;
    }

    private static LocalDateTime getEndTime(LocalDateTime sTime) {
        return sTime.plusMinutes(random.nextInt(15, 120));
    }

    private static LocalDateTime getStartTime() {
        return LocalDateTime.now().minusDays(random.nextInt(-1000, 1000));
    }

    private static String getTitle(List<Participant> pList) {
        return pList.stream().map(p -> p.getFirstName()).collect(Collectors.joining(" / ")).toString();
    }

    private static List<Participant> getParticipants(List<Participant> participants) {
        Set<Participant> pSet = new HashSet<>();
        while (pSet.size() < 2) {
            pSet.add(participants.get(random.nextInt(participants.size())));
        }
        return new ArrayList<Participant>(pSet);
    }
}
