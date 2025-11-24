package com.example.umc9th.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreReqDTO {

    @Getter
    @NoArgsConstructor
    public static class CreateStore {
        @NotBlank
        private String name;
        @NotBlank
        private String location;
        @NotNull
        private Long foodType;
        @NotBlank
        private String region;
    }
}
