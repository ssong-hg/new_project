package com.example.project.reservation;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.project.DataNotFoundException;
import com.example.project.q_and_a.Question;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;
	
	public String reservationDateCheck(ReservationCheck rCheck) {
		Optional<Reservation> roomCheck = 
				this.reservationRepository.findByRoomNoAndCheckInDate(rCheck.getRoomNo(), rCheck.getCheckInDate());
		if(roomCheck.isPresent()) {
			return "impossible";
		}
		
		return "possible";
	}
	
	public List<Reservation> reservationList(String memId){
		
		
		return this.reservationRepository.findByMemId(memId);
	}
	public List<Reservation> reservationRoomListList(String roomNo){
		
		
		return this.reservationRepository.findByRoomNo(roomNo);
	}
	
	
	public Reservation getReservation(Integer reservationNo) {
		Optional<Reservation> reservation 
		= this.reservationRepository.findByReservationNo(reservationNo);
		
			return reservation.get();
		
	}
	
	
	
	public void create(String memId, String roomNo, String roomType, int persons, int price,
						Date inDate, Date outDate) {
		Reservation r = new Reservation();
		r.setMemId(memId);
		r.setRoomNo(roomNo);
		r.setRoomType(roomType);
		r.setPersons(persons);
		r.setPrice(price);
		r.setCheckInDate(inDate);
		r.setCheckOutDate(outDate);
		
		this.reservationRepository.save(r);
		
	}
	
	
}
