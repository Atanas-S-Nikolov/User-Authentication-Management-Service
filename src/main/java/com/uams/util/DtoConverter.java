package com.uams.util;

import com.uams.model.dto.RegisterDto;
import com.uams.model.response.UserResponse;
import com.uams.model.entity.SearchHistoryEntity;
import com.uams.model.entity.UserEntity;

import java.util.HashSet;

import static java.lang.Boolean.FALSE;
import static java.util.stream.Collectors.toSet;

public class DtoConverter {

    public static UserResponse toUserResponse(com.uams.model.internal.User user) {
        return new UserResponse(user.getName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getSearchHistory());
    }

    public static com.uams.model.internal.User toInternal(RegisterDto dto) {
        return new com.uams.model.internal.User(null, dto.getName(), dto.getLastName(), dto.getUsername(), dto.getPassword(), FALSE, new HashSet<>());
    }

    public static com.uams.model.internal.User toInternal(UserEntity entity) {
        return new com.uams.model.internal.User(entity.getId(), entity.getName(), entity.getLastName(), entity.getUsername(), entity.getPassword(), entity.getLoggedIn(),
                entity.getSearchHistory().stream().map(SearchHistoryEntity::getSearch).collect(toSet()));
    }

    public static UserEntity toEntity(com.uams.model.internal.User user) {
        return new UserEntity("", user.getName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getLoggedIn(),
                user.getSearchHistory().stream().map(search -> new SearchHistoryEntity(null, search, null)).collect(toSet()));
    }
}
