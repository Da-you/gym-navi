package com.mini.gymnavi.domain.domain.gym.application;

import com.mini.gymnavi.domain.api.dto.DocumentDto;
import com.mini.gymnavi.domain.api.dto.KakaoApiResponseDto;
import com.mini.gymnavi.domain.api.service.KakaoAddressSearchService;
import com.mini.gymnavi.domain.domain.direction.application.DirectionService;
import com.mini.gymnavi.domain.domain.direction.domain.Direction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class GymRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendGymList(String address) {
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentDtoList())) {
            log.error("[GymRecommendationService recommendGymList fail input address :{}]", address);
            return;
        }
        DocumentDto documentDto = kakaoApiResponseDto.getDocumentDtoList().get(0);

//        List<Direction> directionList = directionService.buildDirectionList(documentDto); // 공공기관 데이터
        List<Direction> directionList = directionService.buildDirectionListByCategoryApi(documentDto); // 키워드 검색

        directionService.saveAll(directionList);
    }
}
