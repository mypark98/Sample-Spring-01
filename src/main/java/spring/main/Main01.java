package spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.printer.MemberInfoPrinter02;
import spring.service.MemberRegisterService02;
import spring.vo.RegisterRequest;

public class Main01 {

	public static void main(String[] args) {
		ApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:appCtx03.xml");
		
		MemberRegisterService02 regSvc = ctx.getBean("memberRegSvc", MemberRegisterService02.class);
		
		MemberInfoPrinter02 info = ctx.getBean("infoPrinter", MemberInfoPrinter02.class);
		
		RegisterRequest regReq = new RegisterRequest();
		regReq.setEmail("mypark9816@gmail.com");
		regReq.setName("박민영");
		regReq.setPassword("1234");
		regReq.setConfirmPassword("1234");
		
		regSvc.regist(regReq);
		
		info.printMemberInfo("mypark9816@gmail.com");
	}

}
