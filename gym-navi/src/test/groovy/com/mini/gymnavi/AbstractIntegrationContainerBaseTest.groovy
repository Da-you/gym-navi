package com.mini.gymnavi

import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.GenericContainer
import spock.lang.Specification

@SpringBootTest // 스프링 컨테이너를 같이 실핼하여 각종 모듈들을 사용하게끔 설정
abstract class AbstractIntegrationContainerBaseTest extends Specification {

    static final GenericContainer MY_REDIS_CONTAINER;


    static {
        MY_REDIS_CONTAINER = new GenericContainer<>("redis:7")
        .withExposedPorts(6379)

        MY_REDIS_CONTAINER.start()

        System.setProperty("spring.redis.host", MY_REDIS_CONTAINER.getHost())
        System.setProperty("spring.redis.port", MY_REDIS_CONTAINER.getMappedPort(6379).toString())
    }
}
