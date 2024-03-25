package com.javateam.foodCrawlingDemo.food;

import static org.assertj.core.api.Assertions.assertThat;

//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javateam.foodCrawlingDemo.dao.WeFoodDAO;
import com.javateam.foodCrawlingDemo.domain.FoodVO;
import com.javateam.foodCrawlingDemo.domain.InfiniteFoodVO;
import com.javateam.foodCrawlingDemo.domain.WEFoodVO;
import com.javateam.foodCrawlingDemo.repository.InfiniteFoodImgRepository;
import com.javateam.foodCrawlingDemo.repository.InfiniteFoodMapping;
import com.javateam.foodCrawlingDemo.repository.InfiniteFoodRepository;
//import com.javateam.foodCrawlingDemo.repository.FoodRepository;
import com.javateam.foodCrawlingDemo.repository.WEFoodRepository;

//import jakarta.websocket.Decoder.Text;
import lombok.extern.slf4j.Slf4j;
//import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.filters.Canvas;
//import net.coobird.thumbnailator.geometry.Positions;

@SpringBootTest
@Slf4j
public class WECrawlFoodListPagesTest3 {
	
//	@Autowired
//	private InfiniteFoodRepository infiniteRepo;

	@Autowired
	private InfiniteFoodImgRepository infiniteImgRepo;
	
//	@Autowired
//	private WeFoodDAO weFoodDao ;

	@Test
	public void test() {
//		// 식품 링크 
//		List<InfiniteFoodVO> infiniteFoodvo = weFoodDao.getWeFoodSiteAll();
//		log.info("식품 링크 = {}",infiniteFoodvo);
		
       
        int start = 81;
        int end = 91;
        int start2 = 94;
        int end2 = 104;
       
//        List<String> foods = infiniteRepo.findByInfiniteFood(start, end);
//        List<String> foods = infiniteRepo.findByInfiniteFood(start, end);
//        List<String> foods = infiniteImgRepo.findByInfiniteFoodImg(start2, end2);
//        List<InfiniteFoodMapping> foods = infiniteRepo.findByAll();

//        assertThat(foods).isNotNull();
//        assertThat(foods.size()).isGreaterThan(0);
		
//        System.out.println(foods);
		
		
	} //

}
