package com.hatm_tracker.service.auth;

import com.hatm_tracker.exception.UserNotFoundException;
import com.hatm_tracker.model.Role;
import com.hatm_tracker.model.dto.AuthResponseDto;
import com.hatm_tracker.model.dto.LoginUserDto;
import com.hatm_tracker.model.dto.RegisterUserDto;
import com.hatm_tracker.model.entity.User;
import com.hatm_tracker.repository.UserRepository;
import com.hatm_tracker.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    final private UserRepository userRepository;
    final private JwtService jwtService;
    final private PasswordEncoder passwordEncoder;
    final private AuthenticationManager authenticationManager;


    @Override
    public AuthResponseDto register(RegisterUserDto registerUserDto) {
        String username = registerUserDto.getUsername();
        String name = registerUserDto.getName();

        if(isNameExist(name)){
            throw new IllegalStateException("Username already exists");
        }

        if(isUsernameExist(username)){
            throw new IllegalStateException("Name already exists");
        }

        User user = User.builder()
                .username(registerUserDto.getUsername())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .name(registerUserDto.getName())
                .role(Role.CLIENT)
                .build();

        userRepository.save(user);
        String token =  jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                )
        );

        return new AuthResponseDto(token);
    }

    @Override
    public AuthResponseDto login(LoginUserDto loginUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(), loginUserDto.getPassword())
        );

        User user = userRepository.findByUsername(loginUserDto.getUsername())
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                )
        );

        return new AuthResponseDto(token);
    }



    private boolean isUsernameExist(String username){

        return userRepository.findByUsername(username)
                .isPresent();
    }

    private boolean isNameExist(String Name){

        return userRepository.findByName(Name)
                .isPresent();
    }
}
