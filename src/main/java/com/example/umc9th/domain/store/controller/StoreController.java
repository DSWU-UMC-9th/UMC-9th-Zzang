package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.domain.store.dto.StoreReqDTO;
import com.example.umc9th.domain.store.dto.StoreResDTO;
import com.example.umc9th.domain.store.exception.code.StoreSuccessCode;
import com.example.umc9th.domain.store.service.StoreService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final MissionService missionService;
    private final StoreService storeService;

    // 가게 미션 목록 조회
    @Operation(
            summary = "가게 미션 조회",
            description = "특정 가게의 마감기한이 지나지 않은 미션 목록 조회 (페이징)"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/{storeId}/missions")
    public ResponseEntity<ApiResponse<MissionResDTO.MissionList>> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                MissionSuccessCode.FOUND,
                missionService.findMissionsByStore(storeId, page)));
    }

    // 특정 지역에 새로운 가게 추가
    @Operation(
            summary = "특정 지역에 새로운 가게 추가"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/new")
    public ResponseEntity<ApiResponse<StoreResDTO.Store>> createStore(
            @Valid @RequestBody StoreReqDTO.CreateStore request
    ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                StoreSuccessCode.CREATED,
                storeService.createStore(request)));
    }

    // 가게 미션 추가
    @Operation(
            summary = "가게 미션 추가"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/{storeId}/missions")
    public ResponseEntity<ApiResponse<MissionResDTO.Mission>> createMission(
            @PathVariable Long storeId,
            @Valid @RequestBody MissionReqDTO.CreateMission request
    ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                MissionSuccessCode.CREATED,
                missionService.createMission(storeId, request)));
    }
}
