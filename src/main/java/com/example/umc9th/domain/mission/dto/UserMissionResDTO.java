package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.enums.MissionStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class UserMissionResDTO {

    @Builder
    @Getter
    public static class UserMission {
        private Long userMissionId;
        private Long missionId;
        private String storeName;
        private MissionStatus status;
        private String content;
        private Integer point;
        private LocalDate deadline;
    }
}
