package com.quyennv.lms.config;

public interface TestInterface {
    default String getValue() {
        return "Hello";
    }
}
