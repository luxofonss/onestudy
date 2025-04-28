package com.quyennv.lms.service.impl;

import com.quyennv.lms.dto.assignment.CreateAssignmentRequest;
import com.quyennv.lms.dto.assignment.CreateAssignmentResponse;
import com.quyennv.lms.dto.assignment.UpdateAssignmentRequest;
import com.quyennv.lms.dto.assignment.QuestionMutationRequest;
import com.quyennv.lms.dto.mappers.AssignmentDtoMapper;
import com.quyennv.lms.entities.Assignment;
import com.quyennv.lms.entities.Course;
import com.quyennv.lms.repository.AssignmentRepository;
import com.quyennv.lms.repository.CourseRepository;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.AssignmentQuestionService;
import com.quyennv.lms.service.AssignmentService;
import com.quyennv.lms.service.QuestionService;
import com.quyennv.lms.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final CourseRepository courseRepository;

    private final AssignmentDtoMapper assignmentDtoMapper;

    private final QuestionService questionService;

    private final AssignmentQuestionService assignmentQuestionService;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository,
                                 CourseRepository courseRepository,
                                 AssignmentDtoMapper assignmentDtoMapper,
                                 QuestionService questionService,
                                 @Lazy AssignmentQuestionService assignmentQuestionService) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
        this.assignmentDtoMapper = assignmentDtoMapper;
        this.questionService = questionService;
        this.assignmentQuestionService = assignmentQuestionService;
    }

    @Override
    public CreateAssignmentResponse create(CreateAssignmentRequest request, UserPrincipal requester) {

        Course course = courseRepository.findByLessonId(UUID.fromString(request.getLessonId()));

        if (Objects.isNull(course)) {
            throw new RuntimeException("Course not found");
        }

        if (!course.getTeacherId().equals(requester.getId())) {
            throw new RuntimeException("You are not allowed to create assignment for this course");
        }
        Assignment assignment = assignmentDtoMapper.toAssignment(request);
        assignment.setTeacherId(requester.getId());
        assignment.setTotalPoint(0);
        assignmentRepository.save(assignment);

        return CreateAssignmentResponse.builder().id(assignment.getId().toString()).build();
    }

    @Override
    public CreateAssignmentResponse update(UpdateAssignmentRequest request, UserPrincipal requester) {
        Assignment assignment = checkAssignmentPermission(request.getAssignmentId(), requester);

        Assignment updateData = assignmentDtoMapper.toAssignment(request);
        BeanUtils.copyProperties(updateData, assignment, Utils.getBlankProperties(updateData));

        if (assignmentRepository.updateSelective(assignment) == 0) {
            throw new RuntimeException("Update assignment failed");
        }

        // update questions
        if (request.getQuestions() != null) {
            for (QuestionMutationRequest question : request.getQuestions()) {
                question.setAssignmentId(assignment.getId());

                if (question.getQuestionId() == null) {
                    // add new question
                    assignmentQuestionService.addQuestion(question, requester);
                } else {
                    // update existing question
                    assignmentQuestionService.updateQuestion(question, requester, false);
                }
            }
        }

        return CreateAssignmentResponse.builder().id(assignment.getId().toString()).build();
    }

    @Override
    public Assignment checkAssignmentPermission(UUID assignmentId, UserPrincipal requester) {
        Assignment assignment = assignmentRepository.findById(assignmentId);

        if (Objects.isNull(assignment)) {
            throw new RuntimeException("Assignment not found");
        }

        if (!assignment.getTeacherId().equals(requester.getId())) {
            throw new RuntimeException("You are not allowed to update this assignment");
        }
        return assignment;
    }

    @Override
    public Assignment getAssignmentByLesson(UUID uuid, UserPrincipal requester) {
        return assignmentRepository.findByLessonId(uuid);
    }

    @Override
    public void softDeleteAssignmentInLesson(UUID lessonId) {
        assignmentRepository.softDeleteByLessonId(lessonId);
    }

    @Override
    public Assignment getAssignment(UUID id, UserPrincipal requester) {
        Assignment assignment = assignmentRepository.findById(id);
        if (Objects.isNull(assignment)) {
            throw new RuntimeException("Assignment not found");
        }
        // TODO: handle permission

        // get questions
        assignment.setQuestions(questionService.getByAssignmentId(assignment.getId()));

        return assignment;
    }

}
