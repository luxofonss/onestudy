package com.quyennv.lms.dto.assignment;

import com.quyennv.lms.annotations.ValueOfEnum;
import com.quyennv.lms.constant.enums.AssignmentType;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAssignmentRequest {

    UUID assignmentId;

    @NotBlank
    String title;

    String description;

    Integer maxAttempts;

    Long timeLimitSecond;

    String startTime;

    String endTime;

    @ValueOfEnum(enumClass = AssignmentType.class)
    String type;

    List<QuestionMutationRequest> questions;

}
