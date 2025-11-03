package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면 (도전 가능한 미션 조회)
    @Query("""
            SELECT m
            FROM Mission m
            JOIN m.store s
            JOIN s.region r
            WHERE r.name = :region
              AND m.deadline >= CURRENT_DATE
              AND NOT EXISTS (
                  SELECT 1
                  FROM UserMission um
                  WHERE um.mission = m
                    AND um.user.id = :userId)
            ORDER BY m.deadline
    """)
    Page<Mission> findMissionsByRegion(
            @Param("region") String region,
            @Param("userId") Long userId,
            Pageable pageable);
}
