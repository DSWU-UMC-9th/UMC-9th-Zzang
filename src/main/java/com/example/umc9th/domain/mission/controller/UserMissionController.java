package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.UserMissionResDTO;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.UserMissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my-missions")
public class UserMissionController {

    private final UserMissionService userMissionService;

    @Operation(
            summary = "사용자 진행중/진행완료 미션 목록 조회",
            description = "미션 도전 상태 IN_PROGRESS/COMPLETED에 따라 각각의 미션 목록 반환 (페이징 10)"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("")
    public ResponseEntity<ApiResponse<UserMissionResDTO.UserMissionList>> getMyMissions(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "IN_PROGRESS") MissionStatus status,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                MissionSuccessCode.FOUND,
                userMissionService.findMyMissions(userId, status, page)));
    }

    @Operation(
            summary = "사용자 미션 '진행 완료' 상태로 변경",
            description = "해당 사용자 미션의 상태를 IN_PROGRESS → COMPLETED로 변경 및 포인트 획득"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PatchMapping("/{userMissionId}")
    public ResponseEntity<ApiResponse<UserMissionResDTO.UserMission>> completeMission(
            @PathVariable Long userMissionId,
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                MissionSuccessCode.UPDATED_STATUS,
                userMissionService.completeMission(userId, userMissionId)));
    }
}
