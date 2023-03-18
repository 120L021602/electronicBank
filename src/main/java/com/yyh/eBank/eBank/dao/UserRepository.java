package com.yyh.eBank.eBank.dao;

import com.yyh.eBank.eBank.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //根据用户名来查询
    List<User> findAllByUsername(String str);


}
