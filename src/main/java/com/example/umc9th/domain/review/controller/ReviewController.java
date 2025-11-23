package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
class ReviewController {

    private final ReviewService reviewService;

    // 내가 작성한 리뷰 조회 (+필터링)
    @GetMapping("/my")
    public ApiResponse<ReviewResDTO.ReviewList> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rate
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewService.findMyReviews(userId, storeName, rate));
    }

    // 가게 리뷰 작성
    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResDTO.ReviewDetail> createReview(
            @PathVariable Long storeId,
            @RequestParam Long userId,
            @RequestBody ReviewReqDTO.CreateReview request
            ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                reviewService.createReview(userId, storeId, request));
    }
}
