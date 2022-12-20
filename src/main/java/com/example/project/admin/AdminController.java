package com.example.project.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

import com.example.project.member.LoginInput;
import com.example.project.member.Member;
import com.example.project.q_and_a.Question;
import com.example.project.q_and_a.QuestionService;
import com.example.project.reservation.Reservation;
import com.example.project.reservation.ReservationService;
import com.example.project.room.Room;
import com.example.project.room.RoomService;
import com.example.project.room.RoomTypeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminService;
	private final QuestionService questionService;
	private final ReservationService reservationService;
	private final RoomService roomService;
	private final RoomTypeService roomTypeService;
	
	
	
	
	@GetMapping("/master")
	public String admin(Model model, HttpSession session) {
		
		model.addAttribute("session", session);
		
		
		return "admin/master";
	}
	
	@GetMapping("/member")
	public String member(Model model, HttpSession session) {
		
        
		List<Member> memberList = 
				this.adminService.getMemberList();

		
		model.addAttribute("memberList",memberList);

		
		return "admin/member";
	}
	
	//admin 로그인
	@GetMapping("/login")
	public String getLogin(LoginInput loginInput) {
		
		log.debug("####입력아이디: " + loginInput.getId());
		log.debug("####입력비밀번호: " + loginInput.getPw());
		
		return "admin/login";
	}
	@PostMapping("/login")
	public String postLogin(LoginInput loginInput, HttpSession session) {
		Admin admin = new Admin();
		
		if(adminService.loginCheck(loginInput) == null) {
				return "admin/error";
			}
		
		admin = adminService.loginCheck(loginInput);
		log.info("####add Session: " + admin);
		session.setAttribute("admin", admin);
		
		return "redirect:/admin/master";
	}
	//admin 로그인
	
	
	//admin 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		Admin admin = (Admin)session.getAttribute("admin");
		
		System.out.println("admin :"+admin);
		
		session.invalidate();
		
		return"redirect:/main";
	}
	//admin 로그아웃
	
	@GetMapping("/crew")
	public String crew(Model model, HttpSession session) {
		List<Admin> adminList = 
				this.adminService.getAdminList();
		
		model.addAttribute("adminList", adminList);
		
		return "admin/crew";
	}
	
	
	
	
	// admin 추가
	@GetMapping("/crew_signup")
	public String getSignup(RegAdminForm regAdminForm) {
		
		return "admin/crew_signup";
	}
	
	
	@GetMapping("/admin_idcheck")
	@ResponseBody
	public String idDuplCheck(@RequestParam String inputId) {

		String idDuplCheck;

		
		//inputId와 동일한 아이디가 있으면 그 member를 리턴
		Admin admin = adminService.getAdmin(inputId);
		
		if(admin == null) {
			idDuplCheck = "possible";
		}else{
			idDuplCheck = "impossible";
		}

		return idDuplCheck;
	}
	@PostMapping("/crew_signup")
	public String postSignup(@Valid RegAdminForm regAdminForm, Admin admin, HttpServletResponse response) {
		
		
		admin.setId(regAdminForm.getId());
		admin.setPw(regAdminForm.getPw());
		admin.setName(regAdminForm.getName());
		admin.setTell(regAdminForm.getTell());
		
		
		adminService.regAdmin(admin);

		log.info("####signupServ.regAdmin(): " + regAdminForm.getId());
		
		return "redirect:master";
	}
	// admin 추가
	
	@GetMapping("/answer")
	public String answer(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		
		Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging); /*페이징*/
		
		List<Question> questionList = 
				this.adminService.getQuestionList();
		
		
		model.addAttribute("questionList",questionList);
		
		return"admin/answer";
	}
	
	@GetMapping(value="/detail/{questionNo}")

	public String detail(Model model, @PathVariable("questionNo")Integer questionNo, HttpSession session, CreateAnswer aCreate) {
		Question question = this.adminService.getQuestion(questionNo);
	
			
			
			model.addAttribute("question",question);
			
			return "admin/answer_detail";
		
	}
	
	@PostMapping("/detail/{questionNo}")
	public String detail(@PathVariable("questionNo")Integer questionNo,CreateAnswer aCreate) {
		Question question = this.adminService.getQuestion(questionNo);
		question.setAnswer(aCreate.getAnswer());
		log.info("####aCreate.getAnswer(): " + aCreate.getAnswer());
		log.info("####question.getAnswer(): " + question.getAnswer());
		
		adminService.createAnswer(question.getQuestionNo(), aCreate.getAnswer());
		return"redirect:/admin/answer";
	}
	
	@GetMapping("/reservation")
	
	public String reservationInfo(Model model, HttpSession session) {
		List<Room> roomList = this.roomService.getAllList();
		model.addAttribute("roomList", roomList);
		List<Room> superiorDouble = this.roomService.getRoomTypeList("Superior Double");
		model.addAttribute("superiorDouble", superiorDouble);
		log.info("############################asdasdasdass : "+superiorDouble);
		List<Room> superiorTwin = this.roomService.getRoomTypeList("Superior Twin");
		model.addAttribute("superiorTwin", superiorTwin);
		List<Room> deluxeDouble = this.roomService.getRoomTypeList("Deluxe Double");
		model.addAttribute("deluxeDouble", deluxeDouble);
		List<Room> deluxeTwin = this.roomService.getRoomTypeList("Deluxe Twin");
		model.addAttribute("deluxeTwin", deluxeTwin);
		List<Room> juniorSuite = this.roomService.getRoomTypeList("Junior Suite");
		model.addAttribute("juniorSuite", juniorSuite);
		List<Room> royalSuite = this.roomService.getRoomTypeList("Royal Suite");
		model.addAttribute("royalSuite", royalSuite);
		
		
		
		return "admin/reservationInfo";
	}
	
	@GetMapping("/reservationDetail/{roomNo}")
	public String reservationList( @PathVariable("roomNo")String roomNo, Model model, HttpSession session) {
		List<Reservation> rList = this.reservationService.reservationRoomListList(roomNo);
	
		model.addAttribute("rList", rList);
		
		return "admin/reservationList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
