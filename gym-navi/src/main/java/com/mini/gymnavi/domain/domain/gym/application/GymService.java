package com.mini.gymnavi.domain.domain.gym.application;

import com.mini.gymnavi.domain.domain.gym.domain.Gym;
import com.mini.gymnavi.domain.domain.gym.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class GymService {

    private final GymRepository gymRepository;

    @Transactional
    public void updateAddress(Long id, String address){
        Gym gym = gymRepository.findById(id).orElse(null);

        if (Objects.isNull(gym)){
            log.error("[Gym Service updateAddress gmyId : {}]", id);
            return;
        }
        gym.changeGymAddress(address);
    }

    @Transactional(readOnly = true)
    public List<Gym> findAll(){
        return gymRepository.findAll();
    }
}
