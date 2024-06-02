package com.uk.shopping.user.mapper;

import com.uk.shopping.user.dto.JoinRequestDto;
import com.uk.shopping.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    User toEntity(JoinRequestDto joinRequestDto);
}
