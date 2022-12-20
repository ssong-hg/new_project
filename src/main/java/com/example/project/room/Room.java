package com.example.project.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Room {
	@Id
	@NotNull

	private String  roomNo;
	@Column(length = 200)
	private String roomType;
	
	
	
}
