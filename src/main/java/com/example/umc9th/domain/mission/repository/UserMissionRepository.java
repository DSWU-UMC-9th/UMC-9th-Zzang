package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 사용자 진행중/진행완료 미션 조회
    @Query("""
            SELECT um
            FROM UserMission um
            JOIN FETCH um.mission m
            JOIN FETCH m.store s
            WHERE um.user.id = :userId
              AND um.status = :status
            ORDER BY m.createdAt DESC
    """)
    Page<UserMission> findUserMissionsByStatus(
            @Param("userId") Long userId,
            @Param("status") MissionStatus status,
            Pageable pageable);

    Optional<UserMission> findByUserIdAndMissionId(Long userId, Long missionId);
}
