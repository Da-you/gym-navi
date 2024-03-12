package com.mini.gymnavi.domain.domain.gym.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.gymnavi.domain.domain.gym.dto.GymDto;
import com.mini.gymnavi.domain.domain.gym.dto.GymDto.GymResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class GymRedisService {

    private static final String CACHE_KEY = "GYM";
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    private HashOperations<String, String, String> hashOperations;; // 키값, pk값, 필드값

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }


    public void save(GymResponseDto gymResponseDto) {
        if(Objects.isNull(gymResponseDto) || Objects.isNull(gymResponseDto.getId())) {
            log.error("Required Values must not be null");
            return;
        }

        try {
            hashOperations.put(CACHE_KEY,
                    gymResponseDto.getId().toString(),
                    serializePharmacyDto(gymResponseDto));
            log.info("[GymRedisService save success] id: {}", gymResponseDto.getId());
        } catch (Exception e) {
            log.error("[GymRedisService save error] {}", e.getMessage());
        }
    }

    public List<GymResponseDto> findAll() {

        try {
            List<GymResponseDto> list = new ArrayList<>();
            for (String value : hashOperations.entries(CACHE_KEY).values()) {
                GymResponseDto gymResponseDto = deserializePharmacyDto(value);
                list.add(gymResponseDto);
            }
            return list;

        } catch (Exception e) {
            log.error("GymRedisService findAll error]: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public void delete(Long id) {
        hashOperations.delete(CACHE_KEY, String.valueOf(id));
        log.info("[GymRedisService delete]: {} ", id);
    }

    private String serializePharmacyDto(GymResponseDto gymResponseDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(gymResponseDto);
    }

    private GymResponseDto deserializePharmacyDto(String value) throws JsonProcessingException, JsonProcessingException {
        return objectMapper.readValue(value, GymResponseDto.class);
    }
}
