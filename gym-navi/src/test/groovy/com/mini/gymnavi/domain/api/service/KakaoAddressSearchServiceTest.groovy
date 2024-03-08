package com.mini.gymnavi.domain.api.service

import com.mini.gymnavi.AbstractIntegrationContainerBaseTest
import org.springframework.beans.factory.annotation.Autowired

class KakaoAddressSearchServiceTest extends AbstractIntegrationContainerBaseTest {
    @Autowired
    private KakaoAddressSearchService kakaoAddressSearchService

    def "adddress 파라미터 값이 null이면 null을 리턴한다."() {

        given:
        String address = null

        when:
        def result = kakaoAddressSearchService.requestAddressSearch(address)

        then:
        result == null
    }

    def "주소값이 valid하다면 requestAddressSearch 메서는 정상적으로 documents를 반환"() {
        given:
        String address = "서울 성북구 종암로 10길"

        when:
        def result = kakaoAddressSearchService.requestAddressSearch(address)

        then:
        result.getDocumentDtoList().size() > 0
        result.metaDto.totalCount > 0
        result.documentDtoList.get(0).getAddressName() != null

    }


}
