package neonaduri.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본생성자 자동추가
@Entity
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id", nullable = false)
    private long spotId;

    @OneToOne
    @JoinColumn(name="region_id", nullable = false) //name 옵션은 DB 컬럼명
    private Region regionId;

    @OneToOne
    @JoinColumn(name="class_id", nullable = false)
    private Classification classId;

    @Column(name = "spot_name", nullable = false)
    private String spotName;

    @Column(name = "lat", nullable = false)
    private float lat;

    @Column(name = "lng", nullable = false)
    private float lng;

    @Column(name = "tel", nullable = false)
    private String tel;

    @Column(name = "spot_image", nullable = false)
    private String spotImage;

    @Column(name = "spot_content", nullable = false)
    private String spotContent;

    @Builder
    public Spot(long spotId, Region regionId, Classification classId, String spotName, float lat, float lng, String tel, String spotImage, String spotContent) {
        this.spotId = spotId;
        this.regionId = regionId;
        this.classId = classId;
        this.spotName = spotName;
        this.lat = lat;
        this.lng = lng;
        this.tel = tel;
        this.spotImage = spotImage;
        this.spotContent = spotContent;
    }
}
