package com.tekcapzule.lms.user.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.lms.user.domain.model.Course;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class RegisterCourseInput {
    private String userId;
    private Course bookmark;
}
