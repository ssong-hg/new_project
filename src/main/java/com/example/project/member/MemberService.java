package com.example.project.member;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.Regex;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberRepository memberRepository;
		
		
		public Member loginCheck(LoginInput loginInput) {
	
			Optional<Member> oMember = this.memberRepository.findByIdAndPw(loginInput.getId(), loginInput.getPw());
			if(oMember.isPresent()) {
				Member member = oMember.get();
				
				return member;
			}
			
			return null;
		}
		
		public void regMember(Member member) {
			

			this.memberRepository.save(member);

		}
		
		public Member getMember(String id) {

			Optional<Member> oMember = this.memberRepository.findById(id);
			if(oMember.isPresent()) {
				Member member = oMember.get();
				
				return member;
			}
			
			
			return null;
		}

		
		public Member getMemberByNameAndEmail(String name, String email) {

			Optional<Member> oMember = this.memberRepository.findByNameAndEmail(name, email);
			if(oMember.isPresent()) {
				Member member = oMember.get();
				
				return member;
			}
			
			
			return null;
		}

		public Member getMemberByIdAndEmail(String id, String email) {

			Optional<Member> oMember = this.memberRepository.findByIdAndEmail(id, email);
			if(oMember.isPresent()) {
				Member member = oMember.get();
				
				return member;
			}
			
			
			return null;
		}
		
		public String changeMemberPw(HttpSession session, String oldPw, String newPw, String newPw2) {
			
			Regex regex = new Regex();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			Member member = (Member)session.getAttribute("member");
			
			if(encoder.matches(oldPw, member.getPw()) == true) { //현재 비밀번호 확인
				if(regex.regexPw(newPw) == null) { //비밀번호 양식에 맞지 않는 경우
					return "regex";
					
				}else if(encoder.matches(newPw, member.getPw())) {//새 비밀번호가 현재 비밀번호와 같은 경우
					return "same";
					
				}else if(!newPw.equals(newPw2)) {//새 비밀번호 확인 오류
					return "unequal";
					
				}else {
					
					String secureNewPw = encoder.encode(newPw);
					
					
					Optional<Member> oMember = memberRepository.findById(member.getId());	
					if(oMember.isPresent()) {
						Member newMember = new Member();
						newMember = oMember.get();
						newMember.setPw(secureNewPw);
						
						this.memberRepository.save(newMember);
						session.setAttribute("member", newMember);
					
						return "true";
					}
				}
				
			}
			return "oldPwError";
				
		}
		
		
		//비밀번호 찾기에서 변경
		public String changeMemberPw(HttpSession session, String newPw, String newPw2) {
			
			Regex regex = new Regex();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			Member member = (Member)session.getAttribute("member");
			

			if(regex.regexPw(newPw) == null) { //비밀번호 양식에 맞지 않는 경우
				return "regex";
					
			}else if(encoder.matches(newPw, member.getPw())) {//새 비밀번호가 현재 비밀번호와 같은 경우
				return "same";
					
			}else if(!newPw.equals(newPw2)) {//새 비밀번호 확인 오류
				return "unequal";
					
			}else {
					
				String secureNewPw = encoder.encode(newPw);
					
					
				Optional<Member> oMember = memberRepository.findById(member.getId());	
				if(oMember.isPresent()) {
					Member newMember = new Member();
					newMember = oMember.get();
					newMember.setPw(secureNewPw);
						
					this.memberRepository.save(newMember);
					session.setAttribute("member", newMember);
					
					return "true";
				}
			}
			
			return null;
					
		}
		
		
		//이메일 변경
		public String  changeMemberEmail(HttpSession session, String newEmail) {

			Regex regex = new Regex();
			
			if(regex.regexEmail(newEmail) == null) {
				return "regex";
			}

			Member sessionMember = (Member)session.getAttribute("member");
			
			Member member = new Member();
			Optional<Member> oMember = memberRepository.findById(sessionMember.getId());
			
			if(oMember.isPresent()) {
				member = oMember.get();
			}
			
			if(member.getEmail().equals(newEmail)) {
				return "same";
			}
			

			member.setPw(newEmail);
						
			this.memberRepository.save(member);
			session.setAttribute("member", member);
					
			return "true";
				
		}
				
				
		public String changeMemberTell(HttpSession session, String newTell) {

			Regex regex = new Regex();
			
			if(regex.regexNum(newTell) == null) {
				return "regex";
			}

			Member sessionMember = (Member)session.getAttribute("member");
			
			Member member = new Member();
			Optional<Member> oMember = memberRepository.findById(sessionMember.getId());
			
			if(oMember.isPresent()) {
				member = oMember.get();
			}
			
			if(member.getTell().equals(newTell)) {
				return "same";
			}
			

			member.setPw(newTell);
						
			this.memberRepository.save(member);
			session.setAttribute("member", member);
					
			return "true";
				
		}
		
}


