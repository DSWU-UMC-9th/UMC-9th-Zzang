package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResDTO.ReviewList findMyReviews(Long userId, String storeName, Integer rate) {
        return ReviewConverter.toReviewListDto(reviewRepository.filterMyReviews(userId, storeName, rate));
    }
}
