package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.UserMissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 가게 미션 -> 사용자 도전 미션 등록
    @Operation(
            summary = "가게 미션 -> 사용자 도전 미션 등록",
            description = "가게 미션을 내가 도전 중인 미션에 추가 등록"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/{missionId}/challenge")
    public ResponseEntity<ApiResponse<UserMissionResDTO.UserMission>> challengeMission(
            @PathVariable Long missionId,
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                MissionSuccessCode.CREATED,
                missionService.challengeMission(userId, missionId)));
    }
}
