package com.quyennv.lms.constant.enums;

import lombok.Getter;

@Getter
public enum CourseLevel {
    BEGINNER("BEGINNER"),
    INTERMEDIATE("INTERMEDIATE"),
    ADVANCED("ADVANCED");

    private final String value;

    CourseLevel(String value) {
        this.value = value;
    }

    public static CourseLevel fromValue(String value) {
        for (CourseLevel level : CourseLevel.values()) {
            if (level.getValue().equalsIgnoreCase(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown course level: " + value);
    }
}
