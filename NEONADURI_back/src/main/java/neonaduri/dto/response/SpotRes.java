package neonaduri.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SpotRes {

    private final String spotName;

    private final Float lat;

    private final Float lng;

    private final String spotContent;

    private final String spotImage;

    @Builder(builderMethodName = "createSpotRes")
    SpotRes(String spotName, Float lat, Float lng, String spotContent, String spotImage){
        this.spotName = spotName;
        this.lat = lat;
        this.lng = lng;
        this.spotContent = spotContent;
        this.spotImage = spotImage;
    }
}
