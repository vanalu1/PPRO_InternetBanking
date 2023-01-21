package com.example.bootintro.repositories;

import com.example.bootintro.model.Role;
import com.example.bootintro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
