package com.quyennv.lms.controller;

import com.quyennv.lms.dto.BaseResponse;
import com.quyennv.lms.entities.Subject;
import com.quyennv.lms.service.BaseService;
import com.quyennv.lms.service.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    private final BaseService baseService;

    public SubjectController(SubjectService subjectService, BaseService baseService) {
        this.subjectService = subjectService;
        this.baseService = baseService;
    }

    @GetMapping
    public BaseResponse<List<Subject>> getAllSubjects() {
        return baseService.ofSucceeded(subjectService.getAllSubjects());
    }

}
