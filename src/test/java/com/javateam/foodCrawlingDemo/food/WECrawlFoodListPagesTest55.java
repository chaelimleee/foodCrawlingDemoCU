package com.javateam.foodCrawlingDemo.food;

import java.io.FileOutputStream;
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
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
import com.javateam.foodCrawlingDemo.domain.ScrollErrorVO;
import com.javateam.foodCrawlingDemo.domain.WEFoodVO;
import com.javateam.foodCrawlingDemo.repository.InfiniteFoodImgRepository;
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
public class WECrawlFoodListPagesTest55 {
	
	@Autowired
	private WEFoodRepository weFoodRepo;
	
	@Autowired
	private InfiniteFoodRepository infiniteRepo;

	@Test
	public void test() {

		// infiniteVO의 foodSite를 뽑아옴.
		List<String> foods = infiniteRepo.findByInfiniteFood(); 
		
		for (int j=1500; j<2000; j++) { // 넣을 데이터 갯수.
			
			//foodSite의 url을 인덱스j부터 차례때로 가져옴. 
			String foodGallerySite = foods.get(j);
			
			// 해당 웹사이트의 기본 링크.
			String foodAllSite = "https://wtable.co.kr/recipes";
		
			log.info("식품 링크 = {}",foodGallerySite);
		
			// 음식 고유 번호 추출
			Document doc;
			
			// 식품 음식 사진. 
			List<String> foodImgList = new ArrayList<>();
			
			// 식품(식품) 리스트
			List<FoodVO> foodList = new ArrayList<>();
			
			// 에러 로그 저장 파일.
			String errorFile = "ScollError.ser";
			
			// 에러 로그 저장. 
			List<ScrollErrorVO> errorSave = new ArrayList<>();
			
			
			try {
				
				doc = Jsoup.connect(foodAllSite).get();
	
				Elements foodListInfo = doc.select("div[class='RecipeListstyle__RecipeList-sc-1s9b4ly-20 eKgfiL'] a");
	

					String foodImgId = null;
					
					try {
						
						foodImgId= foodListInfo.get(j+88) 
								.select("img") 
								.attr("src")  
								.trim(); 

						foodImgList.add(foodImgId);
						
					}catch(Exception e) {
						// 이미지가 아닌, 동영상 등으로 인해 오류가 날 경우 예외처리.
						log.error("이미지를 리스트에 넣는 곳에서 문제가 발생했습니다.");
						errorSave.add(new ScrollErrorVO(j, e.getMessage()));
						
						//이미지가 없는 경우 이미지 파일 명을 "없음"으로 저장
						foodImgId = "없음";
					}
					
			} catch (IOException e) {
				
				log.error("접속 불능");
				e.printStackTrace();
			}
			
			try ( 
					// 에러 로그 직렬화 저장. 
					FileOutputStream fos = new FileOutputStream(errorFile);
		            ObjectOutputStream out = new ObjectOutputStream(fos);
					
				){
				
				out.writeObject(errorSave);
				log.info("에러 로그를 파일에 저장했습니다.");
				
			}catch(IOException e) {
				
				log.info("에러 로그를 저장하는 도중 오류가 발생했습니다. ");
				e.printStackTrace();
			}
			
				
			//////////////////////////////////////////////////////////////////////////////////////////////////
			
			WEFoodVO wefoodVO = null;
			
				String foodImgId = null; 
				
				try {
					// 푸드 이미지 url을 가져옴. 
					foodImgId = foodImgList.get(j);
					log.info("푸드 이미지 리스트 갯수: {}" ,foodImgList.size());
					
				}catch(Exception e) {
					
					log.error("이미지 갯수의 오류가 있습니다. ");
					errorSave.add(new ScrollErrorVO(j, e.getMessage()));
					
					foodImgId = "없음";
				}

				///////////////////////////////////////////////////////////
				
				Document doc2 = null;
				
				wefoodVO = new WEFoodVO();
	
				try {
					doc2 = Jsoup.connect(foodGallerySite).get();
	
					Elements foodInfo = doc2.select("div.RecipeDetailstyle__MetaHeader-q7sykd-1 jyPkMJ div ul");
	
					// 타이틀 => 식품명
					String foodname = doc2.select("title").text().split(" - ")[0].trim();
					log.info("타이틀(식품명) : " + foodname);
					wefoodVO.setFoodName(foodname);
					
					//-------------------------------------------식품 이미지-------------------------------------------------------
					
					String foodImg = null;
					
					try {
						
						foodImg = doc2.select("div.jjTxDH img").attr("src").trim();
						log.info("식품 이미지 : " + foodImg);
						
					}catch(Exception e) {
						
						log.error("이미지 주소가 없습니다. ");
						errorSave.add(new ScrollErrorVO(j, e.getMessage()));
						
						foodImg = "없음.";
						
					}
					
					// 이미지 확장자
					String saveImgFileNameExt = foodImg.substring(foodImg.lastIndexOf('.') + 1);

					// 이미지 저장(다운로드) // 이미지 주소 : https://static.wtable.co.kr/image/production/servi…7554-2db0-4373-beeb-10d022597fd2.jpg?size=800x800
					String path = "C:/ALEELIM/works/spring_food2/weFoodCrawling/upload_image_we3/";
					String saveImgFilename = UUID.randomUUID().toString() + "." + saveImgFileNameExt.replace("?size=800x800", "");// 파일명 저장 시 확장자 뒤의 부분 삭제.

					log.info("path : " + path);
					log.info("saveImgFilename : " + saveImgFilename);
					
					try {

						InputStream in = new URL(foodImg).openStream();
						Files.copy(in, Paths.get(path + saveImgFilename), StandardCopyOption.REPLACE_EXISTING);
						
						log.info("이미지 다운로드 및 저장이 완료되었습니다.");
						
					}catch(Exception e) {
						
						log.error("이미지 파일 저장 에러 : " + e);
						errorSave.add(new ScrollErrorVO(j, e.getMessage()));
						
						saveImgFilename = "없음.";
					}
		            
		            
			            
			        
					//-------------------------------------------식품 이미지 끝!! -------------------------------------------------------
						
					wefoodVO.setFoodImg(saveImgFilename);
	
					// 식재료
					String ingredient = doc2.select("ul.igroups li").get(0).text().trim();
					
					log.info("식재료 : " + ingredient);
					
					wefoodVO.setIngredient(ingredient);
					
					// 부재료
					String minorIngredient = null;
					
					try {
							minorIngredient = doc2.select("ul.igroups li:eq(1)").get(1).text().trim();
							wefoodVO.setMinorIngredient(minorIngredient);
							log.info("부재료 : " + minorIngredient);
							
					}catch(Exception e) {
						
						log.error("{}의 부재료가 존재하지 않습니다. ", foodname);
						errorSave.add(new ScrollErrorVO(j, e.getMessage()));
						// 부재료가 존재하지 않을 경우 예외처리.
						minorIngredient = "없음";
						
					}
					
					// 조리방법
					String cookingInstruction = doc2.select("div.steps p").text().trim();
					log.info("조리방법 : " + cookingInstruction);
					wefoodVO.setCookingInstruction(cookingInstruction);

					// 식품 설명. 
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
				
			// 식품 리스트 정보 출력
			foodList.forEach(x -> { log.info("식품 : " + x);});
			
		} // 10개의 페이지
		
	} //

}
	