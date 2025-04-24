package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.Question;
import com.quyennv.lms.entities.QuestionChoice;
import com.quyennv.lms.entities.QuestionTextAnswer;
import com.quyennv.lms.mappers.QuestionChoiceMapper;
import com.quyennv.lms.mappers.QuestionMapper;
import com.quyennv.lms.repository.QuestionRepository;
import com.quyennv.lms.repository.QuestionTextAnswerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionMapper questionMapper;

    private final QuestionChoiceMapper questionChoiceMapper;

    private final QuestionTextAnswerRepository questionTextAnswerRepository;

    public QuestionRepositoryImpl(QuestionMapper questionMapper,
                                  QuestionChoiceMapper questionChoiceMapper,
                                  QuestionTextAnswerRepository questionTextAnswerRepository) {
        this.questionMapper = questionMapper;
        this.questionChoiceMapper = questionChoiceMapper;
        this.questionTextAnswerRepository = questionTextAnswerRepository;
    }

    @Override
    public int save(Question record) {
        record.setId(UUID.randomUUID());
        questionMapper.insert(record);

        List<QuestionChoice> questionChoices = record.getChoices();
        if (questionChoices != null && !questionChoices.isEmpty()) {
            questionChoices.forEach(choice -> {
                choice.setQuestionId(record.getId());
            });
        }
        questionChoiceMapper.insertBatch(questionChoices);

        List<QuestionTextAnswer> textAnswers = record.getTextAnswers();
        if (textAnswers != null && !textAnswers.isEmpty()) {
            textAnswers.forEach(textAnswer -> {
                textAnswer.setQuestionId(record.getId());
            });
            questionTextAnswerRepository.insertBatch(textAnswers);
        }

        return 1;
    }

    @Override
    public Question findById(String id) {
        return null;
    }

    @Override
    public int update(Question updateData) {
        return questionMapper.updateByPrimaryKey(updateData);
    }
}
