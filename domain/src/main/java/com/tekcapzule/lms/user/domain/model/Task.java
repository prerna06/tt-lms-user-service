package com.tekcapzule.lms.user.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.AggregateRoot;
import com.tekcapzule.core.domain.BaseDomainEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Task {
    private String taskId;
    private Date taskStartDate;
    private Date taskEndDate;
    private Double completionPercentage;
    /*@DynamoDBAttribute(attributeName = "progressInformation")
    private ProgressInformation progressInformation;*/
    private Status status;
}