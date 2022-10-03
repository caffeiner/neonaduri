package neonaduri.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RecommendSpotRes {

    private final Long spotId;

    private final String spotName;

    private final String spotContent;

    private final String spotImage;

    @Builder
    RecommendSpotRes(Long spotId, String spotName, String spotContent, String spotImage){
        this.spotId = spotId;
        this.spotName = spotName;
        this.spotContent = spotContent;
        this.spotImage = spotImage;
    }
}
