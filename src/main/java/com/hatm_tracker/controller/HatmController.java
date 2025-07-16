package com.hatm_tracker.controller;


import com.hatm_tracker.model.dto.HatmDto;
import com.hatm_tracker.service.HatmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hatm-tracker/hatm")
public class HatmController {

    final private HatmService hatmService;

    @GetMapping("/get/{id}")
    public HatmDto getHatmById(@PathVariable Integer id){
        return hatmService.getHatmDtoById(id);
    }

    @GetMapping("/get/all")
    public List<HatmDto> getAllHatm(){
        return hatmService.getAllHatmDto();
    }

    @PostMapping("/create")
    public HatmDto createHatm(@RequestBody HatmDto hatmDto){
        return hatmService.createHatm(hatmDto);
    }

    @PutMapping("/update/{id}")
    public HatmDto updateHatmById(
            @PathVariable Integer id, @RequestBody HatmDto hatmDto){
        return hatmService.updateHatmById(id, hatmDto);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteHatmById(@PathVariable Integer id){
        return hatmService.deleteHatmById(id);
    }
}
