package neonaduri.dto.response;

import lombok.Builder;
import lombok.Getter;
import neonaduri.domain.Review;

import java.util.Set;

@Getter
public class SpotReviewRes {

    private final String spotContent;

    private final String spotName;

    private final String spotImage;

    private final Set<Review> reviews;

    @Builder
    SpotReviewRes(String spotContent, String spotName, String spotImage, Set<Review> reviews) {
        this.spotContent = spotContent;
        this.spotName = spotName;
        this.spotImage = spotImage;
        this.reviews = reviews;
    }
}
