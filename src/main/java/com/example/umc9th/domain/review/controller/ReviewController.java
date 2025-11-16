package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/my")
    public ApiResponse<ReviewResDTO.ReviewList> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rate
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewService.findMyReviews(userId, storeName, rate));
    }
}
