package com.yyh.eBank.eBank.controller;

import com.yyh.eBank.eBank.com.response.BalanceResponse;
import com.yyh.eBank.eBank.dao.EBankRepository;
import com.yyh.eBank.eBank.domain.EBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class BalanceController {

    @Autowired
    EBankRepository eBankRepository;

    @PostMapping(path = "/api/balance_query")
    public @ResponseBody BalanceResponse balanceQuery(
            @RequestParam String accessId
           ){
        BalanceResponse balanceResponse = new BalanceResponse();
        //访问数据库ebank
        Optional<EBank> eBank = eBankRepository.findByAccessId(accessId);
        if(eBank.isEmpty()){
            balanceResponse.setSuccess(false);
            balanceResponse.setMsg("Wrong accessId.");
        }
        else{
            balanceResponse.setSuccess(true);
            balanceResponse.setCents(eBank.get().getCents());
            eBankRepository.save(eBank.get());
        }
        return balanceResponse;


    }
}
