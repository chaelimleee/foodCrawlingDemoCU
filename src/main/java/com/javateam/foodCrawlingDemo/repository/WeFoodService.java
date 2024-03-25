package com.javateam.foodCrawlingDemo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javateam.foodCrawlingDemo.dao.WeFoodDAO;
import com.javateam.foodCrawlingDemo.domain.InfiniteFoodVO;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeFoodService {
	
	@Autowired
	private WeFoodDAO dao;
	
	@Transactional
	public List<InfiniteFoodVO> getWeFoodSiteAll(){
		return dao.getWeFoodSiteAll();
		
	}

}
