package com.kpmacgregor.apptapi.data;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;

import com.kpmacgregor.apptapi.domains.topic.Topic;

public class TopicFactory {
    public static List<Topic> createTopics() {
        String[] names = new String[] { "java", "c#", "react" };
        return Arrays.asList(names)
                .stream()
                .map(name -> new Topic(name))
                .collect(Collectors.toList());
    }
}
