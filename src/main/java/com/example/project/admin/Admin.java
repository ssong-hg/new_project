package com.example.project.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Admin {
	
	@Id
	@NotNull
	@Column(length=100, unique=true) 
	private String id;
	
	@Column(length = 200) 
	private String pw;
	
	@Column(length = 200) 
	private String name;
	
	@Column(length = 200) 
	private String tell;
}

