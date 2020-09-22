package com.microclouddata.ecommerce.repository;

import com.microclouddata.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByName(String username);
    Optional<User> findByMobile(String mobile);
    Optional<User> findByEmail(String email);
}
