package com.yyh.eBank.eBank.controller;

import com.yyh.eBank.eBank.com.response.Response;
import com.yyh.eBank.eBank.dao.EBankRepository;
import com.yyh.eBank.eBank.domain.EBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class BindController {

    @Autowired
    EBankRepository eBankRepository;

    @PostMapping("/api/auth")
    public @ResponseBody Response bindCheck(@RequestParam String account,
                                            @RequestParam String password,
                                            @RequestParam String paymentPassword){
        Optional<EBank> eBank = eBankRepository.findByAccount(account);

        Response response = new Response();

        if(eBank.isEmpty()){
            response.setMsg("Wrong account.");
            response.setSuccess(false);
        }
        else{
            if(eBank.get().getPassword().equals(password) && eBank.get().
                    getPayPassword().equals(paymentPassword)){
                response.setSuccess(true);

            }
            else{
                response.setSuccess(false);
                response.setMsg("Wrong password or paymentpassword.");
            }
        }
        return response;

    }
}
