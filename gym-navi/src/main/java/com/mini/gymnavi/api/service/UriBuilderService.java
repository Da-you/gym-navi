package com.mini.gymnavi.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class UriBuilderService {
    private static final String ADDRESS_SEARCH_URL = "https://dapi.kakao.com/v2/local/search/address.json";

    public URI buildUri(String address) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(ADDRESS_SEARCH_URL);
        uriBuilder.queryParam("query",address);

        URI uri = uriBuilder.build().encode().toUri();
        log.info("[ADDRESS_SEARCH_URL] address :{}, uri :{}", address,uri);
        return uri;
    }

}
