package neonaduri.api.service;

import lombok.RequiredArgsConstructor;
import neonaduri.api.repository.SpotRepository;
import neonaduri.domain.Spot;
import neonaduri.dto.response.SpotReviewRes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SpotService {
    private final SpotRepository spotRepository;

    public Spot getSpotWithContent(String spotContent) {
        return spotRepository.findReviewBySpotContent(spotContent);
    }

    public SpotReviewRes getSpotReview(Long spotId) {
        return spotRepository.findSpotReviewBySpotId(spotId);
    }
}
