package neonaduri.application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id", nullable = false)
    private Long classId;

    @Column(name = "md_class")
    private String mdClass;

    @Column(name = "sm_class")
    private String smClass;

}
