package com.tekcapzule.lms.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    @Getter
    private String value;
}