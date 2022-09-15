package neonaduri.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import neonaduri.domain.Tag;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter @ToString
public class CreateReviewReq {

    @NotNull
    private final Long spotId;

    @NotNull
    private final String reviewImage;

    @NotNull
    private final String reviewContent;

    @Size(min = 4, max = 100)
    private final String reviewPassword;

    private final String tags;

    @Builder
    public CreateReviewReq(
            Long spotId, String reviewImage, String reviewContent, String reviewPassword, String tags) {
        this.spotId = spotId;
        this.reviewImage = reviewImage;
        this.reviewContent = reviewContent;
        this.reviewPassword = reviewPassword;
        this.tags = tags;
    }
}
