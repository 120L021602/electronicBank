package com.yyh.eBank.eBank.controller;

import com.yyh.eBank.eBank.com.response.Response;
import com.yyh.eBank.eBank.dao.EBankRepository;
import com.yyh.eBank.eBank.domain.EBank;
import com.yyh.eBank.eBank.utils.HashHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @Autowired
    EBankRepository eBankRepository;

    @PostMapping("/api/signup")
    public Response signUp(@RequestParam String account,
                           @RequestParam String password, @RequestParam String paymentPassword
    ){
        Response response = new Response();
        if(eBankRepository.existsByAccount(account)){
            response.setMsg("Account is already exist.");
            response.setSuccess(false);
        }
        else {
            EBank eBank = new EBank();

//            eBank.setUserName(userName);
            eBank.setCents(100);
            eBank.setPassword(password);
            eBank.setPayPassword(paymentPassword);
            eBank.setAccount(account);

            eBankRepository.save(eBank);

            response.setSuccess(true);


        }
        return response;


    }
}
