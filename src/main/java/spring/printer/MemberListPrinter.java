package spring.printer;

import java.util.Collection;

import spring.dao.MemberDao;
import spring.vo.Member;

public class MemberListPrinter {

	private MemberDao memberDao;
	private MemberPrinter printer;

	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}

	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		for (Member m : members) {
			printer.print(m);
		}
	}
}
