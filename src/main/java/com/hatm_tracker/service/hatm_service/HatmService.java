package com.hatm_tracker.service.hatm_service;

import com.hatm_tracker.model.dto.HatmDto;
import com.hatm_tracker.model.dto.ReadingProgressDto;
import com.hatm_tracker.model.entity.Hatm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface HatmService {

    HatmDto createHatm(HatmDto hatmDto, UserDetails userDetails);

    List<HatmDto> getAllHatmDto();

    List<HatmDto> getAllHatmDtoByUserId(Integer id);

    HatmDto getHatmDtoById(Integer id);

    HatmDto updateHatmById(Integer id, HatmDto hatmDto);

    boolean deleteHatmById(Integer id);

    Hatm getHatmById(Integer id);

    List<ReadingProgressDto> getAllReadingProgressById(Integer id);
}
