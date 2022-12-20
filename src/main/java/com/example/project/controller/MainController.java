package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.admin.Admin;
import com.example.project.admin.AdminRepository;
import com.example.project.room.RoomService;
import com.example.project.room.RoomTypeService;

import lombok.extern.slf4j.Slf4j;




@Controller
@Slf4j
public class MainController {
	
	@Autowired
	private RoomService roomService;
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private AdminRepository adminRepository;
	
	
	@GetMapping("/main")
	public String main() {
		return "main/main";
	}
	
	@GetMapping("/data")
	public String dataSave() throws Exception{
		return "main/data";
	}
	
	@GetMapping("/datasave")
	public String data() throws Exception{
		 
		Admin admin = new Admin();
		admin.setId("gunexia@naver.com");
		admin.setPw("1q2w3e4r!");
		admin.setName("송혁근");
		admin.setTell("010-4506-2148");
		adminRepository.save(admin);
		log.info("admin 셋팅");
		
		roomService.setRoom();
		log.info("room 셋팅");
		
		roomTypeService.setRoomType();
		log.info("roomType 셋팅");
		
		
		return "redirect:/main";
	}

	
	@GetMapping("/hotelinfo")
	public String hotelinfo() {
		return "main/hotelinfo";
	}
	@GetMapping("/roominfo")
	public String roominfo() {
		return "main/roominfo";
	}
	@GetMapping("/map")
	public String map() {
		return "main/map";
	}
	
}
