package spring.printer;

import org.springframework.beans.factory.annotation.Autowired;

import spring.dao.MemberDao;
import spring.vo.Member;

public class MemberInfoPrinter02 {

	@Autowired
	private MemberDao memberDao;
	private MemberPrinter printer;

	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		
		printer.print(member);
		System.out.println();
	}

}
