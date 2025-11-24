package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.store.dto.StoreReqDTO;
import com.example.umc9th.domain.store.dto.StoreResDTO;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.Food;

public class StoreConverter {
    public static Store toStore(Food food, Region region, StoreReqDTO.CreateStore dto) {
        return Store.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .food(food)
                .region(region)
                .build();
    }

    public static StoreResDTO.Store toStoreDTO(Store store) {
        return StoreResDTO.Store.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .location(store.getLocation())
                .region(store.getRegion().getName())
                .foodType(store.getFood().getName().name())
                .build();
    }
}
