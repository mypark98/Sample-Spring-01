package spring.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.assembler.Assembler;
import spring.exception.AlreadyExistingMemberException;
import spring.exception.IdPasswordNotMatchingException;
import spring.exception.MemberNotFoundException;
import spring.printer.MemberListPrinter;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;
import spring.vo.RegisterRequest;

public class MainForSpring01 {

	static Scanner scan = new Scanner(System.in);
	private static ApplicationContext ctx = null;

	public static void main(String[] args) throws Exception {
		
        ctx = new GenericXmlApplicationContext("classpath:appCtx02.xml");
        
        ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		
		while (true) {
			System.out.println("명령어를 입력하세요.");
			String command = scan.nextLine();

			if (command.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else if (command.equals("new")) { 
				processNewCommand();
				continue;
			} else if (command.equals("change")) { 
				processChangeCommand();
				continue;
			} else if(command.equals("list")) {
				processListCommand();
				continue;
			} else { 
				printHelp();
			}
		}
	}

	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}

	private static void processNewCommand() {
		System.out.println("회원가입을 진행합니다.");
		System.out.println("이메일");
		String email = scan.nextLine();
		System.out.println("이름");
		String name = scan.nextLine();
		System.out.println("비밀번호");
		String password = scan.nextLine();
		System.out.println("비밀번호 확인");
		String confirmPassword = scan.nextLine();

		RegisterRequest reg = new RegisterRequest();
		reg.setEmail(email);
		reg.setName(name);
		reg.setPassword(password);
		reg.setConfirmPassword(confirmPassword);

		if (!reg.isPasswordEqualToConfirmPassword()) {
			System.out.println("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
			return;
		}

		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		try {
			regSvc.regist(reg);
			System.out.println("가입이 완료되었습니다.");
		} catch (AlreadyExistingMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.");
		}
	}

	private static void processChangeCommand() {
		System.out.println("비밀번호를 변경합니다.");
		ChangePasswordService changeSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);

		System.out.println("변경할 계정(이메일)을 입력하세요.");
		String email = scan.nextLine();
		System.out.println("비밀번호를 입력하세요");
		String oldPassword = scan.nextLine();
		System.out.println("새로운 비밀번호를 입력하세요");
		String newPassword = scan.nextLine();

		try {
			changeSvc.changePassword(email, oldPassword, newPassword);
			System.out.println("비밀번호를 변경했습니다.");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일 입니다.");
		} catch (IdPasswordNotMatchingException e) {
			System.out.println("이메일과 비밀번호가 일치하지 않습니다.");
		}

	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령어입니다. \n아래 명령어 사용법을 확인하세요.");
		System.out.println("1.new : 회원 가입 기능");
		System.out.println("2.change : 비밀번호 변경 기능");
		System.out.println("3.list : 전체 회원 정보 조회");
		System.out.println("");
	}

}
