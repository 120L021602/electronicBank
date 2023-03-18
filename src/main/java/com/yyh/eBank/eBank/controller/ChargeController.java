package com.yyh.eBank.eBank.controller;

import com.yyh.eBank.eBank.com.response.ChargeResponse;
import com.yyh.eBank.eBank.com.response.Response;
import com.yyh.eBank.eBank.dao.EBankRepository;
import com.yyh.eBank.eBank.dao.TransactionRepository;
import com.yyh.eBank.eBank.domain.EBank;
import com.yyh.eBank.eBank.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class ChargeController {


    @Autowired
    EBankRepository eBankRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/api/charge")
    public @ResponseBody Response charge(
            @RequestParam String accessId, @RequestParam Integer cents
    ){
        Response response = new Response();
        //访问数据库ebank
        Optional<EBank> eBank = eBankRepository.findByAccessId(accessId);
        if(eBank.isEmpty()){
            response.setSuccess(false);
            response.setMsg("Wrong accessId.");
        }
        else{
            response.setSuccess(true);
            eBank.get().setCents(eBank.get().getCents() + cents);
            eBankRepository.save(eBank.get());
            //在Transaction数据库中添加一条充值信息
            Transaction transaction = new Transaction();
            transaction.setDescription("账户" + eBank.get().getAccount() +
                    "充值了" + new DecimalFormat("0.00").
                    format(new BigDecimal(cents).divide(new BigDecimal(100))) + "元");
            transaction.setTime(new Date().getTime());
            transaction.setSelfAccount(eBank.get().getAccount());
            transaction.setBalanceDiffCents(cents);
            transaction.setOppositeAccount(eBank.get().getAccount());
            transactionRepository.save(transaction);
        }
        return response;
    }
}
