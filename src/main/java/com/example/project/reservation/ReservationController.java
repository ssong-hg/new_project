package com.example.project.reservation;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.member.Member;
import com.example.project.room.Room;
import com.example.project.room.RoomService;
import com.example.project.room.RoomType;
import com.example.project.room.RoomTypeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reservation")
@Slf4j
public class ReservationController {
	

	private final ReservationService reservationService;
	private final RoomService roomService;
	private final RoomTypeService roomTypeService;
	
	@GetMapping("/info/{roomType}")
	
	public String reservation(HttpSession session, Model model, 
			@PathVariable("roomType")String roomType, ReservationCreate rCreate) {
		Member member = (Member)session.getAttribute("member");
		List<Room> rList = roomService.getRoomTypeList(roomType);
		RoomType rType = this.roomTypeService.getRoomType(roomType);

		model.addAttribute("member", member);
		model.addAttribute("rList", rList);
		model.addAttribute("rType", rType);
		log.info("roomType :" +roomType);
		log.info("rList :" +rList.get(1).getRoomNo());
		log.info("rType :" +rType.getRoomType());
		
		
		return "reservation/reservation";
	}


	@PostMapping("/info")
	
	public String reservation(HttpSession session, Model model, 
			 @Valid ReservationCreate rCreate, Reservation reservation) {
		Member member = (Member)session.getAttribute("member");
		
		reservation.setMemId(member.getId());
		reservation.setRoomNo(rCreate.getRoomNo());
		
		reservation.setPersons(rCreate.getPersons());
		
		reservation.setCheckInDate(rCreate.getCheckInDate());
		reservation.setCheckOutDate(rCreate.getCheckOutDate());
		reservation.setPrice(totalPrice(reservation.getCheckInDate(),reservation.getCheckOutDate(),reservation.getRoomNo())
		 );
		reservation.setRoomType(this.roomService.getRoomType(rCreate.getRoomNo()));
		
		reservationService.create(reservation.getMemId(), reservation.getRoomNo(), 
				reservation.getRoomType(),reservation.getPersons(), reservation.getPrice(),
				reservation.getCheckInDate(),reservation.getCheckOutDate());
		
		
		return "redirect:/roominfo";
	}
	
	@GetMapping("/detail/{reservationNo}")
	public String confirmation(Model model, @PathVariable("reservationNo")Integer reservationNo, HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		Reservation reservation = this.reservationService.getReservation(reservationNo);
		
		model.addAttribute("member", member);
		model.addAttribute("reservation", reservation);
	
		
		return "reservation/confirmation";
	}
	
	@GetMapping("/reservationList")
	
	public String reservationList(HttpSession session, Model model) {
		Member member = (Member)session.getAttribute("member");
	
		if(member != null) {
			List<Reservation> rList = reservationService.reservationList(member.getId());
			model.addAttribute("rList", rList);
			
		}
		model.addAttribute("member", member);
		
		
		
		return "reservation/reservationList";
	}

	@GetMapping("/totalPrice")
	@ResponseBody
	public int totalPrice(@RequestParam Date inDate,@RequestParam Date outDate, @RequestParam String roomNo) {
		SimpleDateFormat transIn = new SimpleDateFormat("yyyyMMdd");
		String in = transIn.format(inDate);
		SimpleDateFormat transOut = new SimpleDateFormat("yyyyMMdd");
		String out = transOut.format(outDate);
		int iDate = Integer.parseInt(in);
		int oDate = Integer.parseInt(out);
		int minusDate= oDate-iDate;
		Room room = this.roomService.getRoom(roomNo);
		RoomType roomType = this.roomTypeService.getRoomType(room.getRoomType());
		int totalPrice = roomType.getPrice()*minusDate;
		
		log.info("#####################totalPrice :  "+totalPrice);
		
		return totalPrice ;
	}

	


	
	
}
