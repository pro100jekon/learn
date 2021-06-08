package com.epam.smyrnov.module13.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {

    REJECTED, ACCEPTED;

    @JsonCreator
    public Status create(String type) {
        for (Status existingType : Status.values()) {
            if (existingType.toString().equals(type)) {
                return existingType;
            }
        }
        return null;
    }
}
