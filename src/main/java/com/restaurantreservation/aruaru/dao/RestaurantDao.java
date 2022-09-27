package com.restaurantreservation.aruaru.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.restaurantreservation.aruaru.domain.Holiday;
import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Restaurant_time;
import com.restaurantreservation.aruaru.domain.Tags;

@Mapper
public interface RestaurantDao {
	
	//아이디로 조회하여 식당객체가져오기
	Restaurant_member selectOne(String Member_id);
	
	//식당번호로 개체가져오기
	Restaurant_member selectOne1(int restaurant_num);
		
	//식당회원 저장하기
	int regist1(Restaurant_member member);
		
	//식당파일 저정하기
	int fileregist(Restaurant_file file);
	
	//파일 조회하기
	ArrayList<Restaurant_file> fileselect(int restaurant_num);
	
	//식당 리스트
	ArrayList<Restaurant_member> resList();
	
	//가게리스트 검색
	ArrayList<Restaurant_member> resListSearch(Map<String, String> map);
		
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
	
	//태그 선택
	Tags tagRead(int tags_num);
	
	//태그 삭제
	int tagDelete(Tags tag);
	
	//태그 저장
	int tagInsert(Tags tag);
	
	//공휴일 리스트
	ArrayList<Holiday> readHoliday();
	
	//영업일 가져오기
	ArrayList<Restaurant_time> readTime(int restaurant_num);
	
	//가게당 태그 가져오기
	ArrayList<Tags> searchStoreTags(int restaurant_num);
	
	//파일 가져오기 하나
	Restaurant_file readFile(int restaurant_file_num);


}
