package com.example.project.emailAuth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.member.Member;
import com.example.project.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmailController {
	
//	private final EmailService emailService;
//	private final MemberService memberService;
//	
//	String code = null;
//	
//	
//	//인증 메일 전송
//	@GetMapping("/getKeyToFindId")
//	@ResponseBody
//	public String getKey(@RequestParam("name") String name, @RequestParam("email") String email) throws Exception {
//		Member member = memberService.getMemberByNameAndEmail(name, email);
//		
//		if(member != null) {
//			code = emailService.sendEmail(email);
//			log.info("####인증코드: " + code);
//		}
//			
//		return code;
//
//
//	}
//		
//		
//	@GetMapping("/getKeyToFindPw")
//	@ResponseBody
//	public String getKeyPw(@RequestParam("id") String id, @RequestParam("email") String email) throws Exception {
//		Member member = memberService.getMemberByIdAndEmail(id, email);
//
//		if(member != null) {
//			code = emailService.sendEmail(email);
//			log.info("####인증코드: " + code);
//		}
//		
//		return code;
//
//
//	}
}
