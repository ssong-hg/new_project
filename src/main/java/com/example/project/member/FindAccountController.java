package com.example.project.member;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.emailAuth.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FindAccountController {
	
	private final MemberService memberService;
	private final EmailService emailService;
	
	String code = null;
	

	@GetMapping("/findId")
	public String getFindId() {
		
		return "member/findIdForm";
	}
	
	//아이디 찾기 - 인증 메일 전송
	@GetMapping("/getKeyToFindId")
	@ResponseBody
	public void getKey(@RequestParam("name") String name, @RequestParam("email") String email) throws Exception {
		Member member = memberService.getMemberByNameAndEmail(name, email);

		if(member != null) {
			code = emailService.sendEmail(email);
			log.info("####인증코드: " + code);
		}


	}
	
	//ajax로 아이디 찾기
	@GetMapping("/findIdCheck")
	@ResponseBody
	public String findIdCheck(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("keyInput") String keyInput) throws Exception{

		Member member = memberService.getMemberByNameAndEmail(name, email);
		String result;

		if(!this.code.equals(keyInput)) {
			result = "unequal";
		}else if(member == null) {
			result =  "null";
			
		}else {
			log.info("####name, email로 찾은 member: " + member.getId());
			result =  member.getId();
		}
		
		return result;
	}
	
	
	
	
	//-------------------------비밀번호 찾기-------------------------//
	

	@GetMapping("/findPw")
	public String getFindPw() {
		
		return "member/findPwForm";
		
	}
	
	//비밀번호 찾기 - 인증 메일 전송
	@GetMapping("/getKeyToFindPw")
	@ResponseBody
	public void getKeyPw(@RequestParam("id") String id, @RequestParam("email") String email) throws Exception {
		Member member = memberService.getMemberByIdAndEmail(id, email);

		if(member != null) {
			code = emailService.sendEmail(email);
			log.info("####인증코드: " + code);
		}


	}
	
	@GetMapping("/findPwCheck")
	@ResponseBody
	public String getFindPwCheck(@RequestParam("id") String id, @RequestParam("email") String email, 
			@RequestParam("keyInput") String keyInput, HttpSession session) throws Exception{

		Member member = memberService.getMemberByIdAndEmail(id, email);
		String result;

		if(!this.code.equals(keyInput)) {
			result = "unequal";
			
		}else if(member == null) {
			result =  "null";
			
		}else {
			log.info("####id, email로 찾은 member: " + member.getId());
			result =  member.getId();
			session.setAttribute("member", member);
		}
		
		return result;
		
	}
	
	@GetMapping("/changePwFormWithout")
	public String getChangePw(HttpSession session) {
		Member sessionMember = (Member)session.getAttribute("member");
		log.info("####changePw의 session의 member: " + sessionMember.getId());
		
		return "member/changePwWithoutOldPw";
	}
	
	//비밀번호 찾기에서 변경
	@GetMapping("/changeWithoutOldPwCheck")
	@ResponseBody
	public String getchangePw(@RequestParam String inputNewPw, 
			@RequestParam String inputNewPw2, HttpSession session) {
		
		String result = memberService.changeMemberPw(session, inputNewPw, inputNewPw2);
		
		
		return result;
	}
	
	@GetMapping("/changePwSuccess")
	public String getchangePw(HttpSession session) {
		session.invalidate();

		return "redirect:/login";
	}
}
