package com.javateam.foodCrawlingDemo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.foodCrawlingDemo.domain.InfiniteFoodVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class WeFoodDAO {

	@Autowired
	SqlSession sqlSession; // mybatis 사용. 
	
	// id로 검색 조회
	public InfiniteFoodVO getFindWeFoodSiteAll(String foodSite) {
		
		return sqlSession.selectOne("mapper.weFoodMapper.getWeFoodSiteAll", foodSite); 
	}

	// 전체 조회.
	public List<InfiniteFoodVO> getWeFoodSiteAll() {
		
//		return sqlSession.selectList("mapper.weFoodMapper.getWeFoodSiteAll"); 
		return sqlSession.selectList("com.javateam.foodCrawlingDemo.domain.InfiniteFoodVO.getWeFoodSiteAll"); 
	}
	
}