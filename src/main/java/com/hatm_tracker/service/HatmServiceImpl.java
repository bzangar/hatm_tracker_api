package com.hatm_tracker.service;

import com.hatm_tracker.exception.HatmNotFoundException;
import com.hatm_tracker.model.Mapper;
import com.hatm_tracker.model.dto.HatmDto;
import com.hatm_tracker.model.entity.Hatm;
import com.hatm_tracker.repository.HatmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HatmServiceImpl implements HatmService{

    final private Mapper mapper;
    final private HatmRepository hatmRepository;
    final private UserService userService;

    @Override
    public HatmDto createHatm(HatmDto hatmDto) {
        int maxNumber = hatmRepository.countAllByOrderByHatmNumberAsc() + 1;

        Hatm hatm = Hatm.builder()
                .hatmNumber(maxNumber)
                .name(hatmDto.getName())
                .startTime(hatmDto.getStartTime())
                .endTime(hatmDto.getEndTime())
                .user(userService.getUserById(hatmDto.getUser().getId()))
                .build();
        hatmRepository.save(hatm);
        return mapper.hatmFromEntityToDto(hatm);
    }

    @Override
    public List<HatmDto> getAllHatmDto() {
        return hatmRepository.findAll()
                .stream()
                .map(hatm -> mapper.hatmFromEntityToDto(hatm))
                .toList();
    }

    @Override
    public HatmDto getHatmDtoById(Integer id) {
        Hatm hatm = hatmRepository.findById(id)
                .orElseThrow(() -> new HatmNotFoundException("Hatm doesn't exist"));
        return mapper.hatmFromEntityToDto(hatm);
    }

    @Override
    public HatmDto updateHatmById(Integer id, HatmDto hatmDto) {
        Hatm hatm = hatmRepository.findById(id)
                .orElseThrow(()-> new HatmNotFoundException("Hatm doesn't exists"));
        hatm.setName(hatmDto.getName());
        hatmRepository.save(hatm);
        return null;
    }

    @Override
    public boolean deleteHatmById(Integer id) {
        if(id==null){
            throw new IllegalStateException("Id must not be null");
        }
        if(!hatmRepository.existsById(id)){
            throw new HatmNotFoundException("Hatm doesn't exits!!");
        }

        Hatm hatmToDelete = hatmRepository.findById(id)
                .orElseThrow(()->new HatmNotFoundException("Hatm doesn't exits!!"));

        int deletedHatmNumber = hatmToDelete.getHatmNumber();

        hatmRepository.delete(hatmToDelete);

        List<Hatm> hatmsToUpdate = hatmRepository.findByHatmNumberGreaterThanOrderByHatmNumberAsc(deletedHatmNumber);
        for(Hatm hatm: hatmsToUpdate){
            int currentHatmNumber = hatm.getHatmNumber();
            hatm.setHatmNumber(currentHatmNumber - 1);
        }
        hatmRepository.saveAll(hatmsToUpdate);
        return true;
    }
}
