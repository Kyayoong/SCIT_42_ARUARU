package com.restaurantreservation.aruaru.controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Restaurant_zzim;
import com.restaurantreservation.aruaru.domain.Tags;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.RestaurantService;
import com.restaurantreservation.aruaru.service.UserService;
import com.restaurantreservation.aruaru.util.FileService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("restaurant")
@Controller
@ResponseBody
public class RestaurantRestController {
	
	/**
	 * 게시판 첨부파일 업로드 경로
	 */
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	@Autowired
	RestaurantService service;
	
	@Autowired
	UserService service1;

	@PostMapping("restCheck")
	@ResponseBody
	public int restCheck(@RequestParam String restaurant_name,@RequestParam String restaurant_sectors,
			@RequestParam String restaurant_address1) {
		log.debug("{}",restaurant_name);
		log.debug("{}",restaurant_sectors);
		log.debug("{}",restaurant_address1);


		Map<String,String> map = new HashMap<>();

		map.put("restaurant_name", restaurant_name);

		map.put("restaurant_sectors", restaurant_sectors);

		map.put("restaurant_address1", restaurant_address1);


		int result = service.restCheck(map);
		if(result >= 1) {
			return 1;
		}

		return 0;
	}


	@PostMapping("insertmenu")
	public void insertmenu(MultipartFile upload,Menu menu,@AuthenticationPrincipal UserDetails user) {
		log.debug("업로드 {}",upload);
		log.debug("메뉴 {}",menu);
		
		Restaurant_member member = service.selectOne(user.getUsername());
		
		String savedfile = FileService.saveFile(upload, uploadPath);
		menu.setRestaurant_num(member.getRestaurant_num());
		menu.setMenu_originalfile(upload.getOriginalFilename());
		menu.setMenu_savedfile(savedfile);
		menu.setMenu_suggestion(menu.getMenu_suggestion());
		
		int result = service.insertmenu(menu);
		
		log.debug("결과 {}",result);
	}
	
	@PostMapping("menucheck")
	public ArrayList<Menu> menucheck(@AuthenticationPrincipal UserDetails user) {
		log.debug("바보 {}",1);
		
		Restaurant_member member = service.selectOne(user.getUsername());
		
		ArrayList<Menu> list = service.menucheck(member.getRestaurant_num());
		
		
		log.debug("리스트 {}",list);
	
		
		return list;
		
	}
	
	/**
	 * 보여주기 
	 * @param menunum 메뉴번호
	 */
	@GetMapping("display")
	public String display(int menu_num, Model model, HttpServletResponse response) {
		//전달된 글 번호로 글 정보 조회
		Menu menu = service.readMenu(menu_num);
		
		//원래의 파일명
		String originalfile = new String(menu.getMenu_originalfile());
		try {
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된 파일 경로
		String fullPath = uploadPath + "/" + menu.getMenu_savedfile();
		
		
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
	
	@GetMapping("menuDel")
	public void menuDel(int menu_num) {
		log.debug("num 결과 {}",menu_num);
		int result = service.menuDel(menu_num);
		log.debug("삭제 결과 {}",result);
		
	}
	
	@GetMapping("tagInsert")
	public void tagInsert(int tags_num,@AuthenticationPrincipal UserDetails user,Tags tag2) {
		
		Restaurant_member member = service.selectOne(user.getUsername());
		
		Tags tag = service.tagRead(tags_num);
		log.debug("num 결과 {}",tag);
		
		
		tag2.setRestaurant_num(member.getRestaurant_num());
		tag2.setTags_name(tag.getTags_name());
		tag2.setTags_sector(tag.getTags_sector());
		
		log.debug("num 결과 {}",tag2);
		int result = service.tagInsert(tag2);
		log.debug("저장 결과 {}",result);
		
	}
	
	@GetMapping("tagDelete")
	public void tagDelete(int tags_num,@AuthenticationPrincipal UserDetails user,Tags tag2) {
		
		Restaurant_member member = service.selectOne(user.getUsername());
		
		Tags tag = service.tagRead(tags_num);
		
		tag2.setRestaurant_num(member.getRestaurant_num());
		tag2.setTags_name(tag.getTags_name());
		tag2.setTags_sector(tag.getTags_sector());
		
		log.debug("num 결과 {}",tags_num);
		int result = service.tagDelete(tag2);
		log.debug("삭제 결과 {}",result);
		
	}
	
	
}