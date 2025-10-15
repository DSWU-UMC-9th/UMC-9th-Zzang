package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.user.entity.mapping.PreferFood;
import com.example.umc9th.domain.user.entity.mapping.UserTerm;
import com.example.umc9th.domain.user.enums.Gender;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "address", length = 20, nullable = false)
    private String address;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "phone_num", length = 11)
    private String phoneNum;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "inactive_date")
    private LocalDate inactiveDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTerm> userTermList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PreferFood> preferFoodList = new ArrayList<>();
}
