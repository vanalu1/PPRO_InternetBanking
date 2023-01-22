package com.example.bootintro.repositories;

import com.example.bootintro.model.AccountActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountActivityRepository extends JpaRepository<AccountActivity,Integer> {
}
