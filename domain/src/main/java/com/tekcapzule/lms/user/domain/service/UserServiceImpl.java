package com.tekcapzule.lms.user.domain.service;

import com.tekcapzule.lms.user.domain.command.*;
import com.tekcapzule.lms.user.domain.model.Course;
import com.tekcapzule.lms.user.domain.model.Status;
import com.tekcapzule.lms.user.domain.model.User;
import com.tekcapzule.lms.user.domain.repository.UserDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserDynamoRepository userDynamoRepository;

    @Autowired
    public UserServiceImpl(UserDynamoRepository userDynamoRepository) {
        this.userDynamoRepository = userDynamoRepository;
    }

    @Override
    public void create(CreateCommand createCommand) {

        log.info(String.format("Entering create user service - Phone No.:%s", createCommand.getPhoneNumber()));
        log.info(String.format("Entering create user service - User Id:%s", createCommand.getEmailId()));


        User user = User.builder()
                .userId(createCommand.getEmailId())
                .emailId(createCommand.getEmailId())
                .firstName(createCommand.getFirstName())
                .lastName(createCommand.getLastName())
                .phoneNumber(createCommand.getPhoneNumber())
                .activeSince(DateTime.now(DateTimeZone.UTC).toString())
                .status(Status.ACTIVE)
                .build();

        user.setAddedOn(createCommand.getExecOn());
        user.setUpdatedOn(createCommand.getExecOn());
        user.setAddedBy(createCommand.getExecBy().getUserId());

        userDynamoRepository.save(user);
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update user service - User Id:%s", updateCommand.getUserId()));

        User user = userDynamoRepository.findBy(updateCommand.getUserId());
        if (user != null) {
            user.setEmailId(updateCommand.getEmailId());
            user.setFirstName(updateCommand.getFirstName());
            user.setLastName(updateCommand.getLastName());
            user.setPhoneNumber(updateCommand.getPhoneNumber());
            user.setSubscribedTopics(updateCommand.getSubscribedTopics());
            user.setCourses(updateCommand.getCourses());
            user.setUpdatedOn(updateCommand.getExecOn());
            user.setUpdatedBy(updateCommand.getExecBy().getUserId());
            userDynamoRepository.save(user);
        }
    }

    @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable user service - User Id:%s", disableCommand.getUserId()));

        User user = userDynamoRepository.findBy(disableCommand.getUserId());
        if (user != null) {

            user.setStatus(Status.INACTIVE);

            user.setUpdatedOn(disableCommand.getExecOn());
            user.setUpdatedBy(disableCommand.getExecBy().getUserId());

            userDynamoRepository.save(user);
        }
    }

    @Override
    public void registerCourse(RegisterCourseCommand registerCourseCommand) {

        log.info(String.format("Entering register course service - User Id:%s, course Id:%s, course name:%s", registerCourseCommand.getUserId(),
                registerCourseCommand.getCourse().getCourseId(), registerCourseCommand.getCourse().getCourseName()));

        User user = userDynamoRepository.findBy(registerCourseCommand.getUserId());
        if (user != null) {
            List<Course> courses = new ArrayList<>();
            if ( user.getCourses() != null) {
                courses.addAll(user.getCourses());
            }
            courses.add(registerCourseCommand.getCourse());
            user.setCourses(courses);

            user.setUpdatedOn(registerCourseCommand.getExecOn());
            user.setUpdatedBy(registerCourseCommand.getExecBy().getUserId());

            userDynamoRepository.save(user);
        }
    }

    @Override
    public void dereisterCourse(DeRegisterCourseCommand deRegisterCourseCommand) {

        log.info(String.format("Entering remove bookmark service - User Id:%s, course Id:%s", deRegisterCourseCommand.getUserId(),
                deRegisterCourseCommand.getCourse().getCourseId()));

        User user = userDynamoRepository.findBy(deRegisterCourseCommand.getUserId());
        if (user != null) {
            List<Course> courses = user.getCourses();
            if ( courses != null) {
                courses.removeIf(course -> course.getCourseId().equals(deRegisterCourseCommand.getCourse().getCourseId()));
                user.setCourses(courses);
            }

            user.setUpdatedOn(deRegisterCourseCommand.getExecOn());
            user.setUpdatedBy(deRegisterCourseCommand.getExecBy().getUserId());

            userDynamoRepository.save(user);
        }
    }

    @Override
    public void followTopic(FollowTopicCommand followTopicCommand) {
        log.info(String.format("Entering follow topic service - User Id:%s, Topic Code:%s", followTopicCommand.getUserId(), followTopicCommand.getTopicCodes()));

        User user = userDynamoRepository.findBy(followTopicCommand.getUserId());
        if (user != null) {

            List<String> followedTopics = new ArrayList<>();
            followedTopics.addAll(followTopicCommand.getTopicCodes());
            user.setSubscribedTopics(followedTopics);

            user.setUpdatedOn(followTopicCommand.getExecOn());
            user.setUpdatedBy(followTopicCommand.getExecBy().getUserId());

            userDynamoRepository.save(user);
        }
    }

    @Override
    public void unfollowTopic(UnfollowTopicCommand unfollowTopicCommand) {
        log.info(String.format("Entering unfollow topic service - User Id:%s, Topic Code:%s", unfollowTopicCommand.getUserId(), unfollowTopicCommand.getTopicCodes()));

        User user = userDynamoRepository.findBy(unfollowTopicCommand.getUserId());
        if (user != null) {

            List<String> followedTopics = new ArrayList<>();
            if (user.getSubscribedTopics() != null) {
                followedTopics = user.getSubscribedTopics();
            }

            followedTopics.removeAll(unfollowTopicCommand.getTopicCodes());
            user.setSubscribedTopics(followedTopics);

            user.setUpdatedOn(unfollowTopicCommand.getExecOn());
            user.setUpdatedBy(unfollowTopicCommand.getExecBy().getUserId());

            userDynamoRepository.save(user);
        }
    }

    @Override
    public User get(String userId) {

        log.info(String.format("Entering get user service - User Id:%s", userId));

        return userDynamoRepository.findBy(userId);
    }

    @Override
    public int getAllUsersCount() {
        log.info("Entering getall users count service");
        return userDynamoRepository.getAllUsersCount();
    }


}
