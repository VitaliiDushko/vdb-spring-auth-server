package com.vdb.auth.server.application;

import com.vdb.auth.server.api.CreateUserRequest;
import com.vdb.auth.server.infrastructure.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "id", ignore = true)
    User createUserRequestToUser (CreateUserRequest createUserRequest);
}