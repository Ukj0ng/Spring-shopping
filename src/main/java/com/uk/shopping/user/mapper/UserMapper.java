package com.uk.shopping.user.mapper;

import com.uk.shopping.user.dto.JoinRequestDto;
import com.uk.shopping.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(JoinRequestDto joinRequestDto);
}
