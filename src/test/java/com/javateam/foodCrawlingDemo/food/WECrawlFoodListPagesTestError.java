package com.javateam.foodCrawlingDemo.food;

import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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
public class WECrawlFoodListPagesTestError {
	
	public static void main(String[] args) {
		
	
			String errorFile = "ScollError.ser";
			
			// 에러 로그 저장. 
//			List<ScrollErrorVO> errorSave = new ArrayList<>();
			
			try ( 
					FileInputStream fos = new FileInputStream(errorFile);
		            ObjectInputStream in = new ObjectInputStream(fos);
			){
				
				List<ScrollErrorVO> deserializedCustomer = (List<ScrollErrorVO>) in.readObject();
				
		        System.out.println(deserializedCustomer);
		        
			}catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
	} //

}
