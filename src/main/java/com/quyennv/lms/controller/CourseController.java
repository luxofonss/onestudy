package com.quyennv.lms.controller;

import com.quyennv.lms.annotations.CurrentUser;
import com.quyennv.lms.annotations.LogsActivityAnnotation;
import com.quyennv.lms.dto.BaseResponse;
import com.quyennv.lms.dto.course.*;
import com.quyennv.lms.entities.Course;
import com.quyennv.lms.entities.UserEnrollCourse;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.BaseService;
import com.quyennv.lms.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses")
@Slf4j
public class CourseController {

    private final BaseService baseService;

    private final CourseService courseService;

    public CourseController(BaseService baseService, CourseService courseService) {
        this.baseService = baseService;
        this.courseService = courseService;
    }

    @PostMapping
    public BaseResponse<CreateCourseResponse> create(
            @CurrentUser UserPrincipal requester,
            @RequestBody @Valid CreateCourseRequest request) {
        return baseService.ofSucceeded(courseService.create(request, requester));
    }

    @PutMapping("/{id}")
    public BaseResponse<CreateCourseResponse> update(
            @CurrentUser UserPrincipal requester,
            @PathVariable String id,
            @RequestBody @Valid UpdateCourseRequest request) {
        request.setCourseId(UUID.fromString(id));
        return baseService.ofSucceeded(courseService.update(request, requester));
    }

    @GetMapping("/{id}")
    @LogsActivityAnnotation
    public BaseResponse<Course> getCourseBasic(
            @CurrentUser UserPrincipal requester,
            @PathVariable UUID id) {
        return baseService.ofSucceeded(courseService.getCourseBasic(id, requester));
    }

    @GetMapping("/{id}/detail")
    public BaseResponse<Course> getCourseDetail(
            @CurrentUser UserPrincipal requester,
            @PathVariable UUID id) {
        return baseService.ofSucceeded(courseService.getCourseDetail(id, requester));
    }

    @GetMapping("/public")
    public BaseResponse<List<Course>> getPublicCourses(
            @CurrentUser UserPrincipal requester) {
        return baseService.ofSucceeded(courseService.getPublicCourses(requester));
    }

    @GetMapping("/my-courses")
    public BaseResponse<List<Course>> getAllMyCourses(
            @CurrentUser UserPrincipal requester) {
        return baseService.ofSucceeded(courseService.getAllMyCourses(requester));
    }

    @PostMapping("/register")
    public BaseResponse<UserEnrollCourse> registerCourse(
            @CurrentUser UserPrincipal requester,
            @RequestBody @Valid RegisterCourseRequest request) {
        return baseService.ofSucceeded(courseService.registerCourse(request, requester));
    }

    @PostMapping("/{id}/students")
    @LogsActivityAnnotation
    public BaseResponse<UserEnrollCourse> addStudents(
            @CurrentUser UserPrincipal requester,
            @PathVariable UUID id,
            @RequestBody @Valid AddStudentToCourseRequest request) {
        courseService.addStudents(id, request, requester);
        return baseService.ofSucceeded(null);
    }

    @PutMapping("/{id}/students/{studentId}")
    public BaseResponse<UserEnrollCourse> updateStudentStatus(
            @CurrentUser UserPrincipal requester,
            @PathVariable UUID id,
            @PathVariable UUID studentId,
            @RequestBody @Valid UpdateCourseStudent request) {
        return baseService.ofSucceeded(courseService.updateStudentStatus(id, studentId, request, requester));
    }

    @DeleteMapping("/{id}/students/{studentId}")
    public BaseResponse<UserEnrollCourse> removeStudent(
            @CurrentUser UserPrincipal requester,
            @PathVariable UUID id,
            @PathVariable UUID studentId) {
        return baseService.ofSucceeded(courseService.removeStudent(id, studentId, requester));
    }

    @GetMapping("/{id}/students")
    public BaseResponse<List<UserEnrollCourse>> getStudents(
            @CurrentUser UserPrincipal requester,
            @PathVariable UUID id) {
        return baseService.ofSucceeded(courseService.getStudents(id, requester));
    }

    @GetMapping("/my-registered-courses")
    public BaseResponse<List<Course>> getAllMyRegisteredCourses(
            @CurrentUser UserPrincipal requester) {
        return baseService.ofSucceeded(courseService.getMyRegisteredCourses(requester));
    }

}
