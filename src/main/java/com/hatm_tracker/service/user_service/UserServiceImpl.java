package com.hatm_tracker.service.user_service;

import com.hatm_tracker.exception.UserNotFoundException;
import com.hatm_tracker.model.Mapper;
import com.hatm_tracker.model.dto.HatmDto;
import com.hatm_tracker.model.dto.UserDto;
import com.hatm_tracker.model.dto.UserReqDto;
import com.hatm_tracker.model.entity.User;
import com.hatm_tracker.repository.HatmRepository;
import com.hatm_tracker.repository.UserRepository;
import com.hatm_tracker.util.HatmErrors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private Mapper mapper;
    final private HatmRepository hatmRepository;


    @Override
    public UserDto getUserDtoById(Integer id) {
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(HatmErrors.USER_NOT_FOUND.getMessage()));

        return mapper.userFromEntityToDto(user);
    }

    @Override
    public List<UserDto> getAllUserDto() {

        return userRepository.findAll()
                .stream()
                .map(user -> mapper.userFromEntityToDto(user))
                .toList();
    }

    @Override
    public UserDto createUser(User user) {
        userRepository.save(user);

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

    @Override
    public boolean deleteUserById(Integer id) {

        if(id==null){
            throw new IllegalStateException(HatmErrors.NULL_ID.getMessage());
        }

        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(HatmErrors.USER_NOT_FOUND.getMessage());
        }

        userRepository.deleteById(id);

        return true;
    }

    @Override
    public UserReqDto updateUserById(Integer id, UserReqDto userReqDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(HatmErrors.USER_NOT_FOUND.getMessage()));
        user.setName(userReqDto.getName());
        user.setUsername(userReqDto.getUsername());
        user.setPassword(user.getPassword());
        userRepository.save(user);

        return mapper.userFromEntityToDto_Req(user);
    }

    @Override
    public User getUserById(Integer id) {

        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(HatmErrors.USER_NOT_FOUND.getMessage()));
    }

    @Override
    public List<HatmDto> getAllHatmDtoById(Integer id) {

        return hatmRepository.findAllByUserId(id)
                .stream()
                .map(hatm-> mapper.hatmFromEntityToDto(hatm))
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
