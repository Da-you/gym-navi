package com.mini.gymnavi.domain.domain.direction.application

import com.mini.gymnavi.domain.api.dto.DocumentDto
import com.mini.gymnavi.domain.domain.gym.application.GymSearchService

import spock.lang.Specification

import static com.mini.gymnavi.domain.domain.gym.dto.GymDto.*
import static com.mini.gymnavi.domain.domain.gym.dto.GymDto.GymResponseDto.*

class DirectionServiceTest extends Specification {

    private GymSearchService gymSearchService = Mock();
    private DirectionService directionService = new DirectionService(gymSearchService)
    private List<GymResponseDto> gymList;

    def setup() {
        gymList = new ArrayList<>()
        gymList.addAll(
                builder()
                        .id(1L)
                        .gymName("파크짐 한양대점")
                        .gymAddress("서울특별시 성동구 행당동 32-6")
                        .latitude(37.5570159324068)
                        .longitude(127.042438861029)
                        .build(),
                builder()
                        .id(2L)
                        .gymName("크로스핏 아띠")
                        .gymAddress("서울특별시 성동구 도선동 402 승우카센타")
                        .latitude(37.5647327987902)
                        .longitude(127.030439601512)
                        .build()
        )
    }

    def "buildDirectionList - 결과 없이 거리순으로 정렬이 되는지 확인"(){
        given:
        def addressName = "서울특별시 성동구 행당동 32-6"
        double latitude = 37.5570159324068
        double longitude = 127.042438861029

        def documentDto = DocumentDto.builder()
        .addressName(addressName)
        .latitude(latitude)
        .longitude(longitude)
        .build()

        when:
       gymSearchService.searchGymDtoList() >> gymList

        def result = directionService.buildDirectionList(documentDto)

        then:
        result.size() == 2
        result.get(0).targetGymName == "파크짐 한양대점"
        result.get(1).targetGymName == "크로스핏 아띠"

    }
//    def "buildDirectionList - 정해진 반경 검색이 되는지 확인"(){
//
//        given:
//        def addressName = "서울특별시 성동구 행당동 32-6"
//        double latitude = 37.5570159324068
//        double longitude = 127.042438861029
//
//        def documentDto = DocumentDto.builder()
//                .addressName(addressName)
//                .latitude(latitude)
//                .longitude(latitude)
//                .build()
//
//        when:
//        gymSearchService.searchGymDtoList() >> gymList
//
//        def result = directionService.buildDirectionList(documentDto)
//
//
//    }
}
