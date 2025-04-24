package com.quyennv.lms.service.impl;

import com.quyennv.lms.constant.enums.QuestionType;
import com.quyennv.lms.entities.Question;
import com.quyennv.lms.mappers.QuestionChoiceMapper;
import com.quyennv.lms.mappers.QuestionMapper;
import com.quyennv.lms.mappers.QuestionTextAnswerMapper;
import com.quyennv.lms.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;

    private final QuestionChoiceMapper questionChoiceMapper;

    private final QuestionTextAnswerMapper questionTextAnswerMapper;

    public QuestionServiceImpl(QuestionMapper questionMapper,
                               QuestionChoiceMapper questionChoiceMapper,
                               QuestionTextAnswerMapper questionTextAnswerMapper) {
        this.questionMapper = questionMapper;
        this.questionChoiceMapper = questionChoiceMapper;
        this.questionTextAnswerMapper = questionTextAnswerMapper;
    }

    @Override
    public void deleteByAssignmentId(String assignmentId) {
        questionMapper.deleteByAssignmentId(assignmentId);

    }

    @Override
    public List<Question> getByAssignmentId(UUID id) {
        List<Question> questions = questionMapper.selectByAssignmentId(id);

        for (Question question : questions) {
            if (QuestionType.MULTIPLE_CHOICES.name().equals(question.getType()) || QuestionType.SINGLE_CHOICE.name().equals(question.getType())) {
                question.setChoices(questionChoiceMapper.selectByQuestionId(question.getId()));
            }

            if (QuestionType.SHORT_ANSWER.name().equals(question.getType())) {
                question.setTextAnswers(questionTextAnswerMapper.selectByQuestionId(question.getId()));
            }
        }

        return questions;
    }
}
