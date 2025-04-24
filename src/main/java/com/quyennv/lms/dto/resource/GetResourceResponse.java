package com.quyennv.lms.dto.resource;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.quyennv.lms.constant.Constant;
import lombok.Builder;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class GetResourceResponse {

    private String url;

    private String name;

    private final Integer expiresInSeconds = Constant.SIGNED_URL_EXPIRATION;

}
