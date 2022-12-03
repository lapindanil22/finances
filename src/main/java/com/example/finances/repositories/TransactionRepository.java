package com.example.finances.repositories;

import com.example.finances.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("select t from Transaction t where t.accountId.id = ?1")
    List<Transaction> getAllByAccountId(Integer accountId);
}