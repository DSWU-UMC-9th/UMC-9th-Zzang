package com.example.umc9th.domain.user.converter;

import com.example.umc9th.domain.user.dto.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.enums.Role;

public class UserConverter {

    // Entity -> DTO
    public static UserResDTO.UserJoin toJoinDto(User user) {
        return UserResDTO.UserJoin.builder()
                .userId(user.getId())
                .created_at(user.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static User toUser(UserReqDTO.UserJoin dto, String password, Role role) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .build();
    }

    public static UserResDTO.Login toLoginDto(User user, String accessToken) {
        return UserResDTO.Login.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .build();
    }
}
