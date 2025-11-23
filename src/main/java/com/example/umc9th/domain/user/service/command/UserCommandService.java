package com.example.umc9th.domain.user.service.command;

import com.example.umc9th.domain.user.converter.UserConverter;
import com.example.umc9th.domain.user.dto.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.entity.mapping.PreferFood;
import com.example.umc9th.domain.user.exception.FoodException;
import com.example.umc9th.domain.user.exception.code.FoodErrorCode;
import com.example.umc9th.domain.user.repository.FoodRepository;
import com.example.umc9th.domain.user.repository.PreferFoodRepository;
import com.example.umc9th.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final PreferFoodRepository preferFoodRepository;

    @Transactional
    public UserResDTO.UserJoin signup(UserReqDTO.UserJoin dto) {

        User user = UserConverter.toUser(dto);
        userRepository.save(user);

        if(!dto.preferFood().isEmpty()) {
            List<PreferFood> preferFood = dto.preferFood().stream()
                    .map(id -> PreferFood.builder()
                            .user(user)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    ).toList();

            preferFoodRepository.saveAll(preferFood);
        }
        return UserConverter.toJoinDTO(user);
    }
}
