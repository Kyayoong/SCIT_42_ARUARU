package com.restaurantreservation.aruaru.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Tags;

@Mapper
public interface RestaurantDao {
	
	//아이디로 조회하여 식당객체가져오기
	Restaurant_member selectOne(String Member_id);
		
	//식당회원 저장하기
	int regist1(Restaurant_member member);
		
	//식당파일 저정하기
	int fileregist(Restaurant_file file);
		
	//메뉴 저장하기
	int insertmenu(Menu menu);
	
	//메뉴 전체조회
	ArrayList<Menu> menucheck(int Restaurant_num);
	
	//메뉴 조회
	Menu readMenu(int menu_num);
	
	//메뉴 삭제
	int menuDel(int menu_num);
	
	//식당 수정
	int Rupdate(Restaurant_member member);
	
	//태그 리스트
	ArrayList<Tags> tagList(String tags_sector);
	
	

}
