package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // 사용자 정보 조회
    @Query("SELECT u FROM User u WHERE u.id = :userId")
    List<User> findUserByUserId(@Param("userId") Long userId);
}
