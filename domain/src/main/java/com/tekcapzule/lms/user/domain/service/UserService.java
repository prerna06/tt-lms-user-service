package com.tekcapzule.lms.user.domain.service;

import com.tekcapzule.lms.user.domain.command.*;
import com.tekcapzule.lms.user.domain.model.User;


public interface UserService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    void disable(DisableCommand disableCommand);

    void registerCourse(RegisterCourseCommand registerCourseCommand);

    void dereisterCourse(DeRegisterCourseCommand deRegisterCourseCommand);

    void followTopic(FollowTopicCommand followTopicCommand);

    void unfollowTopic(UnfollowTopicCommand unfollowTopicCommand);

    User get(String userId);

    int getAllUsersCount();
}
