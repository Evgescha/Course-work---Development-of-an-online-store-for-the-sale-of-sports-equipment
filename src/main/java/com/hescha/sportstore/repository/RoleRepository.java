package com.hescha.sportstore.repository;

import com.hescha.sportstore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameIgnoreCase(String login);
}
