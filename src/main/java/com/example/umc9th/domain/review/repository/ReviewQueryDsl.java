package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryDsl {
    List<Review> filterMyReviews(Long userId, String storeName, Integer rate);
}
