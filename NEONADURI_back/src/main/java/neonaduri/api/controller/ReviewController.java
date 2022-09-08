package neonaduri.api.controller;

import lombok.RequiredArgsConstructor;
import neonaduri.api.service.ReviewService;
import neonaduri.api.service.SpotService;
import neonaduri.dto.request.CreateReviewReq;
import neonaduri.dto.response.SpotRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<HttpStatus> createReview(@Valid @RequestBody CreateReviewReq createReviewReq) {
        System.out.println("createReviewReq = " + createReviewReq);
        reviewService.postReview(createReviewReq);
        return ResponseEntity.ok().build();
    }
}
