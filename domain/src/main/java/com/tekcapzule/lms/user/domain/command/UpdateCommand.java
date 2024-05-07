package com.tekcapzule.lms.user.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.Command;
import com.tekcapzule.lms.user.domain.model.Course;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UpdateCommand extends Command {
    private String userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
    private List<String> subscribedTopics;
    private List<Course> courses;
}
