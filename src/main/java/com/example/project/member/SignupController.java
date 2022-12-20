package com.example.project.member;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.Alert;
import com.example.project.Regex;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SignupController {
	
	private MemberService memberService;
	
	@Autowired
	public SignupController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/signup")
	public String getSignup(RegMemberForm regMemberForm) {
		
		return "member/signupForm";
	}
	
	
	
	@PostMapping("/signup")
	public String postSignup(@Valid RegMemberForm regMemberForm, Member member, HttpServletResponse response) {
		

		Regex regex = new Regex();
		String inputId = regex.regexEngNum(regMemberForm.getId());
		
		if(inputId == null) {
			return "member/Error";
		}

		
		member.setId(inputId);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String securePw = encoder.encode(regMemberForm.getPw());
		
		member.setPw(securePw);
		member.setName(regMemberForm.getName());
		member.setBirth(regMemberForm.getBirth());
		

		
		member.setTell(regMemberForm.getTell());
		member.setEmail(regMemberForm.getEmail());
		
		memberService.regMember(member);

		log.info("####signupServ.regMember(): " + regMemberForm.getId());
		
		return "redirect:/main";
	}
	
	
	
	@GetMapping("/idduplcheck")
	@ResponseBody
	public String idDuplCheck(@RequestParam String inputId) {

		String idDuplCheck;

		//inputId와 동일한 아이디가 있으면 그 member를 리턴

		Member member = memberService.getMember(inputId);
		
		if(inputId.contains("admin") == true || inputId.contains("ADMIN") == true) {
			idDuplCheck = "admin";
		}else if(member == null) {
			idDuplCheck = "possible";
		}else {
			idDuplCheck = "impossible";
		}

		return idDuplCheck;
	}

}
