package com.mini.gymnavi.domain.domain.gym.application;

import com.mini.gymnavi.domain.domain.gym.domain.Gym;
import com.mini.gymnavi.domain.domain.gym.dto.GymDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GymSearchService {

    private final GymService gymService;
    private final GymRedisService gymRedisService;


    public List<GymDto.GymResponseDto> searchGymDtoList() {

        List<GymDto.GymResponseDto> gymResponseDtos = gymRedisService.findAll();
        if (!gymResponseDtos.isEmpty()) return gymResponseDtos;

        return gymService.findAll()
                .stream()
                .map(entity -> convertToDto(entity))
                .collect(Collectors.toList());
    }

    private GymDto.GymResponseDto convertToDto(Gym gym) {
        return GymDto.GymResponseDto.builder()
                .id(gym.getId())
                .gymName(gym.getGymName())
                .gymAddress(gym.getGymAddress())
                .latitude(gym.getLatitude())
                .longitude(gym.getLongitude())
                .build();
    }
}
