package com.tekcapzule.lms.user.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.AggregateRoot;
import com.tekcapzule.core.domain.BaseDomainEntity;
import lombok.*;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "User")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseDomainEntity implements AggregateRoot {

    @DynamoDBHashKey(attributeName="userId")
    private String userId;
    @DynamoDBAttribute(attributeName = "tenantId")
    private String tenantId;
    @DynamoDBAttribute(attributeName = "emailId")
    private String emailId;
    @DynamoDBAttribute(attributeName = "firstName")
    private String firstName;
    @DynamoDBAttribute(attributeName = "lastName")
    private String lastName;
    @DynamoDBAttribute(attributeName = "courses")
    private List<Course> courses;
    @DynamoDBAttribute(attributeName = "subscribedTopics")
    private List<String> subscribedTopics;
    @DynamoDBAttribute(attributeName = "phoneNumber")
    private String phoneNumber;
    @DynamoDBAttribute(attributeName = "activeSince")
    private String activeSince;
    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConvertedEnum
    private Status status;

}