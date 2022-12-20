package com.example.project.reservation;



import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class ReservationCreate {
	
		
		private String memId;
		
		private String roomNo;
		
		private String roomType;
		@CreatedDate
		private Date checkInDate;
		@CreatedDate
		private Date checkOutDate;
		
		private int persons;
		
		private int price;
		
		private String pay;
}
