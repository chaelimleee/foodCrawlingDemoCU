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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import com.javateam.foodCrawlingDemo.domain.FoodVO;
import com.javateam.foodCrawlingDemo.domain.InfiniteFoodVO;
import com.javateam.foodCrawlingDemo.repository.InfiniteFoodImgRepository;
import com.javateam.foodCrawlingDemo.repository.InfiniteFoodRepository;
import com.javateam.foodCrawlingDemo.repository.WEFoodRepository;
import com.javateam.foodCrawlingDemo.service.InfiniteFoodService;
import com.javateam.foodCrawlingDemo.util.WebDriverUtil;

//import jakarta.websocket.Decoder.Text;
import lombok.extern.slf4j.Slf4j;
//import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.filters.Canvas;
//import net.coobird.thumbnailator.geometry.Positions;

//----------------<p class="RecipeItemstyle__Title-sc-1gt7vz8-3 hpYiJK">로메인 썸머롤</p> 에서 텍스트만 뽑아서 비교 해서 유형 넣기..!----------------//
//---- 음식명과 we_food_tbl2의 음식명이 동일하면 해당 카테고리의 이름을 넣어줌. 완료
//---- 문제 : 새로 넣을 떄 마다 원래 있던 컬럼값이 없어지고 덮어씌여줌. 채식이있는데 한식도 포함되어있으면 채식은 없어지고 한식이 덮어씌워짐.


@SpringBootTest
@Slf4j
public class CategoryInfiniteScrollCrawlingTest3 {
	
	@Autowired
	WebDriverUtil webDriverUtil;
		
//	@Autowired
//	InfiniteFoodService infiniteFoodService;
	
	@Autowired
	WEFoodRepository wefoodRepository;
					
	
	@Test
	public void test() {

		WebDriver driver = webDriverUtil.getChromeDriver();		
		log.info("driver : " + driver.getCurrentUrl());
		
		List<String> foodLinkList = new ArrayList<>();
		
		
		try {
			
			if (!ObjectUtils.isEmpty(driver)) {
				
				// 무한 스크롤 대비			
				String url = "https://wtable.co.kr/recipes";
				driver.get(url);
				
				
				/*
				 * 레시피 서브 메뉴 구조
				 * 
				 * <ul class="RecipeListstyle__Categories-sc-1s9b4ly-16 fjqwFa">
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">모두보기</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">메인요리</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">밑반찬</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 erDeOn">간식</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">간단요리</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">초대요리</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">채식</li> --완
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">한식</li> --완
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">양식</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">일식</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">중식</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">퓨전</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">분식</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">안주</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">베이킹</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">다이어트</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">도시락</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">키토</li>
						<li class="RecipeListstyle__Category-sc-1s9b4ly-17 eRsThs">오븐 요리</li>
					</ul>
				 * 
				 */
				
				// 특정 카테고리 클릭 : "밑반찬" 선택
				WebElement webElement = driver.findElements(By.cssSelector("ul[class='RecipeListstyle__Categories-sc-1s9b4ly-16 fjqwFa']"
										   	   + " li[class^='RecipeListstyle__Category-sc-1s9b4ly-17']")).get(15);
				
				log.info("클릭할 개체 : " + webElement.getText());
				
				webElement.click(); // 클릭
				
				Thread.sleep(2000); // 2초 대기시간(delay term)
								
				WebElement actions = driver.findElement(By.cssSelector("body"));
				
				String selectors = "div[class^='RecipeListstyle__RecipeList'] a div[class^='RecipeItemstyle__Layout'] p[class^='RecipeItemstyle__Title']";
				List<WebElement> webElementList = new ArrayList<>();
				
				for (int i=0; i<2; i++) {
					
					webElementList = driver.findElements(By.cssSelector(selectors));
						
					for (WebElement element : webElementList) {
						
//						String link = element.getAttribute("href");
//						log.info("개별 음식 사이트 링크 : " + link);
						
						String title = element.getText();
						log.info("개별 음식 제목 : " + title);
						
//						InfiniteFoodVO ifVO = new InfiniteFoodVO();
//						ifVO.setFoodSite(title);
						
						wefoodRepository.updateFoodCateByFoodName(webElement.getText(), title);
						
						// 주의) 여기서 저장시 카텍고리(밑반찬 필드)를 정해두고 저장할 것 !
//						infiniteFoodService.save(ifVO);
						
						
						
						foodLinkList.add(title);
						
						actions.sendKeys(Keys.END);
						Thread.sleep(2000); //2초 대기시간(delay term)
						
					} // for element
					
				} // for i
				
				log.info("링크 크기 :" + webElementList.size());
				
				log.info("---------------------------------------------------------------------");
				log.info("음식 리스트 크기 : " + foodLinkList.size());
				
				foodLinkList.forEach(x -> { log.info("{}", x); });			
				
			} // if
			

		} catch (Exception e) {
			log.error("예외 : " + e);
		}
		
		webDriverUtil.close(driver);
		webDriverUtil.quit(driver);
		
		
	} //

}
	