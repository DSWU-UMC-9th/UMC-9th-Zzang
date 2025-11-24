package com.example.umc9th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

public class UserMissionReqDTO {

    @Builder
    @Getter
    public static class CreateUserMission {
        private String missionId;
        private String userId;
    }
}
