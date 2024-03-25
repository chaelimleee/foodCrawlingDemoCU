package com.javateam.foodCrawlingDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.javateam.foodCrawlingDemo.domain.WEFoodVO;

import jakarta.transaction.Transactional;

public interface WEFoodRepository extends CrudRepository<WEFoodVO, Long> {
	
	@Transactional
	@Modifying
	@Query("UPDATE WEFoodVO f SET f.foodcate = :foodCate WHERE f.foodName = :foodName")
    void updateFoodCateByFoodName(@Param("foodCate") String foodCate,@Param("foodName") String foodName);

	// we_food_tbl2 에서 음식명이 맞는 컬럼의 id를 뽑아냄. 
	@Transactional
	@Modifying
	@Query(value = "SELECT ID FROM WE_FOOD_TBL2 WHERE FOOD_NAME=?1", nativeQuery = true)
	List<Long> selectFoodCateName(String foodName);

}