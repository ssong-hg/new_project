package com.example.project.reservation;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	Optional<Reservation> findByReservationNo(Integer reservationNo);
	Optional<Reservation> findByRoomNoAndCheckInDate(String roomNo, Date checkInDate);
	List<Reservation> findByMemId(String memId);
	List<Reservation> findByRoomNo(String roomNo);

}
