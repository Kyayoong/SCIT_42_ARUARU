package com.restaurantreservation.aruaru.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Tags;
import com.restaurantreservation.aruaru.service.RestaurantService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("stores")
@Slf4j
@Controller
@ResponseBody
public class PageRestController {
	
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	@Autowired
	RestaurantService service;
	
	/**
	 * 보여주기 
	 * @param menunum 메뉴번호
	 */
	@GetMapping("display")
	public String display(int restaurant_num, Model model, HttpServletResponse response) {
		//전달된 글 번호로 글 정보 조회
		Restaurant_member member = service.selectOne1(restaurant_num);
		
		ArrayList<Restaurant_file> fileList = service.fileselect(restaurant_num);
		
		
		//원래의 파일명
		String originalfile = new String(fileList.get(0).getRestaurant_originalfile());
		
		try {
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된 파일 경로
		String fullPath = uploadPath + "/" + fileList.get(0).getRestaurant_savedfile();
		
		
		//서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			//Spring의 파일 관련 유틸 이용하여 출력
			FileCopyUtils.copy(filein, fileout);
			
			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}	
}
