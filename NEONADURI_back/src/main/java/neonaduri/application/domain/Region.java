package neonaduri.application.domain;

import javax.persistence.Column;
import javax.persistence.Id;

public class Region {

    @Id
    @Column(name = "region_id", nullable = false)
    //따로 지역코드가 있는것으로 알고있어 auto_increment 안함
    private Long regionId;

    @Column
    private String sido;

    @Column
    private String sigungu;

    @Column
    private String myeon;
}
