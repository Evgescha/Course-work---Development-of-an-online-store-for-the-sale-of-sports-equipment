package com.hescha.sportstore.repository;

import com.hescha.sportstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsernameIgnoreCase(String username);

    public User findByUsername(String username);
}
