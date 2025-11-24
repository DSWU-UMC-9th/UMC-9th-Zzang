package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.UserMissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.user.entity.User;

public class MissionConverter {

    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .build();
    }

    public static UserMissionResDTO.UserMission toUserMissionDTO(UserMission userMission) {
        return UserMissionResDTO.UserMission.builder()
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getName())
                .status(userMission.getStatus())
                .content(userMission.getMission().getContent())
                .point(userMission.getMission().getPoint())
                .deadline(userMission.getMission().getDeadline())
                .build();
    }
}
