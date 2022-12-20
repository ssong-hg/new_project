package com.example.project.q_and_a;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.member.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/question")
@RequiredArgsConstructor
@Controller// http 통신하는 창구
public class QuestionController {

	private final QuestionService questionService;
	private final QuestionRepository questionRepository;
				
	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		 
		
		Page<Question> paging = this.questionService.getList(page);
	        model.addAttribute("paging", paging); /*페이징*/
	        
	        
		String stateText;
		List<Question> questionList = 
				this.questionService.getList();
		if (questionList != null) {
			stateText =  "검색한 자료있음";
		} else {
			stateText = "검색한 자료가 없습니다.!";
			
		}
		
		model.addAttribute("questionList",questionList);
		model.addAttribute("stateText", stateText);
		
		
		return "q_and_a/question_list";
	}
	
	@GetMapping("/create")
	public String create( Model model, HttpSession session, QuestionCreate qCreate) {
		Member member = new Member();
		member = (Member)session.getAttribute("member");
		model.addAttribute("member", member);
		//추가저장작업이 완료되었으면 리스트로 감
		return "q_and_a/question_create";
	}
	
	@PostMapping("/create")
	public String create(@Valid QuestionCreate qCreate, Question question, HttpSession session) {
		Member member =(Member) session.getAttribute("member");
		
		question.setId(member.getId());
		question.setTitle(qCreate.getTitle());
		question.setContent(qCreate.getContent());
		
		questionService.create(question.getId(), question.getTitle(), question.getContent());
		
		
		return "redirect:/question/list";
	}
	
	@RequestMapping(value="/detail/{questionNo}")

	public String detail(Model model, @PathVariable("questionNo")Integer questionNo, HttpSession session) {
		Question question = this.questionService.getQuestion(questionNo);
	
			model.addAttribute("session", session);
			
			model.addAttribute("question",question);
			
			return "q_and_a/question_detail";
		
	}
	
	
	@RequestMapping("/findAllSubject")
	@ResponseBody
	public String findAllSubject()   {
		List<Question> qList = this.questionRepository.findAll();
		String result = "";
		for(int i= 0 ; i<qList.size();i++) {
		Question q = qList.get(i);
		result += ""+q.getId()+"}"+q.getTitle()+"<br>";
		}
		return result;
		
	}
	

	
	
	
	
}