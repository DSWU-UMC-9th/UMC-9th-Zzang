package com.example.umc9th.domain.mission.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class MissionReqDTO {

    @Getter
    @NoArgsConstructor
    public static class CreateMission {
        @NotBlank
        private String content;
        @NotNull
        private Integer point;
        @NotNull
        @FutureOrPresent
        private LocalDate deadline;
    }
}
