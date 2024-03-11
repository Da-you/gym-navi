package com.mini.gymnavi.domain.domain.gym.application;

import com.mini.gymnavi.domain.api.dto.DocumentDto;
import com.mini.gymnavi.domain.api.dto.KakaoApiResponseDto;
import com.mini.gymnavi.domain.api.service.KakaoAddressSearchService;
import com.mini.gymnavi.domain.domain.direction.application.Base62Service;
import com.mini.gymnavi.domain.domain.direction.application.DirectionService;
import com.mini.gymnavi.domain.domain.direction.domain.Direction;
import com.mini.gymnavi.domain.domain.direction.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GymRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;
    private final Base62Service base62Service;


    @Value("${gym.recommendation.base.url}")
    private String baseUrl;

    public List<ResponseDto> recommendGymList(String address) {
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentDtoList())) {
            log.error("[GymRecommendationService recommendGymList fail input address :{}]", address);
            return Collections.emptyList();
        }
        DocumentDto documentDto = kakaoApiResponseDto.getDocumentDtoList().get(0);

        List<Direction> directionList = directionService.buildDirectionListByCategoryApi(documentDto); // 키워드 검색
        log.info("이름 : {}", directionList.get(0).getTargetGymName());

        return directionService.saveAll(directionList)
                .stream()
                .map(o -> convertToResponse(o))
                .collect(Collectors.toList());
    }

    private ResponseDto convertToResponse(Direction direction) {


        return ResponseDto.builder()
                .gymName(direction.getTargetGymName())
                .gymAddress(direction.getTargetAddress())
                .directionUrl(baseUrl + base62Service.encodeDirectionId(direction.getId()))
                .distance(String.format("%.2f km", direction.getDistance()))
                .build();
    }
}
