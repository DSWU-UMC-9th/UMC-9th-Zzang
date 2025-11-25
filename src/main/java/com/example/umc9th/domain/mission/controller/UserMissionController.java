package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.UserMissionResDTO;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.service.UserMissionService;
import com.example.umc9th.domain.user.exception.code.UserSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my-missions")
public class UserMissionController {

    private final UserMissionService userMissionService;

    // 나의 미션 목록 조회
    @Operation(
            summary = "나의 미션 조회",
            description = "진행 중 / 진행 완료 미션 목록 각각 조회 (필터링)"
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
                UserSuccessCode.FOUND,
                userMissionService.findMyMissions(userId, status, page)));
    }
}
