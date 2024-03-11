package com.mini.gymnavi.domain.domain.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GymDto {


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GymResponseDto {
        private Long id;
        private String gymName;
        private String gymAddress;
        private double latitude;
        private double longitude;
    }
}
