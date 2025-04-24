package com.quyennv.lms.service;

import com.quyennv.lms.dto.course.*;
import com.quyennv.lms.entities.Course;
import com.quyennv.lms.entities.UserEnrollCourse;
import com.quyennv.lms.security.UserPrincipal;

import java.util.List;

public interface CourseService {

    CreateCourseResponse create(CreateCourseRequest request, UserPrincipal requester);

    CreateCourseResponse update(String id, UpdateCourseRequest request, UserPrincipal requester);

    Course getCourseBasic(String id, UserPrincipal requester);

    Course getCourseDetail(String id, UserPrincipal requester);

    List<Course> getPublicCourses(UserPrincipal requester);

    List<Course> getAllMyCourses(UserPrincipal requester);

    UserEnrollCourse registerCourse(RegisterCourseRequest request, UserPrincipal requester);

    void addStudents(String id, AddStudentToCourseRequest request, UserPrincipal requester);

    UserEnrollCourse updateStudentStatus(String courseId, String studentId, UpdateCourseStudent request, UserPrincipal requester);

    UserEnrollCourse removeStudent(String courseId, String studentId, UserPrincipal requester);

    List<UserEnrollCourse> getStudents(String courseId, UserPrincipal requester);

    List<Course> getMyRegisteredCourses(UserPrincipal requester);
}
