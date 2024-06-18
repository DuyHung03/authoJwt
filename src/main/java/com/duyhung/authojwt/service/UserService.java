package com.duyhung.authojwt.service;

import com.duyhung.authojwt.entity.AuthRequest;
import com.duyhung.authojwt.entity.CustomUserDetail;
import com.duyhung.authojwt.entity.Role;
import com.duyhung.authojwt.entity.User;
import com.duyhung.authojwt.jwt.JwtService;
import com.duyhung.authojwt.repository.RoleRepository;
import com.duyhung.authojwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    public String register(AuthRequest request) throws Exception {
        User userExisted = userRepository.findByUsername(request.getUsername());
        if (userExisted != null) {
            throw new Exception("Username is existed");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role = roleRepository.findById(1L).orElseThrow();
        user.setRole(role);
        userRepository.save(user);
        return "Register successfully";
    }

    public String login(AuthRequest request) throws UsernameNotFoundException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
            return jwtService.generateJwtToken(userDetail);
        }
        return "User not found";
    }

}
