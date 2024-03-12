package com.mini.gymnavi.domain.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class UriBuilderService {
    private static final String ADDRESS_SEARCH_URL = "https://dapi.kakao.com/v2/local/search/address.json";

    private static final String KEYWORD_SEARCH_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

    public URI buildUri(String address) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(ADDRESS_SEARCH_URL);
        uriBuilder.queryParam("query",address);

        URI uri = uriBuilder.build().encode().toUri();
        log.info("[ADDRESS_SEARCH_URL] address :{}, uri :{}", address,uri);
        return uri;
    }

    public URI buildUriByCategorySearch(double latitude, double longitude, double radius, String keyword) {

        double meterRadius = radius * 1000;

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(KEYWORD_SEARCH_URL);
        uriBuilder.queryParam("query", keyword);
        uriBuilder.queryParam("x", longitude);
        uriBuilder.queryParam("y", latitude);
        uriBuilder.queryParam("radius", meterRadius);
        uriBuilder.queryParam("sort","distance");

        URI uri = uriBuilder.build().encode().toUri();

        log.info("[KakaoAddressSearchService buildUriByCategorySearch] uri: {} ", uri);

        return uri;
    }

}
