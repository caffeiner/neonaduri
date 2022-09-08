package neonaduri.api.repository;

import neonaduri.domain.Spot;
import neonaduri.dto.response.SpotReviewRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SpotRepository extends JpaRepository<Spot, Long> {

    Spot findSpotBySpotId(Long spotId);
    Spot findReviewBySpotContent(String spotContent);

    SpotReviewRes findSpotReviewBySpotId(Long spotId);
}
