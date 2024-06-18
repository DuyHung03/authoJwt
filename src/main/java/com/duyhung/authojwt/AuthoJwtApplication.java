package com.duyhung.authojwt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthoJwtApplication implements CommandLineRunner {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(AuthoJwtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        Role role = roleRepository.findById(2L).orElseThrow();
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("123"));
//        user.setRole(role);
//        user.setId(2L);
//        userRepository.save(user);
    }
}
