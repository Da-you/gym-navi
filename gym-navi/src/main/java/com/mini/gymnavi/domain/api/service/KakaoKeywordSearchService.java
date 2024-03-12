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
public class KakaoKeywordSearchService {

    private final UriBuilderService uriBuilderService;
    private final RestTemplate restTemplate;
    @Value("${kakao.rest.api.key}")
    private String apiKey;


    public KakaoApiResponseDto requestGymCategorySearch(double latitude, double longitude, double radius) {

        URI uri = uriBuilderService.buildUriByCategorySearch(latitude, longitude, radius, "헬스장");

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + apiKey);
        HttpEntity httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody();
    }
}
