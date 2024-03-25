package com.javateam.foodCrawlingDemo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.javateam.foodCrawlingDemo.domain.FoodCateNameVO;
import com.javateam.foodCrawlingDemo.domain.WEFoodVO;

import jakarta.transaction.Transactional;

public interface FoodCateNameRepository extends CrudRepository<FoodCateNameVO, Long> {
//	
//	@Transactional
//	@Modifying
//	@Query("UPDATE WEFoodVO f SET f.foodcate = :foodCate WHERE f.foodName = :foodName")
//    void updateFoodCateByFoodName(@Param("foodCate") String foodCate,@Param("foodName") String foodName);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO FOOD_CATE_NAME (CATE_ID,ID) VALUES (1,?1)", nativeQuery = true )
	void insertFoodCateName(Long id);

	

}