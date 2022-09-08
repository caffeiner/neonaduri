package neonaduri.api.service;

import lombok.RequiredArgsConstructor;
import neonaduri.api.repository.ReviewRepository;
import neonaduri.api.repository.SpotRepository;
import neonaduri.domain.Review;
import neonaduri.domain.Tag;
import neonaduri.dto.request.CreateReviewReq;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final SpotRepository spotRepository;
    private final PasswordEncoder passwordEncoder;

    public void postReview(CreateReviewReq createReviewReq) {

        /**  Review 생성하기 */
        Review review = Review.builder()
                .spotId(spotRepository.findSpotBySpotId(createReviewReq.getSpotId()))
                .tags(createReviewReq.getTags())
                .reviewContent(createReviewReq.getReviewContent())
                .reviewDate(LocalDateTime.now())
                .reviewImage(createReviewReq.getReviewImage())
                .reviewPassword(passwordEncoder.encode(createReviewReq.getReviewPassword()))
                .build();

        /** Tag 생성하기 */


        Review save = reviewRepository.save(review);
//        for (Tag tag : save.getTags()) {
//            System.out.println(tag.toString());
//        }
    }


    /* Not Take*/
    private Set<Tag> createTagsWithCreateReviewReq(CreateReviewReq createReviewReq) {
        return createReviewReq.getTags().stream()
                .map(tag -> Tag.builder()
                        .review(reviewRepository.findById(createReviewReq.getSpotId())
                                .orElseThrow(() -> {
                                    throw new IllegalArgumentException();
                                }))
                        .tagContent(createReviewReq.getReviewContent())
                        .build())
                .collect(Collectors.toSet());
    }

}
