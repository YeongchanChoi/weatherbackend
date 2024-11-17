package com.audora.weatherapp.service;

import com.audora.weatherapp.entity.User;
import com.audora.weatherapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw  new RuntimeException("이미 사용 중인 이메일입니다.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일입니다."));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}
