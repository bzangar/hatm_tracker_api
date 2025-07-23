package com.hatm_tracker.service.hatm_service;

import com.hatm_tracker.exception.HatmNotFoundException;
import com.hatm_tracker.exception.PrevousHatmIsNotEnded;
import com.hatm_tracker.model.Mapper;
import com.hatm_tracker.model.dto.HatmDto;
import com.hatm_tracker.model.dto.ReadingProgressDto;
import com.hatm_tracker.model.entity.Hatm;
import com.hatm_tracker.repository.HatmRepository;
import com.hatm_tracker.repository.ReadingProgressRepository;
import com.hatm_tracker.service.user_service.UserService;
import com.hatm_tracker.util.HatmErrors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class HatmServiceImpl implements HatmService{

    final private Mapper mapper;
    final private HatmRepository hatmRepository;
    final private UserService userService;
    final private ReadingProgressRepository readingProgressRepository;
    private static Logger logger = LoggerFactory.getLogger(HatmServiceImpl.class.getName());

    @Override
    public HatmDto createHatm(HatmDto hatmDto) {
        int maxNumber = hatmRepository.countAllByOrderByHatmNumberAsc() + 1;
        LocalDate today = LocalDate.now();

        if(!getAllHatmDtoByUserId(hatmDto.getUser().getId()).isEmpty()){
            Hatm latestHatm = hatmRepository.findTopByUserOrderByHatmNumberDesc(userService.getUserById(hatmDto.getUser().getId()))
                    .orElseThrow(()-> new HatmNotFoundException(HatmErrors.HATM_NOT_FOUND.getMessage()));

            logger.info("Previous Hatm ID: " + latestHatm.getId());

            if(!latestHatm.isEnd()){
                throw new PrevousHatmIsNotEnded(HatmErrors.PREVOUS_HATM_IS_NOT_ENDED.getMessage());
            }

        }

        Hatm hatm = Hatm.builder()
                .hatmNumber(maxNumber)
                .name(hatmDto.getName())
                .startTime(today)
                .endTime(null)
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
                .orElseThrow(() -> new HatmNotFoundException(HatmErrors.HATM_NOT_FOUND.getMessage()));

        return mapper.hatmFromEntityToDto(hatm);
    }

    @Override
    public HatmDto updateHatmById(Integer id, HatmDto hatmDto) {
        Hatm hatm = hatmRepository.findById(id)
                .orElseThrow(()-> new HatmNotFoundException(HatmErrors.HATM_NOT_FOUND.getMessage()));
        hatm.setName(hatmDto.getName());
        hatmRepository.save(hatm);

        return null;
    }

    @Override
    public boolean deleteHatmById(Integer id) {

        if(id==null){
            throw new IllegalStateException(HatmErrors.NULL_ID.getMessage());
        }

        if(!hatmRepository.existsById(id)){
            throw new HatmNotFoundException(HatmErrors.HATM_NOT_FOUND.getMessage());
        }

        Hatm hatmToDelete = hatmRepository.findById(id)
                .orElseThrow(()->new HatmNotFoundException(HatmErrors.HATM_NOT_FOUND.getMessage()));

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

    @Override
    public Hatm getHatmById(Integer id) {

        return hatmRepository.findById(id)
                .orElseThrow(()-> new HatmNotFoundException(HatmErrors.HATM_NOT_FOUND.getMessage()));
    }

    @Override
    public List<ReadingProgressDto> getAllReadingProgressById(Integer id) {

        return readingProgressRepository.findAllByHatmId(id)
                .stream()
                .map(rp->mapper.readingProgressFromEntityToDto(rp))
                .toList();
    }

    @Override
    public List<HatmDto> getAllHatmDtoByUserId(Integer id) {

        return hatmRepository.findAllByUserId(id)
                .stream()
                .map(hatm-> mapper.hatmFromEntityToDto(hatm))
                .toList();
    }
}
