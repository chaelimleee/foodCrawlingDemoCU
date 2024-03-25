package com.javateam.foodCrawlingDemo.domain;

import org.jsoup.select.Elements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@SequenceGenerator(
	    name = "FOOD_CATE_NAME_SEQ_GENERATOR",
	    sequenceName = "FOOD_CATE_NAME_SEQ",
	    initialValue = 1,
	    allocationSize = 1)
@Table(name="FOOD_CATE_NAME")
@Data
//@Builder
public class FoodCateNameVO {
	
	// ex) https://www.nongsaro.go.kr/portal/ps/psr/psrc/areaCkRyDtl.ps?menuId=PS03934&pageIndex=1&pageSize=10&pageUnit=10&cntntsNo=90400&type=02&schText=%EA%B0%80
	
	@Id
    @Column(name="cate_id", nullable=false, precision=10, scale=0) // number(10,0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    			   generator = "FOOD_CATE_NAME_SEQ_GENERATOR") // 오타 교정
	private long cateId;
	
	@Column(name="id",nullable=false, precision=10, scale=0) // number(10,0)
	private long id;
	
}
