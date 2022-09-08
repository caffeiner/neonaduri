package neonaduri.api.controller;

import lombok.RequiredArgsConstructor;
import neonaduri.api.service.SpotService;
import neonaduri.domain.Spot;
import neonaduri.dto.response.SpotReviewRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/spot")
public class SpotController {

    private final SpotService spotService;

    @GetMapping("/{content}")
    public ResponseEntity<Spot> findSpotWithContent(@PathVariable String content) {
        return ResponseEntity.ok(spotService.getSpotWithContent(content));
    }

    @GetMapping("/review/{spotId}")
    public ResponseEntity<SpotReviewRes> showSpotReview(@PathVariable("spotId") Long spotId) {
        return ResponseEntity.ok(spotService.getSpotReview(spotId));
    }
}
