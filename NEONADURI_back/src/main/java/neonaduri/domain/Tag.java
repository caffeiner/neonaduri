package neonaduri.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @ToString
@Table(name = "tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<Review> reviews = new HashSet<>();

    @Column(name = "tag_content", nullable = false)
    private String tagContent;

    @Builder
    Tag(Review review, String tagContent) {
        reviews.add(review);
        this.tagContent = tagContent;
    }
}
