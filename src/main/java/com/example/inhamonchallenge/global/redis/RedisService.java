package com.example.inhamonchallenge.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final String suffix = "*";
    private final int limit = 10;

    public List<String> getAutocomplete(String searchWord) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        List<String> autoCompleteList = new ArrayList<>();

        Long rank = zSetOperations.rank("autocomplete-set", searchWord);
        if (rank != null) {
            Set<Object> rangeList = zSetOperations.range("autocomplete-set", rank, rank + 1000);
            if (rangeList != null) {
                for (Object o : rangeList) {
                    String value = (String) o;
                    if (value.endsWith(suffix) && value.startsWith(searchWord)) {
                        autoCompleteList.add(value.replace(suffix, ""));
                        if (autoCompleteList.size() >= limit) {
                            break;
                        }
                    }
                }
            }
        }
        return autoCompleteList;
    }
}
