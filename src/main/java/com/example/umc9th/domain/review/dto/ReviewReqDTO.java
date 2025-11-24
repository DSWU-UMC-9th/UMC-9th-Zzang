package com.example.umc9th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ReviewReqDTO {

    @Builder
    @Getter
    public static class CreateReview {
        private Integer rate;
        private String content;
        private List<String> images;
    }
}
