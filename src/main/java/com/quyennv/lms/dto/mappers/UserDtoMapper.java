package com.quyennv.lms.dto.mappers;

import com.quyennv.lms.dto.auth.AuthRegisterRequest;
import com.quyennv.lms.dto.auth.AuthProfileResponse;
import com.quyennv.lms.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    User authRegisterRequestToUser(AuthRegisterRequest request);

    AuthProfileResponse userToAuthProfileResponse(User user);

}
