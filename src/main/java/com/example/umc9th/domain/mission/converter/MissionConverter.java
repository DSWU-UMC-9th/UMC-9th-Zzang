package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.dto.UserMissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDate;

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

    public static Mission toMission(Store store, MissionReqDTO.CreateMission dto) {
        return Mission.builder()
                .store(store)
                .content(dto.getContent())
                .point(dto.getPoint())
                .deadline(dto.getDeadline())
                .build();
    }

    public static MissionResDTO.Mission toMissionDTO(Mission mission) {
        return MissionResDTO.Mission.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }
}
