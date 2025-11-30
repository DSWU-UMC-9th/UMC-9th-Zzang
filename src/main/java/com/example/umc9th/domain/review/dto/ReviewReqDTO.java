package com.example.umc9th.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewReqDTO {

    @Getter
    @NoArgsConstructor
    public static class CreateReview {
        private Integer rate;
        private String content;
    }
}
