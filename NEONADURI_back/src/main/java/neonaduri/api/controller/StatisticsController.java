package neonaduri.api.controller;

import lombok.RequiredArgsConstructor;
import neonaduri.api.service.StatisticsService;
import neonaduri.domain.statistics.Visited;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    /** 만족도 */

    @GetMapping("/sat")
    public ResponseEntity<?> getSatisfaction() {
        return new ResponseEntity<>(statisticsService.findSatisfaction(),HttpStatus.OK);
    }

    /**선택 이유 */
    @GetMapping("/sel")
    public ResponseEntity<?> getSelected() {
        return new ResponseEntity<>(statisticsService.findSelected(), HttpStatus.OK);
    }

    /** 관광지 방문 횟수 */
    @GetMapping("/visited")
    public ResponseEntity<List<Visited>> getVisited() {
        return new ResponseEntity<>(statisticsService.findVisited(), HttpStatus.OK);

    }
}
