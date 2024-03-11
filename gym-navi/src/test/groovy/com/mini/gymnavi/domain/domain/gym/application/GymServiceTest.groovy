package com.mini.gymnavi.domain.domain.gym.application

import com.mini.gymnavi.AbstractIntegrationContainerBaseTest
import com.mini.gymnavi.domain.domain.gym.domain.Gym
import com.mini.gymnavi.domain.domain.gym.repository.GymRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import java.time.LocalDateTime

class GymServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private GymService gymService

    @Autowired
    private GymRepository gymRepository

    def setup() {
        gymRepository.deleteAll()
    }

    def "gym address change"() {
        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String updateAddress = "서울 광진구 구의동"
        String name = "은혜 약국"


        def gym = Gym.builder()
                .gymAddress(inputAddress)
                .gymName(name)
                .build()

        when:
        def entity = gymRepository.save(gym)
        gymService.updateAddress(entity.getId(), updateAddress)

        def result = gymRepository.findAll()

        then:
        result.get(0).getGymAddress() == updateAddress

    }

    def "BaseTimeEntity 등록"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        String address = "서울 특별시 성북구 종암동"
        String name = "은혜 약국"


        def gym = Gym.builder()
                .gymAddress(address)
                .gymName(name)
                .build()

        when:
        gymRepository.save(gym)
        def result = gymRepository.findAll()

        then:
        result.get(0).getCreatedAt().isAfter(now)
        result.get(0).getModifiedAt().isAfter(now)
    }
}
