package com.javateam.foodCrawlingDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.foodCrawlingDemo.domain.InfiniteFoodVO;
import com.javateam.foodCrawlingDemo.repository.InfiniteFoodRepository;

@Service
public class InfiniteFoodService {
	
	@Autowired
	InfiniteFoodRepository ifr;
	
//	@Transactional
//	public void save(InfiniteFoodVO ifVO) {
//		
//		ifr.save(ifVO);
//	}
	
	@Transactional
	public void save(InfiniteFoodVO ifVO) {
		// TODO Auto-generated method stub
		ifr.save(ifVO);
		
	}

//	@Transactional
//	public void save(String vo) {
//		
//		ifr.save(vo);
//	}

}
