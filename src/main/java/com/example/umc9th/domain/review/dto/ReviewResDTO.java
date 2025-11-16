package com.example.umc9th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    @Getter
    public static class ReviewDetail {
        private Long reviewId;
        private String userName;
        private String storeName;
        private String content;
        private Integer rate;
        private LocalDateTime createdAt;
        private List<String> images;
    }

    @Builder
    @Getter
    public static class ReviewList {
        private List<ReviewDetail> reviews;
    }
}
