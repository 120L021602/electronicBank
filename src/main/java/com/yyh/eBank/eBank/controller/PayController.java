package com.yyh.eBank.eBank.controller;

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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Controller
public class PayController {

    public boolean dsCheckDemo(Integer cents, String timeStamp, String oimd, String ds) throws Exception{
        String str = timeStamp + cents.toString();
        byte[] strByte = str.getBytes(StandardCharsets.UTF_8);
        var md = MessageDigest.getInstance("SHA-256");
        byte[] pimdBytes = md.digest(strByte);
        byte[] oimdBytes = Base64.getDecoder().decode(oimd);
        byte[] concatenated2 = new byte[pimdBytes.length + oimdBytes.length];
        System.arraycopy(pimdBytes, 0, concatenated2, 0, pimdBytes.length);
        System.arraycopy(oimdBytes, 0, concatenated2, pimdBytes.length, oimdBytes.length);
        byte[] pomdBytes = md.digest(concatenated2);
        String pomd = Base64.getEncoder().encodeToString(pomdBytes);
        return pomd.equals(ds);
    }

    @Autowired
    EBankRepository eBankRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/api/pay")
    public @ResponseBody Response charge(
            @RequestParam String accessId, @RequestParam Integer cents,
            @RequestParam String oimd, @RequestParam String ds,
            @RequestParam String payeeAccount, @RequestParam String timeStamp,
            @RequestParam String paymentPassword) throws Exception {
        System.out.println("start");

        Response response = new Response();
        //访问数据库ebank
        Optional<EBank> eBank1 = eBankRepository.findByAccessId(accessId);
        Optional<EBank> eBank2 = eBankRepository.findByAccount(payeeAccount);

        if(!eBank1.isEmpty() && !eBank2.isEmpty()){
            System.out.println("first if");
            //先做哈希，再用顾客的私钥签名，判断签名后的消息是否与ds相同
            if(dsCheckDemo(cents, timeStamp, oimd, ds)){
                System.out.println("second if");
                if(eBank1.get().getCents() >= cents){
                    response.setSuccess(true);
                    eBank1.get().setCents(eBank1.get().getCents() - cents);
                    eBank2.get().setCents(eBank2.get().getCents() + cents);
                    //在Transaction数据库中添加一条支付信息
                    Transaction transaction = new Transaction();
                    transaction.setDescription(eBank1.get().getAccount() + "向" + eBank2.get().getAccount() + "转账" + new DecimalFormat("0.00").format(new BigDecimal(cents).divide(new BigDecimal(100))) + "元");
                    transaction.setTime(new Date().getTime());
                    transaction.setSelfAccount(eBank1.get().getAccount());
                    transaction.setBalanceDiffCents(-cents);
                    transaction.setOppositeAccount(eBank2.get().getAccount());
                    transactionRepository.save(transaction);
                    Transaction transaction1 = new Transaction();
                    transaction1.setDescription(eBank1.get().getAccount() + "向" + eBank2.get().getAccount() + "转账" + new DecimalFormat("0.00").format(new BigDecimal(cents).divide(new BigDecimal(100))) + "元");
                    transaction1.setTime(new Date().getTime());
                    transaction1.setSelfAccount(eBank2.get().getAccount());
                    transaction1.setBalanceDiffCents(cents);
                    transaction1.setOppositeAccount(eBank1.get().getAccount());
                    transactionRepository.save(transaction1);
                    eBankRepository.save(eBank1.get());
                    eBankRepository.save(eBank2.get());
                }

            }
        }
        else{
            response.setSuccess(false);
            response.setMsg("Fail to pay.");
        }
        return response;
    }
}
