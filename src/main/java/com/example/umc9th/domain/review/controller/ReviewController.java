package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/reviews")
class ReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "가게 리뷰 목록 조회",
            description = "특정 가게의 리뷰 목록 반환 (페이징 10)"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("")
    public ResponseEntity<ApiResponse<ReviewResDTO.ReviewPreviewList>> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewService.findReviewsByStore(storeName, page)));
    }

    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 (+필터링)",
            description = "가게 또는 평점 구간을 필터링하여 리뷰 목록 반환 (페이징 10)"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<ReviewResDTO.ReviewPreviewList>> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rate,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
            ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewService.findMyReviews(userId, storeName, rate, page)));
    }

    @Operation(
            summary = "가게 리뷰 작성",
            description = "특정 가게에 리뷰를 작성하여 새로운 Review 생성"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/{storeId}")
    public ResponseEntity<ApiResponse<ReviewResDTO.ReviewPreview>> createReview(
            @PathVariable Long storeId,
            @RequestParam Long userId,
            @RequestBody @Valid ReviewReqDTO.CreateReview request
            ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(
                        GeneralSuccessCode.CREATED,
                        reviewService.createReview(userId, storeId, request))
                );
    }
}
