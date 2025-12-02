package com.example.umc9th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewPreview (
            String userName,
            Integer rate,
            String content,
            LocalDate createdAt
    ) {}

    @Builder
    public record ReviewPreviewList (
        List<ReviewPreview> reviews,
        Integer listSize,
        Integer totalPage,
        Long totalElements,
        Boolean isFirst,
        Boolean isLast
    ) {}
}
