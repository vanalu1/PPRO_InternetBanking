package com.example.banking.repositories;

import com.example.banking.model.AccountActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountActivityRepository extends JpaRepository<AccountActivity,Integer> {
}
