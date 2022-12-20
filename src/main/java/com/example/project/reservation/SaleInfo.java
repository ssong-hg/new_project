package com.example.project.reservation;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SaleInfo {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALE_SEQ")
    @SequenceGenerator(sequenceName = "saleSeq", allocationSize = 1, name = "SALE_SEQ")
    private Integer saleNo;
	
	private String reservation;
	
	private String memId;
	
	private String pay;
	
	private LocalDateTime saleDate;
}
