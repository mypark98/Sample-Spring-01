package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.dao.MemberDao;
import spring.printer.MemberInfoPrinter03;
import spring.printer.MemberPrinter;
import spring.service.MemberRegisterService;

@Configuration
public class JavaConfig {
    
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public MemberPrinter printer() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberInfoPrinter03 infoPrinter() {
		MemberInfoPrinter03 infoPrinter = new MemberInfoPrinter03();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(printer());
		
		return infoPrinter;
	}
}
