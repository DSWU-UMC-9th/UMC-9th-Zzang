package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.UserMissionConverter;
import com.example.umc9th.domain.mission.dto.UserMissionResDTO;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.exception.UserException;
import com.example.umc9th.domain.user.exception.code.UserErrorCode;
import com.example.umc9th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    public UserMissionResDTO.UserMissionList findMyMissions(Long userId, MissionStatus status, Integer page) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page-1, 10);
        Page<UserMission> result = userMissionRepository.findByStatus(user, status, pageRequest);

        return UserMissionConverter.toUserMissionListDto(result);
    }

    @Transactional
    public UserMissionResDTO.UserMission completeMission(Long userId, Long userMissionId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        if (userMission.getStatus() == MissionStatus.COMPLETED) {
            throw new MissionException(MissionErrorCode.ALREADY_COMPLETED);
        }

        userMission.complete();
        user.addPoint(userMission.getMission().getPoint());

        return UserMissionConverter.toUserMissionDto(userMission);
    }
}
