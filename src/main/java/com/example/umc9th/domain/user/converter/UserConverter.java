package com.example.umc9th.domain.user.converter;

import com.example.umc9th.domain.user.dto.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO;
import com.example.umc9th.domain.user.entity.User;

public class UserConverter {

    // Entity -> DTO
    public static UserResDTO.UserJoin toJoinDTO(User user) {
        return UserResDTO.UserJoin.builder()
                .userId(user.getId())
                .created_at(user.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static User toUser(UserReqDTO.UserJoin dto) {
        return User.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .email(dto.email())
                .build();
    }
}
