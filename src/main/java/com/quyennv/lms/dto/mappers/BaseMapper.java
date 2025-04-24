package com.quyennv.lms.dto.mappers;

import org.mapstruct.Named;

import java.util.UUID;

public interface BaseMapper {

    @Named("mapStringToUUID")
    default UUID mapStringToUUID(String id) {
        return id == null ? null : UUID.fromString(id);
    }

}
