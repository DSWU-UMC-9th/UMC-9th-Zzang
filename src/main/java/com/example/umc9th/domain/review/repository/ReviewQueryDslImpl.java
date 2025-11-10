package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    // 내가 작성한 리뷰 조회 (+필터링)
    @Override
    public List<Review> filterMyReviews(Long userId, String storeName, Integer rate) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.user.id.eq(userId));

        if (storeName != null) {
            builder.and(store.name.eq(storeName));
        }

        if (rate != null) {
            builder.and(review.rate.between(rate, rate + 1));
        }

        return queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .where(builder)
                .fetch();
    }
}
