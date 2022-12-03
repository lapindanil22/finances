package com.example.finances.repositories;

import com.example.finances.models.CashOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashOrderRepository extends JpaRepository<CashOrder, Integer> {
    @Query("select co from CashOrder co where co.accountId.id = ?1")
    List<CashOrder> getAllByAccountId(Integer accountId);
}
