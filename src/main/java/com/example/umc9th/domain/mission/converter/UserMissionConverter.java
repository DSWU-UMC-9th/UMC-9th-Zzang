package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.UserMissionResDTO;
import com.example.umc9th.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;

public class UserMissionConverter {
    private static UserMissionResDTO.UserMission toUserMissionDto(UserMission userMission) {
        return UserMissionResDTO.UserMission.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getName())
                .status(userMission.getStatus())
                .content(userMission.getMission().getContent())
                .point(userMission.getMission().getPoint())
                .deadline(userMission.getMission().getDeadline())
                .build();
    }

    public static UserMissionResDTO.UserMissionList toUserMissionListDto(Page<UserMission> userMissions) {
        return UserMissionResDTO.UserMissionList.builder()
                .userMissions(userMissions.getContent().stream()
                        .map(UserMissionConverter::toUserMissionDto)
                        .toList()
                )
                .listSize(userMissions.getSize())
                .totalPage(userMissions.getTotalPages())
                .totalElements(userMissions.getTotalElements())
                .isFirst(userMissions.isFirst())
                .isLast(userMissions.isLast())
                .build();
    }
}
