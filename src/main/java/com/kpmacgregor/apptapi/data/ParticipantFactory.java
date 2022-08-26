package com.kpmacgregor.apptapi.data;

import java.util.ArrayList;
import java.util.List;

import com.kpmacgregor.apptapi.domains.participant.Participant;

public class ParticipantFactory {

    public static List<Participant> createParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Participant("Kyle", "MacGregor", "kpsmacgregor@gmail.com"));
        participants.add(new Participant("Kreelax", "Null", "kpsmacgregore@gmail.com"));
        participants.add(new Participant("Zoltan", "Barbar", "kpsmacgregor@gmail.com"));
        return participants;
    }
}
