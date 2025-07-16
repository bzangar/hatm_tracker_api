package com.hatm_tracker.service;

import com.hatm_tracker.model.dto.HatmDto;
import org.hibernate.dialect.H2SqlAstTranslator;

import java.util.List;

public interface HatmService {

    HatmDto createHatm(HatmDto hatmDto);

    List<HatmDto> getAllHatmDto();

    HatmDto getHatmDtoById(Integer id);

    HatmDto updateHatmById(Integer id, HatmDto hatmDto);

    boolean deleteHatmById(Integer id);
}
