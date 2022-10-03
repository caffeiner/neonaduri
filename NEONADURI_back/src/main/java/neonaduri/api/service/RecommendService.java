package neonaduri.api.service;

import lombok.RequiredArgsConstructor;
import neonaduri.api.repository.SpotRepository;
import neonaduri.constant.RedisConst;
import neonaduri.dto.response.RecommendSpotRes;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendService implements RedisConst {

    private final RedisTemplate<String, Long> redisTemplate;
    private final SpotRepository spotRepository;

    public RecommendSpotRes getHotSpot() {
        Long hotSpotId = countHotSpotFromRedis();

//        RecommendSpotRes hotSpotBySpotId = spotRepository.findHotSpotBySpotId(hotSpotId);
//        System.out.println("hotSpotBySpotId = " + hotSpotBySpotId);
        return null;
    }

    private Long countHotSpotFromRedis() {

        final List<Long> allCacheInRedis = redisTemplate.opsForList().range(REDIS_KEY, 0, -1);

        if (Objects.isNull(allCacheInRedis)) throw new NoSuchElementException();

        Optional<Long> optionSpotId = allCacheInRedis.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey);

        return Long.parseLong(String.valueOf(
                optionSpotId.orElse(REDIS_DEFAULT_SPOT_ID)));
    }

    @Async
    public void cachingSpotData(Long spotId) {
        final ListOperations<String, Long> listOperations = redisTemplate.opsForList();

        if (!canCachingSize()) listOperations.leftPop(REDIS_KEY);
        listOperations.rightPush(REDIS_KEY, spotId);
    }

    private Boolean canCachingSize() {
        Long cacheSize = redisTemplate.opsForList().size(REDIS_KEY);
        return Objects.isNull(cacheSize) ||
                cacheSize.intValue() < REDIS_SIZE;
    }
}
