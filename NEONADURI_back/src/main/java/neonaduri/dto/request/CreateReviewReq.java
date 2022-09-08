package neonaduri.dto.request;

import lombok.Builder;
import lombok.Getter;
import neonaduri.domain.Tag;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
public class CreateReviewReq {

    @NotNull
    private final Long spotId;

    @NotNull
    private final String reviewImage;

    @NotNull
    private final String reviewContent;

    @Size(min = 4, max = 100)
    private final String reviewPassword;

    private final Set<Tag> tags;

    @Builder(builderMethodName = "createReviewReq")
    public CreateReviewReq(
            Long spotId, String reviewImage, String reviewContent, String reviewPassword, Set<Tag> tags) {
        this.spotId = spotId;
        this.reviewImage = reviewImage;
        this.reviewContent = reviewContent;
        this.reviewPassword = reviewPassword;
        this.tags = tags;
    }
}
