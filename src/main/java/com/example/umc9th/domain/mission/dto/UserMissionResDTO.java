package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class UserMissionResDTO {

    @Builder
    public record UserMission (
            Long userMissionId,
            Long missionId,
            String storeName,
            MissionStatus status,
            String content,
            Integer point,
            LocalDate deadline
    ) {}

    @Builder
    public record UserMissionList (
            List<UserMissionResDTO.UserMission> userMissions,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
