package com.yyh.eBank.eBank.dao;

import com.yyh.eBank.eBank.domain.EBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EBankRepository extends JpaRepository<EBank, Integer> {
     boolean existsByAccount(String account);
     Optional<EBank> findByAccessId(String id);
     Optional<EBank> findByAccount(String account);

     boolean existsByAccessId(String accessId);

}
