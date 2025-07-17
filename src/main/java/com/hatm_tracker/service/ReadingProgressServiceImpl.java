package com.hatm_tracker.service;

import com.hatm_tracker.exception.ReadingProgressNotFoundException;
import com.hatm_tracker.model.Mapper;
import com.hatm_tracker.model.dto.ReadingProgressDto;
import com.hatm_tracker.model.entity.ReadingProgress;
import com.hatm_tracker.repository.ReadingProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingProgressServiceImpl implements ReadingProgressService{

    final private ReadingProgressRepository repository;
    final private Mapper mapper;
    final private HatmService hatmService;

    @Override
    public ReadingProgressDto createHatmReadingProgress(ReadingProgressDto readingProgressDto) {
        ReadingProgress readingProgress = ReadingProgress.builder()
                .hatm(hatmService.getHatmById(readingProgressDto.getHatm().getId()))
                .pageReadTo(readingProgressDto.getPageReadTo())
                .date(readingProgressDto.getDate())
                .build();
        repository.save(readingProgress);
        return mapper.readingProgressFromEntityToDto(readingProgress);
    }

    @Override
    public List<ReadingProgressDto> getAllReadingProgressDto() {
        return repository.findAll().
                stream()
                .map(rp -> mapper.readingProgressFromEntityToDto(rp))
                .toList();
    }

    @Override
    public ReadingProgressDto getReadingProgressDtoById(Integer id) {
        return mapper.readingProgressFromEntityToDto(repository.findById(id)
                .orElseThrow( ()->
                        new ReadingProgressNotFoundException("Reading progress does not exits!!")));
    }

    @Override
    public ReadingProgressDto updateReadingProgressById(Integer id, ReadingProgressDto readingProgressDto) {
        ReadingProgress readingProgress = repository.findById(id)
                .orElseThrow( ()->
                        new ReadingProgressNotFoundException("Reading progress does not exits!!"));
        readingProgress.setDate(readingProgressDto.getDate());
        readingProgress.setPageReadTo(readingProgressDto.getPageReadTo());
        readingProgress.setHatm(hatmService.getHatmById(readingProgressDto.getHatm().getId()));
        repository.save(readingProgress);
        return mapper.readingProgressFromEntityToDto(readingProgress);
    }

    @Override
    public boolean deleteReadingProgressById(Integer id) {
        if(id == null){
            throw new IllegalStateException("Id must not be null");
        }
        if(!repository.existsById(id)){
            throw new  ReadingProgressNotFoundException("Reading progress does not exits!!");
        }
        repository.deleteById(id);
        return true;
    }
}
