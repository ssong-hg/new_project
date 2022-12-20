package com.example.project.q_and_a;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTION_SEQ")
    @SequenceGenerator(sequenceName = "question_seq", allocationSize = 1, name = "QUESTION_SEQ")
    private Integer questionNo;
	
	private String id;
	
	@Column(length = 100)
	private String title;
	@Column(length = 2000)
	private String content;
	@CreatedDate
	private LocalDateTime createDate;
	@Column(length = 2000)
	private String answer;
	
}
