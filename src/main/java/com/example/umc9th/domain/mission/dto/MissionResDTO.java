package com.example.umc9th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MissionResDTO {

    @Builder
    @Getter
    public static class Mission {
        private Long missionId;
        private String storeName;
        private String content;
        private Integer point;
        private LocalDate deadline;
    }
}
