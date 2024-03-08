package com.mini.gymnavi.domain.domain.gym.repository

import com.mini.gymnavi.AbstractIntegrationContainerBaseTest
import com.mini.gymnavi.domain.domain.gym.domain.Gym
import org.springframework.beans.factory.annotation.Autowired

class GymRepositoryTest extends AbstractIntegrationContainerBaseTest {
    @Autowired
    private GymRepository gymRepository

    def setup() {
        gymRepository.deleteAll()
    }

    def "GymRepository save"() {
        given:
        String address = "서울 특별시 성북구 종암동"
        String name = "은혜 약국"
        double lat = 36.11
        double lon = 128.11

        def gym = Gym.builder()
                .gymAddress(address)
                .gymName(name)
                .latitude(lat)
                .longitude(lon)
                .build()
        when:
        def entity = gymRepository.save(gym)
        then:
        entity.getGymAddress() == address
        entity.getGymName() == name
        entity.getLatitude() == lat
        entity.getLongitude() == lon
    }

    def "GymRepository saveAll"() {
        given:
        String address = "서울 특별시 성북구 종암동"
        String name = "은혜 약국"
        double lat = 36.11
        double lon = 128.11

        def gym = Gym.builder()
                .gymAddress(address)
                .gymName(name)
                .latitude(lat)
                .longitude(lon)
                .build()
        when:
        gymRepository.saveAll(Arrays.asList(gym))
        def result = gymRepository.findAll()

        then:
        result.size() == 1

    }

}
