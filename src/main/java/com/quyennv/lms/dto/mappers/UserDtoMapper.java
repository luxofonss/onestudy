package com.quyennv.lms.dto.mappers;

import com.quyennv.lms.dto.auth.AuthRegisterRequest;
import com.quyennv.lms.dto.auth.AuthProfileResponse;
import com.quyennv.lms.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mapping(source = "dateOfBirth", target = "dateOfBirth", dateFormat = "dd/MM/yyyy")
    User authRegisterRequestToUser(AuthRegisterRequest request);

    AuthProfileResponse userToAuthProfileResponse(User user);

}
