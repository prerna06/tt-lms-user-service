package com.tekcapzule.lms.user.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.Command;
import com.tekcapzule.lms.user.domain.model.Course;
import lombok.Builder;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class RegisterCourseCommand extends Command {
    private String userId;
    private Course course;
}
