package com.mini.gymnavi.domain.domain.direction.application;

import com.mini.gymnavi.domain.api.dto.DocumentDto;
import com.mini.gymnavi.domain.api.service.KakaoCategorySearchService;
import com.mini.gymnavi.domain.domain.direction.domain.Direction;
import com.mini.gymnavi.domain.domain.direction.repository.DirectionRepository;
import com.mini.gymnavi.domain.domain.gym.application.GymSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectionService {
    private final GymSearchService gymSearchService;
    private final DirectionRepository directionRepository;
    private final KakaoCategorySearchService kakaoCategorySearchService;

    private static final int MAX_SEARCH = 3; //최대 검색 개수
    private static final double RADIUS_KM = 10.0; // 반경

    public List<Direction> saveAll(List<Direction> directionList) {
        if (directionList.isEmpty()) return Collections.emptyList();
        return directionRepository.saveAll(directionList);
    }


    public List<Direction> buildDirectionList(DocumentDto documentDto) {
        if (Objects.isNull(documentDto)) {
            return Collections.emptyList();
        }

        return gymSearchService.searchGymDtoList()
                .stream()
                .map(gymDto ->
                        Direction.builder()
                                .inputAddress(documentDto.getAddressName())
                                .inputLatitude(documentDto.getLatitude())
                                .inputLongitude(documentDto.getLongitude())
                                .targetGymName(gymDto.getGymName())
                                .targetAddress(gymDto.getGymAddress())
                                .targetLatitude(gymDto.getLatitude())
                                .targetLongitude(gymDto.getLongitude())
                                .distance(
                                        calculateDistance(documentDto.getLatitude(), documentDto.getLongitude(),
                                                gymDto.getLatitude(), gymDto.getLongitude())
                                )
                                .build())
                .filter(direction -> direction.getDistance() <= RADIUS_KM)
                .sorted(Comparator.comparing(Direction::getDistance))
                .limit(MAX_SEARCH)
                .collect(Collectors.toList());
    }
    public List<Direction> buildDirectionListByCategoryApi(DocumentDto inputDocumentDto) {
        if(Objects.isNull(inputDocumentDto)) return Collections.emptyList();

        return kakaoCategorySearchService
                .requestGymCategorySearch(inputDocumentDto.getLatitude(), inputDocumentDto.getLongitude(), RADIUS_KM)
                .getDocumentDtoList()
                .stream().map(resultDocumentDto ->
                        Direction.builder()
                                .inputAddress(inputDocumentDto.getAddressName())
                                .inputLatitude(inputDocumentDto.getLatitude())
                                .inputLongitude(inputDocumentDto.getLongitude())
                                .targetGymName(resultDocumentDto.getPlaceName())
                                .targetAddress(resultDocumentDto.getAddressName())
                                .targetLatitude(resultDocumentDto.getLatitude())
                                .targetLongitude(resultDocumentDto.getLongitude())
                                .distance(resultDocumentDto.getDistance() * 0.001) // km 단위
                                .build())
                .limit(MAX_SEARCH)
                .collect(Collectors.toList());
    }

    //Haversine formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371; //Kilometers
        return earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }
}
