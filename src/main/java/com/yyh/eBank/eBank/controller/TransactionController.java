package com.yyh.eBank.eBank.controller;

import com.yyh.eBank.eBank.com.response.TransactionResponse;
import com.yyh.eBank.eBank.dao.EBankRepository;
import com.yyh.eBank.eBank.dao.TransactionRepository;
import com.yyh.eBank.eBank.domain.EBank;
import com.yyh.eBank.eBank.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Controller
public class TransactionController {

    @Autowired
    EBankRepository eBankRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/api/transactions")
    public @ResponseBody TransactionResponse transactions(@RequestParam String accessId){
        TransactionResponse transactionResponse = new TransactionResponse();

        Optional<EBank> eBank = eBankRepository.findByAccessId(accessId);
        if(eBank.isEmpty()){
            transactionResponse.setMsg("Wrong accessId");
            transactionResponse.setSuccess(false);
        }
        else{
            List<Transaction> transactionList =
                    transactionRepository.findBySelfAccount(eBank.get().getAccount());
            transactionResponse.setSuccess(true);
            transactionResponse.setTransactions(transactionList);
        }
        return transactionResponse;
    }
}
