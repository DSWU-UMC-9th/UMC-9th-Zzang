package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.UserMissionResDTO;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 가게 미션 -> 사용자 도전 미션 등록
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<UserMissionResDTO.UserMission> challengeMission(
            @PathVariable Long missionId,
            @RequestParam Long userId
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                missionService.challengeMission(userId, missionId));
    }
}
