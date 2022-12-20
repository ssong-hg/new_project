package com.example.project.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {

	@Id
	@NotNull
	@Column(length=100, unique=true) 
	private String id;
	
	@NotNull
	@Column(length=2000) 
	private String pw;
	
	@NotNull
	@Column(length= 100) 
	private String name;

	@NotNull
	@Column(length=200)
	private String birth;
	
	@NotNull
	@Column(length=200) 
	private String tell;
	
	@NotNull
	@Column(length=320) 
	private String email;
	
}

