package com.example.umc9th.domain.user.controller;

import com.example.umc9th.domain.user.dto.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO;
import com.example.umc9th.domain.user.exception.code.UserSuccessCode;
import com.example.umc9th.domain.user.service.command.UserCommandService;
import com.example.umc9th.domain.user.service.query.UserQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @Operation(
            summary = "회원가입",
            description = "회원 정보와 좋아하는 음식 종류를 받아와 새로운 User 생성"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/api/auth/signup")
    public ResponseEntity<ApiResponse<UserResDTO.UserJoin>> signup(
            @RequestBody @Valid UserReqDTO.UserJoin dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(
                        UserSuccessCode.CREATED, userCommandService.signup(dto))
                );
    }

    @Operation(
            summary = "로그인",
            description = "사용자 이메일 & 비밀번호를 통해 로그인"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/api/login")
    public ResponseEntity<ApiResponse<UserResDTO.Login>> login(
            @RequestBody @Valid UserReqDTO.Login dto
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.onSuccess(
                        UserSuccessCode.FOUND, userQueryService.login(dto))
                );
    }
}

