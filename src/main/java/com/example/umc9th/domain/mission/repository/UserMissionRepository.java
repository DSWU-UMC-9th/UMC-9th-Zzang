package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
            SELECT um
            FROM UserMission um
            JOIN FETCH um.mission m
            JOIN FETCH m.store s
            WHERE um.user = :user
              AND um.status = :status
            ORDER BY m.createdAt DESC
    """)
    Page<UserMission> findByStatus(
            User user,
            MissionStatus status,
            Pageable pageable);

    Optional<UserMission> findByUserIdAndMissionId(Long userId, Long missionId);
}
