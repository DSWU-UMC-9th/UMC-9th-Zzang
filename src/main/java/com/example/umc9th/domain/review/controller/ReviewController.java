package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
class ReviewController {

    private final ReviewService reviewService;

    // 가게 리뷰 조회
    @Operation(
            summary = "가게 리뷰 조회",
            description = "가게에 따른 리뷰 목록 조회 (페이징)"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("")
    public ResponseEntity<ApiResponse<ReviewResDTO.ReviewPreviewList>> getReviewsByStore(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewService.findReviewsByStore(storeName, page)));
    }

    // 내가 작성한 리뷰 조회 (+필터링)
    @Operation(
            summary = "내가 작성한 리뷰 조회 (+필터링)",
            description = "가게, 평점 필터링에 따른 리뷰 목록 조회 (페이징)"
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
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewService.findMyReviews(userId, storeName, rate, page)));
    }

    // 가게 리뷰 작성
    @Operation(
            summary = "가게 리뷰 작성"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/{storeId}")
    public ResponseEntity<ApiResponse<ReviewResDTO.ReviewPreview>> createReview(
            @PathVariable Long storeId,
            @RequestParam Long userId,
            @RequestBody ReviewReqDTO.CreateReview request
            ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                reviewService.createReview(userId, storeId, request)));
    }
}
