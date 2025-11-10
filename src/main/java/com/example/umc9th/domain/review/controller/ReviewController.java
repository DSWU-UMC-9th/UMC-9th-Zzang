package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/my")
    public List<Review> getMyReviews(
            @RequestParam Long userId,
            @RequestParam String storeName,
            @RequestParam Integer rate
    ){
        return reviewService.findMyReviews(userId, storeName, rate);
    }
}
