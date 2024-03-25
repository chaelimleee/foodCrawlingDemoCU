package com.javateam.foodCrawlingDemo.food;

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
//import com.javateam.foodCrawlingDemo.repository.FoodRepository;
import com.javateam.foodCrawlingDemo.repository.WEFoodRepository;

//import jakarta.websocket.Decoder.Text;
import lombok.extern.slf4j.Slf4j;
//import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.filters.Canvas;
//import net.coobird.thumbnailator.geometry.Positions;

@SpringBootTest
@Slf4j
public class WECrawlFoodListPagesTest2 {
	
	@Autowired
	private WEFoodRepository weFoodRepo;
	
	@SuppressWarnings("unused")
	@Autowired
	private WeFoodDAO weFoodDao ;

	@Test
	public void test() {
		
		// 식품 링크 
		@SuppressWarnings("unchecked")
		InfiniteFoodVO infiniteFoodvo = (InfiniteFoodVO) weFoodDao.getWeFoodSiteAll();
		log.info("식품 링크 = {}",infiniteFoodvo);
		
//		for(int num) {
//			List<FoodVO> foodSiteList = new ArrayList<>();
//			log.info("식품 링크 = {}",vo);
//		}
		
		for (int j=0; j<10; j++) { // 
		String foodGallerySite = "https://wtable.co.kr/recipes";
//							   + infiniteFoodvo.get(j);
		
		log.info("식품 링크 = {}",foodGallerySite);
		
			// 음식 고유 번호 추출
			Document doc;
			
			// 식품 고유 번호(Primary Key)
			List<String> foodIdList = new ArrayList<>();

			// 식품 음식 사진. 
			List<String> foodImgList = new ArrayList<>();
			
			// 식품(식품) 리스트
			List<FoodVO> foodList = new ArrayList<>();
			
			
			try {
				doc = Jsoup.connect(foodGallerySite).get();
	
//				Elements foodListInfo = doc.select("div[class='RecipeListstyle__RecipeList-sc-1s9b4ly-20 eKgfiL'] ");
				Elements foodListInfo = doc.select("div[class='RecipeListstyle__RecipeList-sc-1s9b4ly-20 eKgfiL'] a");
	
				// 갤러리 음식 갯수
//				int foodLen = foodListInfo.size();
//	
//				log.info("갤러리 페이지 음식 갯수 : " + foodLen); // ex) 10
	
				// 음식 아이디 : 91511
				
				// <a href="#" onclick="fncDtl('91511');return false;"><img
				// src="/ps/img/common/anvil_img.jpg" alt="이미지 준비중입니다." ></a> </span>
				
				//메인 레시피 페이지에서 각각 페이지 url 뽑아내서 저장. 
//				for (int i=0; i<foodLen; i++) {
//				for (int i=0; i<j; i++) {
				
//					String foodId = foodListInfo.get(i) // <div class="inBox" >
//												.select("a") // <span class="pic"><a>
//												.attr("href")  // onclick="fncDtl('91511');return false;"
////												.substring("fncDtl('".length(), 
////														   "fncDtl('".length() + 5)
//												.trim(); // select("span[class='pic'] a[onclick]").text();
////					log.info("foodId : " + foodId);
//					foodIdList.add(foodId);
					
//					String foodSite = infiniteFoodvo.get(i).trim();
//					log.info("foodSite : " + foodSite);
//					foodSiteList.add(foodSite);

					String foodImgId= foodListInfo.get(j) // <div class="inBox" >
												.select("img") // <span class="pic"><a>
												.attr("src")  // onclick="fncDtl('91511');return false;"
//												.substring("fncDtl('".length(), 
//														   "fncDtl('".length() + 5)
												.trim(); // select("span[class='pic'] a[onclick]").text();
//					log.info("foodId : " + foodId);
					foodImgList.add(foodImgId);
					
//				}
				
				// 식품페이지 링크 출력
				foodIdList.forEach(x -> { log.info("식품 링크 : " + x);});
				
				// 식품이미지 링크 출력
				foodImgList.forEach(x -> { log.info("식품 이미지 링크 : " + x);});
				
			} catch (IOException e) {
				log.error("접속 불능");
				e.printStackTrace();
			}
			
				
			//////////////////////////////////////////////////////////////////////////////////////////////////
			
			WEFoodVO wefoodVO = null;
			
			// 개별 식품 정보 추출
			for (int i=0; i<foodIdList.size(); i++) {
				
//				String foodSiteId = foodIdList.get(i);
//				log.info("푸드 리스트 갯수: {}" ,foodIdList.size());
				
				
				
				String foodImgId = foodImgList.get(j);
//				log.info("푸드 이미지 리스트 갯수: {}" ,foodImgList.size());
//				
//				// 
//				String foodSite = "https://wtable.co.kr"
//								+ foodSiteId;
//				String foodSiteMain = "https://wtable.co.kr/recipes";
//				log.info("푸드 사이트 url: {}" ,foodSite);
				
				///////////////////////////////////////////////////////////
				
				Document doc2 = null;
//				Document doc2Main = null;
				wefoodVO = new WEFoodVO();
	
				try {
//					doc2 = Jsoup.connect(foodSite).get();
					doc2 = Jsoup.connect(foodGallerySite).get();
//					doc2Main = Jsoup.connect(foodSiteMain).get();
	
					Elements foodInfo = doc2.select("div.RecipeDetailstyle__MetaHeader-q7sykd-1 jyPkMJ div ul");
//					Elements foodInfo = doc2.select("div.token__Component-sc-1o2h3sm-0 jjTxDH div "); // 에러..왜..?
	
					// 타이틀 => 식품명
					String foodname = doc2.select("title").text().split(" - ")[0].trim();
					log.info("타이틀(식품명) : " + foodname);
					wefoodVO.setFoodName(foodname);
					
					//-------------------------------------------식품 이미지-------------------------------------------------------
					
					// 식품 이미지
					// https://www.nongsaro.go.kr/cms_contents/789/91391_MF_BIMG_01.jpg
					// <img src="/cms_contents/789/91391_MF_BIMG_01.jpg" alt="가루장국(가리장국)" data-pop="91391_MF_BIMG_01.jpg">
					
//					String foodImg =doc2.select("div.ingredient img").attr("src").trim();
					
//					String foodImg =doc2.select("div.token__Component-sc-1o2h3sm-0 jjTxDH img").attr("src").trim();
//					String foodImg =doc2.select("div.jjTxDH img").attr("src").trim();
//					String foodImg =doc2Main.select("div.erZvWP img").attr("src").trim();
//					String foodImg =doc2Main.select("div.RecipeListstyle__RecipeList-sc-1s9b4ly-20 eKgfiL img").attr("src").trim();
					
//					String foodImg =doc2.select("div.Component-sc-1o2h3sm-0 jjTxDH img").attr("src").trim();
//					String foodImg =doc2.select("meta").attr("content").replace(0, 0).trim();
//					log.info("식품 이미지 : " + foodImg);
					
					log.info("식품 이미지 : " + foodImgId);
					
					
					// 이미지 확장자
					String saveImgFileNameExt = foodImgId.substring(foodImgId.lastIndexOf('.') + 1);
//						String productImgURL = "https:"+product.select("div[class='prodDetail-w'] img").attr("src").trim();
					
					// 이미지 저장(다운로드) // 이미지 주소 : https://static.wtable.co.kr/image/production/servi…7554-2db0-4373-beeb-10d022597fd2.jpg?size=800x800
					String path = "C:/ALEELIM/works/spring_food2/weFoodCrawling/upload_image_we2/";
					String saveImgFilename = UUID.randomUUID().toString() + "." + saveImgFileNameExt.replace("?size=500x500", "");

					log.info("path : " + path);
					log.info("saveImgFilename : " + saveImgFilename);
					
		            InputStream in = new URL(foodImgId).openStream();
		            Files.copy(in, Paths.get(path + saveImgFilename), StandardCopyOption.REPLACE_EXISTING);

		            log.info("이미지 다운로드 및 저장이 완료되었습니다.");
			            
			        
					//-------------------------------------------식품 이미지 끝!! -------------------------------------------------------
						
					/////////////////////////////////////////////////////////////////////////////////////////
															
//					foodVO.setFoodImg(saveImgFilename);
//					wefoodVO.setFoodImg(foodImgId);
					wefoodVO.setFoodImg(saveImgFilename);
	
//					// 식품 유형
//					String foodType = foodInfo.get(0).text().trim();
//					log.info("식품 유형 : " + foodType);
//					wefoodVO.setFoodType(foodType);
	
					// 식재료
					String ingredient = doc2.select("ul.igroups li").get(0).text().trim();
					log.info("식재료 : " + ingredient);
					wefoodVO.setIngredient(ingredient);
					
					// 부재료
					if( doc2.select("ul.igroups li:eq(1)").size() > 1) {
						String minorIngredient = doc2.select("ul.igroups li:eq(1)").get(1).text().trim();
						log.info("부재료 : " + minorIngredient);
						wefoodVO.setMinorIngredient(minorIngredient);
//						// 부재료
//						String minorIngredient = doc2.select("ul.igroups li:eq(1)").get(1).text().trim();
//						log.info("부재료 : " + minorIngredient);
//						wefoodVO.setMinorIngredient(minorIngredient);
					}else {
						log.info("{}의 부재료가 존재하지 않습니다. ", foodname);
					}
	
					// 조리방법
//					String cookingInstruction = doc2.select("div.token__Step-sc-1o2h3sm-1 ihCzrN p").text().trim();
					String cookingInstruction = doc2.select("div.steps p").text().trim();
					log.info("조리방법 : " + cookingInstruction);
					wefoodVO.setCookingInstruction(cookingInstruction);

					// 식품 설명. 
//					String foodManual = doc2.select("p.RecipeDetailstyle__Description-q7sykd-14 IdQIJ").text().trim();
					String foodManual = doc2.select("p.IdQIJ").text().trim();
					log.info("식품 설명 : " + foodManual);
					wefoodVO.setFoodmanual(foodManual);
	
					log.info("음식정보 : " + wefoodVO);
					
					log.info("------------------------------------------------------------");
					
					///////////////////////////////////////////////////////////////////////////
					
					// DB 저장
					weFoodRepo.save(wefoodVO);
	
				} catch (IOException e) {
					log.error("접속 불능");
					e.printStackTrace();	
				}
				
			} // for
	 
			// 식품 리스트 정보 출력
			foodList.forEach(x -> { log.info("식품 : " + x);});
			
		} // 10개의 페이지
		
	} //

}
