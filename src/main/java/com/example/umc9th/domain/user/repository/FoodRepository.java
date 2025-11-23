package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
