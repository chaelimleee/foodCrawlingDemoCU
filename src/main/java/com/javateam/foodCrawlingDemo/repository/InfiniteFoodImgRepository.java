package com.javateam.foodCrawlingDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.javateam.foodCrawlingDemo.domain.InfiniteFoodImgVO;
import com.javateam.foodCrawlingDemo.domain.InfiniteFoodVO;

public interface InfiniteFoodImgRepository extends CrudRepository<InfiniteFoodImgVO, Integer>  {
	
	 @Query(value = "SELECT FOOD_IMG FROM INFINITE_FOOD_IMG_TBL WHERE FOOD_NUM BETWEEN ?1 AND ?2", nativeQuery = true)
	 List<String> findByInfiniteFoodImg(int start, int end);

}
