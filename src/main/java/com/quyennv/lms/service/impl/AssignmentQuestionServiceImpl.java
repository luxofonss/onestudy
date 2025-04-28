package com.quyennv.lms.service.impl;

import com.quyennv.lms.dto.assignment.DeleteQuestionRequest;
import com.quyennv.lms.dto.assignment.QuestionMutationRequest;
import com.quyennv.lms.dto.mappers.QuestionDtoMapper;
import com.quyennv.lms.entities.Assignment;
import com.quyennv.lms.entities.Question;
import com.quyennv.lms.entities.QuestionChoice;
import com.quyennv.lms.entities.QuestionTextAnswer;
import com.quyennv.lms.repository.QuestionChoiceRepository;
import com.quyennv.lms.repository.QuestionRepository;
import com.quyennv.lms.repository.QuestionTextAnswerRepository;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.AssignmentQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class AssignmentQuestionServiceImpl implements AssignmentQuestionService {

    private final AssignmentServiceImpl assignmentService;

    private final QuestionRepository questionRepository;

    private final QuestionTextAnswerRepository questionTextAnswerRepository;

    private final QuestionChoiceRepository questionChoiceRepository;

    private final QuestionDtoMapper questionDtoMapper;

    public AssignmentQuestionServiceImpl(AssignmentServiceImpl assignmentService,
                                         QuestionRepository questionRepository,
                                         QuestionTextAnswerRepository questionTextAnswerRepository,
                                         QuestionChoiceRepository questionChoiceRepository,
                                         QuestionDtoMapper questionDtoMapper) {
        this.assignmentService = assignmentService;
        this.questionRepository = questionRepository;
        this.questionTextAnswerRepository = questionTextAnswerRepository;
        this.questionChoiceRepository = questionChoiceRepository;
        this.questionDtoMapper = questionDtoMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Question addQuestion(QuestionMutationRequest request, UserPrincipal requester) {

        Assignment assignment = assignmentService.checkAssignmentPermission(request.getAssignmentId(), requester);

        Question question = questionDtoMapper.toQuestion(request);
        question.setAssignmentId(assignment.getId());

        questionRepository.save(question);

        if (!CollectionUtils.isEmpty(question.getChoices())) {
            question.getChoices().forEach(choice -> {
                choice.setQuestionId(question.getId());
            });
            questionChoiceRepository.insertBatch(question.getChoices());
        }

        if (!CollectionUtils.isEmpty(question.getTextAnswers())) {
            question.getTextAnswers().forEach(textAnswer -> {
                textAnswer.setQuestionId(question.getId());
            });
            questionTextAnswerRepository.insertBatch(question.getTextAnswers());
        }

        return question;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Question updateQuestion(QuestionMutationRequest request, UserPrincipal requester, boolean isInternal) {
        if (!isInternal) {
            assignmentService.checkAssignmentPermission(request.getAssignmentId(), requester);
        }
        Question updateData = questionDtoMapper.toQuestion(request);

        updateData.setId(request.getQuestionId());
        questionRepository.update(updateData);

        List<QuestionChoice> questionChoices = questionChoiceRepository.findByQuestionId(Collections.singletonList(request.getQuestionId()));
        List<QuestionChoice> newQuestionChoices = new ArrayList<>();

        if (updateData.getChoices() != null && !updateData.getChoices().isEmpty()) {
            updateData.getChoices().forEach(choice -> {
                if (Objects.isNull(choice.getId())) {
                    choice.setQuestionId(request.getQuestionId());
                    newQuestionChoices.add(choice);
                } else {
                    questionChoiceRepository.updateSelective(choice);
                }
            });
        }

        if (!newQuestionChoices.isEmpty()) {
            questionChoiceRepository.insertBatch(newQuestionChoices);
        }

        if (questionChoices != null && !questionChoices.isEmpty()) {
            questionChoices.forEach(choice -> {
                if (updateData.getChoices().stream().noneMatch(c -> choice.getId().equals(c.getId()))) {
                    questionChoiceRepository.delete(choice.getId());
                }
            });
        }

        List<QuestionTextAnswer> textAnswers = questionTextAnswerRepository.findByQuestionId(request.getQuestionId());
        List<QuestionTextAnswer> newTextAnswers = new ArrayList<>();

        if (updateData.getTextAnswers() != null && !updateData.getTextAnswers().isEmpty()) {
            updateData.getTextAnswers().forEach(textAnswer -> {
                if (Objects.isNull(textAnswer.getId())) {
                    textAnswer.setQuestionId(request.getQuestionId());
                    newTextAnswers.add(textAnswer);
                } else {
                    questionTextAnswerRepository.update(textAnswer);
                }
            });
        }

        if (!newTextAnswers.isEmpty()) {
            questionTextAnswerRepository.insertBatch(newTextAnswers);
        }

        if (textAnswers != null && !textAnswers.isEmpty()) {
            textAnswers.forEach(textAnswer -> {
                if (updateData.getTextAnswers().stream().noneMatch(t -> textAnswer.getId().equals(t.getId()))) {
                    questionTextAnswerRepository.delete(textAnswer.getId());
                }
            });
        }

        return updateData;

    }

    @Override
    public void deleteQuestion(DeleteQuestionRequest request) {
        // check permission
        questionRepository.softDelete(request.getQuestionId());
    }
}
