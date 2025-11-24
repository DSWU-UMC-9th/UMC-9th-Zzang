package com.example.umc9th.domain.user.dto;

import lombok.Builder;
import java.time.LocalDateTime;

public class UserResDTO {

    @Builder
    public record UserJoin(
            Long userId,
            LocalDateTime created_at
    ) {}
}
