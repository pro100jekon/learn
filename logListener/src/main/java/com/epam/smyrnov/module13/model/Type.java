package com.epam.smyrnov.module13.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Type {

    COUNTABLE, LIQUID;

    @JsonCreator
    public Type create(String type) {
        for (Type existingType : Type.values()) {
            if (existingType.toString().equals(type)) {
                return existingType;
            }
        }
        return null;
    }
}
