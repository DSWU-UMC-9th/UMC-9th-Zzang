package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.domain.store.dto.StoreReqDTO;
import com.example.umc9th.domain.store.dto.StoreResDTO;
import com.example.umc9th.domain.store.service.StoreService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final MissionService missionService;
    private final StoreService storeService;

    // 특정 지역에 새로운 가게 추가
    @PostMapping("/new")
    public ApiResponse<StoreResDTO.Store> createStore(@Valid @RequestBody StoreReqDTO.CreateStore request) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                storeService.createStore(request)
        );
    }

    // 가게 미션 추가
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.Mission> createMission(
            @PathVariable Long storeId,
            @Valid @RequestBody MissionReqDTO.CreateMission request
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                missionService.createMission(storeId, request)
        );
    }
}
