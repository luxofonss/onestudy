package com.quyennv.lms.dto.course;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteLessonRequest {

    UUID courseId;

    UUID sectionId;

    UUID lessonId;

    UUID requesterId;

}
