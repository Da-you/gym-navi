package com.mini.gymnavi.domain.api.service

import spock.lang.Specification

class UriBuilderServiceTest extends Specification {

    private UriBuilderService uriBuilderService;

    def setup(){
        uriBuilderService = new UriBuilderService()
    }

    def "uriBuilderService - 한글 파라미터의 경우 정상적 인코딩"(){
         given:
         String address = "서울 성북구"

        when:
        def uri= uriBuilderService.buildUri(address);

        then:
        println(uri)

    }



}
