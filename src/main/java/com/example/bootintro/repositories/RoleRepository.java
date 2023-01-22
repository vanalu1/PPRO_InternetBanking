package com.example.bootintro.repositories;

import com.example.bootintro.model.Role;
import com.example.bootintro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
