package com.yyh.eBank.eBank.controller;

import com.yyh.eBank.eBank.com.response.LogInResponse;
import com.yyh.eBank.eBank.dao.EBankRepository;
import com.yyh.eBank.eBank.domain.EBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LogInController {

    @Autowired
    EBankRepository eBankRepository;

    @PostMapping("/api/login")
    public @ResponseBody LogInResponse logIn(@RequestParam String account,
                                             @RequestParam String password) {
        LogInResponse response = new LogInResponse();
        Optional<EBank> eBank = eBankRepository.findByAccount(account);
        if (eBank.isEmpty()) {
            response.setSuccess(false);
            response.setMsg("Wrong account.");
        }
        else {
            if (eBank.get().getPassword().equals(password)) {
                response.setSuccess(true);
                String s = "";
                for(int i = 0; i < 8; i ++) {
                    s += (int) (Math.random() * 10);
                }
                while (eBankRepository.existsByAccessId(s)) {
                    for(int i = 0; i < 8; i ++) {
                        int n = (int) (Math.random() * 10);
                        s += n;
                    }
                }
//                System.out.println(s);
                eBank.get().setAccessId(s);
                eBankRepository.save(eBank.get());
                response.setAccessId(s);
            }

        }
        return response;
    }
}
