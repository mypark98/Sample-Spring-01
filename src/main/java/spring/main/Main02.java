package spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.config.JavaConfig;
import spring.printer.MemberInfoPrinter03;
import spring.service.MemberRegisterService;
import spring.vo.RegisterRequest;

public class Main02 {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
		
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		
		MemberInfoPrinter03 infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter03.class);
		
		RegisterRequest regReq = new RegisterRequest();
		regReq.setName("박민영");
		regReq.setEmail("mypark9816@gmail.com");
		regReq.setPassword("1234");
		regReq.setConfirmPassword("1234");
		
		regSvc.regist(regReq);
		infoPrinter.printMemberInfo("mypark9816@gmail.com");

	}

}
