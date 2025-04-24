package com.quyennv.lms.entities;

import java.util.Date;

public class DocumentPlacement {
    private Object id;

    private Object documentId;

    private Object courseId;

    private Object lessonId;

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Object documentId) {
        this.documentId = documentId;
    }

    public Object getCourseId() {
        return courseId;
    }

    public void setCourseId(Object courseId) {
        this.courseId = courseId;
    }

    public Object getLessonId() {
        return lessonId;
    }

    public void setLessonId(Object lessonId) {
        this.lessonId = lessonId;
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