package com.hatm_tracker.controller;


import com.hatm_tracker.model.dto.ReadingProgressDto;
import com.hatm_tracker.service.readingProgress_service.ReadingProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hatm-tracker/reading-progress")
@RequiredArgsConstructor
public class ReadingProgressController {

    final private ReadingProgressService readingProgressService;

    @GetMapping("/{id}")
    public ReadingProgressDto getReadingProgressById(@PathVariable Integer id){
        return readingProgressService.getReadingProgressDtoById(id);
    }

    @GetMapping()
    public List<ReadingProgressDto> getAllReadingProgress(){
        return readingProgressService.getAllReadingProgressDto();
    }

    @PostMapping()
    public ReadingProgressDto createReadingProgress(@RequestBody ReadingProgressDto readingProgressDto){
        return readingProgressService.createHatmReadingProgress(readingProgressDto);
    }

    @PutMapping("/{id}")
    public ReadingProgressDto updateReadingProgressById(
            @PathVariable Integer id, @RequestBody ReadingProgressDto readingProgressDto){
        return readingProgressService.updateReadingProgressById(id, readingProgressDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteReadingProgressyId(@PathVariable Integer id){
        return readingProgressService.deleteReadingProgressById(id);
    }
}
