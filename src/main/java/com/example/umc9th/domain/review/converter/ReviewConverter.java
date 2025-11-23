package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewImage;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static Review toReview(User user, Store store, ReviewReqDTO.CreateReview dto) {
        Review review = Review.builder()
                .user(user)
                .store(store)
                .rate(dto.getRate())
                .content(dto.getContent())
                .build();

        List<ReviewImage> images = dto.getImages().stream()
                .map(url -> ReviewImage.builder()
                        .review(review)
                        .url(url)
                        .build())
                .collect(Collectors.toList());

        review.getReviewImageList().addAll(images);

        return review;
    }

    public static ReviewResDTO.ReviewDetail toReviewDto(Review review) {
        return ReviewResDTO.ReviewDetail.builder()
                .reviewId(review.getId())
                .userName(review.getUser().getName())
                .storeName(review.getStore().getName())
                .content(review.getContent())
                .rate(review.getRate())
                .createdAt(review.getCreatedAt())
                .images(review.getReviewImageList().stream()
                        .map(ReviewImage::getUrl).collect(Collectors.toList()))
                .build();
    }

    public static ReviewResDTO.ReviewList toReviewListDto(List<Review> reviews) {
        return ReviewResDTO.ReviewList.builder()
                .reviews(reviews.stream()
                                .map(ReviewConverter::toReviewDto).collect(Collectors.toList()))
                .build();

    }
}
