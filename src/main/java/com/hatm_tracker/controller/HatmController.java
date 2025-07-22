package com.hatm_tracker.controller;


import com.hatm_tracker.model.dto.HatmDto;
import com.hatm_tracker.model.dto.ReadingProgressDto;
import com.hatm_tracker.service.hatm_service.HatmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hatm-tracker/hatms")
public class HatmController {

    final private HatmService hatmService;

    @GetMapping("/{id}")
    public HatmDto getHatmById(@PathVariable Integer id){
        return hatmService.getHatmDtoById(id);
    }

    @GetMapping()
    public List<HatmDto> getAllHatm(){
        return hatmService.getAllHatmDto();
    }

    @PostMapping()
    public HatmDto createHatm(@RequestBody HatmDto hatmDto){
        return hatmService.createHatm(hatmDto);
    }

    @PutMapping("/{id}")
    public HatmDto updateHatmById(
            @PathVariable Integer id, @RequestBody HatmDto hatmDto){
        return hatmService.updateHatmById(id, hatmDto);
    }

    @DeleteMapping("{id}")
    public boolean deleteHatmById(@PathVariable Integer id){
        return hatmService.deleteHatmById(id);
    }

    @GetMapping("/{id}/all-reading-progresses")
    public List<ReadingProgressDto> getAllReadingProgresses(@PathVariable Integer id){
        return hatmService.getAllReadingProgressById(id);
    }
}
