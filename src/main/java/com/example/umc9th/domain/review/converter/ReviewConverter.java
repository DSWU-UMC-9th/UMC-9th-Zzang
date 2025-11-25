package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewImage;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public class ReviewConverter {

    public static Review toReview(User user, Store store, ReviewReqDTO.CreateReview dto) {
        return Review.builder()
                .user(user)
                .store(store)
                .rate(dto.getRate())
                .content(dto.getContent())
                .build();
    }

    public static ReviewResDTO.ReviewPreview toReviewPreviewDto(Review review) {
        return ReviewResDTO.ReviewPreview.builder()
                .userName(review.getUser().getName())
                .content(review.getContent())
                .rate(review.getRate())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }

    public static ReviewResDTO.ReviewPreviewList toReviewPreviewListDto(Page<Review> reviews) {
        return ReviewResDTO.ReviewPreviewList.builder()
                .reviews(reviews.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDto)
                        .toList()
                )
                .listSize(reviews.getSize())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .build();
    }
}
