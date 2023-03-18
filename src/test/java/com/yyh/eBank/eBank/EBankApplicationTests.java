package com.yyh.eBank.eBank;

import com.yyh.eBank.eBank.com.response.BalanceResponse;
import com.yyh.eBank.eBank.controller.BalanceController;
import com.yyh.eBank.eBank.dao.EBankRepository;
import com.yyh.eBank.eBank.dao.UserRepository;
import com.yyh.eBank.eBank.domain.EBank;
import com.yyh.eBank.eBank.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import java.util.Optional;

@SpringBootTest
class EBankApplicationTests {

	@Autowired
	EBankRepository eBankRepository;

	//@Autowired
	BalanceController balanceController;

	//@Autowired
	BalanceResponse balanceResponse;

	@Test
	void contextLoads() {
	}
//
//	@Test
//	public void testBalanceQuery() {
//		EBank eBank = new EBank();
//		eBank.setId(1);
//		eBank.setAccessId("123");
//		eBank.setCents(999);
//		eBank.setHashedPassword("12345");
//		eBank.setUserName("czj");
//		eBank.setLoginId("12344");
//		eBank.setAccessIdExp("60");
//		eBankRepository.save(eBank);
//		balanceController = new BalanceController();
//		balanceResponse = new BalanceResponse();
//		balanceResponse = balanceController.balanceQuery("123");
//
//		System.out.println(balanceResponse.getCents());
//		System.out.println(balanceResponse.getMsg());
//		System.out.println(balanceResponse.isSuccess());
//		}
//	}
//	@Test
//	public void testQuery(){
//		userRepository.findById(1).ifPresent(System.out::println);
//	}
//
//	@Test
//	public void testAdd(){
//		User user = new User();
//		user.setId(2);
//		user.setUsername("czj");
//		user.setPassword("sb250");
//		userRepository.save(user);
//	}
//
////	@Test
////	public void testDel(){
////		userRepository.deleteById(3);
////	}
//
//	@Test
//	public void testFind(){
//		userRepository.findAllByUsername("czj").forEach(System.out::println);
//	}
//
}
