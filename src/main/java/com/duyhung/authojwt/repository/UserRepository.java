package com.duyhung.authojwt.repository;

import com.duyhung.authojwt.entity.User;
import com.duyhung.authojwt.service.UserDetailServiceImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
