package com.example.umc9th.domain.user.dto;

import com.example.umc9th.domain.user.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

    public record UserJoin(
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            String address,
            @NotNull
            String email,
            @ExistFoods
            List<Long> preferFood
    ) {}
}
