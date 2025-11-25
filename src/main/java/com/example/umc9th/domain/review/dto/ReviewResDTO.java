package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
