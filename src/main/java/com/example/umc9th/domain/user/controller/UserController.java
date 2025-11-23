package com.example.umc9th.domain.user.controller;

import com.example.umc9th.domain.user.dto.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO;
import com.example.umc9th.domain.user.exception.code.UserSuccessCode;
import com.example.umc9th.domain.user.service.command.UserCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;

    // 회원 가입
    @PostMapping("/api/auth/signup")
    public ApiResponse<UserResDTO.UserJoin> signup(
            @RequestBody @Valid UserReqDTO.UserJoin dto
    ) {
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userCommandService.signup(dto));
    }
}
