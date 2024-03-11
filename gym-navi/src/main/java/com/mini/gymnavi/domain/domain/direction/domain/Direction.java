package com.mini.gymnavi.domain.domain.direction.domain;

import com.mini.gymnavi.domain.model.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "direction")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Direction extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 사용자
    private String inputAddress;
    private double inputLatitude;
    private double inputLongitude;

    // 헬스장
    private String targetGymName;
    private String targetAddress;
    private double targetLatitude;
    private double targetLongitude;

    private double distance;
}