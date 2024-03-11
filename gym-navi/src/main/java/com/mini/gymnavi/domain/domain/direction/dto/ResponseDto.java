package com.mini.gymnavi.domain.domain.direction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDto {
    private String gymName;    // gym 명
    private String gymAddress; // gym 주소
    private String directionUrl;    // 길안내 url
    private String distance;        // 고객 주소와 주소의 거리
}
