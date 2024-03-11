package com.mini.gymnavi.domain.domain.gym.domain;


import com.mini.gymnavi.domain.model.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "gym")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gym extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gymName;
    private String gymAddress;
    private double latitude;
    private double longitude;

    public void changeGymAddress(String address) {
        this.gymAddress = address;
    }
}
