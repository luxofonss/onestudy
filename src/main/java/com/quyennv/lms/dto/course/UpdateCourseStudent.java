package com.quyennv.lms.dto.course;

import com.quyennv.lms.annotations.ValueOfEnum;
import com.quyennv.lms.constant.enums.EnrollStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseStudent {

    @ValueOfEnum(enumClass = EnrollStatus.class)
    private String status;

}
