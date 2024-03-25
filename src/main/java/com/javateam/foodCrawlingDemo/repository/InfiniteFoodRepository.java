package com.javateam.foodCrawlingDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.javateam.foodCrawlingDemo.domain.InfiniteFoodVO;

public interface InfiniteFoodRepository extends CrudRepository<InfiniteFoodVO, Integer>  {

//	 @Query(value = "SELECT FOOD_SITE FROM INFINITE_FOOD_TBL WHERE FOOD_NUM BETWEEN ?1 AND ?2", nativeQuery = true)
//	 List<InfiniteFoodVO> findByInfiniteFood(int start, int end);
	 
//	 @Query(value = "SELECT FOOD_SITE FROM INFINITE_FOOD_TBL WHERE FOOD_NUM BETWEEN ?1 AND ?2", nativeQuery = true)
//	 List<InfiniteFoodMapping> findByInfiniteFood(Long foodSite);
//	 
//	 List<InfiniteFoodMapping> findByAll();
	 
//	 @Query(value = "SELECT * FROM INFINITE_FOOD_TBL WHERE FOOD_NUM BETWEEN ?1 AND ?2", nativeQuery = true)
//	 List<InfiniteFoodMapping> findByInfiniteFood(int start, int end);
	 
//	 @Query(value = "SELECT FOOD_SITE FROM INFINITE_FOOD_TBL WHERE FOOD_NUM BETWEEN ?1 AND ?2", nativeQuery = true)
//	 List<String> findByInfiniteFood(int start, int end);
	 
//	 @Query(value = "SELECT FOOD_SITE FROM INFINITE_FOOD_TBL", nativeQuery = true)
//	 List<String> findByInfiniteFood();
//	 
//	 @Query(value = "SELECT FOOD_SITE FROM INFINITE_FOOD_TBL WHERE FOOD_SITE = '?1'", nativeQuery = true)
//	 List<String> selectInfiniteFood(String vo);
}
