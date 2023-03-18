package com.yyh.eBank.eBank.dao;

import com.yyh.eBank.eBank.domain.EBank;
import com.yyh.eBank.eBank.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findBySelfAccount(String selfAccount);

}
