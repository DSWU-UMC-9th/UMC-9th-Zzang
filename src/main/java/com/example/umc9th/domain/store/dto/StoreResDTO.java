package com.example.umc9th.domain.store.dto;

import lombok.Builder;
import lombok.Getter;

public class StoreResDTO {

    @Builder
    public record Store (
        Long storeId,
        String storeName,
        String location,
        String region,
        String foodType
    ) {}
}
