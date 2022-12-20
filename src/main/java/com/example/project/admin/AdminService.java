package com.example.project.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.project.DataNotFoundException;
import com.example.project.member.LoginInput;
import com.example.project.member.Member;
import com.example.project.member.MemberRepository;
import com.example.project.q_and_a.Question;
import com.example.project.q_and_a.QuestionRepository;
import com.example.project.q_and_a.QuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {
	private final AdminRepository  adminRepository;
	private final MemberRepository memberRepository;
	private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	public Admin loginCheck(LoginInput loginInput) {
		Optional<Admin> oAdmin = this.adminRepository.findByIdAndPw(loginInput.getId(), loginInput.getPw());
		if(oAdmin.isPresent()) {
			Admin admin = oAdmin.get();
			
			return admin;
		}
		
		return null;
		
	}
	
	public void regAdmin(Admin admin) {
		

		this.adminRepository.save(admin);

	}
	
	public Admin getAdmin(String id) {

		Optional<Admin> oAdmin = this.adminRepository.findById(id);
		if(oAdmin.isPresent()) {
			Admin admin = oAdmin.get();
			
			return admin;
		}
		
		
		return null;
		
		
	}
	
	
	public List<Member> getMemberList(){
		
		
		return this.memberRepository.findAll();
	}
	
	public List<Admin> getAdminList(){
		
		
		return this.adminRepository.findAll();
	}
	
	public List<Question> getQuestionList(){
		
		return questionService.getList();
	}
	
	public Question getQuestion(Integer questionNo) {
		Optional<Question> question = this.questionRepository.findByQuestionNo(questionNo);
		if(question.isPresent()) {
			return question.get();
		}else {
			throw new DataNotFoundException("question not found");
		}
	}
	
	public void createAnswer(Integer questionNo, String answer) {
		Question question = this.getQuestion(questionNo);
		question.setAnswer(answer);
		this.questionRepository.save(question);
	}
	
	
	
}
