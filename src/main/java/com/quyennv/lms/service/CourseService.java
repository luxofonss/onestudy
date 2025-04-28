package com.quyennv.lms.service;

import com.quyennv.lms.dto.course.*;
import com.quyennv.lms.entities.Course;
import com.quyennv.lms.entities.UserEnrollCourse;
import com.quyennv.lms.security.UserPrincipal;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    CreateCourseResponse create(CreateCourseRequest request, UserPrincipal requester);

    CreateCourseResponse update(UpdateCourseRequest request, UserPrincipal requester);

    Course getCourseBasic(UUID id, UserPrincipal requester);

    Course getCourseDetail(UUID id, UserPrincipal requester);

    List<Course> getPublicCourses(UserPrincipal requester);

    List<Course> getAllMyCourses(UserPrincipal requester);

    UserEnrollCourse registerCourse(RegisterCourseRequest request, UserPrincipal requester);

    void addStudents(UUID id, AddStudentToCourseRequest request, UserPrincipal requester);

    UserEnrollCourse updateStudentStatus(UUID courseId, UUID studentId, UpdateCourseStudent request, UserPrincipal requester);

    UserEnrollCourse removeStudent(UUID courseId, UUID studentId, UserPrincipal requester);

    List<UserEnrollCourse> getStudents(UUID courseId, UserPrincipal requester);

    List<Course> getMyRegisteredCourses(UserPrincipal requester);
}
