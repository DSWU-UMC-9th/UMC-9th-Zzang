package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.StoreReqDTO;
import com.example.umc9th.domain.store.dto.StoreResDTO;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.RegionErrorCode;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.user.entity.Food;
import com.example.umc9th.domain.user.exception.FoodException;
import com.example.umc9th.domain.user.exception.code.FoodErrorCode;
import com.example.umc9th.domain.user.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final FoodRepository foodRepository;
    private final RegionRepository regionRepository;

    @Transactional
    public StoreResDTO.Store createStore(StoreReqDTO.CreateStore dto) {

        Food food = foodRepository.findById(dto.getFoodType())
                .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

        Region region = regionRepository.findByName(dto.getRegion())
                .orElseThrow(() -> new StoreException((RegionErrorCode.NOT_FOUND)));

        Store store = StoreConverter.toStore(food, region, dto);
        storeRepository.save(store);

        return StoreConverter.toStoreDTO(store);
    }
}
