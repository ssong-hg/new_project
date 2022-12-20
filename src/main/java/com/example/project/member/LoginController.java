package com.example.project.member;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class LoginController {
	
	private MemberService memberService;
	
	@Autowired
	public LoginController(MemberService memberService) {
		this.memberService = memberService;
	}

	

	@GetMapping("/login")
	public String getLogin(LoginInput loginInput) {
		
		log.debug("####입력아이디: " + loginInput.getId());
		log.debug("####입력비밀번호: " + loginInput.getPw());
		
		return "member/loginForm";
	}
	

	@PostMapping("/login")
	public String postLogin(LoginInput loginInput, HttpSession session) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Member member = memberService.getMember(loginInput.getId());
		
		if(member == null) {
			log.info("####로그인 실패####");
			return "member/Error";
		}
		
		if(encoder.matches(loginInput.getPw(), member.getPw()) == true) {
			//Member newMember = memberService.loginCheck(loginInput);
			session.setAttribute("member", member);
			
			log.info("####로그인 성공####");
			
			return "member/loginSuccess";
			
		}else {
			log.info("####로그인 실패####");
			return "member/Error";
		}

	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		
		System.out.println("member :"+member);
		
		session.invalidate();
		
		return"redirect:/main";
	}
	
	@GetMapping("/logincheck")
	public String loginCheck() {
		String loginCheck = null;

		return loginCheck;
	}
}
