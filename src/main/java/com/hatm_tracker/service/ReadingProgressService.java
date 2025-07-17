package com.hatm_tracker.service;

import com.hatm_tracker.model.dto.ReadingProgressDto;

import java.util.List;

public interface ReadingProgressService {
    ReadingProgressDto createHatmReadingProgress(ReadingProgressDto readingProgressDto);

    List<ReadingProgressDto> getAllReadingProgressDto();

    ReadingProgressDto getReadingProgressDtoById(Integer id);

    ReadingProgressDto updateReadingProgressById(Integer id, ReadingProgressDto readingProgressDto);

    boolean deleteReadingProgressById(Integer id);
}
