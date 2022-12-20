package com.example.project.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberDetailController {

	private MemberService memberService;

	@Autowired
	public MemberDetailController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	//마이페이지
	@GetMapping("/memberdetail")
	public String memberDetail(Model model, HttpSession session) {
		
		Member member = new Member();
		member = (Member)session.getAttribute("member");
		
		model.addAttribute("member", member);
	

		return "member/memberDetail";
	}
	
	
	//--------------------------------------------------
	
	//비밀번호 변경
	@GetMapping("/changePwWithOldPw")
	@ResponseBody
	public String getchangePw(@RequestParam String inputOldPw, @RequestParam String inputNewPw, 
			@RequestParam String inputNewPw2, HttpSession session) {
		
		String result = memberService.changeMemberPw(session, inputOldPw, inputNewPw, inputNewPw2);

		return result;
	}
	
	//이메일 변경
	@GetMapping("/changeEmail")
	@ResponseBody
	public String getchangeEmail(@RequestParam String inputNewEmail, HttpSession session) {
		String result = memberService.changeMemberEmail(session, inputNewEmail);
		
		
		return result;
	}
	
	//전화번호 변경
	@GetMapping("/changeTell")
	@ResponseBody
	public String getchangeTell(@RequestParam String inputNewTell, HttpSession session) {
		String result = memberService.changeMemberTell(session, inputNewTell);
		
		
		return result;
	}

	

	
	
	
}




