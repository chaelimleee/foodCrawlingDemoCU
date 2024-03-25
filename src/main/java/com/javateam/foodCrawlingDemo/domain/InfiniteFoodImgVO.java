package com.javateam.foodCrawlingDemo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="infinite_food_img_tbl")
@SequenceGenerator(
	    name = "INFINITE_FOOD_IMG_SEQ_GENERATOR",
	    sequenceName = "INFINITE_FOOD_IMG_SEQ",
	    initialValue = 1,
	    allocationSize = 1)
@Data
public class InfiniteFoodImgVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	   				generator = "INFINITE_FOOD_IMG_SEQ_GENERATOR") 
	@Column(name="food_num")
	private int foodNum;
	
	@Column(name="food_img")
	private String foodimg;

}
