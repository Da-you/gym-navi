package com.mini.gymnavi.domain.api.service;

import com.mini.gymnavi.domain.api.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoCategorySearchService {

    private final UriBuilderService uriBuilderService;
    private final RestTemplate restTemplate;
    @Value("${kakao.rest.api.key}")
    private String apiKey;

    private static final String PHARMACY_CATEGORY = "CT1";

    public KakaoApiResponseDto requestGymCategorySearch(double latitude, double longitude, double radius) {

        URI uri = uriBuilderService.buildUriByCategorySearch(latitude, longitude, radius, PHARMACY_CATEGORY);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + apiKey);
        HttpEntity httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody();
    }
}
