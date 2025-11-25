package com.example.umc9th.domain.mission.dto;

import lombok.Builder;
import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record Mission (
            Long missionId,
            String storeName,
            String content,
            Integer point,
            LocalDate deadline
    ) {}

    @Builder
    public record MissionList (
        List<Mission> missions,
        Integer listSize,
        Integer totalPage,
        Long totalElements,
        Boolean isFirst,
        Boolean isLast
    ) {}
}
