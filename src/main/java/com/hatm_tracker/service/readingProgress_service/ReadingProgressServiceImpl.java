package com.hatm_tracker.service.readingProgress_service;

import com.hatm_tracker.exception.ReadingProgressNotFoundException;
import com.hatm_tracker.model.Mapper;
import com.hatm_tracker.model.dto.ReadingProgressDto;
import com.hatm_tracker.model.entity.Hatm;
import com.hatm_tracker.model.entity.ReadingProgress;
import com.hatm_tracker.repository.HatmRepository;
import com.hatm_tracker.repository.ReadingProgressRepository;
import com.hatm_tracker.service.hatm_service.HatmService;
import com.hatm_tracker.util.HatmErrors;
import com.hatm_tracker.util.HatmStatements;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingProgressServiceImpl implements ReadingProgressService{

    final private ReadingProgressRepository readingProgressRepository;
    final private Mapper mapper;
    final private HatmService hatmService;
    final private HatmRepository hatmRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReadingProgressServiceImpl.class.getName());
    private static final int TOTAL_PAGES = HatmStatements.TOTAL_PAGES.getTotalPages();

    @Override
    public ReadingProgressDto createHatmReadingProgress(ReadingProgressDto readingProgressDto) {
        LocalDateTime now = LocalDateTime.now();

        ReadingProgress readingProgress = ReadingProgress.builder()
                .hatm(hatmService.getHatmById(readingProgressDto.getHatm().getId()))
                .pageReadTo(readingProgressDto.getPageReadTo())
                .dateTime(now)
                .build();
        readingProgressRepository.save(readingProgress);

        Hatm currentHatm = readingProgress.getHatm();
        ReadingProgress lastAddedRP = readingProgressRepository.
                findTopByHatmOrderByDateTimeDesc(currentHatm)
                .orElseThrow(()->new ReadingProgressNotFoundException(HatmErrors.READING_PROGRESS_NOT_FOUND.getMessage()));

        int lastPageReadTo = lastAddedRP.getPageReadTo();

        if(lastPageReadTo >= TOTAL_PAGES){
            LocalDate today = LocalDate.now();
            currentHatm.setEnd(true);
            currentHatm.setEndTime(today);
            hatmRepository.save(currentHatm);
            logger.info("Current Hatm ID: " + currentHatm.getId());
            logger.info("Current Hatm id END?: " + currentHatm.isEnd());
        }

        logger.info("Last added RP ID: " + lastAddedRP.getId());

        return mapper.readingProgressFromEntityToDto(readingProgress);
    }

    @Override
    public List<ReadingProgressDto> getAllReadingProgressDto() {

        return readingProgressRepository.findAll().
                stream()
                .map(rp -> mapper.readingProgressFromEntityToDto(rp))
                .toList();
    }

    @Override
    public ReadingProgressDto getReadingProgressDtoById(Integer id) {

        return mapper.readingProgressFromEntityToDto(readingProgressRepository.findById(id)
                .orElseThrow( ()->
                        new ReadingProgressNotFoundException(HatmErrors.READING_PROGRESS_NOT_FOUND.getMessage())));
    }

    @Override
    public ReadingProgressDto updateReadingProgressById(Integer id, ReadingProgressDto readingProgressDto) {
        ReadingProgress readingProgress = readingProgressRepository.findById(id)
                .orElseThrow( ()->
                        new ReadingProgressNotFoundException(HatmErrors.READING_PROGRESS_NOT_FOUND.getMessage()));

        readingProgress.setPageReadTo(readingProgressDto.getPageReadTo());
        readingProgress.setHatm(hatmService.getHatmById(readingProgressDto.getHatm().getId()));
        readingProgressRepository.save(readingProgress);

        return mapper.readingProgressFromEntityToDto(readingProgress);
    }

    @Override
    public boolean deleteReadingProgressById(Integer id) {

        if(id == null){
            throw new IllegalStateException(HatmErrors.NULL_ID.getMessage());
        }

        if(!readingProgressRepository.existsById(id)){
            throw new  ReadingProgressNotFoundException(HatmErrors.READING_PROGRESS_NOT_FOUND.getMessage());
        }

        readingProgressRepository.deleteById(id);

        return true;
    }
}
