package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.exception.UserException;
import com.example.umc9th.domain.user.exception.code.UserErrorCode;
import com.example.umc9th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.ReviewPreviewList findReviewsByStore(String storeName, Integer page) {

        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page-1, 10);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDto(result);
    }

    public ReviewResDTO.ReviewPreviewList findMyReviews(Long userId, String storeName, Integer rate, Integer page) {

        if (userId != null) {
            userRepository.findById(userId)
                    .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));
        }

        if (storeName != null) {
            storeRepository.findByName(storeName)
                    .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        }

        PageRequest pageRequest = PageRequest.of(page-1, 10);
        Page<Review> result = reviewRepository.filterMyReviews(userId, storeName, rate, pageRequest);

        return ReviewConverter.toReviewPreviewListDto(result);
    }

    @Transactional
    public ReviewResDTO.ReviewPreview createReview(Long userId, Long storeId, ReviewReqDTO.CreateReview request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(user, store, request);
        reviewRepository.save(review);

        return ReviewConverter.toReviewPreviewDto(review);
    }
}
