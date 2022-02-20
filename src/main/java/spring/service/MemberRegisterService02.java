package spring.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import spring.dao.MemberDao;
import spring.exception.AlreadyExistingMemberException;
import spring.vo.Member;
import spring.vo.RegisterRequest;

public class MemberRegisterService02 {

	private MemberDao memberDao;
	
	@Autowired
	public MemberRegisterService02(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new AlreadyExistingMemberException("이미 존재하는 이메일입니다. " + req.getEmail());
		}

		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), new Date());
		
		memberDao.insert(newMember);
	}
	
}
