package com.quyennv.lms.repository;

import com.quyennv.lms.constant.enums.CourseLevel;
import com.quyennv.lms.dto.course.CreateCourseRequest;
import com.quyennv.lms.dto.course.UpdateCourseRequest;
import com.quyennv.lms.entities.Course;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository {

    int persist(Course course);

    Optional<Course> findById(String id);

    Optional<Course> findByCode(String code);

    List<Course> getWithFilters(
            String keyword,
            CourseLevel level,
            Integer grade,
            String code,
            List<String> teacherId
    );

    void updateCourse(Course updatedCourse, UpdateCourseRequest request);

    List<Course> findByTeacherId(UUID id);

    List<Course> getRegisteredCourses(UUID userId);

    Course findByLessonId(UUID lessonId);

}