package com.quyennv.lms.entities;

import java.util.Date;

public class QuestionAnswer {
    private Object id;

    private Object userId;

    private Object questionId;

    private Object assignmentAttemptId;

    private Object selectedOptionId;

    private String textAnswer;

    private Integer score;

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Object questionId) {
        this.questionId = questionId;
    }

    public Object getAssignmentAttemptId() {
        return assignmentAttemptId;
    }

    public void setAssignmentAttemptId(Object assignmentAttemptId) {
        this.assignmentAttemptId = assignmentAttemptId;
    }

    public Object getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(Object selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer == null ? null : textAnswer.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}