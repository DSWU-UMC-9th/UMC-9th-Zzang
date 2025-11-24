package com.example.umc9th.domain.store.dto;

import lombok.Builder;
import lombok.Getter;

public class StoreResDTO {

    @Builder
    @Getter
    public static class Store {
        private Long storeId;
        private String storeName;
        private String location;
        private String region;
        private String foodType;
    }
}
