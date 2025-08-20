package com.hatm_tracker.service.user_service;

import com.hatm_tracker.exception.UserNotFoundException;
import com.hatm_tracker.model.Mapper;
import com.hatm_tracker.model.dto.RegisterUserDto;
import com.hatm_tracker.model.dto.UserReqDto;
import com.hatm_tracker.model.entity.User;
import com.hatm_tracker.repository.HatmRepository;
import com.hatm_tracker.repository.UserRepository;
import com.hatm_tracker.util.HatmErrors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private Mapper mapper;
    final private HatmRepository hatmRepository;


    @Override
    public RegisterUserDto getUserDto(UserDetails userDetails) {
        User user =  userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException(HatmErrors.USER_NOT_FOUND.getMessage()));

        RegisterUserDto result = RegisterUserDto.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        return result;
    }


    @Override
    public RegisterUserDto deleteUser(UserDetails userDetails) {
        User user = getUserByUsername(userDetails.getUsername());
        RegisterUserDto result = RegisterUserDto.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        userRepository.delete(user);

        return result;
    }

    @Override
    public RegisterUserDto updateUser(UserDetails userDetails, UserReqDto userReqDto) {
        PasswordEncoder passwordEncoder = passwordEncoder();

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()->new UserNotFoundException(HatmErrors.USER_NOT_FOUND.getMessage()));
        user.setName(userReqDto.getName());
        user.setUsername(userReqDto.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return RegisterUserDto.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
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

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
