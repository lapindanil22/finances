package com.example.finances.repositories;

import com.example.finances.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("select a from Account a where a.clientId.id = ?1")
    List<Account> getAllByClientId(Integer clientId);
}
