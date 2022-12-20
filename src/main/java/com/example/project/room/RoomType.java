package com.example.project.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RoomType {
	@Id
	@Column(unique=true) 
	private String roomType;
	
	private String bedSize;
	
	private int persons;
	
	private String roomSize;
	
	private String roomView;
	
	private String roomExplain;
	
	private int price;
	
}
